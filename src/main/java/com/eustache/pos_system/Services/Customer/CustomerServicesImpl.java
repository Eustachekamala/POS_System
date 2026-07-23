package com.eustache.pos_system.Services.Customer;

import com.eustache.pos_system.DTO.Customer.Request.CreateCustomerDto;
import com.eustache.pos_system.DTO.Customer.Request.UpdateCustomerDto;
import com.eustache.pos_system.DTO.Customer.Response.CustomerResponseDto;
import com.eustache.pos_system.Entities.Customer;
import com.eustache.pos_system.Entities.LoyaltyCard;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Mappers.CustomerMapper;
import com.eustache.pos_system.Mappers.LoyaltyCardMapper;
import com.eustache.pos_system.Repositories.CustomerRepository;
import com.eustache.pos_system.Repositories.LoyaltyCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices {
    private final CustomerRepository customerRepository;
    private final LoyaltyCardRepository loyaltyCardRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto createCustomer(CreateCustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer = customerRepository.save(customer);
        return customerMapper.toResponseFromCustomer(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, UpdateCustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));

        Optional.ofNullable(customerDto.firstName()).ifPresent(customer::setFirstName);
        Optional.ofNullable(customerDto.lastName()).ifPresent(customer::setLastName);
        Optional.ofNullable(customerDto.email()).ifPresent(customer::setEmail);
        Optional.ofNullable(customerDto.phone()).ifPresent(customer::setPhone);
        Optional.of(customerDto.loyaltyPoints()).ifPresent(customer::setLoyaltyPoints);
        Optional.ofNullable(customerDto.loyaltyCardNumber()).ifPresent(cardNumber -> {
            LoyaltyCard loyaltyCard = loyaltyCardRepository.findByCardNumber(cardNumber)
                    .orElseThrow(() -> new BusinessException("Loyalty card not found with number: " + cardNumber));
            customer.setLoyaltyCard(loyaltyCard);
        });

        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toResponseFromCustomer(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));
        
        CustomerResponseDto responseDto = customerMapper.toResponseFromCustomer(customer);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));
        return customerMapper.toResponseFromCustomer(customer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponseFromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto getCustomersByLoyaltyCard(LoyaltyCard loyaltyCard) {
        Customer customer = customerRepository.findByLoyaltyCard(loyaltyCard)
                .orElseThrow(() -> new BusinessException("Customer not found with loyalty card: " + loyaltyCard.getCardNumber()));
        return customerMapper.toResponseFromCustomer(customer);
    }
}
