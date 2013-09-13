function HomeController($scope, $window, $http, $log){


	var init = function(){
		$http.get('/jtail/home?action=get-files').success(function(data){
			$scope.files = data;
		});
	};


	var fileInterval = null;

	$scope.show = function(f){

		if(fileInterval){
			$window.clearInterval(fileInterval);
			$log.info('Stop read: ' + f.name);
		}

		tail(f);
		fileInterval = $window.setInterval(function(){tail(f)}, 5000);

	}

	var tail = function(f){
		var url = '/jtail/tail?fileName=' + f.name + '&off=' + $scope.off;

		$http.get(url).success(function(data){
			$scope.fileName = data.fileName;

			if( data.off < $scope.off) {
				//restart
				$scope.console = data.value;
			}else{
				//append
				$scope.console += data.value;
			}

			$scope.off = data.off;
			var kbytes = data.off / 1024;
			$scope.kbytes = kbytes.toFixed(2);

		});
	}



	init();
}