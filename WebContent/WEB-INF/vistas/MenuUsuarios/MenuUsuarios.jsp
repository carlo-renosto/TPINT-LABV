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
<title>Usuarios - Menu</title>
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
		<h2 style="text-align: center;">Menu Usuarios</h2>
	</div>
	
	<form method="post" action="redirect_usuarios_filtro.html" class="form-filter">
		<p class="form-filter">Usuario</p> 
		<input type="text" class="add-input" name="txtNombre" maxlength="30"></input>
		<p class="form-filter">Contraseña</p>
		<input type="password" name="txtPassword" maxlength="30" style="width: 100px;"></input>
		<p class="form-filter">Tipo</p> 
		<select name="txtAdmin">
			<option value=""> </option>
			<option value="true">Admin</option>
			<option value="false">Medico</option>
		</select>
		<input type="submit" value="Filtrar"></input>
	</form><br>
	
	<label style="color: red; text-align: center;">${error}</label>
	
	<table id="table_id" border="1">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Contraseña</th>
                <th>Tipo</th>
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.usuario}</td>
                    <td>${usuario.contrasenia}</td>
					<c:if test="${usuario.admin}"><td>Admin</td></c:if>
					<c:if test="${!usuario.admin}"><td>Medico</td></c:if>
					<c:if test="${usuario.estado}"><td>Activo</td></c:if>
					<c:if test="${!usuario.estado}"><td>Dado de baja</td></c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <script type="text/javascript">
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
	
    <form method="get" action="redirect_usuarios_alta.html">
  		<input type="submit" value="Añadir usuario" style="margin-top: 20px; margin-left: 10px; display: block;">
	</form>
	<form method="get" action="redirect_usuarios_mod.html">
		<input type="submit" value="Editar usuario" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
	<form method="get" action="redirect_usuarios_baja.html">
		<input type="submit" value="Borrar usuario" style="margin-top: 20px; margin-left: 10px;"></input>
	</form>
</body>
</html>