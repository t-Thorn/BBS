<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
<div class="baseHead">
    <ul>
        <li><a href="setting.html">我的资料</a></li>
        <li><a href="uploadImg.html" class="on">头像</a></li>
        <li><a href="upload_password.html">密码</a></li>
    </ul>
</div>
<div class="baseCon">
    <div class="upImg">
        <div class="Imgbtn">上传头像
            <input type="file" class="uploadPic"/>
        </div>
        <p>建议尺寸168*168，支持jpg、png、gif,最大不能超过50KB</p>
        <div class="mypic">
            <img src="img/cs.png" alt=""/>
        </div>
    </div>
</body>
</html>