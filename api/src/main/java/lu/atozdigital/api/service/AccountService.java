package lu.atozdigital.api.service;

import lu.atozdigital.api.model.Utilisateur;

public interface AccountService {
	public Utilisateur saveUser(Utilisateur utilisateur);
	public Utilisateur loadUserByUsername(String username);
}

