var phonecatApp = angular.module('LunchTime', []);

phonecatApp.controller('CreatePersonController', function ($scope) {
  $scope.formData = {};
  $scope.processForm = function() {
  console.log($scope.formData);
  $scope.formData.email = "";
  };
});
