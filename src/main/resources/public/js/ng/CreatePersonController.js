'use strict';

var app = angular.module('LunchTime', []);

app.controller('CreatePersonController', ['$scope', '$http', function ($scope, $http) {
  $scope.formData = {};
  $scope.processForm = function() {
    $scope.submitting = true;
    $http.post('person', $scope.formData)
    .success(function () {
        $scope.formData.email = "";
        $scope.submitting = false;
    });
  };
}]);
