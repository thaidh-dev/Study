var app = angular.module("myapp", []);

app.controller("c1", function($scope) {
    $scope.index = true;
    var insert = false;
    var update = false;

    $scope.them = function () {
        $scope.index = false;
        insert = true;
    };

    $scope.sua = function (index) {
        $scope.index = false;
        update = true;
        $scope.sinhVien = angular.copy($scope.sinhViens[index]);
        $scope.id = index;
    };

    $scope.xoa = function(index) {
        $scope.sinhViens.splice(index, 1);
    };

    $scope.huy = function () {
        insert = false;
        update = false;
        $scope.index = true;
        $scope.moi();
    };

    $scope.moi = function () {
        $scope.sinhVien = {};
    };

    $scope.luu = function(id) {
        if (insert) {
            $scope.sinhViens.push(angular.copy($scope.sinhVien));
            // $scope.sinhViens.push($scope.sinhVien);
        }
        if (update) {
            $scope.sinhViens[id] = angular.copy($scope.sinhVien);
        }
    };

    $scope.xepLoai = function(diem) {
        var d = parseFloat(diem);
        if (d >= 0 && d <= 3) {
            return "Yếu";
        }
        if (d > 3 && d <= 5) {
            return "Trung bình";
        }
        if (d > 5 && d <= 8) {
            return "Khá";
        }
        if (d > 8 && d <= 10) {
            return "Giỏi";
        }
    };

    $scope.sinhViens = [
        {
            hoTen: "Dương Hồng Thái",
            ngaySinh: "24-11-1997",
            diem: 7
        }
    ]
});

app.controller("c2", function($scope) {
    $scope.sanPhams = [
        {
            ten: "áo",
            gia: 1000
        },
        {
            ten: "quần",
            gia: 30000
        },
        {
            ten: "mũ",
            gia: 50000
        },
        {
            ten: "khăn tắm",
            gia: 15000
        },
        {
            ten: "tất",
            gia: 100000
        }
    ]
});
