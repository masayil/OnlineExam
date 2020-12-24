<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/fonts.css" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/syalert/syalert.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css" />
</head>

<body>
<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<div class="meal-box">
<h3 style="text-align: center;color: dimgray;">职工号：${thisteacher.t_id}&emsp;&emsp;姓名：${thisteacher.t_name}&emsp;&emsp;学院：${thisteacher.t_department}</h3>
    <div class="meal-cut">
        <ul>
            <c:forEach var="teacherlesson" items="${requestScope.newlessons_admin}">
                <li>
                    <span>
                        <img src="${pageContext.request.contextPath}/img/course_logo.png" alt="图片">
                        <h5 style="color: darkgoldenrod;">科目：${teacherlesson.newlesson_name}</h5>
                        <h5 style="color: cornflowerblue;">授课班级：${teacherlesson.newlesson_class}</h5>
                        <h5>开设时间：${teacherlesson.newlesson_createDate}</h5>
                        课程号：<input type="text"    value=${teacherlesson.newlesson_uuid} readonly style="border:none; width:250px;font-size: 8px;">
                        <b></b>
                        <font>
                            <a href="javascript:void(0)" onclick="remove('${teacherlesson.newlesson_uuid}','${thisteacher.t_id}')"  style="text-decoration:none;margin-left: 75px;">撤销课程</a>
                        </font>
                    </span>
                </li>
            </c:forEach>
        </ul>

    </div>

</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script>
    function remove(lessonid,teacherid) {
        if(confirm("确认撤销此课程？")){
        window.location.href="rootDeletelesson?lessonuuid="+lessonid+"&t_id="+teacherid;}
    }
</script>
</body>
</html>