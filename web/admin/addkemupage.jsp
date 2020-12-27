<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'MyJsp.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <style>
        .form-group{
            margin-top: 30px;
        }
    </style>
</head>

<body>
<%
    String depart=request.getParameter("depart");
%>
<div>
    <ul class="breadcrumb">
        <li>班级学生管理</li>
        <li>班级管理</li>
        <li class="active">增加班级</li>
    </ul>
</div>
<a href="javascript:void (0)" title="返回" onclick="window.history.go(-1)">
    <img src="${pageContext.request.contextPath}/img/back_logo.png" alt="未加载" width="64" height="64" style="position: relative;top:50px;left: 150px;"></a>
<h2 style="text-align: center;color: darkgray;">增加科目</h2>
<form class="form-inline" role="form" style="text-align: center" action="AddkemuAdmin" method="post">
    <div class="form-group">
        <label class="sr-only" for="depart">学院</label>
        学院：<input type="text" class="form-control" id="depart" name="depart" style="outline: none;border:none;" readonly  value="<%=depart%>">
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="course">科目</label>
        科目：<input type="text" class="form-control" id="course" name="course" placeholder="请输入科目" autocomplete="off" required>
    </div>
    <br>
    <button type="submit" class="btn btn-default" style="margin-top: 50px;">提交</button>
</form>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>