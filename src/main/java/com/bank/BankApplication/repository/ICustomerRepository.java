package com.bank.BankApplication.repository;

import com.bank.BankApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,String> {

}
