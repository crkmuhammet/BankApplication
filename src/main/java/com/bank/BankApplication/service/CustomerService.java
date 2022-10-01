package com.bank.BankApplication.service;

import com.bank.BankApplication.dto.CreateCustomerRequest;
import com.bank.BankApplication.dto.CustomerDto;
import com.bank.BankApplication.dto.CustomerDtoConverter;
import com.bank.BankApplication.dto.UpdateCustomerRequest;
import com.bank.BankApplication.model.City;
import com.bank.BankApplication.model.Customer;
import com.bank.BankApplication.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    private final ICustomerRepository iCustomerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(ICustomerRepository iCustomerRepository, CustomerDtoConverter customerDtoConverter) {
        this.iCustomerRepository = iCustomerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }


    @Override
    public CustomerDto createCustomer(CreateCustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setId(customerRequest.getId());
        customer.setAdress(customerRequest.getAdress());
        customer.setName(customerRequest.getName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customer.setCity(City.valueOf(customerRequest.getCity().name()));

        iCustomerRepository.save(customer);
        return customerDtoConverter.convert(customer);
    }
    @Override
    public List<CustomerDto> getAllCustomers(){
        List<Customer> customersList = iCustomerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customersList){
            customerDtoList.add(customerDtoConverter.convert(customer));
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        Optional<Customer> customerOptional = iCustomerRepository.findById(id);
        return customerOptional.map(customer -> customerDtoConverter.convert(customer)).orElse(new CustomerDto());
    }

    @Override
    public void deleteCustomerById(String id) {
        iCustomerRepository.deleteById(id);
    }

    @Override
    public CustomerDto updateCustomer(String id, UpdateCustomerRequest updateCustomerRequest) {
        Optional<Customer> customerOptional = iCustomerRepository.findById(id);
        //Eğer bunun içinde bu değer var ise yap. Yoksa Atla
        customerOptional.ifPresent(customer ->
        {
            customer.setName(updateCustomerRequest.getName());
            customer.setAdress(updateCustomerRequest.getAdress());
            customer.setDateOfBirth(updateCustomerRequest.getDateOfBirth());
            customer.setCity(City.valueOf(updateCustomerRequest.getCity().name()));
            iCustomerRepository.save(customer);
        });
        return customerOptional.map(customer -> customerDtoConverter.convert(customer)).orElse(new CustomerDto());
    }

    protected  Customer getCustomerByIdForAccount(String id){
        return iCustomerRepository.findById(id).orElse(new Customer());
    }

}
