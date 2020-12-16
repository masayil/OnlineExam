<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'MyJsp.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/selectday/bootstrap-datetimepicker.min.css"/>
</head>

<body>
<input type="text"  id="datetimepicker1" readonly>---<input type="text"  id="datetimepicker2" readonly onchange="check()">
<button type="button" onclick="alert(document.getElementById('datetimepicker1').value)">1111111</button>
<button type="button" onclick="alert(document.getElementById('datetimepicker2').value)">222</button>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/selectday/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/selectday/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker1').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            autoclose: 1,
            startDate: new Date()
        });
        $('#datetimepicker2').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            autoclose: 1,
            startDate: new Date()
        });
    });
    function check() {
        if(document.getElementById('datetimepicker1').value===document.getElementById('datetimepicker2').value){
            alert("非法日期")
            document.getElementById('datetimepicker2').value="";
        }
    }
</script>
</body>
</html>