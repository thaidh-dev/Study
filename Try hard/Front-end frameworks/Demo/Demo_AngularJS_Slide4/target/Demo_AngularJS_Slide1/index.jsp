<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Welcome to the international</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>

<body ng-app="myapp">
    <div ng-controller="myctrl">
        <h1>{{welcome}}</h1>
        <h2>{{info.fpoly}}</h2>
        <h3>{{info.course}}</h3>
        <p>{{a[0]}}</p>
        <p>{{a[1]}}</p>

        <br><hr><br>

        <h3 ng-bind="welcome"></h3>
        <input ng-model="welcome" ng-init="welcome = 'jhjhjhjhjhjh'">
<%--        model chỉ gắn đc vào máy cái trong form như form, input, select, option--%>
<%--        bind gắn đc tất ngoài mấy cái trong form--%>

        <br><hr><br>

        <h1 ng-mousemove="count = count+1">Đưa chuột vào đây</h1>
        <h2>{{count}}</h2>

        <br><hr><br>
        Nhập tên của ban: <input type="text" ng-model="username">
        <button ng-click="sayHello()">In thông báo</button>
        {{greeting}}

        <br><hr><br>
        A: <input type="text" ng-model="soA">
        B: <input type="text" ng-model="soB">
        <button ng-click="tinhTong()">Tổng</button>
        <input type="text" value="{{tong}}">

        <p>{{thai}}</p>
    </div>

    <script>
        angular.module("myapp", []).controller("myctrl", function ($scope) {
            $scope.welcome = "Welcome to angularjs";

            $scope.info = {
                fpoly: "FPT polytechnic",
                course: "WELCOME to angularjs"
            }

            $scope.a = [1, 2, 7, 3];

            $scope.count = 0;

            $scope.sayHello = function() {
                $scope.greeting = "Xin chào " + $scope.username;
            }

            $scope.tinhTong = function () {
                $scope.tong = parseInt($scope.soA) + parseInt($scope.soB);
                $scope.thai = "i'am not duong hong thai"
            }

            $scope.thai = "duong hong thai"
        });


    </script>

</body>

</html>