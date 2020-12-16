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
    <!-- Latest compiled and minified CSS -->
    <style>
        select{
            height: 40px;
            padding: 5px;
            background-color:wheat;
            border: 0;

            font-size: 16px;
            width: 240px;

        }
    </style>


</head>

<body>,
<jsp:useBean id="teacher" type="com.bean.entity.Teacher" scope="session"></jsp:useBean>
<div style="padding-left: 50px;padding-top: 30px;">
<div style="background: lightgrey;text-align: center;padding-top: 30px;height: 450px;width: 1200px;">
    <div><h3>浏览题库</h3><br><button type="button" class="btn btn-info" style="width: 110px;" onclick="openBank1('all','none')">所有</button>
        <button type="button" class="btn btn-info" style="margin-left: 50px;width: 110px;" onclick="openBank5('mine','none')">我的</button>
    <div><h2>分类检索</h2></div><br>
        <div style="padding-left:350px;text-align: left;">
    <form class="form-inline" role="form">
        <div class="form-group">
            科目名：<select style="width: 250px;" id="coureseselect">
            <c:forEach var="courselist" items="${courseArrayList}">
            <option value=${courselist.course_name}>${courselist.course_name}</option>
            </c:forEach>
        </select>
        </div>&emsp;&emsp;
        <button type="submit" class="btn btn-warning" style="width: 90px;" onclick="openBank3('course_name','none')">检索</button>
    </form>
    <br>
    <form class="form-inline" role="form">
        <div class="form-group">
            教师名：<select style="width: 250px;" id="teacherselect">
            <c:forEach var="teacherlist" items="${teacherArrayList}">
            <option value=${teacherlist.t_id}>${teacherlist.t_name}</option>
            </c:forEach>
        </select><!--&emsp;&emsp;&emsp;或者&emsp;&emsp;
            <input type="text" class="form-control" id="teachername" placeholder="请教师名字">-->
        </div>&emsp;&emsp;
        <button type="submit" class="btn btn-warning" style="width: 90px;" onclick="openBank4('teacher_name','none')">检索</button>
    </form>
    <br>
    <form class="form-inline" role="form">
    <div class="form-group">
    题&emsp;目：<input type="text" class="form-control" id="title" placeholder="请输入题目关键字" style="width: 250px;" autocomplete="off">
</div>&emsp;&emsp;
    <button type="submit" class="btn btn-warning" style="width: 90px;" onclick="openBank2('title_name','none')">检索</button>
    </form>
        </div>
</div>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function openBank1(category,questiontype) {
        var text="";
        window.open('OpenBankServlet?category='+category+'&questiontype='+questiontype+'&title='+text, '_target');
    }
    function openBank2(category,questiontype) {
        var text = document.getElementById("title").value;
        if (text === "" || text == null) {
            alert("请输入内容");
            return false;
        } else {
            window.open('OpenBankServlet?category=' + category + '&questiontype=' + questiontype + '&title=' + text, '_target');
        }
    }
    function openBank3(category,questiontype) {
        var text=document.getElementById("coureseselect").value;
        window.open('OpenBankServlet?category='+category+'&questiontype='+questiontype+'&title='+text, '_target');
    }
    function openBank4(category,questiontype) {

             var text=document.getElementById("teacherselect").value;
            window.open('OpenBankServlet?category=' + category + '&questiontype=' + questiontype + '&title=' + text, '_target');
    }
    function openBank5(category,questiontype) {
        var text="";
        window.open('OpenBankServlet?category='+category+'&questiontype='+questiontype+'&title='+text, '_target');
    }
</script>
</body>
</html>