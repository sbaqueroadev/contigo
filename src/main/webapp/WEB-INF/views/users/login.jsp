<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="../webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body ng-app="loginApp" class="row">
	<form class="col-md-offset-4 col-md-4" action="../login" method="post"
		ng-controller="loginController" ng-submit="login($event)">
		<div class="form-group">
			<label for="username"> Usuario: </label> 
			<input id="username"
				class="form-control" type="text" ng-model="user.username"
				name="username" />
		</div>
		<div class="form-group">
			<label for="password"> Contrase&ntilde;a: </label>
			<input id="password" type="text" class="form-control"
				ng-model="user.password" name="password" />
		</div>
		<input class="col-md-offset-3 col-md-6 btn btn-success" type="submit" value="Ingresar">
		
	</form>
	<script type="text/javascript" src="../webjars/jquery/3.2.1/jquery.js"></script>
	<script type="text/javascript"
		src="../webjars/angular/1.6.7-1/angular.js"></script>

	<script src="../resources/js/users/login.js" type="text/javascript"></script>
</body>
</html>