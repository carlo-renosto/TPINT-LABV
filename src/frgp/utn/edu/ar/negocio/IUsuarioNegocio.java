package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Usuario;

public interface IUsuarioNegocio {
	public Usuario traerUsuario(String usuarioNombre, String usuarioPass);
	
	public Usuario traerUsuario2(String usuarioNombre);
	
	public List<Usuario> traerUsuarios();
	
	public List<Usuario> traerUsuariosFiltro(String usuario, String usuarioPass, String admin);
	
	public boolean altaUsuario(Usuario usuario);
	
	public boolean modificarUsuario(Usuario usuario);
	
	public boolean bajaUsuario(String usuarioNombre);
	
	public boolean validarUsuario(String usuarioNombre, String usuarioPass);
	
	public boolean validarUsuarioAdmin(String usuarioNombre);
	
	public void listarUsuarios();
}
