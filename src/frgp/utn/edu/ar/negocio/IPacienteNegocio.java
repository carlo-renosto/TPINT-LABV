package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Paciente;

public interface IPacienteNegocio {
	public Paciente traerPaciente(String dni);
	
	public List<Paciente> traerPacientes();
	
	public List<Paciente> traerPacientesFiltro(String dni, String nombre, String apellido, String telefono, String direccion, String localidad, String provincia, String email);
	
	public boolean altaPaciente(Paciente paciente);
	
	public boolean modificarPaciente(Paciente paciente);
	
	public boolean bajaPaciente(String dni);
	
	public boolean listarPacientes();
}

