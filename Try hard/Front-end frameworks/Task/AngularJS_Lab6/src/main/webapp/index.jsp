<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <script src="resources/js/bai1.js"></script>
    <link rel="stylesheet" href="resources/css/index.css">
</head>
<body ng-app="myapp">
    <div ng-controller="myController">
        <form name="frm">
            <h1>Tính thưởng tết nguyên đán</h1>

            <label>Họ tên</label>
            <input type="text"> <br>

            <label>Lương</label>
            <input type="text" ng-model="luong" ng-change="tienThuong()" pattern="^\d+$" name="txtLuong"> <br>

            <label>Giới tính</label>
            <input type="radio" name="gt" ng-model="gioiTinh" ng-value="true" ng-change="tienThuong()">Nam
            <input type="radio" name="gt" ng-model="gioiTinh" ng-value="false" ng-checked="true" ng-change="tienThuong()">Nữ <br>

            <label>Độ tuổi</label>
            <select ng-init="doTuoiSelected = doTuoi[0]" ng-options="t for t in doTuoi" ng-model="doTuoiSelected" ng-change="tienThuong()"></select>

            <p>Tiền thưởng:
                <span ng-if="frm.txtLuong.$valid && luong >= 2000000">{{thuong}}</span>
                <span class="lblSaiLuong" ng-show="frm.txtLuong.$invalid">Vui lòng nhập lại lương nhân viên</span>
                <span class="lblSaiLuong" ng-if="luong < 2000000">Lương ít nhất 20 triệu</span>
            </p>
        </form>
    </div>

    <br><hr><br>

    <div ng-controller="myController2">
        <h1>Bán hàng giải khát</h1>

        <table border="1">
            <tr>
                <th>Tên hàng</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Thành tiền</th>
            </tr>
            <tr ng-repeat="sp in sanPham">
                <td>{{sp.ten}}</td>
                <td><input type="number" ng-model="sp.soLuong" min="0" ng-change="thanhTien()"></td>
                <td>{{sp.gia|currency:'$':0}}</td>
                <td>{{sp.gia*sp.soLuong|currency:'$':0}}</td>
            </tr>
        </table>

        <p>Thành tiền: <span ng-bind="thanhTien()|currency:'$':0"></span></p>
    </div>

    <br><hr><br>

    <div>
        <form name="frm2">
            <h1>Thông tin sinh viên</h1>

            <label>Họ tên</label>
            <input type="text" name="txtTen" ng-model="ten" required maxlength="25">
            <span class="lblSaiLuong" ng-if="ten == null">Vui lòng nhập họ tên</span>
            <br>

            <label>Điểm</label>
            <input type="number" min="0" max="10" name="txtDiem" ng-model="diem">
            <span class="lblSaiLuong" ng-show="frm2.txtDiem.$invalid">Điểm phải từ 0 đến 10</span>
            <br>

            <label>Email</label>
            <input type="email" name="txtEmail" ng-model="email" required>
            <span class="lblSaiLuong" ng-show="frm2.txtEmail.$invalid">Không đúng dạng email</span>
            <br>

            <label>Số xe</label>
            <input type="text" pattern="^5\d-[A-Z]\d-\d{4,5}$" name="txtSoXe" ng-model="soXe">
            <span class="lblSaiLuong" ng-show="frm2.txtSoXe.$invalid">Số xe phải theo dạng 5x-xx-xxxxx(phần cuối có 4 hoặc 5 số)</span>
            <br>

            <button>Lưu</button>
            <p ng-show="frm2.$valid">Chúc mừng bạn đã nhập đúng</p>
        </form>
    </div>

    <br><hr><br>

    <div ng-controller="myController3">
        <form name="frm3">
            <h1>Custom validation</h1>

            <label>Số</label>
            <input type="text" ng-model="so" name="txtSo">
            <span ng-show="frm3.txtSo.$error.test">Vui lòng nhập số chia hết cho cả 2 và 9</span>
            <br>

            <button ng-click="kiemTra(so)">Kiểm tra</button>
        </form>
    </div>
</body>
</html>