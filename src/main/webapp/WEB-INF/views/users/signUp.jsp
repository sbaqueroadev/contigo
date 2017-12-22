<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro</title>
<link rel="stylesheet"
	href="../webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body>
	<form class="col-md-offset-4 col-md-4" action="../users/sign-up"
		method="post">
		<div class="form-group">
			<label for="user"> Usuario: </label> <input id="user"
				class="form-control" type="text" name="user" />
		</div>
		<div class="form-group">
			<label for="pass"> Contrase&ntilde;a: </label> <input id="pass"
				type="password" class="form-control" name="pass" />
		</div>
		<div class="form-group">
			<label for="role"> Role: </label> 
			<select id="role" class="form-control" name="role" >
			<option value="student">Estudiante</option>
			<option value="teacher">Profesor</option>
			</select>
		</div>
		<input class="col-md-offset-3 col-md-6 btn btn-success" type="submit"
			value="Registrar">
		<div class="row"> 
			<a class="col-md-offset-3 col-md-6" href="../users/access" >¿Usuario registrado?</a>
		</div>	
	</form>
</body>
</html>