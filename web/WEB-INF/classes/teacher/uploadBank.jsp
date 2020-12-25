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

</head>

<body style="padding-left: 80px;">
<div>
<div style="padding-left: 100px;padding-top: 30px;">
<div>
    <h4>模板下载</h4>
<table border="1" style="text-align: center;width: 450px;">
    <tr bgcolor="#fff8dc">
        <th>文件名称</th>
        <th>下载</th>
    </tr>
    <c:forEach var="afile" items="${templetlist}">
    <tr bgcolor="#a9a9a9">
        <td>${afile.name}</td>
        <td><a href="GetTempletServlet?resPath=${afile.name}" style="text-decoration: none;">下载</a></td>
    </tr>
    </c:forEach>
</table>
</div>
    <div style="position: relative; top:50px;">

<div>
    <h3>批量上传</h3>
<form action="UploadQServlet" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="filepath1">
    <input type="submit" value="提交" style="border-radius: 10px;background: #2F4056;width: 80px;height: 40px;color: yellow;">
</form>
</div>
</div>
</div>
</div>
<div style="height: 50px;"></div>
</div>
</body>
</html>