package com.bank.BankApplication.service;

import com.bank.BankApplication.dto.AccountDto;
import com.bank.BankApplication.dto.CreateAccountRequest;
import com.bank.BankApplication.dto.UpdateAccountRequest;

import java.util.List;

public interface IAccountService {
    AccountDto createAccount(CreateAccountRequest createAccountRequest);
    AccountDto updateAccount(String id, UpdateAccountRequest request);
    List<AccountDto> getAllAccounts();
    AccountDto getAccountById(String id);
    void deleteAccountById(String id);
    AccountDto withdrawMoney(String id, Double amount);
    AccountDto addMoney(String id,Double amount);
}
