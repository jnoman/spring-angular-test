package lu.atozdigital.api.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lu.atozdigital.api.model.Utilisateur;
import lu.atozdigital.api.service.AccountService;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = accountService.loadUserByUsername(username);
		if(user == null) throw new UsernameNotFoundException("utilisateur invalide",new Throwable("aaaa"));
		Collection<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
	


}
