<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>论坛首页</title>
    <link rel="stylesheet" href="/css/reset.css"/>
    <link rel="stylesheet" href="/css/public.css"/>
    <link rel="stylesheet" href="/css/index.css"/>
    <script src="/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<c:if test="${searchError!=null}">
    <script>alert("${searchError}")</script>
</c:if>
<header class="ltHead">
    <div class="ltHead_cen">
        <!--未登入开始-->
        <div class="ltForm appear">
            <a href=""><img src="/img/indexForm_bg.png" alt="" class="headPic2"/></a>
            <ul>
                <li><a href="">登入</a></li>
                <li><a href="">注册</a></li>
            </ul>
        </div>
        <!-- 未登入结束-->
        <!-- 登入开始，未登入时以下隐藏-->
        <!--<div class="lt_login appear">-->
        <!--<ul>-->
        <!--<li><a href="">花开花落</a></li>-->
        <!--<li><a href="">退出</a></li>-->
        <!--</ul>-->
        <!--</div>-->
        <!-- 登入结束-->
        <div class="navFix">
            <!--登入开始-->
            <a href="" class="navMe">花花</a>
            <!--登入结束-->
            <!--未登入开始-->
            <!--<a href="" class="navLogin">登录</a>-->
            <!--未登入结束-->
            <a href="" class="navWrite">发新帖</a>
        </div>
    </div>
</header>
<div class="indexMain">
    <div class="indexMain_left">
        <div class="indexMain_left_btn">
            <ul>
                <li><a href="javascript:" class="on">最新</a></li>
            </ul>
        </div>
        <div class="indexMain_left_con">
            <!--帖子列表-->
            <c:forEach items="${posts}" var="post" varStatus="x">
                <div class="indexCon_msg">
                    <div class="indexCon_msg_pic">
                        <img src="/photo/${post.getUser().getPhoto()}">
                    </div>
                    <div class="indexCon_msg_detail">
                        <a href="/BBS/post?param=${post.getId()}">
                            <c:choose>
                                <c:when test="${post.getType() eq '1'}">
                                    <div class="indexCon_msg_detail_tittle_help">
                                        <span>求助</span>
                                        <p>${post.getTitle()}
                                        </p>
                                    </div>
                                </c:when>
                                <c:when test="${post.getType() eq '2'}">
                                    <div class="indexCon_msg_detail_tittle_exp">
                                        <span>经验</span>
                                        <p>${post.getTitle()}
                                        </p>
                                    </div>
                                </c:when>
                                <c:when test="${post.getType() eq '3'}">
                                    <div class="indexCon_msg_detail_tittle_chat">
                                        <span>闲聊</span>
                                        <p>${post.getTitle()}
                                        </p>
                                    </div>
                                </c:when>
                                <c:when test="${post.getType() eq '0'}">
                                    <div class="indexCon_msg_detail_tittle_common">
                                        <span>正常</span>
                                        <p onclick="/BBS/post?param=${post.getId()}">
                                                ${post.getTitle()}
                                        </p>
                                    </div>
                                </c:when>
                            </c:choose>
                        </a>
                        <!--<div class="havePic">
                            <a href="">
                                <div class="havePic_head"></div>
                            </a>
                        </div>-->
                        <div class="indexCon_msg_detail_other">
                            <ul>
                                <li>${post.getUsername()}</li>
                                <li>
                                    <fmt:formatDate value="${post.getPosttime()}"
                                                    pattern="yyyy-MM-dd HH:mm"/>
                                </li>
                                <li>${post.getPostnum()}</li>
                                <li>${post.getViews()}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>

            </c:forEach>
            <!--有主题图循环结束-->
        </div>
        <div class="indexFooter">
            <div class="indexFooter_con">
                <c:if test="${nowpage>1}">
                    <a href="/BBS/page?param=${nowpage-1}"><</a>
                </c:if>
                <c:forEach var="page" begin="${nowpage-4>0?nowpage-4:1}" end="${pages}">
                    <c:choose>
                        <c:when test="${page==nowpage}">
                            <a href="/BBS/page?param=${page}" class="on">${page}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/BBS/page?param=${page}">${page}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${nowpage!=null and nowpage !=pages}">
                    <a href="/BBS/page?param=${nowpage+1}">></a>
                </c:if>
                <input type="text" id="page" placeholder="页码" style="width: 80px;
    height: 40px;text-indent: 20px;border: none;border-radius: 10px 0 10px 10px;float: left;">
                <script>
                    function choosepage() {

                        window.location.href = "/BBS/page?param=" + $("#page").attr("value")
                    }
                </script>
                <a href="javascript:void(0)" onclick="choosepage()">跳转</a>

            </div>
        </div>
    </div>
    <div class="indexMain_right">
        <div class="indexMain_rightCon">
            <a href="" class="newMsg">发新帖</a>
            <div class="pwfb">
                <div class="pwfbHead">
                    精品贴
                </div>
                <div class="indexPublic_con">
                    <ul class="jingping">
                        <c:forEach items="${EssencePosts}" var="essencepost">
                            <li>
                                <a href="/BBS/post?param=${essencepost.getId()}">${essencepost.getTitle()}</a>
                                <span style="float:right">${essencepost.getViews()}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="pwfb">
                <div class="pwfbHead">
                    置顶帖
                </div>
                <div class="indexPublic_con">
                    <ul class="jingping">
                        <c:forEach items="${TopPosts}" var="top">
                            <li>
                                <a href="/BBS/post?param=${top.getId()}">${top.getTitle()}</a>
                                <span style="float:right">${top.getViews()}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="indexSearch">
                <form action="/BBS/page" method="post">
                    <input type="hidden" name="method" value="1"/>
                    <input type="text" name="content" placeholder="请输入关键词"/>
                    <input type="submit" value="搜索"/>
                </form>
            </div>
            <div class="indexPublic">
                <div class="indexPublic_head">
                    本周热议
                </div>
                <div class="indexPublic_con">
                    <ul class="weekHot">
                        <c:forEach var="hotpost" items="${HotPosts}">
                            <li>
                                <a href="/BBS/post?param=${hotpost.getId()}">${hotpost.getTitle()}</a>
                                <span style="float:right">${hotpost.getViews()}</span></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<footer class="publicFooter">
    <p>Copyrigh &copy; 2017-2018 PaiWang 上海钦合投资管理有限公司版权所有 沪ICP备16032224号-2</p>
</footer>
</body>
</html>
<script src="js/jquery-1.8.3.min.js"></script>
<script>
    $(".indexMain_left_btn li a").click(function () {
        $(".indexMain_left_btn li a").removeClass("on");
        $(this).addClass("on");
    });
    window.onscroll = function () {
        var scrolls = document.body.scrollTop || document.documentElement.scrollTop;
        var slided = 60;
        if (scrolls >= slided) {
            $(".appear").hide();
            $(".navFix").show();
            $(".ltHead").addClass("navTop");
        } else {
            $(".appear").show();
            $(".navFix").hide();
            $(".ltHead").removeClass("navTop");
        }
    };
</script>