package com.microDemo.Accounts.controller;

import com.microDemo.Accounts.constants.AccountsConstants;
import com.microDemo.Accounts.dto.AccountsDto;
import com.microDemo.Accounts.dto.CustomerDto;
import com.microDemo.Accounts.dto.ResponceDto;
import com.microDemo.Accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponceDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(new ResponceDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }
    @GetMapping("/account")
    public ResponseEntity<CustomerDto> fetchAccount(@RequestParam("mobileNumber") String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.ok(customerDto);
    }
}
