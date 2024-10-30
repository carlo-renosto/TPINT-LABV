package frgp.utn.edu.ar.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocioImp.UsuarioNegocio;

@Controller
public class UsuarioController extends GenericController {
	private UsuarioNegocio negocio;
	private ModelAndView MV;
	
	public UsuarioController() {
		super();
		negocio = this.beanManager.getBean("usuarioNegocio", UsuarioNegocio.class);
		MV = this.beanManager.getBean("MV", ModelAndView.class);
	}
	
	@RequestMapping("redirect_usuarios.html")
	public ModelAndView eventoRedirectUsuarios(HttpSession session) {
		List<Usuario> usuarios = negocio.traerUsuarios();
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(usuarios != null) {
			MV.addObject("usuarios", usuarios);
		}
		else {
			MV.addObject("error", "Usuarios no encontrados (error). La tabla no tiene usuarios o ha ocurrido un error con la base de datos.");
		}
		MV.setViewName("MenuUsuarios/MenuUsuarios");
		return MV;
	}
	
	@RequestMapping("redirect_usuarios_filtro.html")
	public ModelAndView eventoRedirectUsuariosFiltro(String txtNombre, String txtPassword, String txtAdmin, HttpSession session) {
		List<Usuario> usuarios = negocio.traerUsuariosFiltro(txtNombre, txtPassword, txtAdmin);
		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		if(usuarios != null) {
			MV.addObject("usuarios", usuarios);
		}
		else {
			MV.addObject("error", "Usuarios no encontrados (error). La tabla no tiene usuarios o ha ocurrido un error con la base de datos.");
		}
		MV.setViewName("MenuUsuarios/MenuUsuarios");
		return MV;
	}
	
	@RequestMapping("redirect_usuarios_alta.html")
	public ModelAndView eventoRedirectUsuariosAlta(HttpSession session) {
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuUsuarios/NuevoUsuario");
		return MV;
	}
	
	@RequestMapping("redirect_usuarios_mod.html")
	public ModelAndView eventoRedirectUsuariosMod(HttpSession session) {		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuUsuarios/ModUsuario");
		return MV;
	}
	
	@RequestMapping("redirect_usuarios_baja.html")
	public ModelAndView eventoRedirectUsuariosBaja(HttpSession session) {		
		MV.clear();
		MV.addObject("user", session.getAttribute("user"));
		MV.setViewName("MenuUsuarios/BajaUsuario");
		return MV;
	}
	
	@RequestMapping("usuario_alta.html")
	public ModelAndView altaUsuario(String txtNombre, String txtPassword, String txtAdmin, HttpSession session) {
		Usuario usuario = this.beanManager.getBean("usuario", Usuario.class);
		usuario.limpiar();
		usuario.set(txtNombre, txtPassword, Boolean.parseBoolean(txtAdmin));
		
		MV.clear();
		if(negocio.altaUsuario(usuario)) {
			MV.addObject("message", "Usuario dado de alta exitosamente");
		}
		else {
			MV.addObject("error", "Usuario no dado de alta (error)");
		}
		MV.setViewName("MenuUsuarios/NuevoUsuario");
		return MV;
	}
	
	@RequestMapping("usuario_mod_search.html")
	public ModelAndView modUsuarioSearch(String txtNombre) {
		Usuario usuario = negocio.traerUsuario2(txtNombre);
		
		MV.clear();
		if(usuario != null) {
			MV.addObject("usuario", usuario);
			MV.addObject("usuarioShow", true);
		}
		else {
			MV.addObject("error", "Usuario no encontrado (error)");
			MV.addObject("usuarioShow", false);
		}
		MV.setViewName("MenuUsuarios/ModUsuario");
		return MV;
	}
	
	@RequestMapping("usuario_mod.html")
	public ModelAndView modUsuario(String txtNombre, String txtPassword, String txtAdmin) {
		Usuario usuario = this.beanManager.getBean("usuario", Usuario.class);
		usuario.limpiar();
		usuario.set(txtNombre, txtPassword, Boolean.parseBoolean(txtAdmin));
		
		MV.clear();
		if(negocio.modificarUsuario(usuario)) {
			MV.addObject("usuarioShow", false);
			MV.addObject("message", "Usuario modificado exitosamente");
		}
		else {
			MV.addObject("error", "Usuario no modificado (error)");
			MV.addObject("usuarioShow", false);
		}
		MV.setViewName("MenuUsuarios/ModUsuario");
		return MV;
	}
	
	@RequestMapping("usuario_baja.html")
	public ModelAndView bajaUsuario(String txtNombre) {
		MV.clear();
		if(negocio.bajaUsuario(txtNombre)) {
			MV.addObject("message", "Usuario dado de baja exitosamente");
		}
		else {
			MV.addObject("error", "Usuario no dado de baja (error)");
		}
		MV.setViewName("MenuUsuarios/BajaUsuario");
		return MV;
	}
	
	@RequestMapping("login.html")
	public ModelAndView eventoLogin(String txtUser, String txtPassword, HttpSession session) {		
		MV.clear();
		
		if(negocio.validarUsuario(txtUser, txtPassword)) {
			session.setAttribute("user", txtUser);
			
			MV.addObject("user", session.getAttribute("user"));
			if(negocio.validarUsuarioAdmin(txtUser)) {
				session.setAttribute("admin", true);
				MV.setViewName("Inicio");
			}
			else {
				session.setAttribute("admin", false);
				MV.setViewName("InicioMedico");
			}
		}
		else {
			MV.addObject("error", "Credenciales incorrectas.");
			MV.setViewName("Login");
		}
		return MV;
	}
}
