package com.pdfsentimentanalysis.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdfsentimentanalysis.model.Entity;
import com.pdfsentimentanalysis.persistence.PeopleRepository;

@RestController
@RequestMapping("/rest")
public class PeopleController {

	@Autowired
	private PeopleRepository peopleRepository;

	@RequestMapping(path = "/people", method = RequestMethod.GET)
	public List<String> getPeople() {
		List<Entity> entities = peopleRepository.findAll();
		List<String> names = entities.stream().map(Entity::getName).collect(Collectors.toList());
		names.sort(String::compareToIgnoreCase);
		return names;
	}

}
