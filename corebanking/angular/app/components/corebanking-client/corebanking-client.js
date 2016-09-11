'use strict';

angular.module('myApp.corebanking-client', ['ngResource'])

  .factory(
    'BankInfo',
    [
      '$resource',
      function($resource)
      {
        return $resource(
          '/corebanking-login-service/info',{}, 
          {
            query: {method:'GET'}
          });
      }
    ])

  .factory(
    'Logon',
    [
      '$resource',
      function($resource)
      {
        return $resource(
          '/corebanking-login-service/logon/:username',
          {
            password:'@password'
          },
          {
            'logon': {method:'POST'}
          });
      }
    ]);