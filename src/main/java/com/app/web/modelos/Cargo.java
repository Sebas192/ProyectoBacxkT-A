package com.app.web.modelos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cargo")
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdCargo;
	@Column(name = "nombeCargo", nullable = false, length = 20)
	private String nombeCargo;

	public Cargo() {
		super();
	}

	public Cargo(Long idCargo, String nombeCargo) {
		IdCargo = idCargo;
		this.nombeCargo = nombeCargo;
	}

	public Long getIdCargo() {
		return IdCargo;
	}

	public void setIdCargo(Long idCargo) {
		IdCargo = idCargo;
	}

	public String getNombeCargo() {
		return nombeCargo;
	}

	public void setNombeCargo(String nombeCargo) {
		this.nombeCargo = nombeCargo;
	}

	@Override
	public String toString() {
		return "Cargo{" +
				"IdCargo=" + IdCargo +
				", nombeCargo='" + nombeCargo + '\'' +
				'}';
	}
}
