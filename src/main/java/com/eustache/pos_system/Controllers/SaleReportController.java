package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.Services.Sale.SalesReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/sales-report")
@RequiredArgsConstructor
@Tag(name = "Sales Report Controller", description = "API endpoints for managing sales report")
public class SaleReportController {
    private final SalesReportService salesReportService;

    @GetMapping("/today-revenue")
    @Operation(
            summary = "Get today's revenue",
            description = "Get today's revenue"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved today's revenue"),
                    @ApiResponse(responseCode = "404", description = "No sales found for today"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<BigDecimal> getTodayRevenue() {
        return ResponseEntity.ok(salesReportService.getTodayRevenue());
    }

    @GetMapping("/revenue-between-dates/{startDate}/{endDate}")
    @Operation(
            summary = "Get revenue between dates",
            description = "Get revenue between dates"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved revenue between dates"),
                    @ApiResponse(responseCode = "404", description = "No sales found between dates"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<BigDecimal> getRevenueBetweenDates(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    ) {
        return ResponseEntity.ok(salesReportService.getRevenueBetweenDates(
                startDate,
                endDate
        ));
    }

    @GetMapping("/today-sales-count")
    @Operation(
            summary = "Get total sales for today",
            description = "Get total sales for today"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved total sales for today"),
                    @ApiResponse(responseCode = "404", description = "No sales found for today"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<Long> getTodaySalesCount() {
        return ResponseEntity.ok(salesReportService.getTodaySalesCount());
    }

    @GetMapping("/sales-count-between-dates/{startDate}/{endDate}")
    @Operation(
            summary = "Get Count of Sales Between Dates",
            description = "Get Count of Sales Between Dates"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved count of sales between dates"),
                    @ApiResponse(responseCode = "404", description = "No sales found between dates"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<Long> getSalesCountBetweenDates(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    ) {
        return ResponseEntity.ok(salesReportService.getSalesCountBetweenDates(
                startDate,
                endDate
        ));
    }
}
