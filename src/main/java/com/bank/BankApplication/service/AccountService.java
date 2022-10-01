package com.bank.BankApplication.service;

import com.bank.BankApplication.dto.AccountDto;
import com.bank.BankApplication.dto.AccountDtoConverter;
import com.bank.BankApplication.dto.CreateAccountRequest;
import com.bank.BankApplication.dto.UpdateAccountRequest;
import com.bank.BankApplication.model.Account;
import com.bank.BankApplication.model.Customer;
import com.bank.BankApplication.repository.IAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{
    private final IAccountRepository iAccountRepository;
    private CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;
    public AccountService(IAccountRepository iAccountRepository, CustomerService customerService, CustomerService customerService1, AccountDtoConverter accountDtoConverter) {
        this.iAccountRepository = iAccountRepository;
        this.customerService = customerService1;
        this.accountDtoConverter = accountDtoConverter;
    }


    @Override
    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.getCustomerByIdForAccount(createAccountRequest.getCustomerId());
        if(customer.getId()==null || customer.getId()==""){
            return AccountDto.builder().build();
        }
        Account account = Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .currency(createAccountRequest.getCurrency())
                .city(createAccountRequest.getCity())
                .customerId(createAccountRequest.getCustomerId())
                .build();
        return accountDtoConverter.convert(iAccountRepository.save(account));
    }

    @Override
    public AccountDto updateAccount(String id, UpdateAccountRequest request) {
        Customer customer = customerService.getCustomerByIdForAccount(request.getCustomerId());
        if(customer.getId()==null || customer.getId()==""){
            return AccountDto.builder().build();
        }
        Optional<Account> accountOptional = iAccountRepository.findById(id);
        accountOptional.ifPresent(account ->{
                account.setBalance(request.getBalance());
                account.setCity(request.getCity());
                account.setCurrency(request.getCurrency());
                account.setCustomerId(request.getCustomerId());
                iAccountRepository.save(account);
    });
        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountList = iAccountRepository.findAll();
        return accountList.stream().map(accountDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(String id) {
        return iAccountRepository.findById(id).map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    @Override
    public void deleteAccountById(String id) {
        iAccountRepository.deleteById(id);
    }

    @Override
    public AccountDto withdrawMoney(String id, Double amount) {
        Optional<Account> accountOptional = iAccountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            if (account.getBalance()>amount){
                account.setBalance(account.getBalance()-amount);
                iAccountRepository.save(account);
            }else{
                System.out.println("Yetersiz bakiye.");
            }
        });
        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    @Override
    public AccountDto addMoney(String id, Double amount) {
        Optional<Account> accountOptional = iAccountRepository.findById(id);
        accountOptional.ifPresent(account -> {
                account.setBalance(account.getBalance()+amount);
                iAccountRepository.save(account);
        });
        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }
}
