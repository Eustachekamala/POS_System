package com.eustache.pos_system.Services.Customer;

import com.eustache.pos_system.DTO.Customer.Request.CreateCustomerDto;
import com.eustache.pos_system.DTO.Customer.Request.UpdateCustomerDto;
import com.eustache.pos_system.DTO.Customer.Response.CustomerResponseDto;
import com.eustache.pos_system.DTO.LoyaltyCard.Request.CreateLoyaltyCardDto;
import com.eustache.pos_system.Entities.LoyaltyCard;
import com.eustache.pos_system.Helpers.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerServices {
    CustomerResponseDto createCustomer(CreateCustomerDto customerDto);
    CustomerResponseDto updateCustomer(Long id, UpdateCustomerDto customerDto);
    void deleteCustomer(Long id);
    CustomerResponseDto getCustomerById(Long id);
    List<CustomerResponseDto> getAllCustomers();
    CustomerResponseDto getCustomersByLoyaltyCard(LoyaltyCard loyaltyCard);
}
