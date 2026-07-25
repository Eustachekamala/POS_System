package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.LoyaltyTransactions.Request.LoyaltyTransactionRequest;
import com.eustache.pos_system.DTO.LoyaltyTransactions.Response.LoyaltyTransactionResponseDto;
import com.eustache.pos_system.Helpers.LoyaltyTransactionType;
import com.eustache.pos_system.Services.LoyaltyCard.LoyaltyServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loyalty")
@Tag(name = "Loyalty Card Transactions", description = "Loyalty card transaction management")
public class LoyaltyCardTransactionController {
    private final LoyaltyServices loyaltyServices;

    @GetMapping("/points/{customerId}")
    @Operation(
            summary = "Get loyalty points",
            description = "Get loyalty points for a specific customer"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Loyalty points retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<Integer> getLoyaltyPoints(@PathVariable Long customerId) {
        return ResponseEntity.ok(loyaltyServices.getLoyaltyPoints(customerId));
    }

    @GetMapping("/history/{customerId}")
    @Operation(
            summary = "Get loyalty transaction history",
            description = "Get loyalty transaction history for a specific customer, sorted by date (newest first)"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Loyalty history retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<List<LoyaltyTransactionResponseDto>> getLoyaltyHistory(@PathVariable Long customerId) {
        return ResponseEntity.ok(loyaltyServices.getLoyaltyHistory(customerId));
    }

    @GetMapping("/transactions/{customerId}")
    @Operation(
            summary = "Get loyalty transactions",
            description = "Get all loyalty transactions for a specific customer"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Loyalty transactions retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<List<LoyaltyTransactionResponseDto>> getLoyaltyTransactions(@PathVariable Long customerId) {
        return ResponseEntity.ok(loyaltyServices.getLoyaltyTransactions(customerId));
    }

    @GetMapping("/balance/{customerId}")
    @Operation(
            summary = "Get loyalty balance",
            description = "Get the current loyalty points balance for a specific customer"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Loyalty balance retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<Integer> getLoyaltyBalance(@PathVariable Long customerId) {
        return ResponseEntity.ok(loyaltyServices.getLoyaltyBalance(customerId));
    }

    @GetMapping("/transactions/{customerId}/{type}")
    @Operation(
            summary = "Get loyalty transactions by type",
            description = "Get loyalty transactions for a specific customer filtered by transaction type (EARN, REDEEM, BONUS, EXPIRE, ADJUSTMENT )"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Loyalty transactions by type retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<List<LoyaltyTransactionResponseDto>> getLoyaltyTransactionsByType(@PathVariable Long customerId, @PathVariable LoyaltyTransactionType type) {
        return ResponseEntity.ok(loyaltyServices.getLoyaltyTransactionsByType(customerId, type));
    }

    @PostMapping("/spend/{customerId}")
    @Operation(
            summary = "Spend loyalty points",
            description = "Spend loyalty points for a specific customer on a sale"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Loyalty points spent successfully"),
                    @ApiResponse(responseCode = "400", description = "Insufficient loyalty points"),
                    @ApiResponse(responseCode = "404", description = "Customer or sale not found")
            }
    )
    public ResponseEntity<List<LoyaltyTransactionResponseDto>> spendPoints(@PathVariable Long customerId, @RequestBody LoyaltyTransactionRequest request) {
        return ResponseEntity.ok(loyaltyServices.spendPoints(customerId, request));
    }
}
