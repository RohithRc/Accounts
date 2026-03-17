package com.microDemo.Accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AccountsController {

    @GetMapping("sayHello")
    public String sayHello(){
        return "Hello World";
    }
}
