package lu.atozdigital.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lu.atozdigital.api.model.Utilisateur;
import lu.atozdigital.api.service.AccountService;
import lu.atozdigital.api.service.impl.AccountServiceImpl;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	AccountService accountService;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManagerBuilder, ApplicationContext ctx) {
		this.authenticationManager = authenticationManagerBuilder;
	    this.accountService= ctx.getBean(AccountServiceImpl.class);
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Utilisateur user = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
			if(user == null) throw new UsernameNotFoundException("utilisateur invalide",new Throwable("aaaa"));
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user=(User)authResult.getPrincipal();
		Utilisateur utilisateur = accountService.loadUserByUsername(user.getUsername());
        List<String> roles=new ArrayList<>();
        roles.add("USER");
        String jwt= JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(user.getUsername())
                .withClaim("id", utilisateur.getId())
                .withClaim("email", utilisateur.getEmail())
                .withArrayClaim("roles",roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
                .sign(Algorithm.HMAC256(SecurityParams.SECRET));
        response.addHeader(SecurityParams.JWT_HEADER_NAME,jwt);
	}
	

	

	

}
