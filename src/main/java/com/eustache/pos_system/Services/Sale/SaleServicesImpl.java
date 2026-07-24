package com.eustache.pos_system.Services.Sale;

import com.eustache.pos_system.DTO.Sale.Request.CreateSaleDto;
import com.eustache.pos_system.DTO.Sale.Response.ReceiptResponseDto;
import com.eustache.pos_system.DTO.Sale.Response.SaleResponseDto;
import com.eustache.pos_system.Entities.Sale;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;
import com.eustache.pos_system.Mappers.SaleMapper;
import com.eustache.pos_system.Repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServicesImpl implements SaleServices{
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public ReceiptResponseDto createSale(CreateSaleDto createSaleDto) {
        Sale sale = saleMapper.toEntity(createSaleDto);
        saleRepository.save(sale);
        return saleMapper.toResponseFromSale(sale);
    }

    @Override
    public SaleResponseDto getSaleById(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        return saleMapper.toSaleResponseFromSale(sale);
    }

    @Override
    public List<SaleResponseDto> getAllSales() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        sale.setStatus(StatusPayment.CANCELLED);
        saleRepository.save(sale);
    }

    @Override
    public void refundSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        sale.setStatus(StatusPayment.REFUNDED);
        saleRepository.save(sale);
    }

    @Override
    public ReceiptResponseDto getReceipt(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found"));
        return saleMapper.toResponseFromSale(sale);
    }

    @Override
    public List<ReceiptResponseDto> getReceipts() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> getSalesByDate(LocalDateTime date) {
        LocalDateTime startDate = date.toLocalDate().atStartOfDay();
        LocalDateTime endDate = date.toLocalDate().plusDays(1).atStartOfDay();
        return saleRepository.findBySaleDateBetween(startDate, endDate).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> getSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> getSalesByCashier(Long cashierId) {
        return saleRepository.findByCashierId(cashierId).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> getSalesByCustomer(Long customerId) {
        return saleRepository.findByCustomerId(customerId).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> searchSalesByCustomerName(String customerName) {
        return saleRepository.findByCustomerName(customerName).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> getSalesByPaymentMethod(PaymentMethod paymentMethod) {
        PaymentMethod method = PaymentMethod.valueOf(paymentMethod.name());
        return saleRepository.findByPaymentMethod(method).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> getSalesByStatus(StatusPayment status) {
        StatusPayment paymentStatus = StatusPayment.valueOf(status.name());
        return saleRepository.findByStatus(paymentStatus).stream()
                .map(saleMapper::toSaleResponseFromSale)
                .collect(Collectors.toList());
    }
}
