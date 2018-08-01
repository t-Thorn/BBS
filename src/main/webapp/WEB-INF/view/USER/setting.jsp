<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的主页</title>
    <link rel="stylesheet" href="/css/reset.css"/>
    <link rel="stylesheet" href="/css/homeHead.css"/>
    <link rel="stylesheet" href="/css/homePublic.css"/>
    <link rel="stylesheet" href="/css/base.css"/>
</head>
<body>
<div class="baseHead">
    <ul>
        <li><a href="setting.html" class="on">我的资料</a></li>
        <li><a href="uploadImg.html">头像</a></li>
        <li><a href="upload_password.html">密码</a></li>
    </ul>
</div>
<div class="baseCon">
    <div class="baseCon_son">
        <div class="baseCon_son_left">
            姓名
        </div>
        <div class="baseCon_son_right">
            <input type="text"/>
        </div>
    </div>
    <div class="baseCon_son">
        <div class="baseCon_son_left">
            年龄
        </div>
        <div class="baseCon_son_right">
            <input type="text"/>
        </div>
    </div>
    <div class="baseCon_son">
        <div class="baseCon_son_left">
            身份证
        </div>
        <div class="baseCon_son_right">
            <input type="text"/>
        </div>
    </div>
    <div class="chooseSex">
        <p><input type="radio" name="sex" checked/><label>男</label></p>
        <p><input type="radio" name="sex"/><label>女</label></p>
    </div>
    <input type="submit" value="确认修改" class="upload_sure"/>
</div>
</div>
</body>