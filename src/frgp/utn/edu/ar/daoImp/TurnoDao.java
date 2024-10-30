package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import frgp.utn.edu.ar.dao.ConfigHibernate;
import frgp.utn.edu.ar.dao.ITurnoDao;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.entidad.Turno;

@Repository
public class TurnoDao implements ITurnoDao {
	private ConfigHibernate config;

	public TurnoDao(ConfigHibernate config) {
		this.config = config;
	}
	
	@Override
	public List<Turno> traerTurnos() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM Turno WHERE estado = 1");
			
			List<Turno> turnos = query.list();
			tx.commit();
			
			if(!turnos.isEmpty()) {
				return turnos;
		    } 
			else {
				System.err.println("Turnos no encontrados (error). No hay turnos registrados en el sistema.");
				return null;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Turnos no encontrados (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public List<Turno> traerTurnosFiltro(String id, String legajo, String especialidad, String dni, String dia, String horarioDesde, String horarioHasta, String estado) {
		StringBuilder query = new StringBuilder("FROM Turno WHERE estado = 1");
		
		if(id != null && !id.isEmpty()) query.append(" AND id = :id");
		if(legajo != null && !legajo.isEmpty()) query.append(" AND medico_id = :legajo");
		if(dni != null && !dni.isEmpty()) query.append(" AND paciente_id = :dni");
		if(dia != null && !dia.isEmpty()) query.append(" AND dia = :dia");
		if(horarioDesde != null && !horarioDesde.isEmpty()) query.append(" AND hora_inicio >= :horarioDesde");
		if(horarioHasta != null && !horarioHasta.isEmpty()) query.append(" AND hora_fin <= :horarioHasta");
		if(estado != null && !estado.isEmpty()) query.append(" AND estado_turno = :estado");
		
		Transaction tx = null;
	    try {
	        Session session = config.abrirSesion();
	        tx = session.beginTransaction();
	        
	        Query hqlQuery = session.createQuery(query.toString());
	        
	        if(id != null && !id.isEmpty()) hqlQuery.setParameter("id", Long.parseLong(id));
			if(legajo != null && !legajo.isEmpty()) hqlQuery.setParameter("legajo", Integer.parseInt(legajo));
			if(dni != null && !dni.isEmpty()) hqlQuery.setParameter("dni", dni);
			if(dia != null && !dia.isEmpty()) hqlQuery.setParameter("dia", dia);
			if(horarioDesde != null && !horarioDesde.isEmpty()) hqlQuery.setParameter("horarioDesde", Integer.parseInt(horarioDesde));
			if(horarioHasta != null && !horarioHasta.isEmpty()) hqlQuery.setParameter("horarioHasta", Integer.parseInt(horarioHasta));
			if(estado != null && !estado.isEmpty()) hqlQuery.setParameter("estado", estado);
			
			List<Turno> turnos = hqlQuery.list();
	        tx.commit();
	        
	        if(!turnos.isEmpty()) {
	            return turnos;
	        } 
	        else {
	            System.err.println("Turnos no encontrados (error). No hay turnos registrados en el sistema.");
	            return null;
	        }
	    }
	    catch (Exception e) {
	        if(tx != null) {
	            tx.rollback();
	        }
	        
	        System.err.println("Turnos no encontrados (error). " + e.getMessage());
	        return null;
	    } 
	    finally {
	        config.cerrarSession();
	    }
	}
	
	@Override
	public List<Turno> traerTurnosReporte(String medico, String diaDesde, String diaHasta, String horarioDesde, String horarioHasta, String estado) {
		StringBuilder query = new StringBuilder("FROM Turno WHERE estado = 1");
		
		if(medico != null && !medico.isEmpty()) query.append(" AND medico_id = :medico");
		if(diaDesde != null && !diaDesde.isEmpty()) query.append(" AND dia >= :diaDesde");
		if(diaHasta != null && !diaHasta.isEmpty()) query.append(" AND dia <= :diaHasta");
		if(horarioDesde != null && !horarioDesde.isEmpty()) query.append(" AND hora_inicio >= :horarioDesde");
		if(horarioHasta != null && !horarioHasta.isEmpty()) query.append(" AND hora_fin <= :horarioHasta");
		if(estado != null && !estado.isEmpty()) query.append(" AND estado_turno = :estado");
		
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query hqlQuery = session.createQuery(query.toString());
			
			if(medico != null && !medico.isEmpty()) hqlQuery.setParameter("medico", Integer.parseInt(medico));
			if(diaDesde != null && !diaDesde.isEmpty()) hqlQuery.setParameter("diaDesde", diaDesde);
			if(diaHasta != null && !diaHasta.isEmpty()) hqlQuery.setParameter("diaHasta", diaHasta);
			if(horarioDesde != null && !horarioDesde.isEmpty()) hqlQuery.setParameter("horarioDesde", Integer.parseInt(horarioDesde));
			if(horarioHasta != null && !horarioHasta.isEmpty()) hqlQuery.setParameter("horarioHasta", Integer.parseInt(horarioHasta));
			if(estado != null && !estado.isEmpty()) hqlQuery.setParameter("estado", estado);
			
			List<Turno> turnos = hqlQuery.list();
	        tx.commit();
	        
	        if(!turnos.isEmpty()) {
	            return turnos;
	        } 
	        else {
	            System.err.println("Turnos no encontrados (error). No hay turnos registrados en el sistema.");
	            return null;
	        }
		}
		catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		        
			System.err.println("Turnos no encontrados (error). " + e.getMessage());
			return null;
		} 
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public List<Turno> traerTurnosSegunMedico(int legajo) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("FROM Turno WHERE medico_id = :legajo AND estado = 1");
			query.setParameter("legajo", legajo);
			
			List<Turno> turnos = query.list();
			tx.commit();
			
			if(!turnos.isEmpty()) {
				return turnos;
		    } 
			else {
				System.err.println("Turnos no encontrados (error). No hay turnos de ese medico registrados en el sistema.");
				return null;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Turnos no encontrados (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override 
	public List<Integer> traerTurnosHorasMedico(int legajo) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("SELECT horaInicio FROM Turno WHERE medico_id = :legajo AND estado = 1");
			query.setParameter("legajo", legajo);
			
			List<Integer> horas = query.list();
			tx.commit();
			
			Medico medico = (Medico) session.get(Medico.class, legajo);
			int horaInicio = medico.getHorarioInicio();
			int horaFin = medico.getHorarioFin();
			
			if(!horas.isEmpty() && medico != null && medico.getHorarioInicio() != 0 && medico.getHorarioFin() != 0) {
				List<Integer> horasLibres = new ArrayList<Integer>();
				
				for(int i=horaInicio;i<=horaFin-1;i++) {
					if(!horas.contains(i)) {
						horasLibres.add(i);
					}
				}
				return horasLibres;
		    } 
			else {
				if(medico != null && medico.getHorarioInicio() != 0 && medico.getHorarioFin() != 0) {
					for(int i=horaInicio;i<=horaFin-1;i++) {
						horas.add(i);
					}
					return horas;
				}
				else {
					System.err.println("Turnos no encontrados (error). El medico no existe o no tiene un horario registrado.");
					return null;
				}
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Turnos no encontrados (error)." + e.getMessage());
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public boolean altaTurno(Turno turno) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			session.save(turno);
			tx.commit();
			System.out.println("Alta de turno completada.");
			return true;
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Alta de turno fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean asignarTurno(Turno turno) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Medico medico = (Medico) session.get(Medico.class, turno.getMedico().getLegajo());
			Paciente paciente = (Paciente) session.get(Paciente.class, turno.getPaciente().getDni());
			
			turno.setMedico(medico);
			turno.setPaciente(paciente);
			
			session.save(turno);
			tx.commit();
			
			System.out.println("Alta de turno completada.");	
			return true;
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Alta de turno fallida (error)." + e.getMessage());
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public boolean modificarTurno(Turno turno) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
		

			Turno turnoAux = (Turno) session.get(Turno.class, turno.getId());

			if (turnoAux != null) {
				turnoAux = turno;
				session.update(turno);
				tx.commit();
				
				System.out.println("Modificacion de turno completada.");
				return true;
			} 
			else {
				System.out.println("Modificacion de turno fallida (error). No se encontro ningun turno con el ID proporcionado.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Modificacion de turno fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public boolean bajaTurno(Long id) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
		

			Turno turno = (Turno) session.get(Turno.class, id);
	
			if(turno != null) {
				if(turno.isEstado()) {
					turno.setEstado(false);
					session.update(turno);
					tx.commit();
					
					System.out.println("Baja de turno completada.");
					return true;
				}
				else  {
					System.err.println("Baja de turno fallida (error). Turno ya dado de baja.");
					return false;
				}
			} 
			else {
				System.err.println("Baja de turno fallida (error). No se encontro ningun turno con el ID proporcionado.");
				return false;
			}
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Baja de turno fallida (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean marcarTurno(Long id, String estadoTurno) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Turno turno = (Turno) session.get(Turno.class, id);
			if(turno != null) {
				turno.setEstadoTurno(estadoTurno);
				
				session.update(turno);
				tx.commit();
				
				System.out.println("Marcado de turno completado.");
				return true;
			}
			else {
				System.err.println("Marcado de turno fallido (error). No se encontro ningun turno con el ID proporcionado.");
				return false;
			}
			
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Marcado de turno fallido (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}
	
	@Override
	public boolean marcarObservacion(Long id, String observacion) {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();
			
			Turno turno = (Turno) session.get(Turno.class, id);
			if(turno != null) {
				turno.setObservacion(observacion);;
				
				session.update(turno);
				tx.commit();
				
				System.out.println("Marcado de observacion completado.");
				return true;
			}
			else {
				System.err.println("Marcado de observacion fallido (error). No se encontro ningun turno con el ID proporcionado.");
				return false;
			}
			
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Marcado de observacion fallido (error).");
			return false;
		}
		finally {
			config.cerrarSession();
		}
	}

	@Override
	public List<Turno> listarTurnos() {
		Transaction tx = null;
		try {
			Session session = config.abrirSesion();
			tx = session.beginTransaction();

			List<Turno> turnos = session.createQuery("FROM Turno WHERE estado = 1").list();
			tx.commit();
			
			if(!turnos.isEmpty()) {
				System.out.println("Lista de Turnos:");
				for(Turno turno : turnos) {
					System.out.println(turno.toString());
				}
			}
			else {
				System.err.println("Listado de turnos fallida (error). No hay turnos registrados en el sistema.");
			}
			return turnos;
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			
			System.err.println("Listado de turnos fallida (error).");
			return null;
		}
		finally {
			config.cerrarSession();
		}
	}
}
