<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="askfinishCtrl">
    <a href="/dy/home/questions" class="ask-finish">
        <img src="/dy/static/img/0.png">
    </a>
    <div class="common-wrapper">
        <div class="common-content">
            <div class="question-nav-h">
                <img src="/dy/static/img/13.png">
            </div>
            <div class="question-items-b">
                <div class="question-item-b" ng-repeat="d in questions">
                    <div class="question-title-b" ng-bind="d.t_title"></div>
                </div>
            </div>
            <br>
            <div class="article-items">
                <div class="article-item" ng-repeat="d in articles">
                    <img src="{{fileServer}}/article/{{d.t_cover}}">
                    <div class="article-title" ng-bind="d.t_title"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
