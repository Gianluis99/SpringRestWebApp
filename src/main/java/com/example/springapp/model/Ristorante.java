package com.example.springapp.model;

import java.util.Objects;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Ristorante")
public class Ristorante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;

	@Column
	private String indirizzo;

	@Column(columnDefinition = "INTEGER")
	private int stelle;

	public Ristorante(String nome, String indirizzo, int stelle) {
		super();
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.stelle = stelle;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getStelle() {
		return stelle;
	}

	public void setStelle(int stelle) {
		this.stelle = stelle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(indirizzo, nome, stelle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ristorante other = (Ristorante) obj;
		return Objects.equals(indirizzo, other.indirizzo) && Objects.equals(nome, other.nome) && stelle == other.stelle;
	}

	@Override
	public String toString() {
		return "Ristorante [nome=" + nome + ", indirizzo=" + indirizzo + ", stelle=" + stelle + "]";
	}

}
