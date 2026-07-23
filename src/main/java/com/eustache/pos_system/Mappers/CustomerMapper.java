package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Customer.Request.CreateCustomerDto;
import com.eustache.pos_system.DTO.Customer.Response.CustomerResponseDto;
import com.eustache.pos_system.Entities.Customer;
import com.eustache.pos_system.Entities.LoyaltyCard;
import com.eustache.pos_system.Helpers.LoyaltyCardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final LoyaltyCardMapper loyaltyCardMapper;

    /**
     * Convert a CreateCustomerDto to a Customer entity
     * @param customerDto
     * @return Customer
     */
    public Customer toEntity(CreateCustomerDto customerDto){
        /*
         *  The customer is created
         */
        Customer customer = new Customer();
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());
        customer.setEmail(customerDto.email());
        customer.setPhone(customerDto.phone());
        customer.setLoyaltyPoints(0);

        /*
         *  The loyalty card is created and linked to the customer
         */
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        loyaltyCard.setCardNumber(customerDto.loyaltyCard().cardNumber());
        loyaltyCard.setQrCode(customerDto.loyaltyCard().qrCode());
        loyaltyCard.setStatus(LoyaltyCardStatus.ACTIVE);
        loyaltyCard.setIssueAt(LocalDateTime.now());
        loyaltyCard.setCustomer(customer);
        customer.setLoyaltyCard(loyaltyCard);
        return customer;
    }

    /**
     * Convert a Customer entity to a CustomerResponseDto
     * @param customer
     * @return CustomerResponseDto
     */
    public CustomerResponseDto toResponseFromCustomer(Customer  customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getLoyaltyPoints(),
                loyaltyCardMapper.toResponseFromLoyaltyCard(customer.getLoyaltyCard())
        );
    }
}
