<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>
<body ng-app="myapp" ng-init="a=2000">
    <div ng-controller="myctrl1">
        <p>{{t}} olo</p>
        <button ng-click="test2ObjectController()">abc</button>
    </div>

    <div ng-controller="myctrl1">
        <p>{{t}} alo</p>
    </div>

    <br><hr><br>

    <div ng-controller="myctrl1">
        <h3>{{red}}</h3>
    </div>
    <div ng-controller="myctrl2">
        <h3>{{yellow}}</h3>
    </div>

    <br><hr><br>

    <ul ng-controller="parrentCtrl">
        <li>Cha: {{cha}}</li>
        <li>
            Con: {{con}}
            <ul ng-controller="chilCtrl">
                <li>Child: {{con}}</li>
                <li>Father: {{cha}}</li>
                <li>Father2: {{$parent.cha}}</li>
            </ul>

            <ul ng-controller="chilCtrl">
                <li>alo: {{cha}}</li>
                <li>alo: {{$parent.cha}}</li>
            </ul>
        </li>
    </ul>

    <br><hr><br>

    <h1>Root: {{rootmsg}}</h1>
    <ul ng-controller="parrentCtrl">
        <li>Parrent: {{cha}}
            <ul ng-controller="chilCtrl">
                <li>{{con}}</li>
                <li>{{rootmsg}}</li>
                <li>{{$parent.$parent.rootmsg}}</li>
            </ul>
        </li>
    </ul>

    <br><hr><br>

    <h1>Root: {{a}}</h1>
    <ul ng-controller="parrentCtrl" ng-init="b=1000">
        <li>Parrent: {{b}}
            <ul ng-controller="chilCtrl" ng-init="c=3000">
                <li>{{a}}</li>
                <li>{{b}}</li>
                <li>{{c}}</li>
            </ul>
        </li>
    </ul>

    <br><hr><br>

    <div ng-controller="myctrl">
        <ul>
            <li>Ho ten: {{student.name}}</li>
            <li>Gioi tinh:
                <span ng-show="student.gender">Nam</span>
                <span ng-hide="student.gender">Nu</span>
            </li>
            <li>Xep loai
                <span ng-if="student.mark < 5">Yeu/kem</span>
                <span ng-if="student.mark >= 5 && student.mark < 6.5">Trung binh</span>
                <span ng-if="student.mark >= 6.5">Kha gioi</span>
            </li>
            <li>Nganh:
                <span ng-switch="student.major">
                    <span ng-switch-when="UDPM">Ung dung phan mem</span>
                    <span ng-switch-when="TKW">Thiet ke web</span>
                    <span ng-switch-default>Nganh moi</span>
                </span>
            </li>

            <p ng-repeat="sv in students">{{$index}}. {{sv}}</p>
        </ul>
    </div>

    <script>
        var app = angular.module("myapp", []);
        app.controller("myctrl1", function($scope) {
            $scope.red = "mau do";
            
            $scope.test2ObjectController = function () {
                $scope.t = "alo";
            };
        });
        app.controller("myctrl2", function($scope) {
            $scope.yellow = "mau vang";
        });

        app.controller("parrentCtrl", function($scope, $rootScope) {
            $rootScope.rootmsg = "I am root";
            $scope.cha = "tao la cha";
        });
        app.controller("chilCtrl", function($scope, $rootScope) {
            $scope.con = "tao la con";
            alert($rootScope.a)
        });

        app.controller("myctrl", function ($scope) {
            $scope.student = {
                name: "duong hong thai",
                mark: 7,
                gender: true,
                major: "UDPM"
            };

            $scope.students = ["tuan", "hai", "linh", "dang", "chung"];
        });
    </script>
</body>
</html>