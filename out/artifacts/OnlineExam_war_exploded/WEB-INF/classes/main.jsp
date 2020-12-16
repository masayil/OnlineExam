<%--
  Created by IntelliJ IDEA.
  User: 85745
  Date: 2020/11/12
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>用户登录</title>

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
    <!-- 引入bootstrap样式 -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <!--图标库-->
    <link href="css/materialdesignicons.min.css" rel="stylesheet" type="text/css">
    <!--自定义样式-->
    <link href="css/style.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/animate.min.css" />


    <link rel="stylesheet" href="syalert/syalert.min.css" />
    <style>
        .lear-wrapper {
            position: relative;
        }
        .lear-login {
            display: flex !important;
            min-height: 100vh;
            align-items: center !important;
            justify-content: center !important;
        }
        .login-center1 {
            background: #fff;
            min-width: 38.25rem;
            padding: 2.14286em 3.57143em;
            border-radius: 5px;
            margin: 2.85714em 0;
        }
        .login-header {
            margin-bottom: 1.5rem !important;
        }
        .login-center .has-feedback.feedback-left .form-control {
            padding-left: 38px;
            padding-right: 12px;
        }
        .login-center .has-feedback.feedback-left .form-control-feedback {
            left: 0;
            right: auto;
            width: 38px;
            height: 38px;
            line-height: 38px;
            z-index: 4;
            color: #dcdcdc;
        }
        .login-center .has-feedback.feedback-left.row .form-control-feedback {
            left: 15px;
        }
    </style>
</head>
<body size-sensor-id="1" style="position: relative;" onload="message()">
<div class="row lear-wrapper">
    <div class="lear-login">
        <div class="login-center1">
            <div class="login-header text-center">
                <h1>在线考试系统</h1>
            </div>
            <form name="login" action="LoginServlet?method=main" method="post" id="mainLogin">
                <div class="form-group has-feedback feedback-left">
                    <input type="text" placeholder="请输入您的用户名" class="form-control" name="username" id="username"autocomplete="off"><span class="help-block"></span>
                    <span class="mdi mdi-account form-control-feedback" aria-hidden="true"></span>
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="password" placeholder="请输入密码" class="form-control" id="password" name="password"autocomplete="off"><span class="help-block"></span>
                    <span class="mdi mdi-lock form-control-feedback" aria-hidden="true"></span>
                </div>
                <div class="form-group has-feedback feedback-left" style="postiton:relative; left:120px;">
                    <input type="radio"  class="status" id="student" name="status" value="student"style="width:18px; height:18px;" checked><label for="student">学生</label>
                    <input type="radio"  class="status" id="teacher" name="status" value="teacher"style="width:18px; height:18px;"> <label for="teacher">教师</label>
                </div>
                <div id="captcha_note_parent" class="form-group has-feedback feedback-left row">
                    <div class="col-xs-7">
                        <input type="text" class="form-control" id="captcha" name="captcha"  placeholder="请输入验证码" autocomplete="off">
                    </div>
                    <div class="col-xs-5">
                        <canvas id="canvas" width="120" height="45"></canvas>
                    </div>
                    <span id="captcha_note_child" class="help-block" style="margin-left: 15px"></span>
                </div>

                <div class="form-group">
                    <input type="hidden" id="msg1" name="msg1" value=${msg}>
                    <button id="login_btn" class="btn btn-block btn-primary" type="submit">立即登录
                    </button>
                </div>
            </form>

        </div>
    </div>

</div>
<div class="sy-alert sy-alert-alert animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="alert" sy-mask="true" id="alert2">
    <div class="sy-title">温馨提示</div>
    <div class="sy-content">密码或账号错误</div>
    <div class="sy-btn">
        <button onClick="ok('alert2')">确定</button>
    </div>
</div>
<!-- 引入jQuery和bootstrap的js -->

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<!-- 点线粒子插件 -->
<script src="syalert/syalert.min.js"></script>
<script type="text/javascript">
    function ok(id){
        syalert.syhide(id);
    }
    function message(){
        var msg1=$("#msg1").val();
        if(msg1=="账号密码错误"){
            syalert.syopen("alert2");
        }
    }
</script>
<script color="255,0,255" opacity="0.8" zindex="9999" count="99" src="js/canvas-nest.js"></script><canvas width="1280" height="641" style="display: block; position: absolute; top: 0px; left: 0px; height: 100%; width: 100%; overflow: hidden; pointer-events: none; z-index: 9999; opacity: 0.8;"></canvas>
<script>
    var show_num = [];
    $(function () {
        $("input[name='username']").keyup(verifyUsername);
        $("input[name='password']").keyup(removeLoginDisabled);
        //$("#container").hide();
        //生成图片验证码
        drawPic(show_num);
        $("#canvas").click(function (e) {
            //取消事件的默认动作。
            e.preventDefault();
            drawPic(show_num);
        });
        $("#captcha").keyup(verifyCaptcha);
        $("#login_btn").mousedown(verifyLogin);
        //回车登录
        $("body").keyup(function (event) {
            if (event.keyCode === 13) {//keyCode=13是回车键
                verifyLogin();
            }
        });

    });

    function verifyCaptcha() {
        var captcha_num = $("#captcha").val().toLowerCase();
        var real_num = show_num.join("");
        $("#captcha_note_parent").removeClass("has-success has-error");
        $("#captcha_note_child").text("");
        if (captcha_num == "") {
            $("#captcha_note_parent").addClass("has-error");
            $("#captcha_note_child").text("验证码为空");
            $("#login_btn").attr("disabled", "disabled");
            return false;
        } else if (captcha_num == real_num) {
            $("#captcha_note_parent").addClass("has-success");
            $("#captcha_note_child").text("验证码正确");
            $("#login_btn").removeAttr("disabled");
            return true;
        } else {
            $("#captcha_note_parent").addClass("has-error");
            $("#captcha_note_child").text("验证码错误");
            $("#login_btn").attr("disabled", "disabled");
            return false;
        }
    }

    function verifyUsername() {
        var username = document.getElementById("username").value;
        var lock = true;
        if (username === "") {
            show_validate_msg("#username", "error", "用户名为空");
            $("#login_btn").attr("disabled", "disabled");
            return false;
        } else{
            $("#login_btn").removeAttr("disabled");
            show_validate_msg("#username", "success", "");
        }


    }

    function removeLoginDisabled() {
        $("#login_btn").removeAttr("disabled");
        show_validate_msg("#password", "success", "");
    }

    function verifyLogin() {
        // $("input[name='username']").blur(verifyUsername);
        // $("#captcha").blur(verifyCaptcha);

        if ($("#username").val() == "") {
            show_validate_msg("#username", "error", "用户名为空");
            $("#login_btn").attr("disabled", "disabled");
            return false;
        }

        if ($("#password").val() == "") {
            show_validate_msg("#password", "error", "密码为空");
            $("#login_btn").attr("disabled", "disabled");
            return false;
        }

        if ($("#captcha").val() == "") {
            $("#captcha_note_parent").addClass("has-error");
            $("#captcha_note_child").text("验证码为空");
            $("#login_btn").attr("disabled", "disabled");
            return false;
        }
        // document.getElementById("login_btn").submit();


    }

    //显示校验结果的提示信息
    function show_validate_msg(ele, status, msg) {
        //清除当前元素的校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if ("success" === status) {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        } else if ("error" === status) {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }

    /**生成一个随机数**/
    function randomNum(min, max) {
        return Math.floor(Math.random() * (max - min) + min);
    }

    /**生成一个随机色**/
    function randomColor(min, max) {
        var r = randomNum(min, max);
        var g = randomNum(min, max);
        var b = randomNum(min, max);
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    /**绘制验证码图片**/
    function drawPic(show_num) {
        var canvas = document.getElementById("canvas");
        var width = canvas.width;
        var height = canvas.height;
        var ctx = canvas.getContext('2d');
        ctx.textBaseline = 'bottom';

        /**绘制背景色**/
        ctx.fillStyle = randomColor(280, 280); //颜色若太深可能导致看不清
        ctx.fillRect(0, 0, width, height);
        /**绘制文字**/
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        for (var i = 0; i < 4; i++) {
            var txt = aCode[randomNum(0, aCode.length)];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            ctx.fillStyle = randomColor(50, 160); //随机生成字体颜色
            ctx.font = randomNum(15, 40) + 'px SimHei'; //随机生成字体大小
            var x = 10 + i * 25;
            var y = randomNum(25, 45);
            var deg = randomNum(-45, 45);
            //修改坐标原点和旋转角度
            ctx.translate(x, y);
            ctx.rotate(deg * Math.PI / 180);
            ctx.fillText(txt, 0, 0);
            //恢复坐标原点和旋转角度
            ctx.rotate(-deg * Math.PI / 180);
            ctx.translate(-x, -y);
        }
    }
</script>


</body>
</html>

