<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Usuario - Modificar</title>
</head>
<body>
	<div id="div-header">
		<h2 style="margin-top: 10px; margin-left: 30px;">Clinica Medica</h2>
		<a class="menu-bar" href="redirect_inicio.html">Inicio</a>
		<a class="menu-bar" href="redirect_pacientes.html">Pacientes</a>
		<a class="menu-bar" href="redirect_medicos.html">Medicos</a>
		<a class="menu-bar" href="redirect_turnos.html">Turnos</a>
		<a class="menu-bar" href="redirect_reportes.html">Informes</a>
		<a class="menu-bar" href="redirect_usuarios.html">Usuarios</a>
		<p class="menu-bar" style="display:inline-block; margin-left: 50%;">Usuario: ${user}</p>
		<a class="menu-bar" style="text-decoration: underline;" href="redirect_login.html">Cambiar</a>
	</div>
	<div >
		<h2 style="text-align: center;">Modificar usuario</h2>
	</div>
	<form method="get" action="usuario_mod_search.html">
		<input type="text" placeholder="Usuario" required name="txtNombre">
		<input type="submit" value="Buscar">
	</form>
	
	<c:if test="${usuarioShow}">
	<form method="post" action="usuario_mod.html" class="add-form">
		<p class="add-text">Usuario</p>
		<input type="hidden" value="${usuario.usuario}" name="txtNombre"> 
		<input type="text" class="add-input" required value="${usuario.usuario}" disabled maxlength="30"></input>
		<p class="add-text">Contrase�a</p>
		<input type="password" class="add-input" required value="${usuario.contrasenia}" name="txtPassword" maxlength="30" style="width: 100px;"></input>
		<p class="add-text">Tipo</p> 
		<select name="txtAdmin">
			<option value="true">Admin</option>
			<option value="false">Medico</option>
		</select><br><br>
		<input type="submit" value="Modificar"></input>
	</form>
	</c:if>
	<label style="color: red; text-align: center;">${error}</label>
	<label style="text-align: center;">${message}</label>
</body>
</html>