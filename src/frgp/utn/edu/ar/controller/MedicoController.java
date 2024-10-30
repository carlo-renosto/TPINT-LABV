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
import frgp.utn.edu.ar.negocioImp.EspecialidadNegocio;
import frgp.utn.edu.ar.negocioImp.MedicoDTO;
import frgp.utn.edu.ar.negocioImp.MedicoNegocio;

@Controller
public class MedicoController extends GenericController {
	private MedicoNegocio negocio;
	private EspecialidadNegocio negocioE;
	private ModelAndView MV;
	
	public MedicoController() {
		super();
		negocio = this.beanManager.getBean("medicoNegocio", MedicoNegocio.class);
		negocioE = this.beanManager.getBean("espNegocio", EspecialidadNegocio.class);
		MV = this.beanManager.getBean("MV", ModelAndView.class);
	}
	
	@RequestMapping("redirect_medicos.html")
	public ModelAndView eventoRedirectMedicos(HttpSession session) {
		List<Medico> medicos = negocio.traerMedicos();
		List<Especialidad> especialidades = negocioE.traerEspecialidades(); 
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(medicos != null) {
			MV.addObject("medicos", medicos);
		}
		else {
			MV.addObject("error", "Medicos no encontrados (error). La tabla no tiene medicos o ha ocurrido un error con la base de datos.");
		}
		MV.addObject("especialidades", especialidades);
		MV.setViewName("MenuMedicos/MenuMedicos");
		return MV;
	}
	
	@RequestMapping("redirect_medicos_filtro.html")
	public ModelAndView eventoRedirectMedicosFiltro(String txtLegajo, String txtNombre, String txtApellido, String txtSexo, String txtDireccion, String txtLocalidad, String txtProvincia, String txtEmail, String txtTelefono, String txtDia, String txtHorarioDesde, String txtHorarioHasta, String txtEspecialidad, HttpSession session) {
		List<Medico> medicos = negocio.traerMedicosFiltro(txtLegajo, txtNombre, txtApellido, txtSexo, txtDireccion, txtLocalidad, txtProvincia, txtEmail, txtTelefono, txtDia, txtHorarioDesde, txtHorarioHasta, txtEspecialidad);
		List<Especialidad> especialidades = negocioE.traerEspecialidades(); 
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(medicos != null) {
			MV.addObject("medicos", medicos);
		}
		else {
			MV.addObject("error", "Médicos no encontrados (error). La tabla no tiene médicos o ha ocurrido un error con la base de datos.");
		}
		MV.addObject("especialidades", especialidades);
		MV.setViewName("MenuMedicos/MenuMedicos");
		return MV;
	}
	
	@RequestMapping("redirect_medicos_alta.html")
	public ModelAndView eventoRedirectMedicosAlta(HttpSession session) {
		List<Especialidad> especialidades = negocioE.traerEspecialidades(); 
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.addObject("especialidades", especialidades);
		MV.setViewName("MenuMedicos/NuevoMedico");
		return MV;
	}
	
	@RequestMapping("redirect_medicos_mod.html")
	public ModelAndView eventoRedirectMedicosMod(HttpSession session) {		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuMedicos/ModMedico");
		return MV;
	}
	
	@RequestMapping("redirect_medicos_baja.html")
	public ModelAndView eventoRedirectMedicosBaja(HttpSession session) {
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuMedicos/BajaMedico");
		return MV;
	}
	
	@RequestMapping("medico_alta.html")
	public ModelAndView altaMedico(String txtLegajo, String txtNombre, String txtApellido, String txtSexo, String txtFechaNac, String txtDireccion, String txtLocalidad, String txtProvincia, String txtEmail, String txtTelefono, String txtDia, String txtHorarioDesde, String txtHorarioHasta, String txtEspecialidad, String txtUsuario) {
		Medico medico = this.beanManager.getBean("medico", Medico.class);
		medico.limpiar();
		medico.set(Integer.parseInt(txtLegajo), txtNombre, txtApellido, txtSexo, txtFechaNac, txtDireccion, txtLocalidad, txtProvincia, txtEmail, txtTelefono, txtDia, Integer.parseInt(txtHorarioDesde), Integer.parseInt(txtHorarioHasta), txtEspecialidad, txtUsuario);
		
		MV.clear();
		if(negocio.altaMedico(medico)) {
			MV.addObject("message", "Medico dado de alta exitosamente");
		}
		else {
			MV.addObject("error", "Medico no dado de alta (error)");
		}
		MV.setViewName("MenuMedicos/NuevoMedico");
		return MV;
	}
	
	@RequestMapping("medico_mod_search.html")
	public ModelAndView modMedicoSearch(String txtLegajo) {
		Medico medico = negocio.traerMedico(Integer.parseInt(txtLegajo));
		List<Especialidad> especialidades = negocioE.traerEspecialidades(); 
		
		MV.clear();
		if(medico != null) {
			MV.addObject("medico", medico);
			MV.addObject("medicoShow", true);
			MV.addObject("especialidades", especialidades);
		}
		else {
			MV.addObject("error", "Medico no encontrado (error)");
			MV.addObject("medicoShow", false);
		}
		MV.setViewName("MenuMedicos/ModMedico");
		return MV;
	}
	
	@RequestMapping("medico_mod.html")
	public ModelAndView modMedico(String txtLegajo, String txtNombre, String txtApellido, String txtSexo, String txtFechaNac, String txtDireccion, String txtLocalidad, String txtProvincia, String txtEmail, String txtTelefono, String txtDia, String txtHorarioDesde, String txtHorarioHasta, String txtEspecialidad, String txtUsuario) {
		Medico medico = this.beanManager.getBean("medico", Medico.class);
		medico.limpiar();
		medico.set(Integer.parseInt(txtLegajo), txtNombre, txtApellido, txtSexo, txtFechaNac, txtDireccion, txtLocalidad, txtProvincia, txtEmail, txtTelefono, txtDia, Integer.parseInt(txtHorarioDesde), Integer.parseInt(txtHorarioHasta), txtEspecialidad, txtUsuario);

		MV.clear();
		if(negocio.modificarMedico(medico)) {			
			MV.addObject("medicoShow", false);
			MV.addObject("message", "Medico modificado exitosamente");
		}
		else {
			MV.addObject("error", "Medico no modificado (error)");
			MV.addObject("medicoShow", false);
		}
		MV.setViewName("MenuMedicos/ModMedico");
		return MV;
	}
	
	@RequestMapping("medico_baja.html")
	public ModelAndView bajaMedico(String txtLegajo) {
		MV.clear();
		if(negocio.bajaMedico(txtLegajo)) {
			MV.addObject("message", "Medico dado de baja exitosamente");
		}
		else {
			MV.addObject("error", "Medico no dado de baja (error)");
		}
		MV.setViewName("MenuMedicos/BajaMedico");
		return MV;
	}
	
	@RequestMapping("traer_medicos_especialidad.json")
	@ResponseBody
	public List<MedicoDTO> eventoTraerMedicosSegunEspecialidad(@RequestParam("especialidad") String especialidad) {
		List<Medico> medicos = negocio.traerMedicosSegunEspecialidad(especialidad);
		
		List<MedicoDTO> medicosDTO = new ArrayList<>();
	    for (Medico medico : medicos) {
	        MedicoDTO medicoDTO = new MedicoDTO(medico.getLegajo(), medico.getNombre(), medico.getApellido());
	        medicosDTO.add(medicoDTO);
	    }
	    return medicosDTO;
	}
}
