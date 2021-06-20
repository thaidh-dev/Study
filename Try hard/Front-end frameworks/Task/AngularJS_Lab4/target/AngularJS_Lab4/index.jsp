<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <script src="resources/js/index.js"></script>
</head>
<body ng-app="myApp">
    <div ng-controller="myController">
        <p>{{hoTen}}</p>
        <p>{{ngaySinh}}</p>
        <p>{{gioiTinh}}</p>
        <p>{{diem}}</p>
    </div>

    <br><hr><br>
    
    <div ng-controller="myController">
        <p>{{sinhVien.hoTen}}</p>
        <p>{{sinhVien.ngaySinh}}</p>
        <p>{{sinhVien.gioiTinh}}</p>
        <p>{{sinhVien.diem}}</p>
    </div>

    <br><hr><br>

    <div ng-controller="myController">
        <table border="1">
            <tr>
                <th>STT</th>
                <th>Họ tên</th>
                <th>Ngày sinh</th>
                <th>Giới tính</th>
                <th>Điểm</th>
            </tr>
            <tr ng-repeat="sv in lop">
                <td>{{$index + 1}}</td>
                <td>{{sv.hoTen}}</td>
                <td>{{sv.ngaySinh}}</td>
                <td>{{sv.gioiTinh}}</td>
                <td>{{sv.diem}}</td>
            </tr>
        </table>
    </div>

    <br><hr><br>

    <div ng-controller="myController">
        <label>Chiều dài</label>
        <input type="text" ng-model="dai">
        <br>
        <label>Chiều rộng</label>
        <input type="text" ng-model="rong"> <br>
        <label>Diện tích</label>
        <input type="text" value="{{dienTich}}"> <br>
        <label>Chu vi</label>
        <input type="text" value="{{chuVi}}"> <br>
        <button ng-click="tinhDienTichVaChuVi()">Calculate</button>
    </div>

    <br><hr><br>

    <div ng-controller="myController">
        <label>Họ tên:</label>
        <input type="text">
        <br>
        <label>Ngày sinh</label>
        <input type="text"> <br>
        <label>Giới tính</label>
        <select>
            <option>Nam</option>
            <option>Nữ</option>
        </select> <br>
        <label>Điểm</label>
        <input type="text" ng-model="diem4"> <br>
        <label>Học lực</label>
        <input type="text" value="{{hocLuc}}" readonly> <br>
        <button ng-click="xepLoai()">Grade</button>
    </div>
</body>
</html>