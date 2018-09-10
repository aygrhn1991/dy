<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="articleCtrl">
    <div class="article-head">
        <div class="article-head-title" ng-bind="article.t_title"></div>
        <div class="article-head-time">
            作者：<span ng-bind="article.t_author">测试</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span ng-bind="article.t_time|date:'yyyy-MM-dd HH:mm:ss'">测试</span>
        </div>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <div ng-bind-html="article.t_content"></div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
