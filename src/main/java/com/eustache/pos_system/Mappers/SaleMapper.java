package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Sale.Request.CreateSaleDto;
import com.eustache.pos_system.DTO.Sale.Response.ReceiptResponseDto;
import com.eustache.pos_system.DTO.Sale.Response.SaleResponseDto;
import com.eustache.pos_system.DTO.SaleItem.Response.SaleItemResponseDto;
import com.eustache.pos_system.Entities.Payment;
import com.eustache.pos_system.Entities.Product;
import com.eustache.pos_system.Entities.Sale;
import com.eustache.pos_system.Entities.SaleItem;
import com.eustache.pos_system.Entities.User;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Helpers.StatusPayment;
import com.eustache.pos_system.Repositories.CustomerRepository;
import com.eustache.pos_system.Repositories.ProductRepository;
import com.eustache.pos_system.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SaleMapper {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    /**
     * Converts a CreateSaleDto to a Sale entity.
     *
     * @param saleDto the CreateSaleDto to convert
     * @return the Sale entity
     */
    public Sale toEntity(CreateSaleDto saleDto) {
        User cashier = userRepository.findById(saleDto.cashierId()).orElseThrow(
                () -> new BusinessException("Cashier not found"));

        Sale sale = new Sale();
        sale.setCashier(cashier);
        sale.setSaleDate(LocalDateTime.now());
        sale.setDiscount(saleDto.discount().doubleValue());

        if (saleDto.customerId() != null) {
            sale.setCustomer(customerRepository.findById(saleDto.customerId())
                    .orElseThrow(() -> new BusinessException("Customer not found")));
        }

        // Process sale items
        double totalAmount = 0.0;
        for (var itemDto : saleDto.items()) {
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new BusinessException("Product not found"));

            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(product);
            saleItem.setQuantity(itemDto.quantity());
            saleItem.setUnitPrice(product.getSellingPrice());
            saleItem.setSubtotal(itemDto.quantity() * product.getSellingPrice());
            saleItem.setSale(sale);

            sale.getSaleItems().add(saleItem);
            totalAmount += saleItem.getSubtotal();
        }

        sale.setTotalAmount(totalAmount);
        sale.setFinalAmount(totalAmount - saleDto.discount().doubleValue());

        // Process payment
        if (saleDto.payment() != null) {
            Payment payment = new Payment();
            payment.setAmount(saleDto.payment().amount().doubleValue());
            payment.setChange(saleDto.payment().amount().doubleValue() - sale.getFinalAmount());
            payment.setBalance(0.0);
            payment.setPaymentDate(saleDto.payment().paymentDate());
            payment.setNote(saleDto.payment().note());
            payment.setReference(saleDto.payment().reference());
            payment.setSale(sale);

            sale.setPaymentMethod(saleDto.payment().paymentMethod());
            sale.setStatus(StatusPayment.COMPLETED);
            sale.getPayments().add(payment);
        }

        return sale;
    }

    /**
     * Converts a Sale entity to a ReceiptResponseDto.
     *
     * @param sale the Sale entity to convert
     * @return the ReceiptResponseDto
     */
    public ReceiptResponseDto toResponseFromSale(Sale sale) {
        String cashierName = sale.getCashier().getFirstName() + " " + sale.getCashier().getLastName();

        List<SaleItemResponseDto> items = sale.getSaleItems().stream()
                .map(saleItem -> new SaleItemResponseDto(
                        saleItem.getProduct().getName(),
                        saleItem.getQuantity(),
                        saleItem.getUnitPrice(),
                        saleItem.getSubtotal()
                ))
                .collect(Collectors.toList());

        return new ReceiptResponseDto(
                sale.getId(),
                cashierName,
                sale.getSaleDate(),
                items,
                sale.getTotalAmount(),
                sale.getDiscount(),
                sale.getFinalAmount(),
                sale.getPaymentMethod()
        );
    }

    /**
     * Converts a Sale entity to a SaleResponseDto.
     *
     * @param sale the Sale entity to convert
     * @return the SaleResponseDto
     */
    public SaleResponseDto toSaleResponseFromSale(Sale sale) {
        Long customerId = sale.getCustomer() != null ? sale.getCustomer().getId() : null;
        String customerName = sale.getCustomer() != null
                ? sale.getCustomer().getFirstName() + " " + sale.getCustomer().getLastName()
                : null;

        Long cashierId = sale.getCashier().getId();
        String cashierName = sale.getCashier().getFirstName() + " " + sale.getCashier().getLastName();

        // Convert saleItems to SaleItemDto
        List<SaleResponseDto.SaleItemDto> saleItems = sale.getSaleItems().stream()
                .map(saleItem -> new SaleResponseDto.SaleItemDto(
                        saleItem.getProduct().getId(),
                        saleItem.getProduct().getName(),
                        saleItem.getQuantity(),
                        BigDecimal.valueOf(saleItem.getUnitPrice()),
                        BigDecimal.valueOf(saleItem.getSubtotal())
                ))
                .collect(Collectors.toList());

        // Convert payments to PaymentDto
        List<SaleResponseDto.PaymentDto> payments = sale.getPayments().stream()
                .map(payment -> new SaleResponseDto.PaymentDto(
                        payment.getId(),
                        sale.getPaymentMethod(),
                        BigDecimal.valueOf(payment.getAmount()),
                        BigDecimal.valueOf(payment.getChange()),
                        BigDecimal.valueOf(payment.getBalance()),
                        LocalDateTime.ofInstant(payment
                                .getPaymentDate()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant(), ZoneId.systemDefault()),
                        payment.getReference(),
                        payment.getNote()
                ))
                .collect(Collectors.toList());

        return new SaleResponseDto(
                sale.getId(),
                customerId,
                customerName,
                cashierId,
                cashierName,
                sale.getSaleDate(),
                BigDecimal.valueOf(sale.getTotalAmount()),
                BigDecimal.valueOf(sale.getDiscount()),
                BigDecimal.valueOf(sale.getFinalAmount()),
                sale.getPaymentMethod(),
                sale.getStatus(),
                saleItems,
                payments
        );
    }
}
