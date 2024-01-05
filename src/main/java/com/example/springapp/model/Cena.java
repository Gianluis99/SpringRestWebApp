package com.example.springapp.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cena {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long conto;

	@Column
	private int numPersone;
	@Column
	private int numPietanze;

	@ManyToOne
	@JoinColumn(name = "idRistorante")
	private Ristorante ristorante;

	public Cena(Long conto, int numPersone, int numPientanze, Ristorante ristorante) {
		super();
		this.conto = conto;
		this.numPersone = numPersone;
		this.numPietanze = numPientanze;
		this.setRistorante(ristorante);
	}

	public Long getConto() {
		return conto;
	}

	public void setConto(Long conto) {
		this.conto = conto;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}

	public int getNumPientanze() {
		return numPietanze;
	}

	public void setNumPientanze(int numPientanze) {
		this.numPietanze = numPientanze;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conto, numPersone, numPietanze);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cena other = (Cena) obj;
		return Objects.equals(conto, other.conto) && numPersone == other.numPersone && numPietanze == other.numPietanze;
	}

	@Override
	public String toString() {
		return "Cena [conto=" + conto + ", numPersone=" + numPersone + ", numPientanze=" + numPietanze + "]";
	}

	public Ristorante getRistorante() {
		return ristorante;
	}

	public void setRistorante(Ristorante ristorante) {
		this.ristorante = ristorante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
