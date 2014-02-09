'use strict';

describe('CreatePersonController', function () {
    var $httpBackend, $scope, subject;
    var $injector = angular.injector();

    beforeEach(module('LunchTime'));

    beforeEach(inject(function ($injector, $rootScope, $controller) {
        $httpBackend = $injector.get('$httpBackend')
        $scope = $rootScope.$new();

        subject = $controller('CreatePersonController', {'$scope': $scope });
    }));

    afterEach(function() {
        $httpBackend.verifyNoOutstandingRequest();
    });

    describe('submitting the form', function() {
        beforeEach(function() {
            $scope.formData.email = "joe@lightside.com";
            $httpBackend.expectPOST('person', {email: "joe@lightside.com"})
                .respond({});
            $scope.processForm();
        });

        it('disables the button', function() {
            expect($scope.submitting).toBe(true);
        });

        describe('when the post returns', function() {
            beforeEach(function() {
                $httpBackend.flush();
            });

            it('clears the email field', function() {
                expect($scope.formData.email).toBe("");
            });

            it('enables the button', function() {
                expect($scope.submitting).toBe(false);
            });
        });
    });
});