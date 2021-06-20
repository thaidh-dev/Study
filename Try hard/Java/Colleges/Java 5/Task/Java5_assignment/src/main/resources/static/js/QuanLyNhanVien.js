app.controller("QuanLyNhanVienController", function ($scope, $http, $rootScope) {
    $scope.index = false;
    var insert = false;
    var update = false;

    $rootScope.hienThiDanhSachNhanVien = function () {
        $http.get("/TrangChu/QuanLyNhanVien").then(function (value) {
            $scope.lstNhanVien = value.data;
        });

        $http.get("/TrangChu/QuanLyPhongBan").then(function (value) {
            $scope.lstPhongBan = value.data;
        });
    };

    $scope.them = function () {
        $scope.index = true;
        insert = true;
    };

    $scope.sua = function (obj) {
        $scope.index = true;
        update = true;
        var ns = new Date(obj.ngaySinh);

        for (var i = 0; i < $scope.lstPhongBan.length; i++) {
            if ($scope.lstPhongBan[i].idPhongBan == obj.phongBan.idPhongBan) {
                $scope.nhanVien = angular.copy(obj);
                $scope.nhanVien.ngaySinh = ns;
                $scope.nhanVien.phongBan = $scope.lstPhongBan[i];
                // trong cái combobox có đối tượng PhongBan của listPhongBan
                // set nhanVien.phongBan = andgular.copy(obj.phongBan) nó lại nhận 1 đối tượng khác
                // đối tượng đấy nó không thuộc lstPhongBan thế nên là nó đéo nhận
            }
        }
    };

    $scope.xoa = function (idNhanVien) {
        var url = "/TrangChu/QuanLyNhanVien/" + idNhanVien;

        $http.delete(url).then(function () {
            $scope.hienThiDanhSachNhanVien();
        });
    };

    $scope.luu = function (obj) {
        $scope.trungEmail = false;
        $scope.trungSdt = false;

        var checkTrungEmail = new Promise(function (resolve, reject) {
            var urlEmail = "/TrangChu/QuanLyNhanVien/findByEmail/" + obj.email;
            $http.get(urlEmail).then(function (value) {
                if (!angular.equals(value.data, "")) {
                    $scope.trungEmail = true;
                    reject(); // báo là sai
                }
                else {
                    resolve(); // báo là đúng thì mới chạy
                }
            });
        });

        var checkTrungSdt = new Promise(function (resolve, reject) {
            var urlSdt = "/TrangChu/QuanLyNhanVien/findBySdt/" + obj.sdt;
            $http.get(urlSdt).then(function (value) {
                if (!angular.equals(value.data, "")) {
                    $scope.trungSdt = true;
                    reject();
                }
                else {
                    resolve();
                }
            });
        });

        Promise.all([checkTrungEmail, checkTrungSdt]).then(function () {
            if (!$scope.trungEmail && !$scope.trungSdt) {
                if (insert) {
                    $http.post("/TrangChu/QuanLyNhanVien", JSON.stringify(obj)).then(function (value) {
                        $scope.hienThiDanhSachNhanVien();
                        $scope.moi();
                    });
                }

                if (update) {
                    $http.put("/TrangChu/QuanLyNhanVien", JSON.stringify(obj)).then(function (value) {
                        $scope.hienThiDanhSachNhanVien();
                        $scope.moi();
                    });
                }
            }
        });
    };

    $scope.huy = function () {
        insert = false;
        update = false;
        $scope.index = false;
        $scope.trungEmail = false;
        $scope.trungSdt = false;
        $scope.moi();
    };

    $scope.moi = function () {
        $scope.nhanVien = {
            idNhanVien: null,
            tenNhanVien: null,
            gioiTinh: true,
            ngaySinh: null,
            email: null,
            sdt: null,
            luong: null,
            ghiChu: null,
            phongBan: $scope.lstPhongBan[0]
        };

        if (update) {
            $scope.nhanVien.idNhanVien = $scope.nhanVien.idNhanVien;
        }

        $scope.trungEmail = false;
        $scope.trungSdt = false;
    };
});
