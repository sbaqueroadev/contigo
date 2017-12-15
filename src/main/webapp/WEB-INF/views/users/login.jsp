<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body ng-app="loginApp">
<form action="../login" method="post" ng-controller="loginController" ng-submit="login($event)">
Usuario:<input type="text" ng-model="user.username" name="username"/><br>
Contraseña:<input type="text" ng-model="user.password" name="password"/>
<input type="submit" value="Enviar">
</form>
<script type="text/javascript" src="../webjars/jquery/3.2.1/jquery.js"></script>
  <script type="text/javascript" src="../webjars/angular/1.6.7-1/angular.js"></script>

  <script src="../resources/js/users/login.js" type="text/javascript"></script>
</body>
</html>