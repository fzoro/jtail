<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Demo</title>
<script src="<%=request.getContextPath()%>/res/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/res/js/app/app.js"></script>
<script src="<%=request.getContextPath()%>/res/js/angular-1.0.1.min.js"></script>
<script src="<%=request.getContextPath()%>/res/lightbox/jquery.lightbox-0.5.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/lightbox/jquery.lightbox-0.5.css" media="screen" />


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/res/style/style.css" media="screen" />

<!-- Ativando o jQuery lightBox plugin -->
<script type="text/javascript">
	$(function() {
		$('#list-section a').lightBox({
			fixedNavigation : true
		});
	});
</script>


</head>
<body ng-app ng-controller="HomeController">
	<div id="page">
		<div id="container">
			<div id="header">
				<h1>Topo</h1>
				<div style="float:right">
					file: {{fileName}} | kb: {{kbytes}}
				</div>
				<input type="hidden" ng-model="off" />
			</div>
			<div id="console">
				{{console}}

			</div>

			<div class="clear"></div>

		</div>


		<div id="footer">
			<div id="footer">

				<div id="list-section">

				<ul class="list">
						<li ng-repeat="f in files">
							<button class="btn-file" ng-click="show(f)">{{f.name}}</button>
						</li>
				</ul>
					<!--
					<ul class="list">
						<li><a
							href="<%=request.getContextPath()%>/res/img/image1.jpg"
							title="For this example: $('#gallery a').lightBox();"> A<img
								src="<%=request.getContextPath()%>/res/img/thumb_image1.jpg"
								width="72" height="72" alt="" />
						</a></li>
						<li><a
							href="<%=request.getContextPath()%>/res/img/image2.jpg"
							title="For this example: $('#gallery a').lightBox();"> B<img
								src="<%=request.getContextPath()%>/res/img/thumb_image2.jpg"
								width="72" height="72" alt="" />
						</a></li>
						<li><a href="photos/image3.jpg"
							title="For this example: $('#gallery a').lightBox();"> C<img
								src="photos/thumb_image3.jpg" width="72" height="72" alt="" />
						</a></li>
						<li><a href="photos/image4.jpg"
							title="For this example: $('#gallery a').lightBox();"> S<img
								src="photos/thumb_image4.jpg" width="72" height="72" alt="" />
						</a></li>
						<li><a href="photos/image5.jpg"
							title="For this example: $('#gallery a').lightBox();"> E<img
								src="photos/thumb_image5.jpg" width="72" height="72" alt="" />
						</a></li>
					</ul>
					 -->
				</div>
			</div>
		</div>
	</div>

</body>
</html>