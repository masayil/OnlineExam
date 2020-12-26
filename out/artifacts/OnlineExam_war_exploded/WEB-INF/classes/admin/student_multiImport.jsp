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

<body>
<form class="form-inline" role="form" style="text-align: center;" action="UploadStuList" method="post" enctype="multipart/form-data">
    <h2 style="text-align: center;color: darkgrey;">批量导入学生</h2>
    <div class="form-group">
        <label class="sr-only" for="inputfile">文件输入</label>
        <input type="file" id="inputfile" name="inputfile" required>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-info">提交</button></div>
</form>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>