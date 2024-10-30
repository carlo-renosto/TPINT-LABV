<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Paciente - Modificar</title>
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
		<h2 style="text-align: center;">Modificar paciente</h2>
	</div>
	<form method="get" action="paciente_mod_search.html">
		<input type="text" placeholder="DNI" required name="txtDni">
		<input type="submit" value="Buscar">
	</form>
	
	<c:if test="${pacienteShow}">
	<form method="post" action="paciente_mod.html" class="add-form">
		<p class="add-text">DNI</p>
		<input type="hidden" value="${paciente.dni}" name="txtDni"> 
		<input type="text" class="add-input" value="${paciente.dni}" disabled maxlength="30"></input>
		<p class="add-text">Nombre</p> 
		<input type="text" class="add-input" value="${paciente.nombre}" required name="txtNombre" maxlength="30"></input>
		<p class="add-text">Apellido</p> 
		<input type="text" class="add-input" value="${paciente.apellido}" required name="txtApellido" maxlength="30"></input>
		<p class="add-text">Teléfono</p> 
		<input type="text" class="add-input" value="${paciente.telefono}"required name="txtTelefono" maxlength="30"></input>
		<p class="add-text">Provincia</p> 
		<select name="txtProvincia" id="ddlProvincia">
		    <option>Buenos Aires</option>
		    <option>Cordoba</option>
		    <option>Santa Fe</option>
		</select>
		<p class="add-text">Localidad</p> 
		<select name="txtLocalidad" id="ddlLocalidad">
			<option>Tigre</option>
			<option>Pilar</option>
			<option>Benavidez</option>
		</select>
		<p class="add-text">Dirección</p> 
		<input type="text" class="add-input" value="${paciente.direccion}" required name="txtDireccion" maxlength="30"></input>
		<p class="add-text">Fecha de nacimiento</p> 
		<input class="add-input" type="date" value="${paciente.fechaNac}"required name="txtFechaNac" maxlength="30"></input>
		<p class="add-text">Email</p> 
		<input type="text" class="add-input" value="${paciente.email}" required name="txtEmail" maxlength="30"></input><br><br>
		<input type="submit" value="Modificar"></input>
	</form>
	</c:if>
	<label style="color: red; text-align: center;">${error}</label>
	<label style="text-align: center;">${message}</label>
	
	<script>
		var provinciaSelect = document.getElementById("ddlProvincia");
		var localidadSelect = document.getElementById("ddlLocalidad");
	
		var opcionesLocalidad = {
	    "Buenos Aires": ["Tigre", "Pilar", "Benavidez"],
	    "Cordoba": ["Montecristo", "La Calera", "Los Cedros"],
	    "Santa Fe": ["Rosario", "San Lorenzo", "Rafaela"]
		};
	
	    function cargarLocalidades() {
	        var provinciaSeleccionada = provinciaSelect.value;
	        localidadSelect.innerHTML = "";
	        var localidades = opcionesLocalidad[provinciaSeleccionada];
	        for(var i = 0; i < localidades.length; i++) {
	            var option = document.createElement("option");
	            option.text = localidades[i];
	            localidadSelect.add(option);
	        }
	    }
	    provinciaSelect.addEventListener("change", cargarLocalidades);
	</script>
</body>
</html>