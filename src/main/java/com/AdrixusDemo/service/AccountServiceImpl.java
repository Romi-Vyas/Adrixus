package com.AdrixusDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdrixusDemo.entity.Account;
import com.AdrixusDemo.entity.Customer;
import com.AdrixusDemo.exception.EntityNotFoundException;
import com.AdrixusDemo.repository.AccountRepository;
import com.AdrixusDemo.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	CustomerService cutomerService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Customer createAccount(long customerId, Account accountRequest) {
		Customer customer = cutomerService.findSpecificCustomer(customerId);
		accountRequest.setCustomer(customer);
		customer.getAccountLst().add(accountRequest);

		customer.getAccountLst().forEach(account -> {
			account.setCustomer(customer);
			account.getCardLst().forEach(card -> {
				card.setAccount(account);
			});
		});
		return customerRepository.save(customer);
	}

	@Override
	public List<Account> findAllAccounts(long customerId) {
		Customer customer = cutomerService.findSpecificCustomer(customerId);
		return customer.getAccountLst();
	}

	@Override
	public Account findSpecificAccountWithCustomer(long customerId, long accountId) {
		if (customerRepository.findById(customerId).isPresent()) {
			return this.findSpecificAccount(accountId);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Account findSpecificAccount(long accountId) {
		if (accountRepository.findById(accountId).isPresent()) {
			return accountRepository.findById(accountId).get();
		} else {
			throw new EntityNotFoundException();
		}
	}
}
