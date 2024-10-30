package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.daoImp.PacienteDao;
import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.negocio.IPacienteNegocio;

@Service
public class PacienteNegocio implements IPacienteNegocio {
	private PacienteDao dao;
	
	public PacienteNegocio(PacienteDao dao) {
		this.dao = dao;
	}
	
	public List<Paciente> traerPacientes() {
		return dao.traerPacientes();
	}
	
	@Override
	public Paciente traerPaciente(String dni) {
		return dao.traerPaciente(dni);
	}
	
	@Override
	public List<Paciente> traerPacientesFiltro(String dni, String nombre, String apellido, String telefono, String direccion, String localidad, String provincia, String email) {
		return dao.traerPacientesFiltro(dni, nombre, apellido, telefono, direccion, localidad, provincia, email);
	}
	
	@Override
	public boolean altaPaciente(Paciente paciente) {
		return dao.altaPaciente(paciente);
	}

	@Override
	public boolean modificarPaciente(Paciente paciente) {
		return dao.modificarPaciente(paciente);
	}

	@Override
	public boolean bajaPaciente(String dni) {
		return dao.bajaPaciente(dni);
	}

	@Override
	public boolean listarPacientes() {
		return dao.listarPacientes();
	}

}
