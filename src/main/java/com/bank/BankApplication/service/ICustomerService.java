package com.bank.BankApplication.service;

import com.bank.BankApplication.dto.CreateCustomerRequest;
import com.bank.BankApplication.dto.CustomerDto;
import com.bank.BankApplication.dto.UpdateCustomerRequest;
import com.bank.BankApplication.model.Customer;

import java.util.List;

public interface ICustomerService {
    CustomerDto createCustomer(CreateCustomerRequest customerRequest);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(String id);

    void deleteCustomerById(String id);
    CustomerDto updateCustomer(String id, UpdateCustomerRequest updateCustomerRequest);
}
