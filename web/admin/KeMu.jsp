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
        <li>科目管理</li>
        <li class="active">科目列表</li>
    </ul>
</div>
<ul>
    <li><a href="javascript:void(0)" onclick="displayno1()">
        <cite>信息技术学院</cite></a>&emsp;&emsp;<button type="button" class="btn btn-primary btn-sm" onclick="addkemu('信息技术学院')">增加科目</button>
        <ul id="no1" style="display: none;">
            <li>        <c:forEach var="courses_list" items="${requestScope.courses_list}">
                <c:if test="${courses_list.course_creator eq '信息技术学院'}">
                    <button type="button" class="btn btn-link" style="color: darkgoldenrod;font-size: 18px;">${courses_list.course_name}</button>
                    <button type="button" class="btn btn-danger" onclick="removekemu('${courses_list.course_name}')">删除</button>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</c:if>
            </c:forEach></li>
        </ul>
    </li>
    <hr>
    <li><a href="javascript:void(0)" onclick="displayno2()">
        <cite>通识教育学院</cite></a>&emsp;&emsp;<button type="button" class="btn btn-primary btn-sm" onclick="addkemu('通识教育学院')">增加科目</button>
        <ul id="no2" style="display: none;">
            <li>        <c:forEach var="courses_list" items="${requestScope.courses_list}">
                <c:if test="${courses_list.course_creator eq '通识教育学院'}">
                    <button type="button" class="btn btn-link" style="color: darkgoldenrod;font-size: 18px;">${courses_list.course_name}</button>
                    <button type="button" class="btn btn-danger" onclick="removekemu('${courses_list.course_name}')">删除</button>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</c:if>
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
    function removekemu(chosencourse) {
        if(confirm("是否确认删除？")){
            $.ajax({
                url:"RemovekemuAdmin",
                type:"post",
                dataType:"text",
                data:{
                    chosencourse:chosencourse
                },
                success:function (flag) {
                    if(flag==="yes"){
                        alert("科目删除成功");
                        location.reload();
                    }else if(flag==="no"){
                        alert("请再试一次");
                    }else if(flag==="busy"){
                        alert("服务器繁忙请稍后再试");
                    }
                },
                error:function () {
                    alert("发送数据失败，请稍后再试！");
                    location.reload();
                }
            });
        }
    }
    function addkemu(depart) {
        window.location.href="./admin/addkemupage.jsp?depart="+depart;
    }
</script>
</body>
</html>