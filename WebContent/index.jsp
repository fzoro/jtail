<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Java Tail</title>
<script src="<%=request.getContextPath()%>/res/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/res/js/app/app.js"></script>
<script src="<%=request.getContextPath()%>/res/js/angular-1.0.1.min.js"></script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/style/style.css" media="screen" />


</head>
<body ng-app ng-controller="HomeController">
	<div id="page">
		<div id="container">
			<div id="header" >
				<div style="float:left"><img id="logo" src="<%=request.getContextPath()%>/res/img/logo.png" /></div>
				<div id="actions">
					<h3>File: {{fileName}} | KB: {{kbytes}}</h3>
					<img id="actClear" src="<%=request.getContextPath()%>/res/img/clear.png" ng-click="clear()" alt="Clear console" title="Clear console" />
					<img id="actPause" src="<%=request.getContextPath()%>/res/img/pause.png" ng-click="pause()" alt="Pause tail" title="Pause tail" />
					<img style="display:none" id="actPlay" src="<%=request.getContextPath()%>/res/img/play.png" ng-click="resume()" alt="Resume tail" title="Resume tail" />
				</div>
				<input type="hidden" ng-model="off" />
			</div>
			<div id="face">
				<div id="console">
					{{console}}
				</div>
			</div>
			<div class="clear"></div>

		</div>


		<div id="footer">
			<div id="footer">

				<div id="list-section">

					<ul class="list">
						<li ng-repeat="f in files">
							<button id="btn_{{f.name}}" class="btn-file" ng-click="start(f)" title="tail it">{{f.name}}</button>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>