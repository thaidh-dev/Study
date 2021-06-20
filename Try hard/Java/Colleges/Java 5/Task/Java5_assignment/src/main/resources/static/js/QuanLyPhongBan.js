app.controller("QuanLyPhongBanController", function ($scope, $http, $rootScope) {
    // $http.get("/TrangChu/QuanLyPhongBan").then(function (response) {
    //     $scope.lstPhongBan = response.data;
    // }); // 2 cách viết giống nhau

    // $http({
    //     url: "/TrangChu/QuanLyPhongBan",
    //     method: "GET"
    // }).then(function (response) {
    //     $scope.lstPhongBan = response.data;
    // });

    $scope.phongBan = {};
    $scope.index = true;
    var insert = false;
    var update = false;

    $rootScope.hienThiDanhSachPhongBan = function () {
        $http.get("/TrangChu/QuanLyPhongBan").then(function (response) {
            $scope.lstPhongBan = response.data;
        });
    };

    $scope.them = function () {
        $scope.index = false;
        insert = true;
    };

    $scope.sua = function (obj) {
        $scope.index = false;
        update = true;
        $scope.phongBan = angular.copy(obj);
    };

    $scope.xoa = function (idPhongBan) {
        var url = "/TrangChu/QuanLyPhongBan/" + idPhongBan;
        $http.delete(url).then(function () {
            $scope.hienThiDanhSachPhongBan();
        });
    };

    $scope.luu = function (obj) {
        $scope.txtTenPhongEvent();

        if (checkTrong(obj)) {
            var url = "/TrangChu/QuanLyPhongBan/" + obj.tenPhong;

            $http.get(url).then(function (value) {
                if (angular.equals(value.data, "")) {
                    if (insert) {
                        $http.post("/TrangChu/QuanLyPhongBan", JSON.stringify(obj)).then(function () {
                            $scope.hienThiDanhSachPhongBan();
                            $scope.moi();
                        });
                    }

                    if (update) {
                        $http.put("/TrangChu/QuanLyPhongBan", JSON.stringify(obj)).then(function () {
                            $scope.hienThiDanhSachPhongBan();
                            $scope.phongBan.tenPhong = null;
                        });
                    }

                    return;
                }

                $scope.trungTenPhong = true;
            });
        }
    };

    $scope.huy = function () {
        $scope.index = true;
        insert = false;
        update = false;
        $scope.txtTenPhongEvent();
        $scope.moi();
    };

    $scope.moi = function () {
        $scope.phongBan = {
            idPhongBan: null,
            tenPhong: null
        };

        if (update) {
            $scope.phongBan.idPhongBan = $scope.phongBan.idPhongBan;
        }
    };

    $scope.txtTenPhongEvent = function () {
        $scope.trongTenPhong = false;
        $scope.trungTenPhong = false;
    };

    var checkTrong = function (obj) {
        if (obj.tenPhong == null) {
            $scope.trongTenPhong = true;
            return false;
        }

        return true;
    };
});