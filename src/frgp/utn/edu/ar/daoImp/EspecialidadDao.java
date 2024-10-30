package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import frgp.utn.edu.ar.dao.ConfigHibernate;
import frgp.utn.edu.ar.dao.IEspecialidadDao;
import frgp.utn.edu.ar.entidad.Especialidad;

@Repository
public class EspecialidadDao implements IEspecialidadDao {
    private ConfigHibernate config;
    
    public EspecialidadDao(ConfigHibernate config) {
        this.config = config;
    }
 
    @Override
	public Especialidad traerEspecialidad(String id) {
		config.abrirSesion();
		Transaction tx = config.getSession().beginTransaction();
		
		Query query = config.getSession().createQuery("FROM Especialidad WHERE id = :id AND estado = 1");
		query.setParameter("id", id);
		
		Especialidad esp = (Especialidad) query.uniqueResult();
		
		tx.commit();
		if(esp != null) {
			return esp;
		} 
		else {
			System.err.println("Especialidad no encontrada (error). No hay especialidades con la ID" + id + " en el sistema.");
			return null;
		}
	}
	
    @Override
	public List<Especialidad> traerEspecialidades() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			List<Especialidad> especialidades = session.createQuery("FROM Especialidad WHERE estado = 1").list();
			tx.commit();
			
			if(!especialidades.isEmpty()) {
				return especialidades;
			}
			else {
				System.err.println("Especialidades no encontradas (error). No hay especialidades registradas en el sistema.");
				return null;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Medicos no encontrados (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}
}
