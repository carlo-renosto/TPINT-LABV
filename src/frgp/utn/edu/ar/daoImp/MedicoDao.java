package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import frgp.utn.edu.ar.dao.ConfigHibernate;
import frgp.utn.edu.ar.dao.IMedicoDao;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Usuario;

@Repository
public class MedicoDao implements IMedicoDao {
	private ConfigHibernate config;

	public MedicoDao(ConfigHibernate config) {
		this.config = config;
	}

	@Override
	public Medico traerMedico(int legajo) {
		config.abrirSesion();
		Transaction tx = config.getSession().beginTransaction();
		
		Query query = config.getSession().createQuery("FROM Medico WHERE legajo = :legajo AND estado = 1");
		query.setParameter("legajo", legajo);
		
		Medico medico = (Medico) query.uniqueResult();
		
		tx.commit();
		if(medico != null) {
			return medico;
		} 
		else {
			System.err.println("Medico no encontrado (error). No hay medicos con el legajo " + legajo + " en el sistema.");
			return null;
		}
	}
	
	@Override
	public Medico traerMedicoSegunUsuario(String usuario) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
		
			Query query = config.getSession().createQuery("FROM Medico WHERE usuario_c = :usuario AND estado = 1");
			query.setParameter("usuario", usuario);
			
			Medico medico = (Medico) query.uniqueResult();
			
			tx.commit();
			if(medico != null) {
				return medico;
			} 
			else {
				System.err.println("Medico no encontrado (error). No hay medicos con el usuario " + usuario + " en el sistema.");
				return null;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Medico no encontrado (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public List<Medico> traerMedicos() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			List<Medico> medicos = session.createQuery("FROM Medico WHERE estado = 1").list();
			tx.commit();
			
			if(!medicos.isEmpty()) {
				return medicos;
			}
			else {
				System.err.println("Medicos no encontrados (error). No hay medicos registrados en el sistema.");
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
	
	@Override
	public List<Medico> traerMedicosFiltro(String legajo, String nombre, String apellido, String sexo , String direccion, String localidad, String provincia, String email, String telefono, String dia, String horarioInicio, String horarioFin, String especialidad) {
	    StringBuilder query = new StringBuilder("FROM Medico WHERE estado = 1");

	    if(legajo != null && !legajo.isEmpty()) query.append(" AND legajo = :legajo");
	    if(nombre != null && !nombre.isEmpty()) query.append(" AND nombre LIKE :nombre");
	    if(apellido != null && !apellido.isEmpty()) query.append(" AND apellido LIKE :apellido");
	    if(sexo != null && !sexo.isEmpty()) query.append(" AND sexo LIKE :sexo");
	    if(direccion != null && !direccion.isEmpty()) query.append(" AND direccion LIKE :direccion");
	    if(provincia != null && !provincia.isEmpty()) query.append(" AND provincia LIKE :provincia");
	    if(localidad != null && !localidad.isEmpty()) query.append(" AND localidad LIKE :localidad");
	    if(email != null && !email.isEmpty()) query.append(" AND email LIKE :email");
	    if(telefono != null && !telefono.isEmpty()) query.append(" AND telefono LIKE :telefono");
	    if(dia != null && !dia.isEmpty()) query.append(" AND dia LIKE :dia");
	    if(horarioInicio != null && !horarioInicio.isEmpty()) query.append(" AND horario_inicio >= :horarioInicio");
	    if(horarioFin != null && !horarioFin.isEmpty()) query.append(" AND horario_fin <= :horarioFin");
	    if(especialidad != null && !especialidad.isEmpty()) query.append(" AND especialidad.id = :especialidad");

	    Transaction tx = null;
	    try {
	        Session session = config.abrirSesion();
	        tx = session.beginTransaction();
	        
	        Query hqlQuery = session.createQuery(query.toString());

	        if(legajo != null && !legajo.isEmpty()) hqlQuery.setParameter("legajo", Integer.parseInt(legajo));
	        if(nombre != null && !nombre.isEmpty()) hqlQuery.setParameter("nombre", "%" + nombre + "%");
	        if(apellido != null && !apellido.isEmpty()) hqlQuery.setParameter("apellido", "%" + apellido + "%");
	        if(sexo != null && !sexo.isEmpty()) hqlQuery.setParameter("sexo", "%" + sexo + "%");
	        if(direccion != null && !direccion.isEmpty()) hqlQuery.setParameter("direccion", "%" + direccion + "%");   
	        if(provincia != null && !provincia.isEmpty()) hqlQuery.setParameter("provincia", "%" + provincia + "%");
	        if(localidad != null && !localidad.isEmpty()) hqlQuery.setParameter("localidad", "%" + localidad + "%");	        
	        if(email != null && !email.isEmpty()) hqlQuery.setParameter("email", "%" + email + "%");
	        if(telefono != null && !telefono.isEmpty()) hqlQuery.setParameter("telefono", "%" + telefono + "%");
	        if(dia != null && !dia.isEmpty()) hqlQuery.setParameter("dia", "%" + dia + "%");
	        if(horarioInicio != null && !horarioInicio.isEmpty()) hqlQuery.setParameter("horarioInicio", horarioInicio);
	        if(horarioFin != null && !horarioFin.isEmpty()) hqlQuery.setParameter("horarioFin", horarioFin);
	        if(especialidad != null && !especialidad.isEmpty()) hqlQuery.setParameter("especialidad", especialidad);

	        List<Medico> medicos = hqlQuery.list();
	        tx.commit();
	        
	        if(!medicos.isEmpty()) {
	            return medicos;
	        } 
	        else {
	            System.err.println("Medicos no encontrados (error). No hay medicos registrados en el sistema.");
	            return null;
	        }
	    } 
	    catch (Exception e) {
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
	
	@Override
	public List<Medico> traerMedicosSegunEspecialidad(String especialidad) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM Medico WHERE especialidad.id = :especialidad AND estado = 1");
			query.setParameter("especialidad", especialidad);
			
			List<Medico> medicos = query.list();
			tx.commit();
			
			if(!medicos.isEmpty()) {
				return medicos;
		    } 
			else {
				System.err.println("Medicos no encontrados (error). No hay medicos con esa especialidad registrados en el sistema.");
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
	
	@Override
	public boolean altaMedico(Medico medico) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
					
			session.save(medico);
			tx.commit();
			
			System.out.println("Alta de medico completada.");
			return true;
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Alta de medico fallida (error)." + e.getMessage());
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean modificarMedico(Medico medico) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Medico medicoAux = (Medico) session.get(Medico.class, medico.getLegajo());
	
			if(medicoAux != null && medicoAux.isEstado()) {
				BeanUtils.copyProperties(medico, medicoAux);
				session.update(medicoAux);

				tx.commit();
				
				System.out.println("Modificacion de medico completada.");
				return true;
			} 
			else {
				System.err.println("Modificacion de medico fallida (error). Ningun medico registrado con el legajo.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Modificacion de medico fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean bajaMedico(String legajo) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Medico medico = (Medico) session.get(Medico.class, Integer.parseInt(legajo));
	
			if(medico != null) {
				if(medico.isEstado()) {
					Usuario usuario = null;
					if(medico.getUsuario() != null) {
						usuario = (Usuario) session.get(Usuario.class, medico.getUsuario().getUsuario());
					}
					
					medico.setEstado(false);
					session.update(medico);
					
					if(usuario.getUsuario() != null && !usuario.getUsuario().isEmpty()) {
						usuario.setEstado(false);
						session.update(usuario);
					}
					
					tx.commit();
					
					System.out.println("Baja de medico completada.");
					return true;
				}
				else {
					System.err.println("Baja de medico fallida (error). Medico ya dado de baja.");
					return false;
				}
			} 
			else {
				System.err.println("Baja de medico fallida (error). Ningun medico registrado con el legajo.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Baja de medico fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean listarMedicos() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			List<Medico> medicos = session.createQuery("FROM Medico WHERE estado = 1").list();
			tx.commit();
	
			if(!medicos.isEmpty()) {
				System.out.println("Lista de medicos:");
				for (Medico medico : medicos) {
					System.out.println(medico.toString());
				}
				return true;
			} 
			else {
				System.err.println("Listado de medicos fallido (error). No hay medicos registrados en el sistema.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Listado de medicos fallido (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
}
