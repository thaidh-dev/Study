var app = angular.module("TrangChuApp", ["ui.router"]);

app.config(function ($stateProvider) {
    var quanLyPhongBan = {
        name: "QLPB",
        // url: "/QuanLyPhongBan",
        views: {
            "QLPB@": {
                templateUrl: 'QuanLyPhongBan.html',
                // controller: "QuanLyPhongBanController"
            }
        }
    };

    var quanLyNhanVien = {
        name: "QLNV",
        // url: "/QuanLyNhanVien",
        views: {
            "QLNV@": {
                templateUrl : "QuanLyNhanVien.html",
                // controller: "QuanLyNhanVienController"
            }
        }
    };

    var quanLyGhiNhan = {
        name: "QLGN",
        // url: "/QuanLyGhiNhan",
        views: {
            "QLGN@": {
                templateUrl : "KhenThuong.html",
                // controller: "QuanLyGhiNhanController"
            }
        }
    };

    var thongKeCaNhan = {
        name: "TKCN",
        // url: "/ThongKeThanhTichCaNhan",
        views: {
            "TKCN@": {
                templateUrl: "ThongKeCaNhan.html",
                // controller: "ThongKeCaNhanController"
            }
        }
    };

    var thongKePhong = {
        name: "TKP",
        // url: "/ThongKeThanhTichTheoPhong",
        views: {
            "TKP@": {
                templateUrl: "ThongKePhong.html",
                // controller: "ThongKePhongController"
            }
        }
    };

    $stateProvider.state(quanLyPhongBan);
    $stateProvider.state(quanLyNhanVien);
    $stateProvider.state(quanLyGhiNhan);
    $stateProvider.state(thongKeCaNhan);
    $stateProvider.state(thongKePhong);
});

$(function () {
    $(".thongKeCha").click(function () {
        $(".thongKeCon").slideToggle();
    });
});


