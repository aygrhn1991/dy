<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="articleCtrl">
    <div class="common-head">
        <div class="common-head-title" ng-bind="article.t_title"></div>
        <div class="common-head-time">
            作者：<span ng-bind="article.t_author"></span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span ng-bind="article.t_time|date:'yyyy-MM-dd HH:mm:ss'"></span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            浏览：<span ng-bind="article.t_scan"></span>
        </div>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <div ng-bind-html="article.t_content"></div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
