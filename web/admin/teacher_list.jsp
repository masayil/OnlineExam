<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <title>在线考试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>

<body>
<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<a href="" title="返回" onclick="window.history.go(-1);">
<img src="${pageContext.request.contextPath}/img/back_logo.png" alt="未加载" width="64" height="64" style="position: relative;top:50px;left: 50px;"></a>
<div style="padding-left: 150px;margin-top: 100px;">
<table class="table table-hover" style="word-break: break-all;width: 1200px;text-align: center;">
    <caption style="text-align: center;"><h3>${depart}-教师列表-${type}</h3></caption>
    <thead>
    <tr>
        <th style="text-align: center;width: 50px;">序号</th>
        <th style="text-align: center;width: 100px;">职工号</th>
        <th style="text-align: center;width: 150px;">姓名</th>
        <th style="text-align: center;width: 50px;">性别</th>
        <th style="text-align: center;width: 200px;">学院</th>
        <th style="text-align: center;width: 150px;">密码</th>
        <th style="text-align: center;width: 150px;">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.teacher_list_manage}" var="teacher_list_manage" varStatus="status">
    <tr class="warning">
        <td style="height: 40px;line-height: 40px;width: 50px;">${status.count}</td>
        <td style="height: 40px;line-height: 40px;width: 100px;">${teacher_list_manage.t_id}</td>
        <td style="height: 40px;line-height: 40px;width: 150px;">${teacher_list_manage.t_name}</td>
        <td style="height: 40px;line-height: 40px;width: 50px;">${teacher_list_manage.t_sex}</td>
        <td style="height: 40px;line-height: 40px;width: 200px;">${teacher_list_manage.t_department}</td>
        <td style="height: 40px;line-height: 40px;width: 150px;"><button type="button" class="btn btn-info" style="width: 90px;" onclick="resetPwd('${teacher_list_manage.t_id}')">重置密码</button></td>
        <td style="height: 40px;line-height: 40px;width: 150px;"><button type="button" class="btn btn-danger" style="width: 90px;" onclick="">修改资料</button></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function resetPwd(teacherid) {

    }
</script>
</body>
</html>