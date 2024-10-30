package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.daoImp.TurnoDao;
import frgp.utn.edu.ar.entidad.Turno;
import frgp.utn.edu.ar.negocio.ITurnoNegocio;

@Service
public class TurnoNegocio implements ITurnoNegocio {
	private TurnoDao dao;

	public TurnoNegocio(TurnoDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Turno> traerTurnos() {
		return dao.traerTurnos();
	}
	
	@Override
	public List<Turno> traerTurnosFiltro(String id, String legajo, String especialidad, String dni, String dia, String horarioDesde, String horarioHasta, String estado) {
		return dao.traerTurnosFiltro(id, legajo, especialidad, dni, dia, horarioDesde, horarioHasta, estado);
	}
	
	@Override
	public List<Turno> traerTurnosReporte(String medico, String diaDesde, String diaHasta, String horarioDesde, String horarioHasta, String estado) {
		return dao.traerTurnosReporte(medico, diaDesde, diaHasta, horarioDesde, horarioHasta, estado);
	}

	@Override
	public List<Turno> traerTurnosSegunMedico(int legajo) {
		return dao.traerTurnosSegunMedico(legajo);
	}
	
	@Override
	public List<Integer> traerTurnosHorasMedico(int legajo) {
		return dao.traerTurnosHorasMedico(legajo);
	}
	
	@Override
	public boolean altaTurno(Turno turno) {
		return dao.altaTurno(turno);

	}
	
	@Override
	public boolean asignarTurno(Turno turno) {
		return dao.asignarTurno(turno);
	}

	@Override
	public boolean modificarTurno(Turno turno) {
		return dao.modificarTurno(turno);

	}

	@Override
	public boolean bajaTurno(Long id) {
		return dao.bajaTurno(id);

	}
	
	@Override
	public boolean marcarTurno(Long id, String estadoTurno) {
		return dao.marcarTurno(id, estadoTurno);
	}
	
	@Override
	public boolean marcarObservacion(Long id, String observacion) {
		return dao.marcarObservacion(id, observacion);
	}

	@Override
	public List<Turno> listarTurnos() {
		return dao.listarTurnos();
	}

}
