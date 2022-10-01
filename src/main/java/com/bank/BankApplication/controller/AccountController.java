package com.bank.BankApplication.controller;

import com.bank.BankApplication.dto.AccountDto;
import com.bank.BankApplication.dto.CreateAccountRequest;
import com.bank.BankApplication.dto.UpdateAccountRequest;
import com.bank.BankApplication.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/account")
public class AccountController {
    private final IAccountService iAccountService;

    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return ResponseEntity.ok(iAccountService.getAllAccounts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String id){
        return ResponseEntity.ok(iAccountService.getAccountById(id));
    }
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(iAccountService.createAccount(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id,@RequestBody UpdateAccountRequest request){
        return ResponseEntity.ok(iAccountService.updateAccount(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable String id){
        iAccountService.deleteAccountById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable String id, @PathVariable Double amount){
        return ResponseEntity.ok(iAccountService.withdrawMoney(id,amount));
    }
    @PutMapping("/add/{id}/{amount}")
    public ResponseEntity<AccountDto> addMoney(@PathVariable String id, @PathVariable Double amount){
        return ResponseEntity.ok(iAccountService.addMoney(id,amount));
    }
}
