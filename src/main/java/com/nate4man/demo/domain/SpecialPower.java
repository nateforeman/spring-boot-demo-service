package com.nate4man.demo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SpecialPower {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String description;
	@Enumerated(EnumType.STRING)
	private Rate rate;
	
	public SpecialPower() {
		
	}
	
	public SpecialPower(String desription, Rate rate) {
		this.setDescription(desription);
		this.setRate(rate);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}
}
