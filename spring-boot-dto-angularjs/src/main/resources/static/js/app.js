'use strict'

var demoApp = angular.module('demo', ['demo.controllers', 'demo.service']);

demoApp.constant("CONSTANTS", {

    getUserByIdUrl: "/api/v1/users/",

    getAllUsers: "/api/v1/users",

    saveUsers: "/api/v1/users"

});