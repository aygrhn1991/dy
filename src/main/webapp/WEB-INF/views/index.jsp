<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div ng-controller="indexCtrl">
    <a href="/dy/home/ask" class="index-top-img">
        <img src="/dy/static/img/5.png">
    </a>
    <div class="common-wrapper">
        <textarea class="question-textarea" placeholder="输入您想咨询的问题试试？" rows="8" ng-model="t_title"></textarea>
        <button class="question-button" ng-click="ask()">提问</button>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <a href="/dy/home/ask" class="question-nav-v">
                <img src="/dy/static/img/10.png">
            </a>
            <div class="question-items-a">
                <div class="question-item-a">
                    <div class="question-title-a">
                        <span class="question-tag-a">精华</span>
                        这句话可是很长的这句话可是很长的话可是很长的话可是很长的
                    </div>
                </div>
                <div class="question-item-a">
                    <div class="question-title-a">
                        <span class="question-tag-a">精华</span>
                        这句话可是很长的这句话可是很长的话可是很长的话可是很长的
                    </div>
                </div>
                <div class="question-item-a">
                    <div class="question-title-a">
                        <span class="question-tag-a">精华</span>
                        这句话可是很长的这句话可是很长的话可是很长的话可是很长的
                    </div>
                </div>
                <div class="question-item-a">
                    <div class="question-title-a">
                        <span class="question-tag-a">精华</span>
                        这句话可是很长的这句话可是很长的话可是很长的话可是很长的
                    </div>
                </div>
                <div class="question-item-a">
                    <div class="question-title-a">
                        <span class="question-tag-a">精华</span>
                        这句话可是很长的这句话可是很长的话可是很长的话可是很长的
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="common-wrapper">
        <div class="common-content">
            <div class="article-nav-h">
                <img src="/dy/static/img/2.png">
            </div>
            <div class="article-items">
                <div class="article-item">
                    <img src="/dy/static/img/0.png">
                    <div class="article-title">这句话可是很长的这句话可是很长的话可是很长的话可是很长的</div>
                </div>
                <div class="article-item">
                    <img src="/dy/static/img/0.png">
                    <div class="article-title">这句话可是很长的这句话可是很长的话可是很长的话可是很长的</div>
                </div>
                <div class="article-item">
                    <img src="/dy/static/img/0.png">
                    <div class="article-title">这句话可是很长的这句话可是很长的话可是很长的话可是很长的</div>
                </div>
                <div class="article-item">
                    <img src="/dy/static/img/0.png">
                    <div class="article-title">这句话可是很长的这句话可是很长的话可是很长的话可是很长的</div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
