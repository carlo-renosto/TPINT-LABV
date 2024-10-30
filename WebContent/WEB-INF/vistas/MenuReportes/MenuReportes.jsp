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
<title>Informes - Menu</title>
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
		<h2 style="text-align: center;">Menu Informes</h2>
	</div>
	
	
	<h3>Turnos (del 01/01/2024 al 01/03/2024)</h3>
	<table id="table_id" border="1">
        <thead>
            <tr>
            	<th>ID</th>
            	<th>Medico</th>
            	<th>Especialidad</th>
                <th>Paciente</th>
                <th>Dia</th>
                <th>Hora</th>
                <th>Observacion</th>
				<th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="turno" items="${turnos}">
                <tr>
                	<td>${turno.id}</td>
                	<td>${turno.medico.nombre} ${turno.medico.apellido}</td>
                	<td>${turno.medico.especialidad.nombre}</td>
                	<td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                    <td>${turno.dia}</td>
                    <td>${turno.horaInicio}:00-${turno.horaFin}:00</td>
                    <td>${turno.observacion}</td>
					<c:if test="${turno.estadoTurno != null && !turno.estadoTurno.isEmpty()}">
                    <td>${turno.estadoTurno}</td>
                    </c:if>
                    <c:if test="${turno.estadoTurno == null || turno.estadoTurno.isEmpty()}">
                    <td>Pendiente</td>
                    </c:if>   
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <h4>Porcentaje turnos presentes: ${cont1}%</h4>
    <h4>Porcentaje turnos ausentes: ${cont2}%</h4>
    <h4>Porcentaje turnos pendientes: ${cont3}%</h4>
	
	<form method="post" action="redirect_reportes_filtro.html" class="form-filter">
		<p class="form-filter">Medico</p>
		<select name="txtMedico">
			<option value=""></option>
			<c:forEach var="medico" items="${medicos}">
				<option value="${medico.legajo}">${medico.nombre} ${medico.apellido}</option>
			</c:forEach>
		</select>
		<p class="form-filter">Dia desde</p>
		<input type="date" name="txtDiaDesde">
		<p class="form-filter">hasta</p>
		<input type="date" name="txtDiaHasta">
		<p class="form-filter">Horario desde</p>
		<select name="txtHorarioDesde" id="txtHorarioDesde" onchange="cargarHorarioHasta()">
			<option></option>
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
		<p class="form-filter">Horario hasta</p>
		<select name="txtHorarioHasta" id="txtHorarioHasta">
			<option></option>
		</select>
		<p class="form-filter">Estado</p>
		<select name="txtEstado">
			<option value=""></option>
			<option>Presente</option>
			<option>Ausente</option>
		</select>
		<input type="submit" value="Generar"></input>
	</form>
	
	<table id="table_id2" border="1">
        <thead>
            <tr>
            	<th>ID</th>
            	<th>Medico</th>
            	<th>Especialidad</th>
                <th>Paciente</th>
                <th>Dia</th>
                <th>Hora</th>
                <th>Observacion</th>
				<th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="turno" items="${turnosInforme}">
                <tr>
                	<td>${turno.id}</td>
                	<td>${turno.medico.nombre} ${turno.medico.apellido}</td>
                	<td>${turno.medico.especialidad.nombre}</td>
                	<td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                    <td>${turno.dia}</td>
                    <td>${turno.horaInicio}:00-${turno.horaFin}:00</td>
                    <td>${turno.observacion}</td>
                    <c:if test="${turno.estadoTurno != null && !turno.estadoTurno.isEmpty()}">
                    <td>${turno.estadoTurno}</td>
                    </c:if>
                    <c:if test="${turno.estadoTurno == null || turno.estadoTurno.isEmpty()}">
                    <td>Pendiente</td>
                    </c:if>   
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <h4>Porcentaje turnos presentes: ${cont4}%</h4>
    <h4>Porcentaje turnos ausentes: ${cont5}%</h4>
    <h4>Porcentaje turnos pendientes: ${cont6}%</h4>
    
    <label style="text-align: center;">${message}</label>
	
	<script>
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
		$(document).ready(function() {
	        $('#table_id2').DataTable({
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
</body>
</html>