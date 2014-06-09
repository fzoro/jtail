<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="res/lib/bootstrap/assets/ico/favicon.ico">

<title>JTail</title>

<!-- Bootstrap core CSS -->
<link href="res/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<script src="res/js/app/app.js"></script>
<script src="res/lib/angular/js/angular-1.0.1.min.js"></script>
<script src="res/lib/jquery/js/jquery-1.11.0.min.js"></script>
<script src="res/lib/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="res/lib/select-bootstrap/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" type="text/css" href="res/lib/select-bootstrap/css/bootstrap-select.css">

<!-- Custom styles for this template -->
<!-- <link href="res/css/glyphicons.css" rel="stylesheet">-->
<link href="res/lib/bootstrap/css/navbar-fixed-top.css" rel="stylesheet">
<link href="res/css/jtail.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="res/lib/bootstrap/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript">
    $(window).on('load', function () {

        $('.selectpicker').selectpicker({
            'selectedText': 'cat'
        });

        // $('.selectpicker').selectpicker('hide');
    });
</script>
</head>

<body ng-app ng-controller="HomeController">


	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<span class="navbar-brand">Java tail</span>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-cllapse">
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">

						<select id="id_select" class="selectpicker" data-live-search="true" onclick="start(f)">
					        <option ng-repeat="f in files">{{f.name}}</option>
					    </select>
					    <select ng-model="files" ng-options="file.name group by color.shade for color in colors">
  						</select>
					    <button type="button" class="btn btn-danger navbar-btn">Parar</button>
         				<button type="button" class="btn btn-default navbar-btn">Limpar</button>
          				<button type="button" class="btn btn-info navbar-btn">Rolar</button>
						<input type="text" class="form-control" placeholder="Texto">
					</div>
					<button type="submit" class="btn btn-default">Destacar</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-cog" /></a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</div>


	<div class="container-fluid">
			<div class="col-md-12">
				 <div style="overflow: auto; height: 100%;">{{console}}</div>
			</div>
	</div>
	<hr />
	<footer>
		<p>&copy; Company 2014</p>
	</footer>
	<!-- /container -->
</body>
</html>
