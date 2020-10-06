package com.AdrixusDemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "accountId")
	@JsonProperty("account-id")
	private long accountId;

	@Column(name = "currency_code")
	@JsonProperty("currency-code")
	private int currencyCode;

	@Column(name = "country_code")
	@JsonProperty("country-code")
	private int countryCode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@JsonProperty("card-list")
	@NotNull
	@NotEmpty
	private List<Card> cardLst = new ArrayList<Card>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	@JsonIgnore
	private Customer customer;

}
