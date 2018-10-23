<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"/>
    <title>龙江问医</title>
    <link href="/dy/static/css/dy.css" rel="stylesheet">
    <script src="/dy/static/js/jquery.js"></script>
    <script src="/dy/static/layer/layer.js"></script>
    <script src="/dy/static/js/angular.js"></script>
    <script src="/dy/static/js/dy.js"></script>
    <%--微信jssdk--%>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
    <script src="/dy/static/js/wx.js"></script>
</head>
<body ng-controller="mainCtrl">
<div class="header-wrapper">
    <a class="header-back" href="javascript:window.history.back();">
        <img src="/dy/static/img/7.png">
    </a>
    <a class="header-home" href="/dy/home/index">
        <img src="/dy/static/img/8.png">
    </a>
</div>