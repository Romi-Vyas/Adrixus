package com.AdrixusDemo.service;

import java.util.List;

import com.AdrixusDemo.entity.Account;
import com.AdrixusDemo.entity.Card;

public interface CardService {

	Account createCard(long accountId, Card cardRequest);

	List<Card> findAllCards(long customerId);

	Account assignCardToAccount(long accountId, Card card);

	Card findSpecificCardWithAmount(long accountId, long cardId);

}
