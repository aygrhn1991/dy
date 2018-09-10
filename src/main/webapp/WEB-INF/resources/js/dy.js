window.fileServer = 'http://localhost:8000';
window.getUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
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
app.controller('articleCtrl', function ($scope, $http, $sce) {
    $scope.queryarticle = function () {
        $http.post('/dy/home/queryarticle/' + $scope.id, null).success(function (d) {
            $scope.article = d;
            $scope.article.t_content = $sce.trustAsHtml(d.t_content);
        });
    }
    $scope.init = function () {
        $scope.id = window.getUrlParam('id');
        $scope.queryarticle();
    }
    $scope.init();
});
app.controller('questionCtrl', function ($scope, $http) {
    $scope.queryuser=function(){
        $http.post('/dy/home/queryarticle/' + $scope.id, null).success(function (d) {
            $scope.article = d;
            $scope.article.t_content = $sce.trustAsHtml(d.t_content);
        });
    }
    $scope.queryanswer = function () {
        $http.post('/dy/home/queryarticle/' + $scope.id, null).success(function (d) {
            $scope.article = d;
            $scope.article.t_content = $sce.trustAsHtml(d.t_content);
        });
    }
    $scope.init = function () {
        $scope.id = window.getUrlParam('id');
        $scope.queryarticle();
    }
    $scope.init();
});