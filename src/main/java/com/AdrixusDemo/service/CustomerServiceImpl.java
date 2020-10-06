package com.AdrixusDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AdrixusDemo.entity.Customer;
import com.AdrixusDemo.exception.EntityNotFoundException;
import com.AdrixusDemo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public Customer createCustomer(Customer customerRequest) {
		customerRequest.getAccountLst().forEach(account -> {
			account.setCustomer(customerRequest);
			account.getCardLst().forEach(card -> {
				card.setAccount(account);
			});
		});
		return customerRepository.save(customerRequest);
	}

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> customerList = customerRepository.findAll();
		if (customerList.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return customerList;
	}

	@Override
	public Customer findSpecificCustomer(long customerId) {
		if (customerRepository.findById(customerId).isPresent()) {
			return customerRepository.findById(customerId).get();
		} else {
			throw new EntityNotFoundException();
		}
	}
}
