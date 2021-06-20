var app = angular.module("myApp", []);

app.controller("myController", function ($scope) {
    $scope.hoTen = "Dương Hồng Thái";
    $scope.ngaySinh = "24-11-1997";
    $scope.gioiTinh = "Nam";
    $scope.diem = 7;

    $scope.sinhVien = {
        hoTen : "Dương Hồng Thái",
        ngaySinh : "24-11-1997",
        gioiTinh : "Nam",
        diem : 7
    };

    $scope.sinhVien2 = {
        hoTen : "Nguyễn Văn Cường",
        ngaySinh : "24-11-1997",
        gioiTinh : "Nữ",
        diem : 5
    };

    $scope.sinhVien3 = {
        hoTen : "Phạm Duy Anh",
        ngaySinh : "24-11-1997",
        gioiTinh : "Nam",
        diem : 7
    };

    $scope.lop = [$scope.sinhVien, $scope.sinhVien2, $scope.sinhVien3];

    $scope.tinhDienTichVaChuVi = function() {
        $scope.dienTich = parseFloat($scope.dai) * parseFloat($scope.rong);
        $scope.chuVi = (parseFloat($scope.dai) + parseFloat($scope.rong))*2;
    };

    $scope.xepLoai = function () {
        var d = parseFloat($scope.diem4);
        if (d >= 0 && d <= 3) {
            $scope.hocLuc = "Yếu";
        }
        if (d > 3 && d <= 5) {
            $scope.hocLuc = "Trung bình";
        }
        if (d > 5 && d <= 8) {
            $scope.hocLuc = "Khá";
        }
        if (d > 8 && d <= 10) {
            $scope.hocLuc = "Giỏi";
        }
    };
});