var app = angular.module("myapp", []);
app.controller("c", function ($scope) {
    $scope.sanPhams = [
        {
            ten: 'áo',
            gia: 100000,
            giamGia: 19,
            ngay: '2000-08-16'
        },
        {
            ten: 'quần',
            gia: 120000,
            giamGia: 0,
            ngay: '1982-12-19'
        },
        {
            ten: 'mũ',
            gia: 290000,
            giamGia: 3,
            ngay: '2000-08-16'
        },
        {
            ten: 'túi xách',
            gia: 195000,
            giamGia: 5,
            ngay: '2000-08-16'
        },
        {
            ten: 'tất',
            gia: 19000,
            giamGia: 0,
            ngay: '2000-08-16'
        },
        {
            ten: 'điện thoại',
            gia: 2000000,
            giamGia: 5,
            ngay: '2000-08-16'
        },
        {
            ten: 'tủ',
            gia: 1000000,
            giamGia: 10,
            ngay: '2000-08-16'
        }
    ];

    $scope.sapXep = function (chon) {
        switch (chon) {
            case 1:
                $scope.sanPhams.sort(function (a, b) {
                    return a.ten.localeCompare(b.ten);
                });
                $scope.sx = "";
                break;
            case 2:
                $scope.sx = "'gia'";
                break;
            case 3:
                $scope.sx = "'giamGia'";
                break;
            case 4:
                $scope.sx = "'ngay'";
                break;
        }
    };

    $scope.phanTrang = function (trang) {
        switch (trang) {
            case '>':
                if ($scope.tongSanPham - $scope.begin < 2) {
                    $scope.begin = 0;
                    break;
                }
                $scope.begin += 2;
                break;
            case '<':
                if ($scope.begin < 2) {
                    $scope.begin = $scope.tongSanPham-1;
                    break;
                }
                $scope.begin -= 2;
                break;
            case '>>>':
                $scope.begin = $scope.tongSanPham-1;
                break;
            case '<<<':
                $scope.begin = 0;
                break;
        }
    };

    $scope.sx = "'gia'";
    $scope.begin = 0;
    $scope.tongSanPham = $scope.sanPhams.length;
});

app.filter("phanTram", function () {
    return function (giamGia) {
        giamGia = giamGia + "%";
        return giamGia;
    }
});

// app.filter("orderByVietNamese", function () {
//     return function (sp) {
//         sp.sort(function (a, b) {
//             return a.ten.localeCompare(b.ten);
//         });
//
//         return sp;
//     }
// });
