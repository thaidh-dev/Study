var app = angular.module("myapp", []);
app.controller("myController", function($scope) {
    $scope.doTuoi = ["Dưới 25 tuổi", "Từ 25 đến 40", "Trên 40"];

    $scope.tienThuong = function() {
        switch ($scope.doTuoi.indexOf($scope.doTuoiSelected)) {
            case 0:
                $scope.thuong = $scope.luong/100*7;
                break;
            case 1:
                $scope.thuong = $scope.luong/100*10;
                break;
            case 2:
                $scope.thuong = $scope.luong/100*15;
                break;
        }

        if (!$scope.gioiTinh) {
            $scope.thuong = $scope.thuong+200000;
        }
    };
});

app.controller("myController2", function ($scope) {
    $scope.thanhTien = 0;

    $scope.sanPham = [
        {
            ten: "Nước ngọt",
            soLuong: 4,
            gia: 10000
        },
        {
            ten: "Cà phê đen",
            soLuong: 1,
            gia: 12000
        },
        {
            ten: "Thuốc lá",
            soLuong: 1,
            gia: 18000
        }
    ];

    $scope.thanhTien = function () {
        var tong = 0;
        for (var i = 0; i < $scope.sanPham.length; i++) {
            tong += $scope.sanPham[i].gia * $scope.sanPham[i].soLuong;
        }
        return tong;
    }
});

app.controller("myController3", function ($scope) {
    $scope.kiemTra = function (so) {
        if (so % 2 == 0 && so % 9 == 0) {
            $scope.frm3.txtSo.$setValidity("test", true);
            console.log(true);
        }
        if (so % 2 != 0 || so % 9 != 0) {
            $scope.frm3.txtSo.$setValidity("test", false);
            console.log(false);
        }
    };
});

