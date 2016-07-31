'use strict';

angular.module('myApp.logon',['ngRoute','myApp.credentials','myApp.corebanking-client'])

  .config(
    [
      '$routeProvider',
      function($routeProvider)
      {
        $routeProvider.when(
          '/logon',
          {
            templateUrl: 'logon/logon.html',
            controller: 'LogonCtrl'
          });
      }
    ])

  .controller(
    'LogonCtrl',
    [
      '$scope','username','token','Logon',
      function($scope,username,token,Logon)
      {
        
        $scope.sendCredentials = function()
        {
          Logon.logon(
            {
              username:$scope.usernameField
            },
            {
              password:$scope.passwordField
            },
            function(response)
            {
              token = response;
              $scope.username = response.username;
            });
        }
        
      }
    ]);