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
<style>
    .test1 {
        display: none;
    }
    .test2{
        display: none;
    }
</style>
</head>

<body>
<input type="radio" name="n1" id="b1" value="学院1">学院1
<br>
<input type="radio" name="n1" id="b2" value="学院2">学院2
<br>
<select id="s1" style="width: 100px;">
    <option value="all">all</option>
    <option value="1" class="test2">1</option>
    <option value="2" class="test2">2</option>
    <option value="3" class="test2">3</option>
    <option value="4" class="test1">4</option>
    <option value="5" class="test1">5</option>
</select>
<button id="l">111111</button>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#b1").click(function(){
            $(".test2").css("display","block");
            $(".test1").css("display","none");
            $("#s1").val('all');
        });
        $("#b2").click(function(){
            $("#s1").val('');
            $(".test2").css("display","none");
            $(".test1").css("display","block");
            $("#s1").val('all');
        });
        $("#l").click(function () {
            alert($("#s1").val());
        })
    });
</script>
</body>
</html>