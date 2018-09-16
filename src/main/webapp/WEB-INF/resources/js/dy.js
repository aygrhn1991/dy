window.fileServer = 'http://localhost:8000';
window.getUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);
    return null;
};
window.getUserId = function (name) {
    var cookies = document.cookie;
    var cookieArray = cookies.split(";");
    for (var i = 0; i < cookieArray.length; i++) {
        var arr = cookieArray[i].split("=");
        if (arr[0] === name) {
            return arr[1];
        }
    }
    return null;
};
var app = angular.module('app', []);
app.controller('indexCtrl', function ($scope, $http) {
    $scope.ask = function () {
        if ($scope.t_title == null || $scope.t_title === '' || $scope.t_title === 'undefined' || $scope.t_title === undefined) {
            layer.msg('您还没有输入内容', {time: 500, offset: '50%'});
            return;
        }
        $http.post('/dy/home/addquestion', {
            t_title: $scope.t_title,
            t_user_id: $scope.userid
        }).success(function (d) {
            if (d === true) {
                window.location.href = '/dy/home/askfinish?t_title=' + $scope.t_title;
            } else {
                layer.msg('提交失败', {time: 500, offset: '50%'});
            }
        }).error(function () {
            layer.msg('提交失败', {time: 500, offset: '50%'});
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
        if ($scope.t_title == null || $scope.t_title === '' || $scope.t_title === 'undefined' || $scope.t_title === undefined) {
            layer.msg('您还没有输入内容', {time: 500, offset: '50%'});
            return;
        }
        $http.post('/dy/home/addquestion', {
            t_title: $scope.t_title,
            t_user_id: $scope.userid
        }).success(function (d) {
            if (d === true) {
                window.location.href = '/dy/home/askfinish?t_title=' + $scope.t_title;
            } else {
                layer.msg('提交失败', {time: 500, offset: '50%'});
            }
        }).error(function () {
            layer.msg('提交失败', {time: 500, offset: '50%'});
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
app.controller('askfinishCtrl', function ($scope, $http) {
    $scope.queryquestionsbytag = function () {
        $http.post('/dy/home/queryquestionsbytag/' + $scope.t_title, null).success(function (d) {
            $scope.questions = d;
        });
    };
    $scope.queryarticlesbytag = function () {
        $http.post('/dy/home/queryarticlesbytag/' + $scope.t_title, null).success(function (d) {
            $scope.articles = d;
        });
    };
    $scope.init = function () {
        $scope.fileServer = window.fileServer;
        $scope.t_title = window.getUrlParam('t_title');
        $scope.queryquestionsbytag();
        $scope.queryarticlesbytag();
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
            if (d.length === 0) {
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
        if ($scope.global_count === 0) {
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
        $scope.keyword = '';
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
            setTimeout(function () {
                var ele = document.getElementsByTagName('body')[0];
                window.scrollTo(0, ele.scrollHeight)
            }, 200);
        });
    };
    $scope.answer = function () {
        if ($scope.t_content == null || $scope.t_content === '' || $scope.t_content === 'undefined' || $scope.t_content === undefined) {
            layer.msg('您还没有输入内容', {time: 500, offset: '50%'});
            return;
        }
        $http.post('/dy/home/addanswer', {
            t_question_id: $scope.id,
            t_content: $scope.t_content,
            t_user_id: $scope.userid
        }).success(function (d) {
            if (d === true) {
                $scope.t_content = '';
                $scope.queryallanswers();
            } else {
                layer.msg('提交失败', {time: 500, offset: '50%'});
            }
        }).error(function () {
            layer.msg('提交失败', {time: 500, offset: '50%'});
        });
    };
    $scope.onFileChange = function (files) {
        var file = files[0];
        var extensionName = file.name.substring(file.name.lastIndexOf('.') + 1, file.name.length);
        if ('PNG,JPG,JPEG'.indexOf(extensionName.toUpperCase()) === -1) {
            layer.msg('请上传png,jpg,jpeg格式的图片', {time: 500, offset: '50%'});
            return;
        }
        var fileSize = file.size; //这里读到的是字节数
        if (fileSize > 1000 * 1000) {
            layer.msg('请上传1M以内的图片', {time: 500, offset: '50%'});
            return;
        }
        var formData = new FormData();
        formData.append('file', file);
        formData.append('t_question_id', $scope.id);
        formData.append('t_user_id', $scope.userid);
        $http({
            method: 'post',
            url: '/dy/home/addimg',
            data: formData,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function (d) {
            if (d === true) {
                $scope.queryallanswers();
            } else {
                layer.msg('提交失败', {time: 500, offset: '50%'});
            }
        }).error(function () {
            layer.msg('提交失败', {time: 500, offset: '50%'});
        });
    };
    $scope.init = function () {
        $scope.fileServer = window.fileServer;
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
