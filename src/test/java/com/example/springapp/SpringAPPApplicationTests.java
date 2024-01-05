package com.example.springapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import com.example.springapp.model.Cliente;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringAPPApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void Test() {
		ResponseEntity<String> response = restTemplate.withBasicAuth("gino", "123").getForEntity("/metodi/richiesta",
				String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void TestSecurity2() {
		ResponseEntity<String> response = restTemplate.withBasicAuth("gino", "123")
				.getForEntity("/metodiAdmin/richiestaTuttiClienti", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void TestAddCliente() {
		Cliente c = new Cliente("gianni", "pineo");

		ResponseEntity<Void> response = restTemplate.withBasicAuth("gino", "123")
				.postForEntity("/metodi/aggiungiCliente", c, void.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void TestUpdateCliente() {
		Cliente c = new Cliente("gianni", "pineo");
		restTemplate.withBasicAuth("gino", "123").postForEntity("/metodi/aggiungiCliente", c, void.class);

		c = new Cliente("giannia", "pineo");
		HttpEntity<Cliente> req = new HttpEntity<Cliente>(c);

		ResponseEntity<Void> response = restTemplate.withBasicAuth("gino", "123").exchange("/metodi/aggiornaCliente/1",
				HttpMethod.PUT, req, Void.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	//aggiungo dirtiesContext perchè senno elimina l'oggetto e gli altri test vanno in confusione
	@Test
	@DirtiesContext
	void TestDeleteCliente() {
		Cliente c = new Cliente("gianni", "pineo");
		restTemplate.withBasicAuth("gino", "123").postForEntity("/metodi/aggiungiCliente", c, void.class);

		ResponseEntity<Void> response = restTemplate.withBasicAuth("gino", "123").exchange("/metodi/deleteCliente/1",
				HttpMethod.DELETE, null, Void.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
