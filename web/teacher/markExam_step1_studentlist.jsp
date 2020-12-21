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

    <title>在线考试系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">

</head>

<body>
<jsp:useBean id="teacher" type="com.bean.entity.Teacher" scope="session"></jsp:useBean>
<div>
<ul class="breadcrumb">
    <li><a href="CheckExamT?type=approve">返回上一页</a></li>
    <li class="active">${examname}</li>
</ul>
</div>
<h4 style="padding-left: 70px;">待批阅的试卷：</h4>
<div style="padding-left: 50px;padding-right: 50px;padding-top: 20px;">
    <c:forEach var="idlist" items="${requestScope.studentIDlist}">
        <button type="button" class="btn btn-link" onclick="markPaper('${examuuid}','${idlist.s_id}','${examname}','${idlist.s_name}')">${idlist.s_id}---${idlist.s_name}</button>
    </c:forEach>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function markPaper(examuuid,studentid,examname,studentname) {
        window.location.href="MarkPaper?examuuid="+examuuid+"&studentid="+studentid+"&examname="+examname+"&studentname="+studentname;
    }
</script>
</body>
</html>