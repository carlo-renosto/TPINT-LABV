package frgp.utn.edu.ar.negocioImp;


import java.util.List;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.daoImp.EspecialidadDao;
import frgp.utn.edu.ar.entidad.Especialidad;
import frgp.utn.edu.ar.negocio.IEspecialidadNegocio;

@Service
public class EspecialidadNegocio implements IEspecialidadNegocio {
	private EspecialidadDao dao;
	  
	public EspecialidadNegocio(EspecialidadDao dao) {
		this.dao = dao;
	}

	@Override
	public Especialidad traerEspecialidad(String id) {
		return dao.traerEspecialidad(id);
	}
		
	@Override
	public List<Especialidad> traerEspecialidades() {
		return dao.traerEspecialidades();
	}
}
	

