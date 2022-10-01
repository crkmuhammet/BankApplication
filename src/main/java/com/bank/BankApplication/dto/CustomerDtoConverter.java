package com.bank.BankApplication.dto;

import com.bank.BankApplication.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto convert(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setAdress(customer.getAdress());
        customerDto.setCity(CityDto.valueOf(customer.getCity().name()));
        customerDto.setName(customer.getName());
        customerDto.setDateOfBirth(customer.getDateOfBirth());

        return customerDto;
    }
}
