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
public class Premio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nomePremio;
	
	@Column
	private Long prezzoPremio;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Premio(String nomePremio, Long prezzoPremio, Cliente cliente) {
		super();
		this.nomePremio = nomePremio;
		this.prezzoPremio = prezzoPremio;
		this.cliente=cliente;
	}

	public String getNomePremio() {
		return nomePremio;
	}

	public void setNomePremio(String nomePremio) {
		this.nomePremio = nomePremio;
	}

	public Long getPrezzoPremio() {
		return prezzoPremio;
	}

	public void setPrezzoPremio(Long prezzoPremio) {
		this.prezzoPremio = prezzoPremio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomePremio, prezzoPremio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Premio other = (Premio) obj;
		return Objects.equals(nomePremio, other.nomePremio) && Objects.equals(prezzoPremio, other.prezzoPremio);
	}

	@Override
	public String toString() {
		return "Premio [nomePremio=" + nomePremio + ", prezzoPremio=" + prezzoPremio + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
