package com.AdrixusDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdrixusDemo.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
