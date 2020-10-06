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

import com.AdrixusDemo.entity.Customer;
import com.AdrixusDemo.service.CustomerService;

@RestController
@RequestMapping(value = "api/v1/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customerRequest, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Customer customer = customerService.createCustomer(customerRequest);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Customer>> findAllCustomers() {
		List<Customer> customers = customerService.findAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping(value = "{customerId}")
	public ResponseEntity<Customer> findSpecificCustomer(@PathVariable long customerId) {
		Customer customer = customerService.findSpecificCustomer(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
}
