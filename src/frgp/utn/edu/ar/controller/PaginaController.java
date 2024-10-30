package frgp.utn.edu.ar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaginaController extends GenericController {
	private ModelAndView MV;
	
	public PaginaController() {
		super();
		MV = this.beanManager.getBean("MV", ModelAndView.class);
	}
	
	@RequestMapping("redirect_login.html")
	public ModelAndView eventoRedirectLogin() {
		MV.clear();
		MV.setViewName("Login");
		return MV;
	}
	
	@RequestMapping("redirect_inicio.html")
	public ModelAndView eventoRedirectInicio(HttpSession session) {
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if((boolean)session.getAttribute("admin")) {
			MV.setViewName("Inicio");
		}
		else {
			MV.setViewName("InicioMedico");
		}
		return MV;
	}
	
	@RequestMapping("redirect_signup.html")
	public ModelAndView eventoRedirectSignup() {
		MV.clear();
		MV.setViewName("Signup");
		return MV;
	}
}
