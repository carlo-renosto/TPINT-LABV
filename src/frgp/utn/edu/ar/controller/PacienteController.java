package frgp.utn.edu.ar.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.negocioImp.PacienteNegocio;

@Controller
public class PacienteController extends GenericController {
	private PacienteNegocio negocio;
	private ModelAndView MV;
	
	public PacienteController() {
		super();
		negocio = this.beanManager.getBean("pacienteNegocio", PacienteNegocio.class);
		MV = this.beanManager.getBean("MV", ModelAndView.class);
	}
	
	@RequestMapping("redirect_pacientes.html")
	public ModelAndView eventoRedirectPacientes(HttpSession session) {
		List<Paciente> pacientes = negocio.traerPacientes();
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(pacientes != null) {
			MV.addObject("pacientes", pacientes);
		}
		else {
			MV.addObject("error", "Pacientes no encontrados (error). La tabla no tiene pacientes o ha ocurrido un error con la base de datos.");
		}
		MV.setViewName("MenuPacientes/MenuPacientes");
		return MV;
	}
	
	@RequestMapping("redirect_pacientes_filtro.html")
	public ModelAndView eventoRedirectPacientesFiltro(String txtDni, String txtNombre, String txtApellido, String txtTelefono, String txtDireccion, String txtLocalidad, String txtProvincia, String txtEmail, HttpSession session) {
		List<Paciente> pacientes = negocio.traerPacientesFiltro(txtDni, txtNombre, txtApellido, txtTelefono, txtDireccion, txtLocalidad, txtProvincia, txtEmail);
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(pacientes != null) {
			MV.addObject("pacientes", pacientes);
		}
		else {
			MV.addObject("error", "Pacientes no encontrados (error). La tabla no tiene pacientes o ha ocurrido un error con la base de datos.");
		}
		MV.setViewName("MenuPacientes/MenuPacientes");
		return MV;
	}
	
	@RequestMapping("redirect_pacientes_alta.html")
	public ModelAndView eventoRedirectPacientesAlta(HttpSession session) {
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuPacientes/NuevoPaciente");
		return MV;
	}
	
	@RequestMapping("redirect_pacientes_mod.html")
	public ModelAndView eventoRedirectPacientesMod(HttpSession session) {
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuPacientes/ModPaciente");
		return MV;
	}
	
	@RequestMapping("redirect_pacientes_baja.html")
	public ModelAndView eventoRedirectPacientesBaja(HttpSession session) {
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuPacientes/BajaPaciente");
		return MV;
	}
	
	@RequestMapping("paciente_alta.html")
	public ModelAndView altaPaciente(String txtDni, String txtNombre, String txtApellido, String txtTelefono, String txtDireccion, String txtLocalidad, String txtProvincia, String txtFechaNac, String txtEmail) {
		Paciente paciente = this.beanManager.getBean("paciente", Paciente.class);
		paciente.limpiar();
		paciente.set(txtDni, txtNombre, txtApellido, txtTelefono, txtDireccion, txtLocalidad, txtProvincia, txtFechaNac, txtEmail);
		
		MV.clear();
		if(negocio.altaPaciente(paciente)) {
			MV.addObject("message", "Paciente dado de alta exitosamente");
		}
		else {
			MV.addObject("error", "Paciente no dado de alta (error)");
		}
		MV.setViewName("MenuPacientes/NuevoPaciente");
		return MV;
	}
	
	@RequestMapping("paciente_mod_search.html")
	public ModelAndView modPacienteSearch(String txtDni) {
		Paciente paciente = negocio.traerPaciente(txtDni);
		
		MV.clear();
		if(paciente != null) {
			MV.addObject("paciente", paciente);
			MV.addObject("pacienteShow", true);
		}
		else {
			MV.addObject("error", "Paciente no encontrado (error)");
			MV.addObject("pacienteShow", false);
		}
		MV.setViewName("MenuPacientes/ModPaciente");
		return MV;
	}
	
	@RequestMapping("paciente_mod.html")
	public ModelAndView modPaciente(String txtDni, String txtNombre, String txtApellido, String txtTelefono, String txtDireccion, String txtLocalidad, String txtProvincia, String txtFechaNac, String txtEmail) {
		Paciente paciente = this.beanManager.getBean("paciente", Paciente.class);
		paciente.limpiar();
		paciente.set(txtDni, txtNombre, txtApellido, txtTelefono, txtDireccion, txtLocalidad, txtProvincia, txtFechaNac, txtEmail);

		MV.clear();
		if(negocio.modificarPaciente(paciente)) {			
			MV.addObject("pacienteShow", false);
			MV.addObject("message", "Paciente modificado exitosamente");
		}
		else {
			MV.addObject("error", "Paciente no modificado (error)");
			MV.addObject("pacienteShow", false);
		}
		MV.setViewName("MenuPacientes/ModPaciente");
		return MV;
	}
	
	@RequestMapping("paciente_baja.html")
	public ModelAndView bajaPaciente(String txtDni) {
		MV.clear();
		if(negocio.bajaPaciente(txtDni)) {
			MV.addObject("message", "Paciente dado de baja exitosamente");
		}
		else {
			MV.addObject("error", "Paciente no dado de baja (error)");
		}
		MV.setViewName("MenuPacientes/BajaPaciente");
		return MV;
	}
}
