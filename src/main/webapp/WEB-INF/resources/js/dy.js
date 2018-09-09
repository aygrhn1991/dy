window.fileServer = 'http://localhost:8000';
var app = angular.module('app', []);
app.controller('indexCtrl', function ($scope, $http) {
    $scope.ask = function () {
        if ($scope.t_title == null || $scope.t_title == 'undefined' || $scope.t_title == '') {
            layer.msg('请输入您的问题', {time: 500});
            return;
        }
        $http.post('/dy/home/addquestion', {t_title: $scope.t_title}).success(function (d) {
            if (d == true) {
                window.location.href = '/dy/home/askfinish';
            } else {
                layer.msg('提交失败', {time: 500});
            }
        }).error(function (e) {
            layer.msg('提交失败', {time: 500});
        });
    }
    $scope.queryquestionsbytop = function () {
        $http.post('/dy/home/queryquestionsbytop', null).success(function (d) {
            $scope.questions = d;
        });
    }
    $scope.queryarticlesbytop = function () {
        $http.post('/dy/home/queryarticlesbytop', null).success(function (d) {
            $scope.articles = d;
        });
    }
    $scope.init = function () {
        $scope.fileServer = window.fileServer;
        $scope.queryquestionsbytop();
        $scope.queryarticlesbytop();
    }
    $scope.init();
});