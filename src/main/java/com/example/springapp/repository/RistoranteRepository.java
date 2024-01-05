package com.example.springapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springapp.model.Ristorante;

public interface RistoranteRepository extends CrudRepository<Ristorante, Long> {

}
