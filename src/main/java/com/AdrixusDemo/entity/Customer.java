package com.AdrixusDemo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue
	@Column(name = "customerId")
	@JsonProperty("customer-id")
	private long customerId;

	@Valid
	@NotNull
	@NotEmpty
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonProperty("account-list")
	private List<Account> accountLst;

	@NotBlank
	@JsonProperty("customer-name")
	private String name;
	@JsonProperty("customer-add1")
	private String add1;
	@JsonProperty("customer-add2")
	private String add2;
	@JsonProperty("customer-city")
	private String city;
	@JsonProperty("customer-state")
	private String state;
	@JsonProperty("customer-country")
	private String country;

}
