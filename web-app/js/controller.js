var app = angular.module('jft', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: '../view/list.html',
            controller: 'ListCtrl'}).
        when('/AddNewProduct', {
            templateUrl: '../view/save.html',
            controller: 'SaveCtrl'}).
        when('/show/:id', {
            templateUrl: '../view/show.html',
            controller: 'ShowCtrl'}).
        when('/edit/:id', {
            templateUrl: '../view/save.html',
            controller: 'SaveCtrl'});
});

app.controller("ListCtrl", function ($scope, $http) {
    $scope.products = {
        product: [
            {name: '', category: '', serialNumber: '', price: 0, description: '', dateCreated: null, lastUpdated: null, id: 0}
        ]
    };

    angular.element(document).ready(function () {
        $http({method: 'GET', url: '/product/index'}).
            success(function (data, status, headers, config) {
                data.forEach(function (value, key) {
                    $scope.products.product.push(value);
                });
            }).
            error(function (data, status, headers, config) {
                console.error("Status=" + status + " Data=" + data);
            });
    });
});

app.controller("SaveCtrl", function ($scope, $http, $location, $routeParams) {
    var id = $routeParams.id;
    if (id) {
        $scope.actionName = 'update';
        $http({method: 'GET', url: '/product/show/' + id}).
            success(function (data, status, headers, config) {
                $scope.product = data;
            }).
            error(function (data, status, headers, config) {
                console.error("Status=" + status + " Data=" + data);
            });
    } else {
        $scope.actionName = 'create';
        $scope.product = {name: '', category: '', serialNumber: '', price: 0, description: ''};
    }

    $scope.saveProduct = function () {
        $http.post('/product/save', $scope.product).
            success(function (data, status, headers, config) {
                if (data.status == 'error') {
                    alert(data.message)
                } else {
                    $location.path('/show/' + data.id);
                }
            }).
            error(function (data, status, headers, config) {
                console.error("Status=" + status + " Data=" + data);
            });
    }
});

app.controller("ShowCtrl", function ($scope, $routeParams, $http, $location) {
    var id = $routeParams.id;
    $http({method: 'GET', url: '/product/show/' + id}).
        success(function (data, status, headers, config) {
            $scope.product = data;
        }).
        error(function (data, status, headers, config) {
            console.error("Status=" + status + " Data=" + data);
        });

    $scope.deleteProduct = function (index) {
        var result = confirm('Are you sure?');
        if (result) {
            $http({method: 'DELETE', url: '/product/delete/' + id}).
                success(function (data, status, headers, config) {
                    $location.path("/");
                }).
                error(function (data, status, headers, config) {
                    console.error("Status=" + status + " Data=" + data);
                });
        }
    };
});
