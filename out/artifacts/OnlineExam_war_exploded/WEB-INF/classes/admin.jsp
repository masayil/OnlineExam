<%--
  Created by IntelliJ IDEA.
  User: 85745
  Date: 2020/11/12
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>管理员登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" href="css/admin/style.css">
    <link rel="stylesheet" href="css/animate.min.css" />
    <link rel="stylesheet" href="syalert/syalert.min.css" />

    <style>
        #load{
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.8);
        }
    </style>
</head>
<body onload="message()">

<div class="container">
    <div class="loginBox">
        <div class="userImage">
            <img src="img/admin.jpg">
        </div>
        <div align="center">
            <span style="font-size:20px;">管理员登录</span></div>
        <form id="loginForm" method="post" action="LoginServlet?method=admin" name="loginForm">
            <div class="input-wrapper">
                <label>账号:</label>
                <input type="text" name="username"id="username" placeholder="请输入账号" autocomplete="off">
                <div class="error-email"><div class="email-msg"></div><div class="triangle"></div></div>
            </div>
            <div class="input-wrapper">
                <label>密码:</label>
                <input type="password" name="password" id="password" placeholder="密码" autocomplete="off">
                <div class="error-pass"><div class="pass-msg"></div><div class="triangle"></div></div>
            </div>
            <input type="hidden" id="msg" name="msg" value=${msg}>
            <input type="submit" id="login_btn" style="background-color:#02C874;"value="登录" onclick="validateForm()" >
        </form>
    </div>
</div>
<!--  <div id="load" align="center"><img src="img/loading.gif" style="width:700px; height:700px;" align="absmiddle"/></div>-->
<div class="sy-alert sy-alert-alert animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="alert" sy-mask="true" id="alert2">
    <div class="sy-title">温馨提示</div>
    <div class="sy-content">密码或账号错误</div>
    <div class="sy-btn">
        <button onClick="ok('alert2')">确定</button>
    </div>
</div>
<script src='js/jquery.min.js'></script>
<script src="syalert/syalert.min.js"></script>
<script type="text/javascript">
    function ok(id){
        syalert.syhide(id);
    }
    function message(){
        var msg=$("#msg").val();
        if(msg=="管理员不存在"){
            syalert.syopen("alert2");
        }}
</script>
<script>
    $(function(){
        $(".error-pass, .error-email").hide();
        $("#login_btn").click(function(){
            if($("#username").val()== "") {
                $(".error-email").fadeIn();
                $(".email-msg").html("账号不可为空");
                setTimeout(function showErrorMsg() {
                    $(".error-email").fadeOut();
                }, 2000)
                //$("#username").addClass("warning");
                return false;
            }

            if($("#password").val() == "") {
                $(".error-pass").fadeIn();
                $(".pass-msg").html("密码不可为空");
                setTimeout(function showErrorMsg() {
                    $(".error-pass").fadeOut();
                }, 2000)
                //$("#password").addClass("warning");
                return false;
            }

        })
    })

</script>
</body>
</html>

