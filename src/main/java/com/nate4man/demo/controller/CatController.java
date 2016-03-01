package com.nate4man.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.nate4man.demo.domain.Cat;
import com.nate4man.demo.domain.SpecialPower;

@RestController
public class CatController {

	@Autowired
	private CatRepo catRepo;
	
	@RequestMapping(value="/cats", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> putCat(@RequestBody Cat cat) {
		catRepo.save(cat);
        return ResponseEntity.
        		status(HttpStatus.OK).
        		body(String.format("Successfully added the name cat %s", cat.getName()));
    }
	
	@RequestMapping(value="/cats", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<Cat>> getAllCats() {
		List<Cat> allCats = Lists.newArrayList(catRepo.findAll());
		return ResponseEntity.
        		status(HttpStatus.OK).
        		body(allCats);
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cats/{name}", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<Cat> getCat(@PathVariable String name) {
		Cat cat = catRepo.findOne(name);
		if (null == cat) {
			return (ResponseEntity<Cat>) ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		else {
			return ResponseEntity.
        		status(HttpStatus.OK).
        		body(cat);
		}
    }
	
	@RequestMapping(value="/cats/{name}/special-power", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> putSpecialPower(@PathVariable String name, @RequestBody SpecialPower specialPower) {
		Cat cat = catRepo.findOne(name);
		if (null == cat) {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cat found with name = " + name);
		}
		cat.getSpecialPowers().add(specialPower);
        return ResponseEntity.
        		status(HttpStatus.OK).
        		body(String.format("Successfully added special power to Cat named %s", cat.getName()));
    }
}