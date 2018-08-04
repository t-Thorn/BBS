<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改头像</title>
    <link rel="stylesheet" href="/css/reset.css"/>
    <link rel="stylesheet" href="/css/homeHead.css"/>
    <link rel="stylesheet" href="/css/homePublic.css"/>
    <link rel="stylesheet" href="/css/base.css"/>
</head>
<body>
<!-- tip -->
<c:if test="${!(tip ==null)}">
    <script>alert("${tip}")</script>
</c:if>
<c:remove var="tip" scope="session"/>
<!-- endtip -->
<div class="baseHead">
    <ul>
        <li><a href="setting.jsp">我的资料</a></li>
        <li><a href="uploadImg.jsp" class="on">头像</a></li>
        <li><a href="upload_password.jsp">密码</a></li>
    </ul>
</div>
<form action="/user/updatephoto" method="post" enctype="multipart/form-data">
    <div class="baseCon">
        <div class="upImg">
            <div class="Imgbtn">选择头像
                <input type="file" class="uploadPic" name="file">
            </div>
            <br>

            <div class="Imgbtn">上传
                <input type="submit" value="" class="uploadPic">
            </div>
            <p>建议尺寸168*168，支持jpg、png、gif,最大不能超过50KB</p>
            <div class="mypic">
                <img src="/photo/${userSession.getPhoto()}" alt=""/>
            </div>
        </div>
    </div>
</form>

</body>
</html>