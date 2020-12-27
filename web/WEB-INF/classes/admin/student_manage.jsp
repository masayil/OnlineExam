<%@ page import="java.io.File" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
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
        .no1{
            display: none;
        }
        .no2{
            display: none;
        }
        .no3{
            display: none;
        }
        .no4{
            display: none;
        }
    </style>


</head>

<body>,
<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<%
    File downLoadFileDir=new File(request.getServletContext().getRealPath("/uploadfile/list/student"));
    File [] studentlist;
    studentlist=downLoadFileDir.listFiles();
%>
<div style="padding-left: 50px;padding-top: 30px;">
    <div style="background: lightgrey;text-align: center;padding-top: 30px;height: 500px;width: 1200px;">
        <div><h1>学生管理</h1><br>
            <div><h2>浏览学生</h2></div><br>
            <div style="padding-left:350px;text-align: left;">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        学&emsp;院：<select style="width: 250px;" id="departmentselect">
                        <option value="全部" selected>全部</option>
                        <option value="信息技术学院">信息技术学院</option>
                        <option value="通识教育学院">通识教育学院</option>
                    </select>&emsp;&emsp;
                        班级：<select id="classselect">
                            <option class="all" value="全部" selected>全部</option>
                            <c:forEach var="majorclass_list_admin" items="${requestScope.majorclass_list_admin}">
                                <c:if test="${majorclass_list_admin.department eq '信息技术学院'}">
                                    <option class="no1" value=${majorclass_list_admin.class_1}>${majorclass_list_admin.class_1}</option>
                                </c:if>
                                <c:if test="${majorclass_list_admin.department eq '通识教育学院'}">
                                    <option class="no2" value=${majorclass_list_admin.class_1}>${majorclass_list_admin.class_1}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>&emsp;&emsp;
                    <button type="button" class="btn btn-warning" style="width: 90px;" onclick="searchone()">检索</button>
                </form>
                <br>
                <form class="form-inline" role="form">
                    <div class="form-group">
                        学生名：<input type="text" class="form-control" id="studentname" placeholder="请输入名字关键字" style="width: 250px;" autocomplete="off">
                    </div>&emsp;&emsp;
                    <button type="button" class="btn btn-warning" style="width: 90px;" onclick="searchtwo()">检索</button>
                </form>
            </div>
            <br>
            <div><h2>导入学生</h2></div><br>
            <button type="button" class="btn btn-info" style="width: 90px;" onclick="window.location.href='./admin/student_multiImport.jsp'">批量导入</button>&emsp;&emsp;&emsp;&emsp;
            <button type="button" class="btn btn-info" style="width: 120px;" onclick="window.location.href='DownloadStuTemplet?resPath=<%=studentlist[0].getName()%>'">导入模板下载</button>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
$(function () {
    $("#departmentselect").change(function () {
        if(document.getElementById("departmentselect").value==="全部"){
            document.getElementById("classselect").value="全部";
            $(".all").css("display","block");
            $(".no1").css("display","none");
            $(".no2").css("display","none");
        }else if(document.getElementById("departmentselect").value==="信息技术学院"){
            document.getElementById("classselect").value="全部";
            $(".all").css("display","block");
            $(".no1").css("display","block");
            $(".no2").css("display","none");
        }else if(document.getElementById("departmentselect").value==="通识教育学院"){
            document.getElementById("classselect").value="全部";
            $(".all").css("display","block");
            $(".no1").css("display","none");
            $(".no2").css("display","block");
        }
    });
});
function searchone() {
    var depart=document.getElementById("departmentselect").value;
    var classselect=document.getElementById("classselect").value;
    window.open('BrowerStudent?depart='+depart+'&classselect='+classselect,'_blank');
}
function searchtwo() {
    var studentname=document.getElementById("studentname").value;
    if(studentname===""){
        alert("请输入学生姓名");
    }else {
        window.open('BrowerStudent_search?studentname='+studentname,'_blank');
    }
}
</script>
</body>
</html>