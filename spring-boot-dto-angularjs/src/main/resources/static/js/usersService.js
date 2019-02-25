'use strict'

angular.module('demo.service', []).factory('UsersService', ["$http", "CONSTANTS",
function($http, CONSTANTS){
    var service = {};
    service.getUsersById = function(usersId){
        var url = CONSTANTS.getUserByIdUrl + usersId;
        return $http.get(url);
    }
    service.getAllUsers = function(){
        return $http.get(CONSTANTS.getAllUsers);
    }
    service.saveUsers = function(usersDto){
        return $http.post(CONSTANTS.saveUsers, usersDto);
    }
    return service;
}]);