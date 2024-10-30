<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Turno - Asignar</title>
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
		<h2 style="text-align: center;" >Asignar Turno</h2>
	</div>
	
	<form method="post" action="turno_asignar.html" class="add-form">
		<p class="add-text">Especialidad</p>
		<select id="txtEspecialidad" name="txtEspecialidad" onchange="cargarMedicos()">
			<c:forEach var="especialidad" items="${especialidades}">
				<option value="${especialidad.id}">${especialidad.nombre}</option>
			</c:forEach>
		</select>
		
		<p class="add-text">Medico</p>
		<select id="txtMedico" name="txtMedico" onchange="cargarMedicosHoras()">
			<c:forEach var="medico" items="${medicos}">
				<option value="${medico.legajo}">${medico.nombre} ${medico.apellido}</option>
			</c:forEach>
		</select>
		
		<p class="add-text">Paciente</p>
		<select name="txtPaciente">
			<c:forEach var="paciente" items="${pacientes}">
				<option value="${paciente.dni}">${paciente.nombre} ${paciente.apellido}</option>
			</c:forEach>
		</select>
		
		<p class="add-text">Dia</p> 
		<input class="add-input" type="date" required name="txtDia" maxlength="30"></input>
		
		<p class="add-text">Horario</p>
		<select id="txtHorario" name="txtHorario">
			<c:forEach var="hora" items="${horas}">
				<option value="${hora}">${hora}</option>
			</c:forEach>
		</select><br><br>
		<input type="submit" value="Asignar"></input>
	</form>
	<label style="color: red; text-align: center;">${error}</label>
	<label style="text-align: center;">${message}</label>
	
	<script>
		function cargarMedicos() {
		    var especialidad = document.getElementById("txtEspecialidad").value;
		    
		    fetch("/TPINT_GRUPO_5_LAB4/traer_medicos_especialidad.json?especialidad=" + encodeURIComponent(especialidad), {
		        headers: {
		            'Accept': 'application/json'
		        }
		    })
		    .then(response => {
		        if (!response.ok) {
		            throw new Error('Respuesta de la red incorrecta: ' + response.statusText);
		        }
		        return response.json();
		    })
		    .then(data => {
		        var medicoSelect = document.getElementById("txtMedico");
		        medicoSelect.innerHTML = '';
		        
		        data.forEach(medico => {
		            var option = document.createElement("option");
		            option.value = medico.legajo;
		            option.text = medico.nombre + ' ' + medico.apellido;
		            medicoSelect.appendChild(option);
		            
		        });
		        cargarMedicosHoras();
		    })
		    .catch(error => {
		        console.error('Error:', error);
		    });
		}
		    
		function cargarMedicosHoras() {
		    var medico = document.getElementById("txtMedico").value;
		    
		    fetch("/TPINT_GRUPO_5_LAB4/traer_turnos_medico_horas.json?medico=" + encodeURIComponent(medico), {
		        headers: {
		            'Accept': 'application/json'
		        }
		    })
		    .then(response => {
		        if (!response.ok) {
		            throw new Error('Respuesta de la red incorrecta: ' + response.statusText);
		        }
		        return response.json();
		    })
		    .then(data => {
		        var horarioSelect = document.getElementById("txtHorario");
		        horarioSelect.innerHTML = '';
		        
		        data.forEach(hora => {
		            var option = document.createElement("option");
		            option.value = hora;
		            option.text = hora;
		            horarioSelect.appendChild(option);
		        });
		    })
		    .catch(error => {
		        console.error('Error:', error);
		    });
		}
	</script>
</body>
</html>