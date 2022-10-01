package com.bank.BankApplication.dto;

import com.bank.BankApplication.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {
    public AccountDto convert(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .customerId(account.getCustomerId())
                .currency(account.getCurrency())
                .build();
    }
}
