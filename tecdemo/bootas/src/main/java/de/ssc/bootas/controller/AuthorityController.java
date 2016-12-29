package de.ssc.bootas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.ssc.bootas.model.Authority;
import de.ssc.bootas.repository.AuthorityRepository;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Authority> list(){		
		return authorityRepository.findAll();
	}
	
}
