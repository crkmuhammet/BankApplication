package com.bank.BankApplication.repository;

import com.bank.BankApplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,String> {
}
