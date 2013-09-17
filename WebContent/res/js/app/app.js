function HomeController($scope, $window, $http, $log){


	var

		fileInterval,
		autoScroll = true,
		current,
		$ = jQuery
	;



	$scope.changeAutoscroll = function(){
		autoScroll = !autoScroll;
		var imgSrc = autoScroll ? '/jtail/res/img/autoscroll.png' : '/jtail/res/img/autoscroll_not.png';
		$('#actScroll').attr('src',imgSrc);
	}

	$scope.clear= function(){
		$scope.console = '';
	}

	$scope.resume= function(){
		$scope.start(current);
	}


	$scope.pause = function(){
		stop();
		$('#actPause').css('display','none');
		$('#actPlay').css('display','inline');
	}



	$scope.start = function(f){

		stop();
		$('#actions').css('display','inline-block');
		$('#actPause').css('display','inline');
		$('#actPlay').css('display','none');

		// hold current file to tail
		if( !current || current.name != f.name ){

			if( current ){
				$('#btn_'+current.name).attr('class','btn-file');
			}

			$('#btn_'+f.name).attr('class','btn-file-active');

			current = f;
			$scope.console = "";
		}

		// taaaaailll!!!
		tail(f);

		// tail interval 1.5 secs
		fileInterval = $window.setInterval(function(){tail(f)}, 1500);

	}


	var init = function(){
		$http.get('/jtail/home?action=get-files').success(function(data){
			$scope.files = data;
		});
	};

	var stop = function(){

		if(fileInterval){
			$window.clearInterval(fileInterval);
			$log.info('Stop current tail.');
		}

	}


	/**
	*
	* Tail function: Call the url: <host>/jtail/tail/fileName?=<fileName>&off=<off>
	* store size bytes[] in {{off}}, value(txt) in {{console}} and off to kBytes in {{kbytes}}
	*/
	var tail = function(f){
		var url = '/jtail/tail?fileName=' + f.name + '&off=' + $scope.off;

		$http.get(url).success(function(data){
			$scope.fileName = data.fileName;

			var append = data.off >= $scope.off;
			appendConsole(data.value, append);

			$scope.off = data.off;
			var kbytes = data.off / 1024;
			$scope.kbytes = kbytes.toFixed(2);
		});
	}

	var appendConsole = function (value, append){

		if( !$scope.console || $scope.console.length < 1 || !append) {

			//restart
			$scope.console = '\n--> JTail console!!! ;P\n\n';
		}
		$scope.console += value;

		if( autoScroll ){
			$('#console').scrollTop($('#console')[0].scrollHeight);
		}
	}

	init();
}