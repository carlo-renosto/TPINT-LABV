package frgp.utn.edu.ar.dao;

import java.util.List;

import frgp.utn.edu.ar.entidad.Especialidad;

public interface IEspecialidadDao {
	public Especialidad traerEspecialidad(String id);
	
	public List<Especialidad> traerEspecialidades();
}
