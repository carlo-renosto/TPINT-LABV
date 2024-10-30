package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Especialidad;

public interface IEspecialidadNegocio {
	public Especialidad traerEspecialidad(String id);
	
	public List<Especialidad> traerEspecialidades();
}
