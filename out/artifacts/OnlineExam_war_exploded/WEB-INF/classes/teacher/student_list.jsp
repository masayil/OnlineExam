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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/btn1.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <style>
        .content {
            width: 600px;
            height: 600px;
            /* background: yellow; */
            margin: 50px auto;
        }
        table {
            text-align:center;

        }
        table th{
            text-align:center;
        }

    </style>
</head>

<body>
<div style="margin-left: 50px;">
<button class="btn-6" onclick="window.location.href='FirstLoad?status=teacher'">
    <div class="effect">
                <span>
                    返回
                </span>
    </div>
    <span>
                返回
            </span>
</button>
<div style="text-align: center;">
    <button type="button" style="background: red;border-radius: 15px;" id="banji" class="b1">${classname}</button>
    <button type="button" style="background: lightblue; border-radius: 15px;" id="qita" class="b1">其他</button>
</div>
    <div style="height: 60px;"></div>
    <div class="container" style="display: block;margin-left: -5px;" id="table1">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-bordered" style="width: 1200px;">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>学院</th>
                        <th>专业</th>
                        <th>班级</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="studentlist_class" items="${requestScope.studentlist_class}">
                        <tr class="warning">
                            <td>${studentlist_class.s_id}</td>
                            <td>${studentlist_class.s_name}</td>
                            <td>${studentlist_class.s_sex}</td>
                            <td>${studentlist_class.s_department}</td>
                            <td>${studentlist_class.s_major}</td>
                            <td>${studentlist_class.s_class}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="container" style="display: none;margin-left: -5px;" id="table2">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-bordered" style="width: 1200px;">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>学院</th>
                        <th>专业</th>
                        <th>班级</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="studentlist_retake" items="${requestScope.studentlist_retake}">
                        <tr class="warning">
                            <td>${studentlist_retake.s_id}</td>
                            <td>${studentlist_retake.s_name}</td>
                            <td>${studentlist_retake.s_sex}</td>
                            <td>${studentlist_retake.s_department}</td>
                            <td>${studentlist_retake.s_major}</td>
                            <td>${studentlist_retake.s_class}</td>
                            <td><a href="javascript:void(0)" onclick="window.location.href='DeleteRetakeServlet?studentid=${studentlist_retake.s_id}&lessonuuid=${lessonuuid}'" style="text-decoration:none;">踢出</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>




</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    $(function () {
        $("#banji").click(function () {
            document.getElementById("banji").style.backgroundColor="#ff0000";
            document.getElementById("qita").style.backgroundColor="#add8e6";
            document.getElementById("table1").style.display="block";
            document.getElementById("table2").style.display="none";
        });
        $("#qita").click(function () {
            document.getElementById("qita").style.backgroundColor="#ff0000";
            document.getElementById("banji").style.backgroundColor="#add8e6";
            document.getElementById("table2").style.display="block";
            document.getElementById("table1").style.display="none";
        });
    });
</script>
</body>
</html>