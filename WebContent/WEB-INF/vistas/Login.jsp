<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clinica - Login</title>
</head>
<body>
	<h2 style="text-align: center;">Login</h2>
	<form method="post" action="login.html" style="text-align: center;">
		<p class="add-text">Usuario</p> <input type="text" required name="txtUser" maxlength="30" style="width: 100px;"></input><br>
		<p class="add-text">Contraseña</p> <input type="password" required name="txtPassword" maxlength="30" style="width: 100px;"></input><br><br>
		<input type="submit" value="Ingresar"></input><br>
		<label style="color: red;">${error}</label>
	</form>
</body>
</html>