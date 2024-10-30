package frgp.utn.edu.ar.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="pass")
	private String contrasenia;

	@OneToOne(mappedBy="usuario", fetch=FetchType.EAGER)
	private Medico medico;
	
	@Column(name="admin")
	private boolean admin;
	
	@Column(name="estado")
	private boolean estado;
	
	public Usuario() {
		
	}
	
	public Usuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Usuario(String usuario, String contrasenia, boolean admin) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.admin = admin;
		this.estado = true;
	}
	
	public Usuario(String usuario, String contrasenia, Medico medico, boolean admin) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.medico = medico;
		this.admin = admin;
		this.estado = true;
	}
	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public void set(String usuario, String contrasenia, boolean admin) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.admin = admin;
		this.estado = true;
	}
	
	public void limpiar() {
		this.usuario = "";
		this.contrasenia = "";
		this.admin = false;
		this.estado = false;
	}

	@Override
	public String toString() {
		String mensaje = "Usuario [usuario=" + usuario + ", contrasenia=" + contrasenia + ", legajo medico=";
		if(this.medico != null) {
			mensaje += this.medico.getLegajo() + "]";
		}
		else {
			mensaje += "-]";
		}
		
		return mensaje;
	}
}
