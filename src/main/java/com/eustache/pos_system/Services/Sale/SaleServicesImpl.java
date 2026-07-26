package com.eustache.pos_system.Services.Sale;

import com.eustache.pos_system.DTO.Sale.Request.CreateSaleDto;
import com.eustache.pos_system.DTO.Sale.Response.ReceiptResponseDto;
import com.eustache.pos_system.DTO.Sale.Response.SaleResponseDto;
import com.eustache.pos_system.Entities.*;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;
import com.eustache.pos_system.Mappers.SaleMapper;
import com.eustache.pos_system.Repositories.CustomerRepository;
import com.eustache.pos_system.Repositories.ProductRepository;
import com.eustache.pos_system.Repositories.SaleRepository;
import com.eustache.pos_system.Repositories.UserRepository;
import com.eustache.pos_system.Services.LoyaltyCard.LoyaltyServicesImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServicesImpl implements SaleServices{
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SaleMapper saleMapper;
    private final LoyaltyServicesImpl loyaltyServicesImpl;

    /**
     * Creates a new sale
     * @param createSaleDto Create sale request
     * @return Receipt response
     */
    @Override
    @Transactional
    public ReceiptResponseDto createSale(CreateSaleDto createSaleDto) {
        /*
         * Checks if cashier exists
         */
        User cashier = userRepository.findById(createSaleDto.cashierId())
                .orElseThrow(
                        () -> new BusinessException("Cashier not found")
                );

        Customer customer = null;

        /*
         * Checks if customer exists
         */
        if(createSaleDto.customerId() != null){

            customer = customerRepository.findById(
                    createSaleDto.customerId()
            ).orElseThrow(
                    () -> new BusinessException("Customer not found")
            );
        }
        /*
         * Maps create sale dto to sale entity
         */
        Sale sale = saleMapper.toEntity(
                createSaleDto,
                cashier,
                customer
        );

        double totalAmount = 0;

        /*
         * Loops through items in create sale dto
         */
        for(var itemDto : createSaleDto.items()){
            Product product =
                    productRepository.findById(itemDto.productId())
                            .orElseThrow(
                                    () -> new BusinessException("Product not found")
                            );
            /*
             * Checks if there is enough stock
             */
            Stock stock = product.getStock();
            if(stock.getQuantity() < itemDto.quantity()){
                throw new BusinessException(
                        "Not enough stock for "
                                + product.getName()
                );
            }

            /*
             * Checks if product is expired
             */
            if (product.getExpiryDate() != null
                    && !product.getExpiryDate().isAfter(LocalDate.now())) {
                throw new BusinessException(
                        "Product is expired: " + product.getName()
                );
            }

            /*
             * Reduces stock quantity after sale
             */
            stock.setQuantity(
                    stock.getQuantity()
                            - itemDto.quantity()
            );

            /*
             * Saves updated product
             */
            productRepository.save(product);
            SaleItem saleItem =
                    saleMapper.toSaleItem(
                            product,
                            itemDto.quantity(),
                            sale
                    );
            sale.getSaleItems().add(saleItem);
            totalAmount += saleItem.getSubtotal();

        }
        /*
         * Sets total amount and final amount
         */
        sale.setTotalAmount(totalAmount);

        /*
         * Sets final amount after discount
         */
        sale.setFinalAmount(
                totalAmount
                        - createSaleDto.discount().doubleValue()
        );


        /*
         * Payment processing
         */
        if(createSaleDto.payment()!=null){
            Payment payment =
                    saleMapper.toPayment(
                            createSaleDto.payment(),
                            sale
                    );
            sale.setPaymentMethod(
                    createSaleDto.payment()
                            .paymentMethod()
            );
            sale.setStatus(
                    createSaleDto.payment()
                            .paymentMethod()
                            .equals(PaymentMethod.CASH)

                            ? StatusPayment.COMPLETED
                            : StatusPayment.PENDING
            );
            sale.getPayments().add(payment);

        }

        /*
         * Saves sale
         */
        Sale savedSale =
                saleRepository.save(sale);

        /*
         * Loyalty points earned
         */
        if(savedSale.getCustomer()!=null
                && savedSale.getStatus()
                == StatusPayment.COMPLETED){

            loyaltyServicesImpl.addPoints(
                    savedSale.getCustomer(),
                    savedSale,
                    savedSale.getFinalAmount()
            );
        }
        return saleMapper.toResponseFromSale(savedSale);
    }

    /**
     * Gets a sale by id
     * @param saleId Sale id
     * @return Sale response
     * @throws BusinessException if sale not found
     */
    @Override
    public SaleResponseDto getSaleById(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        return saleMapper.toSaleResponseFromSale(sale);
    }

    /**
     * Gets all sales
     * @return List of sales
     */
    @Override
    public List<SaleResponseDto> getAllSales() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    /**
     * Cancel a sale
     * @param saleId Sale id
     * @throws BusinessException if sale not found
     */
    @Override
    public void cancelSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        if (sale.getStatus() == StatusPayment.CANCELLED){
            throw new BusinessException("Sale already cancelled");
        }
        if (sale.getStatus() == StatusPayment.REFUNDED){
            throw new BusinessException("Sale already refunded");
        }
        /*
         * Restores stock quantity
         */
        restoreStock(sale);

        /*
         * Remove loyalty points when sales is canceled or refunded
         */
        loyaltyServicesImpl.removePoints(sale.getCustomer(), sale);

        sale.setStatus(StatusPayment.CANCELLED);
        saleRepository.save(sale);
    }

    /**
     * Refund a sale
     * @param saleId Sale id
     * @throws BusinessException if sale not found
     */
    @Override
    public void refundSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        if (sale.getStatus() == StatusPayment.CANCELLED){
            throw new BusinessException("Sale already cancelled");
        }
        if (sale.getStatus() == StatusPayment.REFUNDED){
            throw new BusinessException("Sale already refunded");
        }
        /*
         * Restores stock quantity
         */
        restoreStock(sale);

        /*
         * Remove loyalty points when sales is canceled or refunded
         */
        loyaltyServicesImpl.removePoints(sale.getCustomer(), sale);
        sale.setStatus(StatusPayment.REFUNDED);
        saleRepository.save(sale);
    }

    /**
     * Gets a receipt by id
     * @param saleId Sale id
     * @return Receipt response
     * @throws BusinessException if sale not found
     */
    @Override
    public ReceiptResponseDto getReceipt(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        return saleMapper.toResponseFromSale(sale);
    }

    /**
     * Gets all receipts
     * @return List of receipts
     */
    @Override
    public List<ReceiptResponseDto> getReceipts() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toResponseFromSale)
                .collect(Collectors.toList());
    }

    /**
     * Gets sales between dates
     * @param startDate Start date
     * @param endDate End date
     * @return List of sales
     */
    @Override
    public List<SaleResponseDto> getSalesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findBySaleDateBetween(
                        startDate.atStartOfDay(),
                        endDate.plusDays(1).atStartOfDay().minusNanos(1))
                .stream()
                .map(saleMapper::toSaleResponseFromSale)
                .toList();
    }

    /**
     * Gets sales by cashier
     * @param cashierId Cashier id
     * @return List of sales made by cashier
     */
    @Override
    public List<SaleResponseDto> getSalesByCashier(Long cashierId) {
        return saleRepository.findByCashierId(cashierId).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    /**
     * Gets sales by customer
     * @param customerId Customer id
     * @return List of sales made by customer
     */
    @Override
    public List<SaleResponseDto> getSalesByCustomer(Long customerId) {
        return saleRepository.findByCustomerId(customerId).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    /**
     * Gets sales by customer name
     * @param customerName Customer name
     * @return List of sales made by loyal customer
     */
    @Override
    public List<SaleResponseDto> searchSalesByCustomerName(String customerName) {
        return saleRepository.findByCustomerName(customerName).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    /**
     * Gets sales by payment method
     * @param paymentMethod Payment method
     * @return List of sales made by payment method
     */
    @Override
    public List<SaleResponseDto> getSalesByPaymentMethod(PaymentMethod paymentMethod) {
        PaymentMethod method = PaymentMethod.valueOf(paymentMethod.name());
        return saleRepository.findByPaymentMethod(method).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    /**
     * Gets sales by status
     * @param status Status
     * @return List of sales with status
     */
    @Override
    public List<SaleResponseDto> getSalesByStatus(StatusPayment status) {
        StatusPayment paymentStatus = StatusPayment.valueOf(status.name());
        return saleRepository.findByStatus(paymentStatus).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }


    /**
     *  A helper method when It comes to cancel or refound a sales
     * @param sale Sale to restore stock
     */
    private void restoreStock(Sale sale) {
        for (SaleItem saleItem : sale.getSaleItems()) {
            Product product = saleItem.getProduct();
            Stock stock = product.getStock();

            stock.setQuantity(stock.getQuantity() + saleItem.getQuantity());

            productRepository.save(product);
        }
    }
}
