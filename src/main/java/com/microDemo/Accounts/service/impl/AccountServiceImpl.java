package com.microDemo.Accounts.service.impl;

import com.microDemo.Accounts.dto.CustomerDto;
import com.microDemo.Accounts.repository.AccountsRepository;
import com.microDemo.Accounts.repository.CustomerRepository;
import com.microDemo.Accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
