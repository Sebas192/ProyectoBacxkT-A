package com.app.web.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Contrato")
public class Contrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdContrato;

	@Column(name = "NombreContrato", nullable = false, length = 20)
	private String NombreContrato;
	@Column(name = "Periodo", nullable = false, length = 20)
	private String Periodo;

	public Contrato() {
		super();
	}

	public Contrato(Long idContrato, String nombreContrato, String periodo) {
		super();
		IdContrato = idContrato;
		NombreContrato = nombreContrato;
		Periodo = periodo;
	}

	public Long getIdContrato() {
		return IdContrato;
	}

	public void setIdContrato(Long idContrato) {
		IdContrato = idContrato;
	}

	public String getNombreContrato() {
		return NombreContrato;
	}

	public void setNombreContrato(String nombreContrato) {
		NombreContrato = nombreContrato;
	}

	public String getPeriodo() {
		return Periodo;
	}

	public void setPeriodo(String periodo) {
		Periodo = periodo;
	}

	@Override
	public String toString() {
		return "Contrato [IdContrato=" + IdContrato + ", NombreContrato=" + NombreContrato + ", Periodo=" + Periodo
				+ "]";
	}

}