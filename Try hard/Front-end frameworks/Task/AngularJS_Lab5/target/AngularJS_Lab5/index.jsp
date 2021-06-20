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
    <div>
        <div ng-controller="c1">
            <span ng-hide="true" ng-model="id"></span>
            <label>Họ tên:</label>
            <input type="text" ng-model="sinhVien.hoTen"> <br>
            <label>Ngày sinh:</label>
            <input type="text" ng-model="sinhVien.ngaySinh"> <br>
            <label>Điểm:</label>
            <input type="text" ng-model="sinhVien.diem"> <br>
            <button ng-show="index" ng-click="them()">Thêm</button>
            <button ng-hide="index" ng-click="luu(id)">Lưu</button>
            <button ng-hide="index" ng-click="huy()">Hủy</button>
            <button ng-click="moi()">Mới</button>

            <table border="1">
                <tr>
                    <th>STT</th>
                    <th>Họ tên</th>
                    <th>Ngày sinh</th>
                    <th>Điểm</th>
                    <th>Xếp loại</th>
                </tr>

                <tr ng-repeat="sv in sinhViens">
                    <td>{{$index+1}}</td>
                    <td>{{sv.hoTen}}</td>
                    <td>{{sv.ngaySinh}}</td>
                    <td>{{sv.diem}}</td>
                    <td>{{xepLoai(sv.diem)}}</td>
                    <td>
                        <a href="" ng-show="index" ng-click="sua($index)">Sửa</a> |
                        <a href="" ng-show="index" ng-click="xoa($index)">Xóa</a>
                    </td>
                </tr>
            </table>
        </div>

        <br><hr><br>

        <div ng-controller="c2">
            <table border="1">
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <td>Giá</td>
                </tr>
                <tr ng-repeat="sp in sanPhams">
                    <td>{{$index+1}}</td>
                    <td>{{sp.ten}}</td>
                    <td>{{sp.gia}}</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>