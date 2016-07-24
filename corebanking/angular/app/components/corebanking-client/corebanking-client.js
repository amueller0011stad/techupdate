'use strict';

angular.module('myApp.corebanking-client', ['ngResource'])

.factory('BankInfo', ['$resource',
  function($resource)
  {
    /* TODO make that configurable */
    return $resource('/corebanking-service/resources/bank/info',{}, 
    {
      query: {method:'GET'}
    });
  }]);
