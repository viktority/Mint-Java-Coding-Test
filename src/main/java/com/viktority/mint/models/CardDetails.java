package com.viktority.mint.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "card_details")
public class CardDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String cardNumber;

	public CardDetails(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public CardDetails() {
	}

}
