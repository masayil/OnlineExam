<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html class="x-admin-sm">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>在线考试</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./fontAlibaba/iconfont.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>

        var is_remember = false;
    </script>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="javascript:void(0); "><img src="./img/logo.png" alt="logo"></a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <div class="lafitewu_circle">
                    <i class="iconfont">&#xe6ff;</i>
                </div>
                <jsp:useBean id="student" type="com.bean.entity.Student" scope="session"></jsp:useBean>
                欢迎您， ${student.s_name} 学生
            </a>
        </li>
        <li class="layui-nav-item to-index">
            <a>欢迎登陆</a></li>
        <li class="layui-nav-item lafitewu_exit to-index">
            <a href="LogoutServlet"><i class="iconfont">&#xe704;</i>退出</a>
        </li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:void(0)" onclick="window.maincontent.location.href='FirstLoad?status=student'">
                    <i class="iconfont left-nav-li" lay-tips="我的课程">&#xe708;</i>
                    <cite>我的课程</cite>
                </a>
            </li>
            <li>
                <a href="javascript:void(0)">
                    <i class="iconfont left-nav-li">&#xe708;</i>
                    <cite>我的考试</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:void(0)" onclick="window.maincontent.location.href='StuExamServlet?method=ing'">
                            <cite>进行中的考试</cite></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="window.maincontent.location.href='StuExamServlet?method=done'">
                            <cite>已完成的考试</cite></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:void(0)" onclick="window.maincontent.location.href='./profile/stuPro.jsp'">
                    <i class="iconfont left-nav-li">&#xe708;</i>
                    <cite>个人信息</cite>
                </a>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">


        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd></dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <!--  -->
                <iframe src="welcome.jsp" frameborder="0" scrolling="yes" class="x-iframe" name="maincontent"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>

</div>
<div class="page-content-bg"></div>
<!--
<div class="lafitewu_footer">
    <div class="lafitewu_footer_con">
        <div class="lafite_f_con_left">
            <div class="lafite_font1">1720267-黄凌皓</div>

        </div>


    </div>
</div>-->
<style id="theme_style"></style>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
</body>
</html>
<script>layui.use(['laydate', 'form', 'upload'],
    function() {
        var $ = layui.jquery

        $(".layui-tab-title li").click(function() {
            console.log($(this));
        });
    });

</script>