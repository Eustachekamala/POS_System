package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Customer.Request.CreateCustomerDto;
import com.eustache.pos_system.DTO.Customer.Request.UpdateCustomerDto;
import com.eustache.pos_system.DTO.Customer.Response.CustomerResponseDto;
import com.eustache.pos_system.DTO.LoyaltyCard.Request.CreateLoyaltyCardDto;
import com.eustache.pos_system.Entities.LoyaltyCard;
import com.eustache.pos_system.Services.Customer.CustomerServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "API endpoints for customer management")
public class CustomerController {
    private final CustomerServices customerServices;

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Create a new customer by providing customer details and optional loyalty card")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Customer created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @RequestBody @Valid CreateCustomerDto customerDto) {
        CustomerResponseDto customerResponseDto = customerServices.createCustomer(customerDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(customerResponseDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing customer", description = "Update an existing customer by providing customer details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Customer not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCustomerDto customerDto) {
        return ResponseEntity.ok(customerServices.updateCustomer(id, customerDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Delete a customer by providing customer ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerServices.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all customers", description = "Get all customers")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customers retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No customers found")
            }
    )
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return ResponseEntity.ok(customerServices.getAllCustomers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by ID", description = "Get a customer by providing customer ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerServices.getCustomerById(id));
    }

    @GetMapping("/loyalty-card/{loyaltyCardNumber}")
    @Operation(summary = "Get a customer by loyalty card", description = "Get a customer by providing loyalty card details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            }
    )
    public ResponseEntity<CustomerResponseDto> getCustomerByLoyaltyCard(@PathVariable String loyaltyCardNumber) {
        return ResponseEntity.ok(customerServices.getCustomersByLoyaltyCard(loyaltyCardNumber));
    }
}
