package com.example.springapp;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springapp.model.Cliente;

//http://localhost:8080/metodiAdmin/richiestaTuttiClienti

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/metodiAdmin")
public class RestControllerAdmin {

	private final Service service;

	public RestControllerAdmin(Service service) {
		this.service = service;
	}

	@GetMapping("/richiestaTuttiClienti")
	public ResponseEntity<List<Cliente>> getReq() {

		return ResponseEntity.ok(service.getAllClienti());
	}
	
	@GetMapping("/richiestaAdmin")
	public ResponseEntity<String> getAdmin() {
		return  ResponseEntity.ok("Sei un ADMIN");
	}

}
