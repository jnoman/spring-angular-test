package lu.atozdigital.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lu.atozdigital.api.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	public Utilisateur findByUsername(String username);
	
}
