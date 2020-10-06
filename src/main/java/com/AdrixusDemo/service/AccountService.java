package com.AdrixusDemo.service;

import java.util.List;

import com.AdrixusDemo.entity.Account;
import com.AdrixusDemo.entity.Customer;

public interface AccountService {

	Customer createAccount(long customerId, Account accountRequest);

	List<Account> findAllAccounts(long customerId);

	Account findSpecificAccountWithCustomer(long customerId, long accountId);

	Account findSpecificAccount(long accountId);

}
