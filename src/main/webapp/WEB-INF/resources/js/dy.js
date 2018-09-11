window.fileServer = 'http://localhost:8000';
window.getUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
};
window.getUserId = function (name) {
    var cookies = document.cookie;
    var cookieArray = cookies.split(";");
    for (var i = 0; i < cookieArray.length; i++) {
        var arr = cookieArray[i].split("=");
        if (arr[0] == name) {
            return arr[1];
        }
    }
    return null;
};
var app = angular.module('app', []);
app.controller('indexCtrl', function ($scope, $http) {
    $scope.ask = function () {
        if ($scope.t_title == null || $scope.t_title == 'undefined' || $scope.t_title == '') {
            layer.msg('您还没有输入内容', {time: 500});
            return;
        }
        $http.post('/dy/home/addquestion', {
            t_title: $scope.t_title,
            t_user_id: $scope.userid
        }).success(function (d) {
            if (d == true) {
                window.location.href = '/dy/home/askfinish';
            } else {
                layer.msg('提交失败', {time: 500});
            }
        }).error(function (e) {
            layer.msg('提交失败', {time: 500});
        });
    };
    $scope.queryquestionsbytop = function () {
        $http.post('/dy/home/queryquestionsbytop', null).success(function (d) {
            $scope.questions = d;
        });
    };
    $scope.queryarticlesbytop = function () {
        $http.post('/dy/home/queryarticlesbytop', null).success(function (d) {
            $scope.articles = d;
        });
    };
    $scope.init = function () {
        $scope.fileServer = window.fileServer;
        $scope.userid = window.getUserId('userid');
        $scope.queryquestionsbytop();
        $scope.queryarticlesbytop();
    };
    $scope.init();
});
app.controller('askCtrl', function ($scope, $http) {
    $scope.queryuser = function () {
        $http.post('/dy/home/queryuser/' + $scope.userid, null).success(function (d) {
            $scope.user = d;
        });
    };
    $scope.ask = function () {
        if ($scope.t_title == null || $scope.t_title == 'undefined' || $scope.t_title == '') {
            layer.msg('您还没有输入内容', {time: 500});
            return;
        }
        $http.post('/dy/home/addquestion', {
            t_title: $scope.t_title,
            t_user_id: $scope.userid
        }).success(function (d) {
            if (d == true) {
                window.location.href = '/dy/home/askfinish';
            } else {
                layer.msg('提交失败', {time: 500});
            }
        }).error(function (e) {
            layer.msg('提交失败', {time: 500});
        });
    };
    $scope.queryquestionsbytop = function () {
        $http.post('/dy/home/queryquestionsbytop', null).success(function (d) {
            $scope.questions = d;
        });
    };
    $scope.init = function () {
        $scope.userid = window.getUserId('userid');
        $scope.queryuser();
        $scope.queryquestionsbytop();
    };
    $scope.init();
});
app.controller('articleCtrl', function ($scope, $http, $sce) {
    $scope.queryarticle = function () {
        $http.post('/dy/home/queryarticle/' + $scope.id, null).success(function (d) {
            $scope.article = d;
            $scope.article.t_content = $sce.trustAsHtml(d.t_content);
        });
    };
    $scope.init = function () {
        $scope.id = window.getUrlParam('id');
        $scope.queryarticle();
    };
    $scope.init();
});
app.controller('articlesCtrl', function ($scope, $http) {
    $scope.queryarticles = function () {
        $scope.global_text = '加载中...';
        $http.post('/dy/home/queryarticles/' +
            $scope.pageIndex + '/' +
            $scope.pageSize + '/' +
            $scope.orderby + '/' +
            $scope.id + '/' +
            $scope.keyword, null).success(function (d) {
            $scope.global_count = d.length;
            if (d.length == 0) {
                $scope.global_text = '没有更多了';
            } else {
                $scope.global_text = '点击加载更多';
            }
            d.forEach(function (e) {
                $scope.articles.push(e);
            });
        });
    };
    $scope.querymore = function () {
        if ($scope.global_count == 0) {
            return;
        }
        $scope.pageIndex++;
        $scope.queryarticles();
    };
    $scope.querykeyword = function () {
        $scope.reset();
        $scope.queryarticles();
    };
    $scope.queryorderby = function (e) {
        $scope.reset();
        $scope.orderby = e;
        $scope.queryarticles();
    };
    $scope.reset = function () {
        $scope.articles = [];
        $scope.pageIndex = 1;
        $scope.pageSize = 10;
    };
    $scope.init = function () {
        $scope.reset();
        $scope.global_count = 0;
        $scope.global_text = '点击加载更多';
        $scope.orderby = 1;//1.最新 2.热门
        $scope.keyword = '';
        $scope.fileServer = window.fileServer;
        $scope.id = window.getUrlParam('id');
        $scope.queryarticles();
    };
    $scope.init();
});
app.controller('questionCtrl', function ($scope, $http) {
    $scope.queryuser = function () {
        $http.post('/dy/home/queryuser/' + $scope.userid, null).success(function (d) {
            $scope.user = d;
        });
    };
    $scope.queryquestion = function () {
        $http.post('/dy/home/queryquestion/' + $scope.id, null).success(function (d) {
            $scope.question = d;
        });
    };
    $scope.queryallanswers = function () {
        $http.post('/dy/home/queryallanswers/' + $scope.id, null).success(function (d) {
            $scope.answers = d;
        });
    };
    $scope.answer = function () {
        console.log($scope.t_content);
        if ($scope.t_content == null || $scope.t_content == 'undefined' || $scope.t_content == '') {
            layer.msg('您还没有输入内容', {time: 500});
            return;
        }
        $http.post('/dy/home/addanswer', {
            t_question_id: $scope.id,
            t_content: $scope.t_content,
            t_user_id: $scope.userid
        }).success(function (d) {
            if (d == true) {
                $scope.queryallanswers();
            } else {
                layer.msg('提交失败', {time: 500});
            }
        }).error(function (e) {
            layer.msg('提交失败', {time: 500});
        });
    };
    $scope.init = function () {
        $scope.id = window.getUrlParam('id');
        $scope.userid = window.getUserId('userid');
        $scope.queryuser();
        $scope.queryquestion();
        $scope.queryallanswers();
    };
    $scope.init();
});
app.controller('questionsCtrl', function ($scope, $http) {
    $scope.queryuser = function () {
        $http.post('/dy/home/queryuser/' + $scope.userid, null).success(function (d) {
            $scope.user = d;
        });
    };
    $scope.queryallquestions = function () {
        $http.post('/dy/home/queryallquestions/' + $scope.userid, null).success(function (d) {
            $scope.questions = d;
        });
    };
    $scope.init = function () {
        $scope.userid = window.getUserId('userid');
        $scope.queryuser();
        $scope.queryallquestions();
    };
    $scope.init();
});