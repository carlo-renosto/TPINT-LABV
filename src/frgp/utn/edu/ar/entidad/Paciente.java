package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Pacientes")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DNI")
	private String dni;

	@Column(name="Nombre")
	private String nombre;

	@Column(name="Apellido")
	private String apellido;

	@Column(name="Telefono")
	private String telefono;

	@Column(name="Direccion")
	private String direccion;

	@Column(name="Localidad")
	private String localidad;

	@Column(name="Provincia")
	private String provincia;

	@Column(name="FechaNac")
	private String fechaNac;

	@Column(name="Email")
	private String email;

	@Column(name="estado")
	private boolean estado;

	@OneToMany(mappedBy="paciente")
	private Set<Turno> turnos;

	public Paciente() {

	}

	public Paciente(String dni) {
		this.dni = dni;
	}
	
	public Paciente(String dni, String nombre, String apellido, String telefono, String direccion, String localidad, String provincia, String fechaNac, String email) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.fechaNac = fechaNac;
		this.email = email;
		this.estado = true;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(Set<Turno> turnos) {
		this.turnos = turnos;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public void set(String dni, String nombre, String apellido, String telefono, String direccion, String localidad, String provincia, String fechaNac, String email) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.fechaNac = fechaNac;
		this.email = email;
		this.estado = true;
	}
	
	public void limpiar() {
		this.dni = "";
		this.nombre = "";
		this.apellido = "";
		this.telefono = "";
		this.direccion = "";
		this.localidad = "";
		this.provincia = "";
		this.fechaNac = "";
		this.email = "";
		this.estado = false;
		this.turnos = null;
	}

	@Override
	public String toString() {
		return "Paciente [DNI=" + dni + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Telefono=" + telefono
				+ ", Direccion=" + direccion + ", Localidad=" + localidad + ", Provincia=" + provincia + ", FechaNac="
				+ fechaNac + ", Email=" + email + "]";
	}
}
