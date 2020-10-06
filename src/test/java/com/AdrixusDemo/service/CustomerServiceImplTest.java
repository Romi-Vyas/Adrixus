package com.AdrixusDemo.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.AdrixusDemo.entity.Account;
import com.AdrixusDemo.entity.Card;
import com.AdrixusDemo.entity.Customer;
import com.AdrixusDemo.exception.EntityNotFoundException;
import com.AdrixusDemo.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Captor
	ArgumentCaptor<Customer> customerCaptor;

	@Test
	public void testCreateCustomer() {
		Mockito.when(customerRepository.save(Matchers.any(Customer.class))).thenReturn(getCustomer());
		Customer customer = customerServiceImpl.createCustomer(getCustomer());

		verify(customerRepository, times(1)).save(Matchers.any(Customer.class));
		assertNotNull(customer);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testfindAllCustomersWithEmptyList() {
		Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
		customerServiceImpl.findAllCustomers();
	}

	@Test
	public void testfindAllCustomers() {
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(getCustomer());
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
		List<Customer> returnedList = customerServiceImpl.findAllCustomers();
		assertNotNull(returnedList);
	}

	@Test
	public void findSpecificCustomer() {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(getCustomer()));
		Customer customer = customerServiceImpl.findSpecificCustomer(100);
		assertNotNull(customer);
	}

	@Test(expected = EntityNotFoundException.class)
	public void findSpecificCustomerWithEmptyData() {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		customerServiceImpl.findSpecificCustomer(100);
	}

	private Card getCard() {
		Card card = new Card();
		card.setCardId(100);
		card.setCardType("cardType");
		card.setCvc("cvc");
		card.setPassword("pass");
		return card;
	}

	private Account getAccount() {
		Account acc = new Account();
		acc.setAccountId(200);
		acc.setCountryCode(21);
		acc.setCurrencyCode(22);

		List<Card> cardLst = new ArrayList<Card>();
		cardLst.add(getCard());
		acc.setCardLst(cardLst);
		return acc;
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setAdd1("add1");
		customer.setAdd1("add2");
		customer.setCity("city");
		customer.setState("state");
		customer.setCountry("country");
		customer.setName("ABC");
		customer.setCustomerId(300);

		List<Account> accLst = new ArrayList<Account>();
		accLst.add(getAccount());
		customer.setAccountLst(accLst);
		return customer;
	}
}
