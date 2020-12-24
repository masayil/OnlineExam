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
    <style>
        cite{
            font-size: 30px;
            padding: 20px;
        }
        cite:hover{
            cursor: pointer;
        }
        a{
            text-decoration: none;
        }
        a:hover {color: dimgray;
            text-decoration: none;
        }
    </style>
</head>


<body>
<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<div>
    <ul class="breadcrumb">
        <li>考试管理</li>
        <li>已发布的考试</li>
        <li class="active">教师列表</li>
    </ul>
</div>
<ul>
    <li><a href="javascript:void(0)" onclick="displayno1()">
        <cite>学院1</cite></a>
        <ul id="no1" style="display: none;">
            <li>        <c:forEach var="teachers" items="${requestScope.teachers_admin}">
                <c:if test="${teachers.t_department eq '学院1'}">
                    <button type="button" class="btn btn-link" style="color: darkgoldenrod;font-size: 18px;" onclick="gotoExam('${teachers.t_id}')">${teachers.t_id}-${teachers.t_name}</button></c:if>
            </c:forEach></li>
        </ul>
    </li>
    <li><a href="javascript:void(0)" onclick="displayno2()">
        <cite>学院2</cite></a>
        <ul id="no2" style="display: none;">
            <li>        <c:forEach var="teachers" items="${requestScope.teachers_admin}">
                <c:if test="${teachers.t_department eq '学院2'}">
                    <button type="button" class="btn btn-link" style="color: darkgoldenrod;font-size: 18px;" onclick="gotoExam('${teachers.t_id}')">${teachers.t_id}-${teachers.t_name}</button></c:if>
            </c:forEach></li>
        </ul>
    </li>
    <li><a href="javascript:void(0)" onclick="displayno3()">
        <cite>学院3</cite></a>
        <ul id="no3" style="display: none;">
            <li>        <c:forEach var="teachers" items="${requestScope.teachers_admin}">
                <c:if test="${teachers.t_department eq '学院3'}">
                    <button type="button" class="btn btn-link" style="color: darkgoldenrod;font-size: 18px;" onclick="gotoExam('${teachers.t_id}')">${teachers.t_id}-${teachers.t_name}</button></c:if>
            </c:forEach></li>
        </ul>
    </li>
    <li><a href="javascript:void(0)" onclick="displayno4()">
        <cite>学院4</cite></a>
        <ul id="no4" style="display: none;">
            <li>        <c:forEach var="teachers" items="${requestScope.teachers_admin}">
                <c:if test="${teachers.t_department eq '学院4'}">
                    <button type="button" class="btn btn-link" style="color: darkgoldenrod;font-size: 18px;" onclick="gotoExam('${teachers.t_id}')">${teachers.t_id}-${teachers.t_name}</button></c:if>
            </c:forEach></li>
        </ul>
    </li>
</ul>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function displayno1() {
        if(document.getElementById("no1").style.display==="none"){
            document.getElementById("no1").style.display="block";
        }else {
            document.getElementById("no1").style.display="none";
        }
    }
    function displayno2() {
        if(document.getElementById("no2").style.display==="none"){
            document.getElementById("no2").style.display="block";
        }else {
            document.getElementById("no2").style.display="none";
        }
    }
    function displayno3() {
        if(document.getElementById("no3").style.display==="none"){
            document.getElementById("no3").style.display="block";
        }else {
            document.getElementById("no3").style.display="none";
        }
    }
    function displayno4() {
        if(document.getElementById("no4").style.display==="none"){
            document.getElementById("no4").style.display="block";
        }else {
            document.getElementById("no4").style.display="none";
        }
    }
    function gotoExam(t_id) {
        window.open('CheckExamA?type=released&t_id='+t_id,'_blank');
    }
</script>
</body>
</html>