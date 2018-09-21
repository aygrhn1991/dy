<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="questionCtrl">
    <div class="user-wrapper" ng-if="question.t_user_id==userid">
        <img src="{{user.w_headimgurl}}" class="user-headimg">
        <span class="user-nickname">&nbsp;&nbsp;<span ng-bind="user.w_nickname"></span></span>
        <a href="/dy/home/questions" class="user-myquestion">
            <img src="/dy/static/img/1.png">
        </a>
    </div>
    <div class="common-head">
        <div class="common-head-title" ng-bind="question.t_title"></div>
        <div class="common-head-time">
            <span ng-bind="question.t_time|date:'yyyy-MM-dd HH:mm:ss'"></span>
        </div>
    </div>
    <div class="common-wrapper" style="{{'margin-bottom:'+(question.t_user_id==userid?50:0)+'px'}}">
        <div class="common-content">
            <div ng-class="{'answer-left':d.t_user_id==0,'answer-right':d.t_user_id!=0}" ng-repeat="d in answers"
                 ng-if="!(d.t_isimg==true&&d.t_user_id!=userid)">
                <div class="answer-time" ng-bind="d.t_time|date:'yyyy-MM-dd HH:mm:ss'"></div>
                <div class="answer-item">
                    <img src="/dy/static/img/6.png" class="answer-tag" ng-if="d.t_user_id==0">
                    <div class="answer-content" ng-bind="d.t_content" ng-if="d.t_isimg==false"></div>
                    <div class="answer-content" ng-if="d.t_isimg==true">
                        <img src="{{fileServer}}/user/{{d.t_content}}">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="common-ask-wrapper" ng-show="question.t_user_id==userid">
        <div class="common-ask">
            <input type="text" placeholder="点击此处回复..." ng-model="t_content">
            <button ng-click="answer()">发送</button>
            <button onclick="document.getElementById('img').click()">附件</button>
            <input type="file" id="img" hidden onchange="angular.element(this).scope().onFileChange(this.files)"
                   ng-model="img">
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
