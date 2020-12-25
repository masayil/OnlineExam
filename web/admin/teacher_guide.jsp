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
    </style>


</head>

<body>,
<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<%
    File downLoadFileDir=new File(request.getServletContext().getRealPath("/uploadfile/list/teacher"));
    File [] teacherlist;
    teacherlist=downLoadFileDir.listFiles();
%>
<div style="padding-left: 50px;padding-top: 30px;">
    <div style="background: lightgrey;text-align: center;padding-top: 30px;height: 500px;width: 1200px;">
        <div><h1>教师管理</h1><br>
            <div><h2>浏览教师</h2></div><br>
            <div style="padding-left:350px;text-align: left;">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        学&emsp;院：<select style="width: 250px;" id="departmentselect">
                        <option value="全部">全部</option>
                        <option value="学院1">学院1</option>
                        <option value="学院2">学院2</option>
                        <option value="学院3">学院3</option>
                        <option value="学院4">学院4</option>
                    </select>
                    </div>&emsp;&emsp;
                    <button type="button" class="btn btn-warning" style="width: 90px;" onclick="browserDepart()">检索</button>
                </form>
                <br>
                <form class="form-inline" role="form">
                    <div class="form-group">
                        教师名：<input type="text" class="form-control" id="teachername" placeholder="请输入名字关键字" style="width: 250px;" autocomplete="off">
                    </div>&emsp;&emsp;
                    <button type="button" class="btn btn-warning" style="width: 90px;" onclick="browserName()">检索</button>
                </form>
            </div>
            <br>
            <div><h2>导入教师</h2></div><br>
            <button type="button" class="btn btn-info" style="width: 90px;" onclick="window.location.href='./admin/teacher_singleimport.jsp'">单个导入</button>&emsp;&emsp;&emsp;&emsp;
            <button type="button" class="btn btn-info" style="width: 90px;" onclick="window.location.href='./admin/teacher_multiImport.jsp'">批量导入</button>&emsp;&emsp;&emsp;&emsp;
            <button type="button" class="btn btn-info" style="width: 120px;" onclick="window.location.href='DownloadTeaTemplet?resPath=<%=teacherlist[0].getName()%>'">导入模板下载</button>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function browserDepart() {
        var depart=document.getElementById("departmentselect").value;
        window.open('BrowserTDepart?type=1'+'&depart='+depart,'_blank');
    }
    function browserName() {
        var teachername=document.getElementById("teachername").value;
        if(teachername===""){
            alert("请输入教师名字");
        }else {
            window.open('BrowserTDepart?type=2' + '&teachername=' + teachername, '_blank');
        }
    }
</script>
</body>
</html>