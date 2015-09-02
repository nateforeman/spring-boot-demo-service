package com.demo.controller;

import java.util.List;
import java.util.Map;

import com.demo.domain.Cat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public class CatRepo {
	private Map<String, Cat> catMap = Maps.newHashMap();
	
	public Cat getByName(String catName) {
		return catMap.get(catName);
	}
	
	public void put(Cat cat) {
		catMap.put(cat.getName(), cat);
	}

	public List<Cat> getAll() {
		return ImmutableList.copyOf(catMap.values());
	}
}
