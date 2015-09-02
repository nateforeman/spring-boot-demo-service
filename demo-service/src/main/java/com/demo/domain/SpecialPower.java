package com.demo.domain;

public class SpecialPower {
	private String description;
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
