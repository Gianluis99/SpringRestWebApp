package com.example.springapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.springapp.model.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Long>,  PagingAndSortingRepository<Cliente, Long> {
	
	public List<Cliente> findAll();

}
