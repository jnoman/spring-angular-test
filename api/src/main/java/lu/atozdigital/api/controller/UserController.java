package lu.atozdigital.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lu.atozdigital.api.model.Utilisateur;
import lu.atozdigital.api.service.AccountService;


@RepositoryRestController
public class UserController {
	
	@Autowired 
	private AccountService accountService;

	@ResponseBody
	@RequestMapping(path="/utilisateurs", method=RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@RequestBody Utilisateur user) {
		try {
			accountService.saveUser(user);
			return new ResponseEntity<Object>("ajouter du compte terminée avec succès", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
}
