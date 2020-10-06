package com.AdrixusDemo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AdrixusDemo.entity.Account;
import com.AdrixusDemo.entity.Card;
import com.AdrixusDemo.exception.EntityNotFoundException;
import com.AdrixusDemo.repository.AccountRepository;
import com.AdrixusDemo.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CardRepository cardRepository;

	@Override
	public Account createCard(long accountId, Card cardRequest) {
		Account account = accountService.findSpecificAccount(accountId);
		cardRequest.setAccount(account);
		account.getCardLst().add(cardRequest);
		return accountRepository.save(account);
	}

	@Override
	public List<Card> findAllCards(long accountId) {
		Account account = accountService.findSpecificAccount(accountId);
		return account.getCardLst();
	}

	@Override
	public Card findSpecificCardWithAmount(long accountId,long cardId) {
		if(accountRepository.findById(accountId).isPresent()) {
			return this.findSpecificCard(cardId);
		}else {
			throw new EntityNotFoundException();
		}
	}
	
	private Card findSpecificCard(long cardId) {
		if (cardRepository.findById(cardId).isPresent()) {
			return cardRepository.findById(cardId).get();
		} else {
			throw new EntityNotFoundException();
		}		
	}

	@Transactional
	@Override
	public Account assignCardToAccount(long accountId, Card card) {
		Account account = accountService.findSpecificAccount(accountId);
		cardRepository.setCardToAccount(card.getCardType(), card.getCvc(), card.getPassword(), accountId,
				card.getCardId());
		return account;
	}

	
	

}
