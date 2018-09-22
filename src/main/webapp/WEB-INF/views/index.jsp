<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="indexCtrl">
    <a href="/dy/home/ask" class="index-top-img">
        <img src="/dy/static/img/5.png">
    </a>
    <div class="common-wrapper">
        <textarea class="question-textarea" placeholder="输入您想咨询的问题试试？" rows="8" ng-model="t_title"></textarea>
        <button ng-click="ask()">提问</button>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <a href="/dy/home/ask" class="question-nav-v">
                <img src="/dy/static/img/10.png">
            </a>
            <div class="question-items-a">
                <a href="/dy/home/question?id={{d.t_id}}" class="question-item-a" ng-repeat="d in questions">
                    <div class="question-title-a">
                        <span class="question-tag-a">精华</span>
                        {{d.t_title}}
                    </div>
                </a>
            </div>
        </div>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <div class="article-nav-h">
                <img src="/dy/static/img/2.png">
            </div>
            <div class="article-items">
                <a href="/dy/home/article?id={{d.t_id}}" class="article-item" ng-repeat="d in articles">
                    <img src="{{fileServer}}/article/{{d.t_cover}}">
                    <div class="article-title" ng-bind="d.t_title"></div>
                </a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
