package frgp.utn.edu.ar.dao;

import java.util.List;

import frgp.utn.edu.ar.entidad.Turno;

public interface ITurnoDao {
	public List<Turno> traerTurnos();
	
	public List<Turno> traerTurnosFiltro(String id, String legajo, String especialidad, String dni, String dia, String horarioDesde, String horarioHasta, String estado);
	
	public List<Turno> traerTurnosReporte(String medico, String diaDesde, String diaHasta, String horarioDesde, String horarioHasta, String estado);
	
	public List<Turno> traerTurnosSegunMedico(int legajo);
	
	public List<Integer> traerTurnosHorasMedico(int legajo);
	
	public boolean altaTurno(Turno turno);
	
	public boolean asignarTurno(Turno turno);

	public boolean modificarTurno(Turno turno);

	public boolean bajaTurno(Long id);
	
	public boolean marcarTurno(Long id, String estadoTurno);
	
	public boolean marcarObservacion(Long id, String observacion);
	
	public List<Turno> listarTurnos();
}
