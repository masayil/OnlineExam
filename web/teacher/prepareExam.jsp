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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/selectday/bootstrap-datetimepicker.min.css"/>
    <style>
        input{
            margin-top: 15px;
            border-radius: 3px;
            width: 250px;
            height: 30px;
            line-height: 30px;
        }
        select{
            margin-top: 15px;
            width: 250px;
            height: 25px;
        }
        .father {
            text-align: center;
        }
    </style>
</head>

<body>
<div class="father">
<form action="CreateExamServlet" method="post">
    <input type="hidden" name="lessonuuid" value=${lessonuuid}>
    考试名称：<input type="text" name="examname" autocomplete="off"><br>
    班&emsp;&emsp;级：<input type="text" name="thisclass" readonly style="border: none;outline: none;" value=${thisclass}><br>
    科&emsp;&emsp;目：<input type="text" name="thiscourse" style="border: none;outline: none;" readonly value=${course}><br>
    试&emsp;&emsp;卷：<select name="paperuuid" id="paperuuid">
    <c:forEach items="${requestScope.paperBaseArrayList}" var="paperBaseArrayList">
        <option value="" selected>---</option>
        <option value=${paperBaseArrayList.paperbase_uuid}>${paperBaseArrayList.paperbase_name}</option>
    </c:forEach>
</select><br>
    总&emsp;&emsp;分：<input type="text" value="100" readonly style="border:none; outline: none;"><br>
    开始时间：<input type="text"  id="datetimepicker1" name="datetimepicker1" readonly ><br>
    结束时间：<input type="text"  id="datetimepicker2" name="datetimepicker2" readonly  onchange="check()"><br>
    考试时间：<input type="number" name="lasttime" id="lasttime" placeholder="以分钟为单位"><br>
    <input type="submit" value="创建" class="btn-info" style="height: 35px;" onclick=" return emptyCheck()"><br>
</form>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/selectday/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/selectday/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker1').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:00',
            autoclose: 1,
            startDate: new Date()
        });
        $('#datetimepicker2').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:00',
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
    function emptyCheck() {
        if($("#examname").val()===""||$("#paperuuid").val()===""||$("#datetimepicker1").val()===""||$("#datetimepicker2").val()===""||$("#lasttime").val()===""){
            alert("信息不可为空");
            return false;
        }
        if($("#lasttime").val()==="0"){
            alert("考试时间不可为0");
            return false;
        }
    }
</script>
</body>
</html>