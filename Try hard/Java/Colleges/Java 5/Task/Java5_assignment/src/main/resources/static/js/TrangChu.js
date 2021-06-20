app.controller("ThanhTichCaoNhatController", function ($scope, $rootScope, $http, $window) {
    $rootScope.$on("showThanhTichCaoNhat", function () {
        x();
    });

    var x = function () {
        $http.get("/TrangChu").then(function (value) {
            $scope.lstThanhTichCaoNhat = value.data;
        });
    };

    $window.onload = function () {
        x();
    };
});
