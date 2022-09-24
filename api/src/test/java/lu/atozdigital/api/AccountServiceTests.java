package lu.atozdigital.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lu.atozdigital.api.model.Utilisateur;
import lu.atozdigital.api.service.AccountService;


@SpringBootTest
class AccountServiceTests {

	@Autowired
	private AccountService accountService;

	@Test
	void saveUserTest() {
		accountService.saveUser(new Utilisateur(null, "jamalnoman", "jamalnoman@gmail.com",  "aaaaaaaa"));
		assertThat(accountService.loadUserByUsername("jamalnoman")).isNotNull();
	}

}
