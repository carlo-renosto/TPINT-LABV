package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.daoImp.MedicoDao;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.negocio.IMedicoNegocio;

@Service
public class MedicoNegocio implements IMedicoNegocio {
	private MedicoDao dao;
	
	public MedicoNegocio(MedicoDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Medico traerMedico(int legajo) {
		return dao.traerMedico(legajo);
	}
	
	@Override 
	public Medico traerMedicoSegunUsuario(String usuario) {
		return dao.traerMedicoSegunUsuario(usuario);
	}
	
	@Override
	public List<Medico> traerMedicos() {
		return dao.traerMedicos();
	}
	
	@Override
	public List<Medico> traerMedicosFiltro(String legajo, String nombre, String apellido, String sexo, String direccion, String localidad, String provincia, String email, String telefono, String dia, String horarioInicio, String horarioFin, String especialidad) {
		return dao.traerMedicosFiltro(legajo, nombre, apellido, sexo, direccion, localidad, provincia, email, telefono, dia, horarioInicio, horarioFin, especialidad);
	}
	
	@Override
	public List<Medico> traerMedicosSegunEspecialidad(String especialidad) {
		return dao.traerMedicosSegunEspecialidad(especialidad);
	}
	
	@Override
	public boolean altaMedico(Medico medico) {
		return dao.altaMedico(medico);
	}

	@Override
	public boolean modificarMedico(Medico medico) {
		return dao.modificarMedico(medico);
	}

	@Override
	public boolean bajaMedico(String legajo) {
		return dao.bajaMedico(legajo);
	}

	@Override
	public boolean listarMedicosBidireccion() {
		return dao.listarMedicos();
	}
}
