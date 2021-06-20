app.controller("QuanLyGhiNhanController", function ($scope, $http, $rootScope) {
    $rootScope.ghiNhan = {};
    $scope.index = false;
    var insert = false;
    var update = false;

    $rootScope.hienThiDanhSachGhiNhan = function(obj) {
        $http.post("/TrangChu/QuanLyGhiNhan", JSON.stringify(obj)).then(function (value) {
            $rootScope.lstGhiNhan = value.data;
            $rootScope.ghiNhan.nhanVienDTO = angular.copy(obj);
        });
    };

    $scope.themBtn = function () {
        $scope.index = true;
        insert = true;
    };

    $scope.suaBtn = function (obj) {
        $rootScope.ghiNhan = angular.copy(obj);
        $scope.index = true;
        update = true;
    };

    $scope.xoaBtn = function (obj) {
        var url = "/TrangChu/QuanLyGhiNhan/" + obj.idHoSo;
        $http.delete(url).then(function () {
            $scope.hienThiDanhSachGhiNhan(obj.nhanVienDTO);
            $rootScope.$emit("showThanhTichCaoNhat");
        });
    };

    $scope.luuBtn = function (obj) {
        $rootScope.ghiNhan.ngayGhiNhan = new Date();

        if (insert) {
            $http.post("/TrangChu/QuanLyGhiNhan/Them", JSON.stringify(obj)).then(function () {
                $scope.hienThiDanhSachGhiNhan(obj.nhanVienDTO);
                $scope.moiBtn();
                $rootScope.$emit("showThanhTichCaoNhat");
            });
        }

        if (update) {
            $http.put("/TrangChu/QuanLyGhiNhan", JSON.stringify(obj)).then(function () {
                $scope.hienThiDanhSachGhiNhan(obj.nhanVienDTO);
                $scope.moiBtn();
                $rootScope.$emit("showThanhTichCaoNhat");
            });
        }
    };

    $scope.huyBtn = function () {
        $scope.index = false;
        insert = false;
        update = false;
        $scope.moiBtn();
    };

    $scope.moiBtn = function () {
        $rootScope.ghiNhan = {
            idHoSo: null,
            loaiGhiNhan: true,
            lyDo: null,
            nhanVienDTO: $rootScope.ghiNhan.nhanVienDTO
        };

        if (update) {
            $rootScope.ghiNhan.idHoSo = $rootScope.ghiNhan.idHoSo;
        }
    };
});

