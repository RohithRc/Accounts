package com.microDemo.Accounts.service.impl;

import com.microDemo.Accounts.constants.AccountsConstants;
import com.microDemo.Accounts.dto.AccountsDto;
import com.microDemo.Accounts.dto.CustomerDto;
import com.microDemo.Accounts.entity.Accounts;
import com.microDemo.Accounts.entity.Customer;
import com.microDemo.Accounts.exceptions.CustomerAlreadyExistsException;
import com.microDemo.Accounts.exceptions.ResourceNotFoundException;
import com.microDemo.Accounts.mapper.AccountsMapper;
import com.microDemo.Accounts.mapper.CustomerMapper;
import com.microDemo.Accounts.repository.AccountsRepository;
import com.microDemo.Accounts.repository.CustomerRepository;
import com.microDemo.Accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("King");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("King");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        // 1. Fetch customer safely
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "mobileNumber", mobileNumber));

        // 2. Fetch account by customerId
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account", "customerId", customer.getCustomerId().toString()));

        // 3. Map entity -> DTO
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }
}