package com.microDemo.Accounts.controller;

import com.microDemo.Accounts.constants.AccountsConstants;
import com.microDemo.Accounts.dto.CustomerDto;
import com.microDemo.Accounts.dto.ResponceDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    @PostMapping("/create")
    public ResponseEntity<ResponceDto> createAccount(@RequestBody CustomerDto customerDto){

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(new ResponceDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

}
