<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Usuario - Baja</title>
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
	<div>
		<h2 style="text-align: center;">Baja usuario</h2>
	</div>
	
	<form method="post" action="usuario_baja.html" onsubmit="confirmarEliminacion(event)">
		<input type="text" placeholder="Usuario" required name="txtNombre"></input>
		<input type="submit" value="Eliminar"></input>
	</form>
	<label style="color: red; text-align: center;">${error}</label>
	<label style="text-align: center;">${message}</label>
	
	<script>
		function confirmarEliminacion(event) {
	        if(!confirm("¿Esta seguro de que desea dar de baja este usuario?")) {
	            event.preventDefault();
	        }
	    }
	</script>
</body>
</html>