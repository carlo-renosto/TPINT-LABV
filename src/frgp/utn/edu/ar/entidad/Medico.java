package frgp.utn.edu.ar.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Medicos")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="legajo")
	private int legajo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="sexo")
	private String sexo;
	
	@Column(name="fecha_nac")
	private String fecha_nac;
	
	@Column(name="localidad")
	private String localidad;
	
	@Column(name="provincia")
	private String provincia;
	
	@Column(name="direccion")
	private String direccion; 
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="dia")
	private String dia;
	
	@Column(name="horario_inicio")
	private int horarioInicio;
	
	@Column(name="horario_fin")
	private int horarioFin;
	
	@OneToOne
	@JoinColumn(name="especialidad")
	private Especialidad especialidad;
	
	@OneToOne
	@JoinColumn(name="usuario_c")
	private Usuario usuario;
	
	@Column(name="estado")
	private boolean estado;
	
	public Medico() {
		
	}
	
	public Medico(int legajo) {
		this.legajo = legajo;
	}
	
	public Medico(int legajo, String nombre, String apellido) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.estado = true;
	}
	
	public Medico(int legajo, String nombre, String apellido, String sexo, String fecha_nac, String direccion, String localidad, String provincia, String email, String telefono, String dia, int horarioInicio, int horarioFin) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fecha_nac = fecha_nac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.email = email;
		this.telefono = telefono;
		this.dia = dia;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.estado = true;
	}
	
	public Medico(int legajo, String nombre, String apellido, String sexo, String fecha_nac, String direccion, String localidad, String provincia, String email, String telefono, String dia, int horarioInicio, int horarioFin, String especialidad) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fecha_nac = fecha_nac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.email = email;
		this.telefono = telefono;
		this.dia = dia;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.especialidad = new Especialidad(especialidad);
		this.estado = true;
	}
	
	public Medico(int legajo, String nombre, String apellido, String sexo, String fecha_nac, String direccion, String localidad, String provincia, String email, String telefono, String dia, int horarioInicio, int horarioFin, String especialidad, String usuario) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fecha_nac = fecha_nac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.email = email;
		this.telefono = telefono;
		this.dia = dia;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.especialidad = new Especialidad(especialidad);
		this.usuario = new Usuario(usuario);
		this.estado = true;
	}
	
	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
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
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public int getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(int horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public int getHorarioFin() {
		return horarioFin;
	}

	public void setHorarioFin(int horarioFin) {
		this.horarioFin = horarioFin;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public void set(int legajo, String nombre, String apellido, String sexo, String fecha_nac, String direccion, String localidad, String provincia, String email, String telefono, String dia, int horarioInicio, int horarioFin, String especialidad, String usuario) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fecha_nac = fecha_nac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.email = email;
		this.telefono = telefono;
		this.dia = dia;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.especialidad = new Especialidad(especialidad);
		this.usuario = new Usuario(usuario);
		this.estado = true;
	}
	
	public void limpiar() {
        this.legajo = 0;
        this.nombre = "";
        this.apellido = "";
        this.sexo = "";
        this.fecha_nac = "";
        this.localidad = "";
        this.provincia = "";
        this.direccion = "";
        this.email = "";
        this.telefono = "";
        this.dia = "";
        this.horarioInicio = 0;
        this.horarioFin = 0;
        this.especialidad = null;
        this.usuario = null;
        this.estado = false;
    }

	@Override
	public String toString() {
		String mensaje = "Medico [legajo=" + legajo + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", fecha_nac=" + fecha_nac + ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia + ", email="
				+ email + ", telefono=" + telefono + ", especialidad " + especialidad + ", usuario=";
		
		if(this.usuario != null) {
			mensaje += usuario +"]]";
		} else { mensaje += " - ]"; }

		return mensaje;
	}
}
