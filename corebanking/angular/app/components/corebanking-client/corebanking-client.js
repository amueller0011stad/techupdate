'use strict';

angular.module('myApp.corebanking-client', ['ngResource'])

  .factory(
    'BankInfo',
    [
      '$resource',
      function($resource)
      {
        return $resource(
          '/corebanking-service/resources/bank/info',{}, 
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
          '/corebanking-service/resources/logon/:username',
          {
            password:'@password'
          },
          {
            'logon': {method:'POST'}
          });
      }
    ]);