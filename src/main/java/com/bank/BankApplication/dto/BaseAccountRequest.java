package com.bank.BankApplication.dto;

import com.bank.BankApplication.model.City;
import com.bank.BankApplication.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseAccountRequest {
    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;
}
