<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 50px;"></div>
<div class="footer-wrapper">
    <a class="footer-tab" ng-click="changeTab(1)">
        <img src="/dy/static/img/{{menuId=='1'?'12':'16'}}.png">
        <div>妇产科</div>
    </a>
    <div class="footer-line"></div>
    <a class="footer-tab" ng-click="changeTab(2)">
        <img src="/dy/static/img/{{menuId=='2'?'11':'15'}}.png">
        <div>儿科</div>
    </a>
    <div class="footer-line"></div>
    <a class="footer-tab" ng-click="changeTab(3)">
        <img src="/dy/static/img/{{menuId=='3'?'14':'17'}}.png">
        <div>我的</div>
    </a>
</div>
</body>
</html>
