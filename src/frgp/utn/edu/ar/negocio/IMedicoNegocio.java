package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Medico;

public interface IMedicoNegocio {
	public Medico traerMedico(int legajo);
	
	public Medico traerMedicoSegunUsuario(String usuario);
	
	public List<Medico> traerMedicos();
	
	public List<Medico> traerMedicosFiltro(String legajo, String nombre, String apellido, String sexo, String direccion, String localidad, String provincia, String email, String telefono, String dia, String horarioInicio, String horarioFin, String especialidad);
	
	public List<Medico> traerMedicosSegunEspecialidad(String especialidad);
	
	public boolean altaMedico(Medico medico);
	
	public boolean modificarMedico(Medico medico);
	
	public boolean bajaMedico(String legajo);
	
	public boolean listarMedicosBidireccion();
}
