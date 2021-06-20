<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>
<body ng-app="myapp">
    <div ng-controller="myCtrl">
<%--        <input ng-model="name" ng-model-options="{updateOn: 'blur'}">--%>
        <input ng-model="name" ng-model-options="{debounce: 3000}">
        <div>Đã nhập: {{name}}</div>
    </div>

    <br><hr><br>

    <div class="form-group">
        <label>Sở thích: </label>
        <input type="checkbox" ng-model="all">Tất cả
        <input type="checkbox" ng-model="reading" ng-checked="all">Đọc sách
        <input type="checkbox" ng-model="traveling" ng-checked="all">Du lịch
        <input type="checkbox" ng-model="music" ng-checked="all">Âm nhạc
    </div>

    <br><hr><br>

    <div ng-controller="myCtrl">
        <form>
            <label>Country</label>
<%--            <select ng-model="country">--%>
<%--                <option ng-repeat="q in countries" value="{{q}}">{{q}}</option>--%>
<%--            </select>--%>
            <select ng-model="country" ng-options="x for x in countries"></select>

            <button ng-click="read()">OK</button>

        </form>
    </div>

    <br><hr><br>

    <div ng-controller="myCtrl">
        <label>Country</label>
<%--        <select ng-options="x.name for x in countries2" ng-model="country"></select>--%>
        <select ng-model="country">
            <option ng-repeat="x in countries2" value="{{x.id}}">{{x.name}}</option>
        </select>
    </div>

    <br><hr><br>

    <div ng-controller="myCtrl">
        <select ng-options="x for (x,y) in countries3" ng-model="prop"></select>
    </div>

    <br><hr><br>

    <div ng-controller="myCtrl">
        <select ng-model="country" ng-options="y.name for (x,y) in countries4"></select>
    </div>

    <br><hr><br>

    <div ng-controller="myCtrl">
        <form name="frmUser">
            <label>Fullname</label>
            <input type="text" name="txtName" ng-model="fullname" required>
            <br>
            <label>Email</label>
            <input type="email" name="txtEmail" ng-model="email">
        </form>
        <h3>Tình trạng lỗi của các điều khiển</h3>
        <ul>
            <li>Fullname: {{frmUser.txtName.$valid}}</li>
            <li>Email: {{frmUser.txtEmail.$valid}}</li>
        </ul>
        <h3>Tình trạng lỗi của form: {{frmUser.$valid}}</h3>
    </div>

    <br><hr><br>

    <style>
        input.ng-invalid {
            border: 1px solid red;
        }
    </style>
    <div>
        <form name="frmUser">
            <label>Fullname</label>
            <input type="text" name="txtName" ng-model="fullname" required>
            <p ng-hide="frmUser.txtName.$valid">Vui lòng nhập tên</p>
            <br>
            <label>Email</label>
            <input type="email" ng-model="email" name="txtEmail">
            <p ng-hide="frmUser.txtEmail.$valid">Email không hợp lệ</p>
        </form>
        <h3 ng-show="frmUser.$valid">Chúc mừng bạn đã nhập đúng</h3>
    </div>

    <br><hr><br>

    <div ng-controller="myCtrl">
        <form name="frmUser">
            <input type="text" name="txtAge" ng-model="age" even-number>
            <span ng-show="frmUser.txtAge.$invalid">Vui lòng nhập số chẵn</span>
        </form>
    </div>

    <script>
        var app = angular.module("myapp", []);
        app.controller("myCtrl", function ($scope) {
            $scope.name = "Võ thị 6";

            $scope.countries = ["Việt Nam", "Mỹ", "Trung Quốc"];

            $scope.read = function () {
                alert($scope.country);
            };

            $scope.countries2 = [
                {id: "VN", name: "Việt Nam"},
                {id: "TQ", name: "Trung Quốc"}
            ];

            $scope.countries3 = {
                id: "VN",
                name: "Việt Nam",
                currency: "VNĐ"
            };

            $scope.countries4 = {
                VN: {
                    id: "VN",
                    name: "Việt Nam",
                    currency: "VNĐ"
                },
                TQ: {
                    id: "TQ",
                    name: "Trung Quốc",
                    currency: "nhân dân tệ"
                }
            };
        });

        app.directive("evenNumber", function() {
            return {
                require: "ngModel",
                link: function(scope, element, attr, mCtrl) {
                    function fnValidate(value) {
                        if (parseInt(value) % 2 == 0) {
                            mCtrl.$setValidity("thai", true);
                        }
                        else {
                            mCtrl.$setValidity("thai", false);
                        }

                        console.log(value);
                        return value;
                    }

                    mCtrl.$parsers.push(fnValidate); // thêm fnValidate vào mảng các validation mặc định, nó sẽ được thực hiện khi ta thay đổi giá trị đầu vào tại ô input
                }
            }
        });

    </script>

</body>
</html>