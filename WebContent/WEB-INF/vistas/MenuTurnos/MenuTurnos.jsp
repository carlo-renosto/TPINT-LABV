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
<title>Turnos - Menu</title>
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
		<h2 style="text-align: center;">Menu Turnos</h2>
	</div>
	
	<form method="post" action="redirect_turnos_filtro.html" class="form-filter">
		<p class="form-filter">ID turno</p>
		<input type="text" class="add-input" name="txtTurno" maxlength="30"></input>
		<p class="form-filter">Legajo medico</p> 
		<input type="text" class="add-input" name="txtLegajo" maxlength="30"></input>
		<!-- 
		<p class="form-filter">Especialidad</p> 
		<select name="txtEspecialidad">
			<option value=""></option>
			<c:forEach var="especialidad" items="${especialidades}">
				<option value="${especialidad.id}">${especialidad.nombre}</option>
			</c:forEach>
		</select>
		 -->
		<p class="form-filter">DNI paciente</p> 
		<input type="text" class="add-input" name="txtDni" maxlength="30"></input>
		<p class="form-filter">Dia</p> 
		<select name="txtDia">
			<option value=""></option>
			<option>Lunes</option>
			<option>Martes</option>
			<option>Miercoles</option>
			<option>Jueves</option>
			<option>Viernes</option>
			<option>Sabado</option>
			<option>Domingo</option>
		</select>
		<p class="form-filter">Horario desde</p> 
		<select name="txtHorarioDesde" id="txtHorarioDesde" onchange="cargarHorarioHasta()">
			<option> </option>
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
			<option> </option>
		</select>
		<p class="form-filter">Estado</p>
		<select name="txtEstado">
			<option value=""></option>
			<option>Presente</option>
			<option>Ausente</option>
		</select>
		<input type="submit" value="Filtrar"></input>
	</form><br>
	
	<label style="color: red; text-align: center;">${error}</label>
	<label style="text-align: center;">${message}</label>
	
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
	
	<form method="get" action="redirect_turnos_asignar.html"> 
		<input type="submit" value="Asignar turno" style="margin-top: 20px; margin-left: 10px; display: block;"></input>
	</form>
	<form method="get" action="redirect_turnos_cancelar.html"> 
		<input type="submit" value="Cancelar turno" style="margin-top: 20px; margin-left: 10px; display: block;"></input>
	</form>
	
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
    </script>
</body>
</html>