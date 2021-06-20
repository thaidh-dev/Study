app.controller("ThongKeCaNhanController", function ($scope, $rootScope, $http) {
    $rootScope.hienThiThanhTichCaNhan = function () {
        $http.get("/TrangChu/ThongKeThanhTichCaNhan").then(function (value) {
            $scope.lstThanhTichCaNhan = value.data;
        });
    };
});

app.controller("ThongKePhongController", function ($scope, $rootScope, $http) {
    $rootScope.hienThiThanhTichPhong = function () {
        $http.get("/TrangChu/ThongKeThanhTichPhong").then(function (value) {
            $scope.lstThanhTichPhong = value.data;
        });
    };
});
