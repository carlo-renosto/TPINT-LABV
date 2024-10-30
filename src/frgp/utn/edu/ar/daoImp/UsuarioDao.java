package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import frgp.utn.edu.ar.dao.ConfigHibernate;
import frgp.utn.edu.ar.dao.IUsuarioDao;
import frgp.utn.edu.ar.entidad.Usuario;

@Repository
public class UsuarioDao implements IUsuarioDao {
	private ConfigHibernate config;

	public UsuarioDao(ConfigHibernate config) {
		this.config = config;
	}
	
	@Override
	public Usuario traerUsuario(String usuarioNombre, String usuarioPass) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM Usuario WHERE usuario = :usuarioNombre AND pass = :usuarioPass AND estado = 1");
			query.setParameter("usuarioNombre", usuarioNombre);
			query.setParameter("usuarioPass", usuarioPass);
			
			Usuario usuario = (Usuario) query.uniqueResult();
			
			tx.commit();
			
			if(usuario != null) {
				return usuario;
			} 
			else {
				System.out.println("Usuario no encontrado (error). No hay usuarios con el nombre " + usuarioNombre + " o la contrasenia " + usuarioPass + " en el sistema.");
				return null;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Usuario no encontrado (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public Usuario traerUsuario2(String usuarioNombre) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM Usuario WHERE usuario = :usuarioNombre AND estado = 1");
			query.setParameter("usuarioNombre", usuarioNombre);
			
			Usuario usuario = (Usuario) query.uniqueResult();
			
			tx.commit();
			
			if(usuario != null) {
				return usuario;
			} 
			else {
				return null;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Usuario no encontrado (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public List<Usuario> traerUsuarios() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			List<Usuario> usuarios = session.createQuery("FROM Usuario WHERE estado = 1").list();
			tx.commit();
			
			if(!usuarios.isEmpty()) {
				return usuarios;
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
	public List<Usuario> traerUsuariosFiltro(String usuario, String usuarioPass, String admin) {
		StringBuilder query = new StringBuilder("FROM Usuario WHERE estado = 1");
			
		if(usuario != null && !usuario.isEmpty()) query.append(" AND usuario LIKE :usuario");
		if(usuarioPass != null && !usuarioPass.isEmpty()) query.append(" AND pass LIKE :usuarioPass");
		if(admin != null && !admin.isEmpty()) query.append(" AND admin = :admin");

		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
		        
			Query hqlQuery = session.createQuery(query.toString());

			if(usuario != null && !usuario.isEmpty()) hqlQuery.setParameter("usuario", "%" + usuario + "%");
			if(usuarioPass != null && !usuarioPass.isEmpty()) hqlQuery.setParameter("usuarioPass", "%" + usuarioPass + "%");
			if(admin != null && !admin.isEmpty()) hqlQuery.setParameter("admin", Boolean.parseBoolean(admin));

			List<Usuario> usuarios = hqlQuery.list();
			tx.commit();
		        
			if(!usuarios.isEmpty()) {
				return usuarios;
			} 
			else {
				System.err.println("Usuarios no encontrados (error). No hay usuarios registrados en el sistema.");
				return null;
			}
		} 
		catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		        
			System.err.println("Usuarios no encontrados (error)." + e.getMessage());
			return null;
		} 
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public boolean altaUsuario(Usuario usuario) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			session.save(usuario);
			tx.commit();
			
			System.out.println("Alta de usuario completada.");
			return true;
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Alta de usuario fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public boolean modificarUsuario(Usuario usuario) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Usuario usuarioAux = (Usuario) session.get(Usuario.class, usuario.getUsuario());
	
			if(usuarioAux != null) {
				BeanUtils.copyProperties(usuario, usuarioAux);
	
				session.update(usuarioAux);
				tx.commit();
				
				System.out.println("Modificacion de usuario completada.");
				return true;
			} 
			else {
				System.err.println("Modificacion de usuario fallida (error). No se encontro ningun usuario con el nombre proporcionado.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Modificacion de usuario fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public boolean bajaUsuario(String usuarioNombre) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Usuario usuario = (Usuario) session.get(Usuario.class, usuarioNombre);
	
			if(usuario != null) {
				usuario.setEstado(false);
				
				session.save(usuario);
				tx.commit();

				System.out.println("Baja de usuario completada.");
				return true;
			} 
			else {
				System.err.println("Baja de usuario fallida (error). No se encontro ningun usuario con el nombre proporcionado.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Baja de usuario fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean validarUsuario(String usuarioNombre, String usuarioPass) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM Usuario WHERE usuario = :usuarioNombre AND pass = :usuarioPass AND estado = 1");
			query.setParameter("usuarioNombre", usuarioNombre);
			query.setParameter("usuarioPass", usuarioPass);
			
			Usuario usuario = (Usuario) query.uniqueResult();
			
			tx.commit();
			
			return usuario != null;
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Validacion de usuario fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean validarUsuarioAdmin(String usuarioNombre) {
		Usuario usuario = this.traerUsuario2(usuarioNombre);
		
		return usuario.isAdmin();
	}

	@Override
	public boolean listarUsuarios() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			List<Usuario> usuarios = session.createQuery("FROM Usuario WHERE estado = 1").list();
			tx.commit();
			
			if(!usuarios.isEmpty()) {
				System.out.println("Lista de usuarios:");
				for (Usuario usuario : usuarios) {
					System.out.println(usuario.toString());
				}
				return true;
			} 
			else {
				System.err.println("Listado de usuarios fallido (error). No hay usuarios registrados en el sistema.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Listado de usuarios fallido (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
}
