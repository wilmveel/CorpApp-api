angular.module("App", []);

angular.module("App").controller("IndexController", function($scope, $http, $log) {

	$scope.signup = function() {

		var data = {
			"email" : $scope.email,
			"corpKey" : $scope.corpKey
		}
		
		$log.debug("Register", data);
		
		$http.post("../rest/user/register", data).then(function(){
			$log.debug("Register succesfull");
		});

	}

});