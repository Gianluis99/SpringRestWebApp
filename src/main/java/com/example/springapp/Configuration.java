package com.example.springapp;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import com.example.springapp.repository.ClienteRepository;
import com.example.springapp.utility.Consts;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class Configuration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// metodo per abilitare la sicurezza sui path dei metodi specifici
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// .authenticated()) chiamato da hasrole
		http.authorizeHttpRequests(request -> request.requestMatchers("**").hasRole(Consts.USER))
				.csrf(csrf -> csrf.disable())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

//	@Bean
//	SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(request -> request
//				.requestMatchers("/metodiAdmin/**")
//				.hasRole(Consts.ADMIN))
//				.httpBasic(Customizer.withDefaults());
//		return http.build();
//	}

	// tramite questo metodo creiamo un oggetto tramite UserDetails e lo inseriamo
	// in memoria
	// utile ai fini di test
	@Bean
	public UserDetailsService user1(PasswordEncoder pwdEncoder) {

		User.UserBuilder users = User.builder();

		UserDetails gino = users.username("gino").password(pwdEncoder.encode("123")).roles(Consts.USER).build();
		UserDetails admin = users.username(Consts.ADMIN).password(pwdEncoder.encode(Consts.ADMIN)).roles(Consts.ADMIN)
				.build();

		return new InMemoryUserDetailsManager(gino, admin);
	}

}
