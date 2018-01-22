<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>

<head>
<meta charset="UTF-8" lang="es">
<title>Class</title>
<link rel="stylesheet" href="../../resources/css/classstyle.css"
	type="text/css" />
<link rel="stylesheet"
	href="../../webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</script>
</head>
<body ng-app="classApp" width="100%" height="100%"
	ng-controller="boardController">
	<!--BOARD-->
	<canvas id="canvas"></canvas>
	<div id="tools-div">
		<div class="tool" ng-repeat='tool in tools' ng-click="selectTool(tool)">{{tool.name}}</div>
		<div id="clear" class="tool" ng-click="selectTool({order:'clear'})">Borrar</div>
	</div>
	<div id="sub-tools-div">
		<div class="sub-tool" ng-repeat='subtool in selectedTool.subtools' 
		ng-click="selectSubTool(subtool)">{{subtool.name}}
			<select ng-model="subtool.subtoolItemSelected" ng-if='subtool.type == "options"' 
			ng-options="item.name for item in subtool.items"/>
			<input ng-model="subtool.subtoolItemSelected" type="number" ng-if='subtool.type == "range"' min="{{subtool.items[0]}}"
			 max = "{{subtool.items[1]}}"/>
		</div>
		
	</div>
	<!--CHAT-->
	<div id="chat-section" ng-controller="chatController">
		<div id="chat-record">
			<div class="row" ng-repeat="message in messages track by $index">
				<div class="chat-record-message {{message.sender}}">
					<p>{{message.msg}}</p>
				</div>
			</div>
		</div>
		<div id="chat-input">
			<div class="row">
				<div class="col-md-9 form-group">
					<textarea ng-model="newMessage"></textarea>
				</div>
				<div class="col-md-3 form-group">
					<button class="btn btn-success" ng-click="sendMessage()"
						value="Enviar">Enviar</button>
				</div>
			</div>
		</div>
		<div class="chat-opener" ng-click="openCloseChat()"><<</div>
	</div>
	<!--VIDEO-->
	<div ng-controller="videoController">
		<video id="remoteVideo" ng-click="openCloseVideo()" autoplay>remote
			video
		</video>
		<video id="localVideo" autoplay muted></video>
		<div id="videoButtons" ng-click="openCloseVideo()"></div>
	</div>
	<!--ENF-->

	<c:if test="${not empty role}">
		<script type="text/javascript">
  			var globalRole = '${role}';
  			<c:if test="${not empty classId}">
  			var classId = '${classId}';
  			</c:if>
  		</script>
	</c:if>
	<script type="text/javascript" src="../../webjars/jquery/3.2.1/jquery.js"></script>
	<script type="text/javascript"
		src="../../webjars/sockjs-client/1.1.4/dist/sockjs.js"></script>
	<script type="text/javascript"
		src="../../webjars/stomp-websocket/2.3.4/lib/stomp.js"></script>
	<script type="text/javascript"
		src="../../webjars/angular/1.6.7-1/angular.js"></script>

	<script src="../../resources/js/classsource.js" type="text/javascript"></script>
	<script src="../../resources/js/cartesian.js" type="text/javascript"></script>


</body>