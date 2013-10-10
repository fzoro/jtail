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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/style/jquery.pageslide.css" media="screen" />
</head>

	<body ng-app ng-controller="HomeController">
		<script src="<%=request.getContextPath()%>/res/js/jquery.pageslide.min.js"></script>
		<div id="page">
			<div id="container">
				<div id="header" >
					<div style="float:left">
						<img id="logo" src="<%=request.getContextPath()%>/res/img/logo.png" />
						<a href="#modal" class="second">Link text</a>
					</div>
					<div id="actions">
						<h3>File: {{fileName}} | KB: {{kbytes}}</h3>
						<img id="actScroll" src="<%=request.getContextPath()%>/res/img/autoscroll.png" ng-click="changeAutoscroll()" alt="Auto scroll" title="Auto scroll" />
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

		<div id="modal" style="display:none">
	    	<h2>Files</h2>
	    	<div id="persistFields" style="display:inline">
		    	<form ng-repeat="f in files">
			    	<input id="name" name="name" type="text" ng-model="f.name" />
			    	<input id="path" name="path" type="text" ng-model="f.path" />
			    	<input id="maxBuffer" name="maxBuffer" type="number" ng-model="f.maxBuffer" style="width:50px" />
			    	<a href="#" ng-click="addFile(f)" class="btn">Save</a>
			    </form>
	    	</div>
			<!--
			<ul>
				<li ng-repeat="f in files">
					{{f.name}} - <a href="#" ng-click="editFile(f)" class="btn">Edit</a> or <a href="#" ng-click="deleteFile(f)" class="btn">Delete</a>
				</li>
			</ul>-->

	    	<a class="btn" href="javascript:$.pageslide.close()">Close</a>
		</div>

		<script type="text/javascript">
			$(".second").pageslide({ direction: "right", modal: true });
		</script>

	</body>
</html>