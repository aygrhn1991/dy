<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="articlesCtrl">
    <div class="common-search">
        <img src="/dy/static/img/3.png" ng-click="querykeyword()">
        <input type="text" placeholder="点击此处搜索..." ng-model="keyword">
    </div>
    <div class="common-tabs">
        <div class="common-tab" ng-class="{'common-tab-active':orderby==1}" ng-click="queryorderby(1)">最新</div>
        <div class="common-tab" ng-class="{'common-tab-active':orderby==2}" ng-click="queryorderby(2)">热门</div>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <div class="article-items">
                <a href="/dy/home/article?id={{d.t_id}}" class="article-item" ng-repeat="d in articles">
                    <img src="{{fileServer}}/article/{{d.t_cover}}">
                    <div class="article-title" ng-bind="d.t_title"></div>
                </a>
            </div>
            <div class="article-more" ng-bind="global_text" ng-click="querymore()"></div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
