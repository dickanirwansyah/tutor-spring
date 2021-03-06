var app = angular.module('app', []);
 
console.log('angularjs mode');

// #######################
// POST CONTROLLER
// #######################
 
app.controller('postcontroller', function($scope, $http, $location) {
	
	// be used to decide for showing PostResults   
	$scope.postDivAvailable = false;
	// be used for decide for showing GetResults 
	$scope.getDivAvailable = false;
	
	// for Form-Customer
	$scope.formCust;
	
	// add List-Customer
	$scope.listCustomers = [];
	
	/*
	 * Add a Customer
	 */
	$scope.addCustomer = function(){
		if($scope.formCust){
			$scope.listCustomers.push($scope.formCust);	
			// reset Form Customer
			$scope.formCust = null;
		}else{
			alert("Please Fill Out Customer Info!");
		}
	}
	
	/*
	 * Delete a Customer item
	 */
	$scope.deleteItem = function(index){
		// remove item on angularjs model
		$scope.listCustomers.splice(index, 1);
	}
	
	/*
	 * Do post a List-Customer to Back-End server
	 */
	$scope.postListCustsFunc = function(){
		// post URL
		var url = $location.absUrl() + "api/customer/save";
		
		// prepare headers for posting
		var config = {
                headers : {
                	'Content-Type': 'application/json',
                	'Accept': 'text/plain'
                }
        }
		
		// prepare data for post messages
		var dataArr = $scope.listCustomers;
		
		// do posting
		$http.post(url, dataArr, config).then(function (response) {
			// turn on flag for post successfully
			$scope.postDivAvailable = true;
			
			$scope.postCust =  response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error Status: " +  response.statusText;
		});
		
		// reset List-Customer
		$scope.listCustomers = [];
	}
});
 
//#######################
//GET CONTROLLER
//#######################
 
app.controller('getcontroller', function($scope, $http, $location) {
	
	$scope.getfunction = function(){
		// get URL
		var url = $location.absUrl() + "api/customer/all";
		
		// do getting
		$http.get(url).then(function (response) {
			// turn on flag for get successfully
			$scope.getDivAvailable = true;
			
			$scope.response = response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error Status: " +  response.statusText;
		});
	}
});