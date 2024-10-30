<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/styles.css?v=1" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<title>Pacientes - Menu</title>
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
		<h2 style="text-align: center;">Menu Pacientes</h2>
	</div>
	
	<form method="post" action="redirect_pacientes_filtro.html">
		<p class="form-filter">DNI </p> 
		<input type="text" class="add-input" name="txtDni" maxlength="30"></input>
		<p class="form-filter">Nombre</p> 
		<input type="text" class="add-input" name="txtNombre" maxlength="30"></input>
		<p class="form-filter">Apellido</p> 
		<input type="text" class="add-input" name="txtApellido" maxlength="30"></input>
		<p class="form-filter">Teléfono</p> 
		<input type="text" class="add-input" name="txtTelefono" maxlength="30"></input>
		<p class="form-filter">Provincia</p> 
		<select name="txtProvincia" id="ddlProvincia">
			<option> </option>
		    <option>Buenos Aires</option>
		    <option>Cordoba</option>
		    <option>Santa Fe</option>
		</select>
		<p class="form-filter">Localidad</p> 
		<select name="txtLocalidad" id="ddlLocalidad">
			<option> </option>
			<option>Tigre</option>
			<option>Pilar</option>
			<option>Benavidez</option>
		</select>
		<p class="form-filter">Dirección</p> 
		<input type="text" class="add-input" name="txtDireccion" maxlength="30"></input>
		<p class="form-filter">Email</p> 
		<input type="text" class="add-input" style="display: inline-block;" name="txtEmail" maxlength="30"></input>
		<input type="submit" value="Filtrar"></input>
	</form>
	
	<label style="color: red; text-align: center;">${error}</label>
	
	<table id="table_id" border="1">
        <thead>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Telefono</th>
                <th>Provincia</th>
                <th>Localidad</th>
                 <th>Direccion</th>
                <th>Fecha Nacimiento</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="paciente" items="${pacientes}">
                <tr>
                	<td>${paciente.dni}</td>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.apellido}</td>
                    <td>${paciente.telefono}</td>
                    <td>${paciente.provincia}</td>
                    <td>${paciente.localidad}</td>
                    <td>${paciente.direccion}</td>
                    <td>${paciente.fechaNac}</td>
                    <td>${paciente.email}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <script type="text/javascript">
	    var provinciaSelect = document.getElementById("ddlProvincia");
		var localidadSelect = document.getElementById("ddlLocalidad");
	
		var opcionesLocalidad = {
		"": [],
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
    
	    $(document).ready(function() {
	        $('#table_id').DataTable({
	            paging: true, // Habilita la paginación
	            pageLength: 5, // Define la cantidad de filas por página
	            searching: false,
	            language: {
	                lengthMenu: 'Mostrar _MENU_ registros por página',
	                zeroRecords: 'No se encontraron resultados',
	                info: 'Mostrando página _PAGE_ de _PAGES_',
	                infoEmpty: 'No hay registros disponibles',
	                infoFiltered: '(filtrado de _MAX_ registros totales)',
	                paginate: {
	                    first: 'Primera',
	                    last: 'Última',
	                    next: 'Siguiente',
	                    previous: 'Anterior'
	                }
	            }
	        });
	    });
	</script>
	
	<form method="get" action="redirect_pacientes_alta.html">
  		<input type="submit" value="Añadir paciente" style="margin-top: 20px; margin-left: 10px; display: block;">
	</form>
	<form method="get" action="redirect_pacientes_mod.html">
		<input type="submit" value="Editar paciente" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
	<form method="get" action="redirect_pacientes_baja.html">
		<input type="submit" value="Borrar paciente" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
</body>
</html>