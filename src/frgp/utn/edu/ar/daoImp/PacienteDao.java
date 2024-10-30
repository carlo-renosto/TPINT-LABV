package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import frgp.utn.edu.ar.dao.ConfigHibernate;
import frgp.utn.edu.ar.dao.IPacienteDao;
import frgp.utn.edu.ar.entidad.Paciente;

@Repository
public class PacienteDao implements IPacienteDao {
	private ConfigHibernate config;

	public PacienteDao(ConfigHibernate config) {
		this.config = config;
	}
	
	@Override
	public Paciente traerPaciente(String dni) {
		config.abrirSesion();
		Transaction tx = config.getSession().beginTransaction();
		
		Query query = config.getSession().createQuery("FROM Paciente WHERE dni = :dni AND estado = 1");
		query.setParameter("dni", dni);
		
		Paciente paciente = (Paciente) query.uniqueResult();
		
		tx.commit();
		if(paciente != null) {
			return paciente;
		} 
		else {
			System.err.println("Paciente no encontrado (error). No hay pacientes con el DNI " + dni + " en el sistema.");
			return null;
		}
	}
	
	@Override
	public List<Paciente> traerPacientes() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			List<Paciente> pacientes = session.createQuery("FROM Paciente WHERE estado = 1").list();
			tx.commit();
			
			if(!pacientes.isEmpty()) {
				return pacientes;
			}
			else {
				System.err.println("Pacientes no encontrados (error). No hay pacientes registrados en el sistema.");
				return null;
			}
			
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Pacientes no encontrados (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public List<Paciente> traerPacientesFiltro(String dni, String nombre, String apellido, String telefono, String direccion, String localidad, String provincia, String email) {
	    StringBuilder query = new StringBuilder("FROM Paciente WHERE estado = 1");

	    if(dni != null && !dni.isEmpty()) query.append(" AND dni = :dni");
	    if(nombre != null && !nombre.isEmpty()) query.append(" AND nombre LIKE :nombre");
	    if(apellido != null && !apellido.isEmpty()) query.append(" AND apellido LIKE :apellido");
	    if(telefono != null && !telefono.isEmpty()) query.append(" AND telefono LIKE :telefono");
	    if(direccion != null && !direccion.isEmpty()) query.append(" AND direccion LIKE :direccion");
	    if(localidad != null && !localidad.isEmpty()) query.append(" AND localidad LIKE :localidad");
	    if(provincia != null && !provincia.isEmpty()) query.append(" AND provincia LIKE :provincia");
	    if(email != null && !email.isEmpty()) query.append(" AND email LIKE :email");

	    Transaction tx = null;
	    try {
	        Session session = config.abrirSesion();
	        tx = session.beginTransaction();
	        
	        Query hqlQuery = session.createQuery(query.toString());

	        if(dni != null && !dni.isEmpty()) hqlQuery.setParameter("dni", dni);
	        if(nombre != null && !nombre.isEmpty()) hqlQuery.setParameter("nombre", "%" + nombre + "%");
	        if(apellido != null && !apellido.isEmpty()) hqlQuery.setParameter("apellido", "%" + apellido + "%");
	        if(telefono != null && !telefono.isEmpty()) hqlQuery.setParameter("telefono", "%" + telefono + "%");
	        if(direccion != null && !direccion.isEmpty()) hqlQuery.setParameter("direccion", "%" + direccion + "%");
	        if(localidad != null && !localidad.isEmpty()) hqlQuery.setParameter("localidad", "%" + localidad + "%");
	        if(provincia != null && !provincia.isEmpty()) hqlQuery.setParameter("provincia", "%" + provincia + "%");
	        if(email != null && !email.isEmpty()) hqlQuery.setParameter("email", "%" + email + "%");

	        List<Paciente> pacientes = hqlQuery.list();
	        tx.commit();
	        
	        if(!pacientes.isEmpty()) {
	            return pacientes;
	        } 
	        else {
	            System.err.println("Pacientes no encontrados (error). No hay pacientes registrados en el sistema.");
	            return null;
	        }
	    } 
	    catch (Exception e) {
	        if(tx != null) {
	            tx.rollback();
	        }
	        
	        System.err.println("Pacientes no encontrados (error).");
	        return null;
	    } 
	    finally {
	        config.cerrarSession();
	    }
	}

	@Override
	public boolean altaPaciente(Paciente paciente) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			session.save(paciente);
			tx.commit();
			
			System.out.println("Alta de paciente completada.");
			return true;
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Alta de paciente fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean modificarPaciente(Paciente paciente) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Paciente pacienteAux = (Paciente) session.get(Paciente.class, paciente.getDni());
	
			if(pacienteAux != null && pacienteAux.isEstado()) {
				BeanUtils.copyProperties(paciente, pacienteAux);
	
				session.update(pacienteAux);
				tx.commit();
				
				System.out.println("Modificacion de paciente completada.");
				return true;
			} 
			else {
				System.err.println("Modificacion de paciente fallida (error). Ningun paciente registrado con el DNI.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Modificacion de paciente fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean bajaPaciente(String dni) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Paciente paciente = (Paciente) session.get(Paciente.class, dni);
	
			if(paciente != null) {
				if(paciente.isEstado()) {
					paciente.setEstado(false);
					
					session.update(paciente);
					tx.commit();
					
					System.out.println("Baja de paciente completada.");
					return true;
				}
				else {
					System.err.println("Baja de paciente fallida (error). Paciente ya dado de baja.");
					return false;
				}
			} 
			else {
				System.err.println("Baja de paciente fallida (error). Ningun paciente registrado con el DNI.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Baja de paciente fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean listarPacientes() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			List<Paciente> pacientes = session.createQuery("FROM Paciente WHERE estado = 1").list();
			tx.commit();
	
			if(!pacientes.isEmpty()) {
				System.out.println("Lista de pacientes:");
				for (Paciente paciente : pacientes) {
					System.out.println(paciente.toString());
				}
				return true;
			} 
			else {
				System.err.println("Listado de pacientes fallido (error). No hay pacientes registrados en el sistema.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Listado de pacientes fallido (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
}
