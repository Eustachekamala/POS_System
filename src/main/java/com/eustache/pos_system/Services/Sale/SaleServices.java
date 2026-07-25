package com.eustache.pos_system.Services.Sale;

import com.eustache.pos_system.DTO.Sale.Request.CreateSaleDto;
import com.eustache.pos_system.DTO.Sale.Response.ReceiptResponseDto;
import com.eustache.pos_system.DTO.Sale.Response.SaleResponseDto;
import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;

import java.time.LocalDate;
import java.util.List;

public interface SaleServices {

    // SALE OPERATIONS
    ReceiptResponseDto createSale(CreateSaleDto createSaleDto);
    SaleResponseDto getSaleById(Long saleId);
    List<SaleResponseDto> getAllSales();
    void cancelSale(Long saleId);
    void refundSale(Long saleId);

    // RECEIPTS
    ReceiptResponseDto getReceipt(Long saleId);
    List<ReceiptResponseDto> getReceipts();

    List<SaleResponseDto> getSalesBetweenDates(
            LocalDate startDate,
            LocalDate endDate
    );
    List<SaleResponseDto> getSalesByCashier(Long cashierId);
    List<SaleResponseDto> getSalesByCustomer(Long customerId);
    List<SaleResponseDto> searchSalesByCustomerName(String customerName);
    List<SaleResponseDto> getSalesByPaymentMethod(PaymentMethod paymentMethod);
    List<SaleResponseDto> getSalesByStatus(StatusPayment status);

    // ==========================
    // BRANCH OPERATIONS
    // ==========================

//    List<SaleResponseDto> getSalesByBranch(Long branchId);
//
//    BigDecimal getBranchRevenue(Long branchId);
//
//    BigDecimal getBranchRevenueBetweenDates(
//            Long branchId,
//            LocalDate startDate,
//            LocalDate endDate
//    );
//
//    Long getBranchSalesCount(Long branchId);
}