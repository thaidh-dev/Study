var app =  angular.module("myApp", ["ui.router"]);

app.config(function ($stateProvider) {
    var home = {
        name: "home",
        templateUrl: "home.html"
    };

    var about = {
        name: "about",
        templateUrl: "about.html"
    };

    var contactUs = {
        name: "contactUs",
        templateUrl: "contactUs.html"
    };

    $stateProvider.state(home);
    $stateProvider.state(about);
    $stateProvider.state(contactUs);
});

app.run(function ($rootScope) {
    console.log("duong hong thai");

    $rootScope.$on("a", function () {
        console.log("start");
        $rootScope.loading = true;
    });

    $rootScope.$on("b", function () {
        console.log("success");
        $rootScope.loading = false;
    });

    $rootScope.$on("c", function () {
        console.log("error");
        $rootScope.loading = false;
        alert("Lỗi không tải đc trang");
    });
});