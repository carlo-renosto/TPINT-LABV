package frgp.utn.edu.ar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Especialidad;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.entidad.Turno;
import frgp.utn.edu.ar.negocioImp.EspecialidadNegocio;
import frgp.utn.edu.ar.negocioImp.MedicoNegocio;
import frgp.utn.edu.ar.negocioImp.PacienteNegocio;
import frgp.utn.edu.ar.negocioImp.TurnoNegocio;

@Controller
public class TurnoController extends GenericController {
	private TurnoNegocio negocio;
	private EspecialidadNegocio negocioE;
	private MedicoNegocio negocioM;
	private PacienteNegocio negocioP;
	private ModelAndView MV;
	
	public TurnoController() {
		super();
		negocio = this.beanManager.getBean("turnoNegocio", TurnoNegocio.class);
		negocioE = this.beanManager.getBean("espNegocio", EspecialidadNegocio.class);
		negocioM = this.beanManager.getBean("medicoNegocio", MedicoNegocio.class);
		negocioP = this.beanManager.getBean("pacienteNegocio", PacienteNegocio.class);
		MV = this.beanManager.getBean("MV", ModelAndView.class);
	}
	
	@RequestMapping("redirect_turnos.html")
	public ModelAndView eventoRedirectTurnos(HttpSession session)  {
		List<Turno> turnos = negocio.traerTurnos();
		List<Especialidad> especialidades = negocioE.traerEspecialidades();

		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(turnos != null) {
			MV.addObject("turnos", turnos);
		}
		else {
			MV.addObject("error", "Turnos no encontrados (error). La tabla no tiene turnos o ha ocurrido un error con la base de datos.");
		}
		MV.addObject("especialidades", especialidades);
		MV.setViewName("MenuTurnos/MenuTurnos");
		return MV;
	}
	
	@RequestMapping("redirect_turnos_filtro.html")
	public ModelAndView eventoRedirectTurnosFiltro(String txtTurno, String txtLegajo, String txtEspecialidad, String txtDni, String txtDia, String txtHorarioDesde, String txtHorarioHasta, String txtEstado, HttpSession session)  {
		List<Turno> turnos = negocio.traerTurnosFiltro(txtTurno, txtLegajo, txtEspecialidad, txtDni, txtDia, txtHorarioDesde, txtHorarioHasta, txtEstado);
		List<Especialidad> especialidades = negocioE.traerEspecialidades();

		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(turnos != null) {
			MV.addObject("turnos", turnos);
		}
		else {
			MV.addObject("error", "Turnos no encontrados (error). La tabla no tiene turnos o ha ocurrido un error con la base de datos.");
		}
		MV.addObject("especialidades", especialidades);
		MV.setViewName("MenuTurnos/MenuTurnos");
		return MV;
	}
	
	@RequestMapping("redirect_turnos_medico.html")
	public ModelAndView eventoRedirectTurnosMedico(HttpSession session)  {
		String usuario = (String) session.getAttribute("user");
		Medico medico = negocioM.traerMedicoSegunUsuario(usuario);
		List<Turno> turnos = negocio.traerTurnosSegunMedico(medico.getLegajo());
		List<Especialidad> especialidades = negocioE.traerEspecialidades();
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(turnos != null) {
			MV.addObject("turnos", turnos);
		}
		else {
			MV.addObject("error", "Turnos no encontrados (error). Usted no tiene turnos asignados o ha ocurrido un error con la base de datos.");
		}
		MV.addObject("especialidades", especialidades);
		MV.setViewName("MenuTurnos/MenuTurnosMedico");
		return MV;
	}
	
	@RequestMapping("redirect_turnos_filtro_medico.html")
	public ModelAndView eventoRedirectTurnosFiltroMedico(String txtTurno, String txtLegajo, String txtDni, String txtDia, String txtHorarioDesde, String txtHorarioHasta, String txtEstado, HttpSession session)  {
		List<Turno> turnos = negocio.traerTurnosFiltro(txtTurno, txtLegajo, "", txtDni, txtDia, txtHorarioDesde, txtHorarioHasta, txtEstado);
		List<Especialidad> especialidades = negocioE.traerEspecialidades();

		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(turnos != null) {
			MV.addObject("turnos", turnos);
		}
		else {
			MV.addObject("error", "Turnos no encontrados (error). La tabla no tiene turnos o ha ocurrido un error con la base de datos.");
		}
		MV.addObject("especialidades", especialidades);
		MV.setViewName("MenuTurnos/MenuTurnosMedico");
		return MV;
	}
	
	
	@RequestMapping("redirect_turnos_asignar.html")
	public ModelAndView eventoRedirectTurnosAsignar(HttpSession session)  {
		List<Especialidad> especialidades = negocioE.traerEspecialidades();
		List<Medico> medicos = new ArrayList<Medico>();
		if(especialidades != null) {
			medicos = negocioM.traerMedicosSegunEspecialidad(especialidades.get(0).getId());
		}
		List<Integer> medicosHoras = new ArrayList<Integer>();
		if(medicos != null) {
			medicosHoras = negocio.traerTurnosHorasMedico(medicos.get(0).getLegajo());
		}
		List<Paciente> pacientes = negocioP.traerPacientes();
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(medicos != null && especialidades != null && pacientes != null) {
			MV.addObject("especialidades", especialidades);
			MV.addObject("medicos", medicos);
			MV.addObject("horas", medicosHoras);
			MV.addObject("pacientes", pacientes);
			MV.setViewName("MenuTurnos/AsignarTurno");
		}
		else {
			MV.addObject("error", "Turnos no asignados (error). Las tablas no tienen medicos, especialidades y/o pacientes.");
			MV.setViewName("MenuTurnos/MenuTurnos");
		}
		return MV;
	}
	
	@RequestMapping("redirect_turnos_cancelar.html")
	public ModelAndView eventoRedirectTurnosCancelar(HttpSession session)  {
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuTurnos/CancelarTurno");
		return MV;
	}
	
	@RequestMapping("turno_asignar.html")
	public ModelAndView turnoAsignar(String txtMedico, String txtPaciente, String txtDia, String txtHorario, HttpSession session)  {
		Turno turno = this.beanManager.getBean("turno", Turno.class);
		turno.limpiar();
		turno.set(txtMedico, txtPaciente, txtDia, Integer.parseInt(txtHorario), Integer.parseInt(txtHorario) + 1, "", "");
		
		MV.clear();
		traerTurnosAsignarMV(session);
		if(negocio.asignarTurno(turno)) {
			MV.addObject("message", "Turno asignado exitosamente");
		}
		else {
			MV.addObject("error", "Turno no asignado (error)");
		}
		MV.setViewName("MenuTurnos/AsignarTurno");
		return MV;
	}
	
	@RequestMapping("turno_cancelar.html")
	public ModelAndView turnoCancelar(String txtTurno, HttpSession session)  {
		MV.clear();
		if(negocio.bajaTurno(Long.parseLong(txtTurno))) {
			MV.addObject("message", "Turno cancelado exitosamente");
		}
		else {
			MV.addObject("error", "Turno no cancelado (error)");
		}
		MV.setViewName("MenuTurnos/CancelarTurno");
		return MV;
	}
	
	@RequestMapping("turno_marcar_presente.html")
	public ModelAndView eventoRedirectTurnoMarcarPresente(String txtTurno, HttpSession session)  {
		boolean marcado = negocio.marcarTurno(Long.parseLong(txtTurno), "Presente");
		MV.clear();
		traerTurnosMedicoMV(session);
		MV.addObject("user", session.getAttribute("user"));
		if(marcado) {
			MV.addObject("message", "Turno marcado exitosamente");
		}
		else {
			MV.addObject("error", "Turno no marcado (error)");
		}
		MV.setViewName("MenuTurnos/MenuTurnosMedico");
		return MV;
	}
	
	@RequestMapping("turno_marcar_ausente.html")
	public ModelAndView eventoRedirectTurnoMarcarAusente(String txtTurno, HttpSession session)  {
		boolean marcado = negocio.marcarTurno(Long.parseLong(txtTurno), "Ausente");
		MV.clear();
		traerTurnosMedicoMV(session);
		MV.addObject("user", session.getAttribute("user"));
		if(marcado) {
			MV.addObject("message", "Turno marcado exitosamente");
		}
		else {
			MV.addObject("error", "Turno no marcado (error)");
		}
		MV.setViewName("MenuTurnos/MenuTurnosMedico");
		return MV;
	}
	
	@RequestMapping("turno_marcar_observacion.html")
	public ModelAndView eventoRedirectTurnoMarcarObservacion(String txtTurno, String txtObservacion, HttpSession session)  {
		boolean marcado = negocio.marcarObservacion(Long.parseLong(txtTurno), txtObservacion);
		MV.clear();
		traerTurnosMedicoMV(session);
		MV.addObject("user", session.getAttribute("user"));
		if(marcado) {
			MV.addObject("message", "Observacion marcada exitosamente");
		}
		else {
			MV.addObject("error", "Observacion no marcada (error)");
		}
		MV.setViewName("MenuTurnos/MenuTurnosMedico");
		return MV;
	}
	
	@RequestMapping("traer_turnos_medico_horas.json")
	@ResponseBody
	public List<Integer> eventoTraerTurnoMedicoHoras(@RequestParam("medico") String medico) {
		return negocio.traerTurnosHorasMedico(Integer.parseInt(medico));
	}
	
	public void traerTurnosAsignarMV(HttpSession session) {
		List<Especialidad> especialidades = negocioE.traerEspecialidades();
		List<Medico> medicos = new ArrayList<Medico>();
		if(especialidades != null) {
			medicos = negocioM.traerMedicosSegunEspecialidad(especialidades.get(0).getId());
		}
		List<Integer> medicosHoras = new ArrayList<Integer>();
		if(medicos != null) {
			medicosHoras = negocio.traerTurnosHorasMedico(medicos.get(0).getLegajo());
		}
		List<Paciente> pacientes = negocioP.traerPacientes();
		
		MV.addObject("user", session.getAttribute("user"));
		if(medicos != null && especialidades != null && pacientes != null) {
			MV.addObject("especialidades", especialidades);
			MV.addObject("medicos", medicos);
			MV.addObject("horas", medicosHoras);
			MV.addObject("pacientes", pacientes);
		}
		else {
			MV.addObject("error", "Turnos no asignados (error). Las tablas no tienen medicos, especialidades y/o pacientes.");
			MV.setViewName("MenuTurnos/MenuTurnos");
		}
	}
	
	public void traerTurnosMedicoMV(HttpSession session) {
		String usuario = (String) session.getAttribute("user");
		Medico medico = negocioM.traerMedicoSegunUsuario(usuario);
		List<Turno> turnos = negocio.traerTurnosSegunMedico(medico.getLegajo());
		
		if(turnos != null) {
			MV.addObject("turnos", turnos);
		}
		else {
			MV.addObject("error", "Turnos no encontrados (error). Usted no tiene turnos asignados o ha ocurrido un error con la base de datos.");
		}
	}
}
