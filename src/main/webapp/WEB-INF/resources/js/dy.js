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
});