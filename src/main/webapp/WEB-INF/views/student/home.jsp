<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="../webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body class="row">
	<c:if test="${not empty student}">
		<div class="col-md-12">
			<h3>Hola ${student.name}</h3>
		</div>
		<c:if test="${not empty student.classes}">
			<div class="col-md-6">
				<h4>Clases próximas</h4>
				<table class="col-md-12">
					<thead>
						<tr>
							<th>Fecha
							<th>Tema
					</thead>
					<tbody>
						<c:forEach items="${student.classes}" var="cClass">
							<c:if test="${cClass.status == 'active'}">
								<tr>
									<td>${cClass.date}
									<td>${cClass.subject.name}
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<h4>Clases Solicitadas</h4>
				<table class="col-md-12">
					<thead>
						<tr>
							<th>Fecha
							<th>Tema
					</thead>
					<tbody>
						<c:forEach items="${student.classes}" var="cClass">
							<c:if test="${cClass.status == 'asked'}">
								<tr>
									<td>${cClass.date}
									<td>${cClass.subject.name}
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>
	<c:if test="${not empty currentClass}">
		<p style="padding-top:20px;" class="col-md-12">
			<br> <br> <br> <a class="col-md-12"
				href="../board/home/${currentClass.id}">Ir a clase</a>
		</p>
	</c:if>
</body>
</html>