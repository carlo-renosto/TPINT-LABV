package frgp.utn.edu.ar.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Turno;
import frgp.utn.edu.ar.negocioImp.MedicoNegocio;
import frgp.utn.edu.ar.negocioImp.TurnoNegocio;

@Controller
public class ReporteController extends GenericController {
	private TurnoNegocio negocioT;
	private MedicoNegocio negocioM;
	private ModelAndView MV;
	
	public ReporteController() {
		super();
		negocioT = this.beanManager.getBean("turnoNegocio", TurnoNegocio.class);
		negocioM = this.beanManager.getBean("medicoNegocio", MedicoNegocio.class);
		MV = this.beanManager.getBean("MV", ModelAndView.class);
	}
	
	@RequestMapping("redirect_reportes.html")
	public ModelAndView eventoRedirectReportes(HttpSession session) {
		List<Medico> medicos = negocioM.traerMedicos();
		
		MV.clear();
		traerTurnosReporteMV(session);
		MV.addObject("user", session.getAttribute("user"));
		MV.addObject("medicos", medicos);
		MV.setViewName("MenuReportes/MenuReportes");
		return MV;
	}
	
	@RequestMapping("redirect_reportes_filtro.html")
	public ModelAndView eventoRedirectReportesFiltro(String txtMedico, String txtDiaDesde, String txtDiaHasta, String txtHorarioDesde, String txtHorarioHasta, String txtEstado, HttpSession session) {
		List<Medico> medicos = negocioM.traerMedicos();
		List<Turno> turnos = negocioT.traerTurnosReporte(txtMedico, txtDiaDesde, txtDiaHasta, txtHorarioDesde, txtHorarioHasta, txtEstado);
		
		MV.clear();
		traerTurnosReporteMV(session);
		MV.addObject("user", session.getAttribute("user"));
		MV.addObject("medicos", medicos);
		
		int cont = 0;
		int contPresente = 0;
		int contAusente = 0;
		int contPendiente = 0;
		if(turnos != null) {
			MV.addObject("turnosInforme", turnos);
			
			for(Turno turno : turnos) {
				if(turno.getEstadoTurno() != null && !turno.getEstadoTurno().isEmpty()) {
					if(turno.getEstadoTurno().equals("Presente")) contPresente++;
					else if(turno.getEstadoTurno().equals("Ausente")) contAusente++;
				}
				else {
					contPendiente++;
				}
				cont++;
			}
			
			if(contPresente > 0) contPresente = (contPresente * 100) / cont;
			if(contAusente > 0) contAusente = (contAusente * 100) / cont;
			if(contPendiente > 0) contPendiente = (contPendiente * 100) / cont;
		}
		
		MV.addObject("cont4", contPresente);
		MV.addObject("cont5", contAusente);
		MV.addObject("cont6", contPendiente);
		
		MV.setViewName("MenuReportes/MenuReportes");
		return MV;
	}
	
	public void traerTurnosReporteMV(HttpSession session) {
		List<Turno> turnos = negocioT.traerTurnosReporte("", "2024-01-01", "2024-03-01", "6", "20", "");
		
		int cont = 0;
		int contPresente = 0;
		int contAusente = 0;
		int contPendiente = 0;
		if(turnos != null) {
			for(Turno turno : turnos) {
				if(turno.getEstadoTurno() != null && !turno.getEstadoTurno().isEmpty()) {
					if(turno.getEstadoTurno().equals("Presente")) contPresente++;
					else if(turno.getEstadoTurno().equals("Ausente")) contAusente++;
				}
				else {
					contPendiente++;
				}
				cont++;
			}
		}
		
		if(contPresente > 0) contPresente = (contPresente * 100) / cont;
		if(contAusente > 0) contAusente = (contAusente * 100) / cont;
		if(contPendiente > 0) contPendiente = (contPendiente * 100) / cont;
		
		if(turnos != null) {
			MV.addObject("turnos", turnos);
		}

		MV.addObject("cont1", contPresente);
		MV.addObject("cont2", contAusente);
		MV.addObject("cont3", contPendiente);
	}
}
