package com.nate4man.demo.controller;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nate4man.demo.domain.Cat;

public interface CatRepo extends CrudRepository<Cat, String> {
	
	public List<Cat> findByName(String name);
}
