package com.bank.BankApplication.controller;


import com.bank.BankApplication.dto.CreateCustomerRequest;
import com.bank.BankApplication.dto.CustomerDto;
import com.bank.BankApplication.dto.UpdateCustomerRequest;
import com.bank.BankApplication.service.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/customer")
public class CustomerController {

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }


    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest customerRequest){
        return ResponseEntity.ok(iCustomerService.createCustomer(customerRequest));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(iCustomerService.getAllCustomers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(iCustomerService.getCustomerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id){
        iCustomerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id,
                                                      @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return ResponseEntity.ok(iCustomerService.updateCustomer(id,updateCustomerRequest));
    }
}
