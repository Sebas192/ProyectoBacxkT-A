package com.app.web.modelos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "Contrato")
public class Contrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdContrato;

	@Column(name = "NombreContrato", nullable = false)
	private String NombreContrato;


	@Column(name = "Fecha_inicio")
	private Date Fecha_inicio;

	@Column(name = "Fecha_final")
 	private Date Fecha_final;

	@OneToOne
	@JoinColumn(name = "IdUsuario")
	private Usuario usuario;

	public Contrato() {
		super();
	}

	public Contrato(Long idContrato, String nombreContrato, Date fecha_inicio, Date fecha_final, Usuario usuario) {
		IdContrato = idContrato;
		NombreContrato = nombreContrato;
		Fecha_inicio = fecha_inicio;
		Fecha_final = fecha_final;
		this.usuario = usuario;
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

	public Date getFecha_inicio() {
		return Fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		Fecha_inicio = fecha_inicio;
	}

	public Date getFecha_final() {
		return Fecha_final;
	}

	public void setFecha_final(Date fecha_final) {
		Fecha_final = fecha_final;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Contrato{" +
				"IdContrato=" + IdContrato +
				", NombreContrato='" + NombreContrato + '\'' +
				", Fecha_inicio=" + Fecha_inicio +
				", Fecha_final=" + Fecha_final +
				", usuario=" + usuario +
				'}';
	}
}

