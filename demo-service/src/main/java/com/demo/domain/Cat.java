package com.demo.domain;

import java.util.List;

import com.google.common.collect.Lists;

public class Cat {

    private String name;
    private String color;
    private List<SpecialPower> specialPowers;
    
    public Cat() {
    	specialPowers = Lists.newArrayList();
    }
    
    public Cat(String name, String color, List<SpecialPower> specialPowers) {
    	this.setName(name);
    	this.setColor(color);
    	this.setSpecialPowers(specialPowers);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<SpecialPower> getSpecialPowers() {
		return specialPowers;
	}

	public void setSpecialPowers(List<SpecialPower> specialPowers) {
		if (null != specialPowers) {
			this.specialPowers = specialPowers;
		}
	}
    
}
