<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.chart.BarChart_ByDatasetUtilities"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'MyJsp.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body>
<%
    String fileName = BarChart_ByDatasetUtilities.generateBarChart(session);
%>
<!--设置图片路径：（servlet的url-pattern）？filename=（上面获取的文件名fileName）  -->
<img src="DisplayChart?filename=<%=fileName%>" width="700" height="500" border="0" alt="图片">
<br>
名字：<%=fileName%>
</body>
</html>