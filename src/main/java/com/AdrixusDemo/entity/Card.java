package com.AdrixusDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card {

	@Id
	@GeneratedValue
	@Column(name = "cardId")
	@JsonProperty("card-id")
	private long cardId;

	@NotBlank
	@Column(name = "card_type")
	@JsonProperty("card-type")
	private String cardType;

	@NotBlank
	@Column(name = "cvc")
	@JsonProperty("card-cvc")
	private String cvc;

	@NotBlank
	@Column(name = "pass")
	@JsonProperty("card-pass")
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountId")
	@JsonIgnore
	private Account account;
}
