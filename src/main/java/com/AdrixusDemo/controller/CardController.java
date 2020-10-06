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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AdrixusDemo.entity.Account;
import com.AdrixusDemo.entity.Card;
import com.AdrixusDemo.service.CardService;

@RestController
@RequestMapping(value = "api/v1/accounts/{accountId}/cards")
public class CardController {

	@Autowired
	CardService cardService;

	@PostMapping
	public ResponseEntity<Account> createAccount(@PathVariable long accountId, @Valid @RequestBody Card cardRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Account account = cardService.createCard(accountId, cardRequest);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Card>> getAllCardsForSpecificAccount(@PathVariable long accountId) {
		List<Card> cards = cardService.findAllCards(accountId);
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}

	@GetMapping(value = "{cardId}")
	public ResponseEntity<Card> getSpecificCard(@PathVariable long accountId, @PathVariable long cardId) {
		Card card = cardService.findSpecificCardWithAmount(accountId, cardId);
		return new ResponseEntity<>(card, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Account> assignCardToAccount(@PathVariable long accountId, @Valid @RequestBody Card card,
			BindingResult result) {
		if (result.hasErrors() || card.getCardId() == 0) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Account account = cardService.assignCardToAccount(accountId, card);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
}
