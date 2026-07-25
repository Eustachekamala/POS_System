package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Sale.Request.CreateSaleDto;
import com.eustache.pos_system.DTO.Sale.Response.ReceiptResponseDto;
import com.eustache.pos_system.DTO.Sale.Response.SaleResponseDto;
import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;
import com.eustache.pos_system.Services.Sale.SaleServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
@Tag(name = "Sales Controller", description = "API endpoints for sales management")
public class SaleController {
    private final SaleServices saleServices;

    @PostMapping
    @Operation(
            summary = "Create a new sale",
            description = "Create a new sale with the provided details"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Sale created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<ReceiptResponseDto> createSale(@RequestBody CreateSaleDto createSaleDto) {
        ReceiptResponseDto receiptResponseDto = saleServices.createSale(createSaleDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(receiptResponseDto.saleId()).toUri();
        return ResponseEntity.created(location).body(receiptResponseDto);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a sale by ID",
            description = "Get a sale by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sale found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sale not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleServices.getSaleById(id));
    }

    @GetMapping
    @Operation(
            summary = "Get all sales",
            description = "Get all sales"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sales found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sales not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<SaleResponseDto>> getAllSales() {
        return ResponseEntity.ok(saleServices.getAllSales());
    }

    @PostMapping("/cancel/{id}")
    @Operation(
            summary = "Cancel a sale",
            description = "Cancel a sale by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sale cancelled successfully"),
                    @ApiResponse(responseCode = "404", description = "Sale not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<Void> cancelSale(@PathVariable Long id) {
        saleServices.cancelSale(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refund/{id}")
    @Operation(
            summary = "Refound a sale",
            description = "Refound a sale by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sale refunded successfully"),
                    @ApiResponse(responseCode = "404", description = "Sale not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<Void> refundSale(@PathVariable Long id) {
        saleServices.refundSale(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/receipt/{id}")
    @Operation(
            summary = "Get a receipt by sale ID",
            description = "Get a receipt by sale ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Receipt found successfully"),
                    @ApiResponse(responseCode = "404", description = "Receipt not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<ReceiptResponseDto> getReceipt(@PathVariable Long id) {
        return ResponseEntity.ok(saleServices.getReceipt(id));
    }


    @GetMapping("{startDate}/{endDate}")
    @Operation(
            summary = "Get sales between dates",
            description = "Get sales between dates"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sales found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sales not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<SaleResponseDto>> getSalesBetweenDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return ResponseEntity.ok(saleServices.getSalesBetweenDates(startDate, endDate));
    }

    @GetMapping("/cashier/{cashierId}")
    @Operation(
            summary = "Get all the sales made by a cashier",
            description = "Get all the sales made by a cashier"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sales found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sales not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<SaleResponseDto>> getSalesByCashier(@PathVariable Long cashierId) {
        return ResponseEntity.ok(saleServices.getSalesByCashier(cashierId));
    }

    @GetMapping("/customer/{customerId}")
    @Operation(
            summary = "Get all the sales for a Customer By Id",
            description = "Get all the sales for a Customer By Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sales found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sales not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<SaleResponseDto>> getSalesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(saleServices.getSalesByCustomer(customerId));
    }

    @GetMapping("/{customerName}")
    @Operation(
            summary = "Get all the sales for a Customer By its Name",
            description = "Get all the sales for a Customer By its Name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sales found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sales not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<SaleResponseDto>> searchSalesByCustomerName(@PathVariable String customerName) {
        return ResponseEntity.ok(saleServices.searchSalesByCustomerName(customerName));
    }

    @GetMapping("/{paymentMethod}")
    @Operation(
            summary = "Get all the sales by Payment Method",
            description = "Get all the sales by Payment Method"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sales found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sales not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<SaleResponseDto>> getSalesByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
        return ResponseEntity.ok(saleServices.getSalesByPaymentMethod(paymentMethod));
    }

    @GetMapping("/{status}")
    @Operation(
            summary = "Get all the sales by Status Payment",
            description = "Get all the sales by Status Payment"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sales found successfully"),
                    @ApiResponse(responseCode = "404", description = "Sales not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<SaleResponseDto>> getSalesByStatus(@PathVariable StatusPayment status) {
        return ResponseEntity.ok(saleServices.getSalesByStatus(status));
    }
}
