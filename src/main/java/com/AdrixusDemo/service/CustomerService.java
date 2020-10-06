package com.AdrixusDemo.service;

import java.util.List;

import com.AdrixusDemo.entity.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customerRequest);

	List<Customer> findAllCustomers();

	Customer findSpecificCustomer(long customerId);

}
