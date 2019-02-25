'use strict'

var module = angular.module('demo.controllers', []);

module.controller("usersController", ["$scope", "UsersService",

    function($scope, UsersService) {

        $scope.usersDto = {
            usersId: 3,
            usersName: null,
            skillDtos: []
        };

        $scope.skills = [];

        $scope.allUsers = {};

        UsersService.getUsersById(1).then(function(value) {

            console.log(value.data);

        }, function(reason) {

            console.log("error occured");

        }, function(value) {

            console.log("no callback");

        });

        $scope.saveUsers = function() {

            $scope.usersDto.skillDtos = $scope.skills.map(skill => {
                return {
                    skillId: 7,
                    skillName: skill
                };
            });

            UsersService.saveUsers($scope.usersDto).then(function() {

                console.log("works");

                UsersService.getAllUsers().then(function(value) {

                    $scope.allUsers = value.data;

                }, function(reason) {

                    console.log("error occured");

                }, function(value) {

                    console.log("no callback");

                });

                $scope.skills = [];

                $scope.usersDto = {
                    usersId: '',
                    usersName: '',
                    skillDtos: []

                };

            }, function(reason) {

                console.log("error occured");

            }, function(value) {

                console.log("no callback");

            });

        }

    }

]);