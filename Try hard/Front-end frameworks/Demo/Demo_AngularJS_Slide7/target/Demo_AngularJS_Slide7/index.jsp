<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>
<body ng-app="myapp">
    <div ng-controller="personCtrl">
        <p>Họ và tên: {{fullname | uppercase}}</p>
        <p>{{value|number:5}}</p>
        <p>{{value2|currency:'VNĐ':3}}</p>
        <p>{{value3|date:'dd-MM-yyyy a'}}</p>
        <p>{{user|json}}</p>

        <br><hr><br>

        <div>
            <ul><li ng-repeat="item in mang | limitTo : 3 : 2">{{item}}</li></ul>
            <span ng-repeat="item in chuoi | limitTo : 3">{{item}}</span>
        </div>

        <br><hr><br>

        <div>
            <ul><li ng-repeat="item in mang | orderBy : '$index' : true">{{item}}</li></ul>
            <ul><li ng-repeat="item in mang | orderBy">{{item}}</li></ul>
        </div>

        <br><hr><br>

        <div>
            <ul>
                <li ng-repeat="x in cars | filter : 'A'">{{x}}</li>
            </ul>
        </div>

        <br><hr><br>

        <div>
            <h3>{{name | pretty}}</h3>
        </div>

        <br><hr><br>

        <div>
            <ul>
                <li ng-repeat="num in numbers | between : 100 : 150">{{num}}</li>
            </ul>
        </div>

        <br><hr><br>

        <h3>{{time | date : 'hh:mm:ss a'}}</h3>

        <br><hr><br>

        <h3>URL: {{url}}</h3>
        <button ng-click="reload()">Reload</button>

        <br><hr><br>

        <ul>
            <li ng-repeat="s in suppliers">
                {{s.Id}} - {{s.Name}}
            </li>
        </ul>
    </div>

    <script>
        var app = angular.module("myapp", []);
        app.controller("personCtrl", function ($scope, $interval, $window, $location, $http) {
            $scope.fullname = "Dương Hồng Thái";

            $scope.value = 74.6887475;

            $scope.value2 = 500000;

            $scope.value3 = new Date();

            $scope.user = {
                id: "Nguyễn Văn Cường",
                name: "Nguyễn Văn Tèo"
            };

            $scope.mang = ["Tuấn", "Cường", "Phương", "Hạnh", "Hoa", "Hồng"];
            $scope.chuoi = "Nguyễn Văn Tèo";

            $scope.cars = ["Aston", "Audi", "Bentley", "BMW", "Bugatti"];

            $scope.name = "nguyễn VĂN tÈo";

            $scope.numbers = [100, 5, 200, 250, 150, 107];

            $scope.time = new Date();
            $interval(function() {
                $scope.time = new Date();
            }, 1000);

            $scope.url = $location.absUrl();
            $scope.reload = function () {
                $window.location.reload();
            };

            $scope.suppliers = [];
            $http.get('Suppliers.js').then(
                function (response) {
                    $scope.suppliers = response.data;
                },
                function (response) {
                    alert('Lỗi');
                }
            );
        });

        app.filter("pretty", function () {
            return function (input) {
                var output = input.split(" ");
                for (i = 0; i < output.length; i++) {
                    var s = output[i].trim();
                    output[i] = s.charAt(0).toUpperCase() + s.substr(1).toLowerCase();
                }
                return output.join(" ");
            }
        });

        app.filter("between", function () {
            return function (input, min, max) {
                var output = [];
                for (i = 0; i < input.length; i++) {
                    if (input[i] >= min && input[i] <= max) {
                        output.push(input[i]);
                    }
                }
                return output;
            }
        })
    </script>
</body>
</html>