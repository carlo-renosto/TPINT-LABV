<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/styles.css?v=1" rel="stylesheet" type="text/css">
<title>Medico - Alta</title>
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
		<h2 style="text-align: center;" >Nuevo Médico</h2>
	</div>

	<form method="post" action="medico_alta.html" class="add-form">
		<p class="add-text">Legajo</p> 
		<input type="text" class="add-input" required name="txtLegajo" maxlength="30"></input>
		<p class="add-text">Nombre</p> 
		<input type="text" class="add-input" required name="txtNombre" maxlength="30"></input>
		<p class="add-text">Apellido</p> 
		<input type="text" class="add-input" required name="txtApellido" maxlength="30"></input>
		<p class="add-text">Sexo</p> 
		<select name="txtSexo">
			<option>M</option>
			<option>F</option>
		</select>
		<p class="add-text">Fecha de nacimiento</p> 
		<input type="date" class="add-input" required name="txtFechaNac" maxlength="30"></input>
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
		<input type="text" class="add-input" required name="txtDireccion" maxlength="30"></input>
		<p class="add-text">Email</p> 
		<input type="text" required class="add-input" name="txtEmail" maxlength="30"></input>
		<p class="add-text">Teléfono</p> 
		<input type="text" class="add-input" required name="txtTelefono" maxlength="30"></input>
		<p class="add-text">Dia</p> 
		<select name="txtDia">
			<option>Lunes</option>
			<option>Martes</option>
			<option>Miercoles</option>
			<option>Jueves</option>
			<option>Viernes</option>
			<option>Sabado</option>
			<option>Domingo</option>
		</select>
		<p class="add-text">Horario desde</p> 
		<select name="txtHorarioDesde" id="txtHorarioDesde" onchange="cargarHorarioHasta()">
			<option>6</option>
			<option>7</option>
			<option>8</option>
			<option>9</option>
			<option>10</option>
			<option>11</option>
			<option>12</option>
			<option>13</option>
			<option>14</option>
			<option>15</option>
			<option>16</option>
			<option>17</option>
			<option>18</option>
			<option>19</option>
			<option>20</option>
		</select>
		<p class="add-text">Horario hasta</p>
		<select name="txtHorarioHasta" id="txtHorarioHasta">
			<option>7</option>
			<option>8</option>
			<option>9</option>
			<option>10</option>
			<option>11</option>
			<option>12</option>
			<option>13</option>
			<option>14</option>
			<option>15</option>
			<option>16</option>
			<option>17</option>
			<option>18</option>
			<option>19</option>
			<option>20</option>
		</select>
		<p class="add-text">Especialidad</p>
		<select name="txtEspecialidad">
			<c:forEach var="especialidad" items="${especialidades}">
				<option value="${especialidad.id}">${especialidad.nombre}</option>
			</c:forEach>
		</select>
		<p class="add-text">Usuario</p> 
		<input type="text" class="add-input" required name="txtUsuario" maxlength="30"></input><br><br>
		<input type="submit" value="Agregar"></input>
	</form>
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
	    
	    function cargarHorarioHasta() {
			let horarioDesde = document.getElementById("txtHorarioDesde").value;
			let horarioHasta =  document.getElementById("txtHorarioHasta");			
			horarioHasta.innerHTML = "";
			
			for(let i=parseInt(horarioDesde)+1;i<=20;i++) {
    			let option = document.createElement("option");
    			option.text = i;
    			horarioHasta.add(option);
    		}
		}
	</script>
</body>
</html>