package com.example.springapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.springapp.model.Cliente;
import com.example.springapp.repository.CenaRepository;
import com.example.springapp.repository.ClienteRepository;
import com.example.springapp.repository.PremioRepository;
import com.example.springapp.repository.RistoranteRepository;

@org.springframework.stereotype.Service
public class Service {

	private final CenaRepository cenaRepository;
	private final RistoranteRepository ristoranteRepository;
	private final ClienteRepository clienteRepository;
	private final PremioRepository premioRepository;

	public Service(CenaRepository cenaRepository, RistoranteRepository ristoranteRepository,
			ClienteRepository clienteRepository, PremioRepository premioRepository) {
		this.cenaRepository = cenaRepository;
		this.ristoranteRepository = ristoranteRepository;
		this.clienteRepository = clienteRepository;
		this.premioRepository = premioRepository;
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> getAllClienti() {
		return clienteRepository.findAll();

	}

	public Cliente getClienteByID(Long id) {
		Optional<Cliente> cl = clienteRepository.findById(id);
		if (cl.isPresent())
			return cl.get();

		return null;

	}

	public boolean clienteExists(Long id) {
		Optional<Cliente> cl = clienteRepository.findById(id);
		if (cl.isPresent())
			return true;

		return false;

	}
	
	public Page<Cliente> getAllClienti(Pageable pageable) {
        // myDataRepository.findAll restituisce un Page<MyData>
        return clienteRepository.findAll(pageable);
    }

	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);

	}

	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);

	}

	public List<Cliente> findAll(PageRequest of) {
		return clienteRepository.findAll();

	}

	public CenaRepository getCenaRepository() {
		return cenaRepository;
	}

	public RistoranteRepository getRistoranteRepository() {
		return ristoranteRepository;
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public PremioRepository getPremioRepository() {
		return premioRepository;
	}



	
}
