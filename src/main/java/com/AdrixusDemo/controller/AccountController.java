package com.AdrixusDemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AdrixusDemo.entity.Account;
import com.AdrixusDemo.entity.Customer;
import com.AdrixusDemo.service.AccountService;

@RestController
@RequestMapping(value = "api/v1/customer/{customerId}/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping
	public ResponseEntity<Customer> createAccount(@PathVariable long customerId,
			@Valid @RequestBody Account accountRequest, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Customer customer = accountService.createAccount(customerId, accountRequest);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Account>> getAllAccountForSpecificCustomer(@PathVariable long customerId) {
		List<Account> accounts = accountService.findAllAccounts(customerId);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = "{accountId}")
	public ResponseEntity<Account> getSpecificAccount(@PathVariable long customerId, @PathVariable long accountId) {
		Account account = accountService.findSpecificAccountWithCustomer(customerId, accountId);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
}
