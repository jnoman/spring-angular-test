package lu.atozdigital.api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lu.atozdigital.api.model.Utilisateur;
import lu.atozdigital.api.repository.UtilisateurRepository;
import lu.atozdigital.api.service.AccountService;


@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired 
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Utilisateur saveUser(Utilisateur utilisateur) {
		Utilisateur user = utilisateurRepository.findByUsername(utilisateur.getUsername());
		if(user != null) throw new RuntimeException("utilisateur exist déja");
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur loadUserByUsername(String username) {
		Utilisateur user = utilisateurRepository.findByUsername(username);
		if(user == null) throw new RuntimeException("username ou password est invalide");
		return utilisateurRepository.findByUsername(username);
	}

}
