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
        .form-group{
            margin-top: 100px;
        }
    </style>
</head>

<body style="padding-left: 10px;">
<form class="form-inline" role="form" style="text-align: center;" action="ManMadePaperStepTwo" method="post">
    <h2 style="text-align: center;color: darkgrey;">创建试卷</h2>
    <div class="form-group">
        <label class="sr-only" for="papername">试卷名字</label>
        名字：<input type="text" id="papername" name="papername" autocomplete="off">
    </div>
    <div class="form-group">
        <label class="sr-only" for="course">科目</label>
        科目：<select id="course" name="course">
        <option value="" selected>----</option>
        <c:forEach items="${requestScope.courseslist_madePaper}" var="courseslist_madePaper">
            <option value=${courseslist_madePaper.course_name}>${courseslist_madePaper.course_name}</option>
        </c:forEach>
    </select>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-info" onclick="return isempty()">下一步</button></div>
</form>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function isempty() {
        if($("#papername").val()===""||$("#course").val()===""){
            alert("内容不可为空");
            return false;
        }else {
            return true;
        }
    }
</script>
</body>
</html>