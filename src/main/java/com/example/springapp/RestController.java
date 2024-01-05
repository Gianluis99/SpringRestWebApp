package com.example.springapp;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springapp.model.Cliente;
import com.example.springapp.model.Ristorante;
//http://localhost:8080/metodi/richiesta

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/metodi")
public class RestController {

	private final Service service;

	public RestController(Service service) {
		this.service = service;
	}

	@GetMapping("/richiesta")
	public ResponseEntity<String> getReq() {
		return ResponseEntity.ok("Eccoti la req ");
	}

	@GetMapping("/richiestaCliente/{id}")
	public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
		return ResponseEntity.ok(service.getClienteByID(id));
	}

	@GetMapping("/aggiungiClienteBasic")
	public ResponseEntity<Void> saveClienteBasic() {
		Cliente cliente = service.saveCliente(new Cliente("gianni", "pino"));
		return ResponseEntity.ok(null);

	}
	@GetMapping("/aggiungiClientePath/{nome}/{cognome}")
	public ResponseEntity<String> saveClienteBasic(@PathVariable String nome,@PathVariable String cognome) {
		Cliente cliente = service.saveCliente(new Cliente(nome, cognome));
		System.out.println(cliente);
		return ResponseEntity.ok(cliente.toString());

	}

	@PostMapping("/aggiungiCliente")
	public ResponseEntity<Void> saveCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucb) {
		Cliente clienteSaved = service.saveCliente(cliente);

		URI locationNewClient = ucb.path("metodi/richiestaCliente").buildAndExpand(clienteSaved.getId()).toUri();
		return ResponseEntity.created(locationNewClient).build();
	}


	@GetMapping("/richiestaRistorante/{nome}")
	public ResponseEntity<Ristorante> getRistorante(@PathVariable String nome) {
		Ristorante ristorante = new Ristorante(nome, "via rossi 1", 2);
		return ResponseEntity.ok(ristorante);
	}

	@GetMapping("/richiestaRistoranteGenerale")
	public ResponseEntity<Ristorante> getRistoranteGenerale() {
		Ristorante ristorante = new Ristorante("Ristorante del popolo", "via rossi 1", 2);
		return ResponseEntity.ok(ristorante);
	}

	@PutMapping("/aggiornaCliente/{id}")
	public ResponseEntity<Void> updateCliente(@PathVariable Long id, @RequestBody Cliente cl, Principal principal) {
		System.out.println("SONO IN AGGIORNACLIENTE - PRINCIPAL: " + principal);

		if (service.clienteExists(id)) {
			System.out.println("ID TROVATO FACCIO UPDATE");
			service.updateCliente(cl);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/deleteCliente/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id, Principal principal) {
		System.out.println("PRINCIPAL: " + principal);

		if (service.clienteExists(id)) {
			System.out.println("ID TROVATO FACCIO DELETE");
			service.deleteCliente(id);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();

	}
	
	//creiamo una paginazione in base ai dati ricevuti dalla pageable che si crea in automatico
	@GetMapping("/getAllClienti")
	private ResponseEntity<List<Cliente>> findAll(Pageable pageable) {
		Page<Cliente> page = (Page<Cliente>) service.getAllClienti(
				PageRequest.of(
				pageable.getPageNumber(), 
				pageable.getPageSize(),
				//inseriamo anche su quale elemento vogliamo ordinare
				pageable.getSortOr(Sort.by(Sort.Direction.ASC,"id"))));
		
		System.out.println(page);

		return ResponseEntity.ok(page.getContent());

	}
}
