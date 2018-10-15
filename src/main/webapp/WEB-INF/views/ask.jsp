<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="askCtrl">
    <div class="user-wrapper">
        <img src="{{user.w_headimgurl}}" class="user-headimg">
        <span class="user-nickname">&nbsp;&nbsp;<span ng-bind="user.w_nickname"></span></span>
        <a href="/dy/home/questions" class="user-myquestion">
            <img src="/dy/static/img/1.png">
        </a>
    </div>
    <div class="common-wrapper">
        <textarea class="question-textarea" placeholder="输入您想咨询的问题试试？" rows="8" ng-model="t_title"></textarea>
        <button ng-click="ask()">提问</button>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <div class="question-nav-h">
                <img src="/dy/static/img/9.png">
            </div>
            <div class="question-items-b">
                <a href="/dy/home/question?id={{d.t_id}}" class="question-item-b" ng-repeat="d in questions">
                    <div class="question-title-b" ng-bind="d.t_title"></div>
                </a>
            </div>
            <div class="article-more" ng-bind="global_text" ng-click="querymore()"></div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
