package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.daoImp.UsuarioDao;
import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocio.IUsuarioNegocio;

@Service
public class UsuarioNegocio implements IUsuarioNegocio {
	private UsuarioDao dao;
	
	public UsuarioNegocio(UsuarioDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Usuario traerUsuario(String usuarioNombre, String usuarioPass) {
		return dao.traerUsuario(usuarioNombre, usuarioPass);
	}
	
	@Override
	public Usuario traerUsuario2(String usuarioNombre) {
		return dao.traerUsuario2(usuarioNombre);
	}
	
	@Override
	public List<Usuario> traerUsuarios() {
		return dao.traerUsuarios();
	}

	@Override
	public List<Usuario> traerUsuariosFiltro(String usuario, String usuarioPass, String admin) {
		return dao.traerUsuariosFiltro(usuario, usuarioPass, admin);
	}
	
	@Override
	public boolean altaUsuario(Usuario usuario) {
		return dao.altaUsuario(usuario);
	}

	@Override
	public boolean modificarUsuario(Usuario usuario) {
		return dao.modificarUsuario(usuario);
	}

	@Override
	public boolean bajaUsuario(String usuarioNombre) {
		return dao.bajaUsuario(usuarioNombre);
	}
	
	@Override
	public boolean validarUsuario(String usuarioNombre, String usuarioPass) {
		return dao.validarUsuario(usuarioNombre, usuarioPass);
	}
	
	@Override
	public boolean validarUsuarioAdmin(String usuarioNombre) {
		return dao.validarUsuarioAdmin(usuarioNombre);
	}

	@Override
	public void listarUsuarios() {
		dao.listarUsuarios();
	}
}
