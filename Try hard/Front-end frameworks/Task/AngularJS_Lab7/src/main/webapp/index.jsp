<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <script src="resources/js/index.js"></script>
</head>
<body ng-app="myapp">
    <div ng-controller="c">
        <div>
            <table border="1">
                <tr>
                    <th ng-click="sapXep(1)">Sản phẩm</th>
                    <th ng-click="sapXep(2)">Đơn giá</th>
                    <th ng-click="sapXep(3)">Giảm giá</th>
                    <th ng-click="sapXep(4)">Ngày</th>
                </tr>

                <tr ng-repeat="sp in sanPhams | orderBy: sx | limitTo:2:begin">
                    <td>{{sp.ten | uppercase}}</td>
                    <td>{{sp.gia | currency:'':0}}</td>
    <%--                <td>{{sp.giamGia | number:2}}</td>--%>
                    <td>{{sp.giamGia | phanTram}}</td>
                    <td>{{sp.ngay | date:'MMMM-dd, yyyy'}}</td>
                </tr>
            </table>
        </div>

        <div>
            <button ng-click="phanTrang('<<<')"><<<</button>
            <button ng-click="phanTrang('<')"><</button>
            <button ng-click="phanTrang('>')">></button>
            <button ng-click="phanTrang('>>>')">>>></button>
        </div>
    </div>

</body>
</html>