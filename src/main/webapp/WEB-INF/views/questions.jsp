<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="questionsCtrl">
    <div class="user-wrapper">
        <img src="{{user.w_headimgurl}}" class="user-headimg">
        <span class="user-nickname">&nbsp;&nbsp;<span ng-bind="user.w_nickname"></span></span>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <div class="question-items-c">
                <a href="/dy/home/question?id={{d.t_id}}" class="question-item-c" ng-repeat="d in questions">
                    <div class="question-badge" ng-if="d.t_read==0"></div>
                    <div ng-class="{'question-title-c':d.t_solved==1,'question-title-c-tag':d.t_solved==0}"
                         ng-bind="d.t_title"></div>
                    <div class="question-time-c" ng-bind="d.t_time|date:'yyyy-MM-dd HH:mm:ss'"></div>
                    <img src="/dy/static/img/4.png" class="question-tag-c" ng-if="d.t_solved==0">
                </a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
