package com.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Cat;
import com.demo.domain.SpecialPower;
import com.demo.metrics.WithTimerMetric;

@RestController
public class CatController {

	private static final CatRepo catRepo = new CatRepo();
	
	@RequestMapping(value="/cats", method=RequestMethod.POST, produces="application/json")
	@WithTimerMetric("com.demo.put-cat")
    public ResponseEntity<String> putCat(@RequestBody Cat cat) {
		catRepo.put(cat);
        return ResponseEntity.
        		status(HttpStatus.OK).
        		body(String.format("Successfully added the name cat %s", cat.getName()));
    }
	
	@RequestMapping(value="/cats", method=RequestMethod.GET, produces="application/json")
	@WithTimerMetric("com.demo.get-all-cats")
    public ResponseEntity<List<Cat>> getAllCats() {
		return ResponseEntity.
        		status(HttpStatus.OK).
        		body(catRepo.getAll());
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cats/{name}", method=RequestMethod.GET, produces="application/json")
	@WithTimerMetric("com.demo.get-cat")
    public ResponseEntity<Cat> getCat(@PathVariable String name) {
		Cat cat = catRepo.getByName(name);
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
	@WithTimerMetric("com.demo.post-special-power")
    public ResponseEntity<String> putSpecialPower(@PathVariable String name, @RequestBody SpecialPower specialPower) {
		Cat cat = catRepo.getByName(name);
		if (null == cat) {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cat found with name = " + name);
		}
		cat.getSpecialPowers().add(specialPower);
        return ResponseEntity.
        		status(HttpStatus.OK).
        		body(String.format("Successfully added special power to Cat named %s", cat.getName()));
    }

}