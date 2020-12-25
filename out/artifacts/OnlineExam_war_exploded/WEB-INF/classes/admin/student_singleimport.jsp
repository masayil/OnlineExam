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
            margin-top: 30px;
        }
    </style>
</head>

<body>
<%
String depart=request.getParameter("depart");
String major=request.getParameter("major");
String classselect=request.getParameter("classselect");
%>
<a href="javascript:void (0)" title="返回" onclick="window.location.href = document.referrer">
    <img src="${pageContext.request.contextPath}/img/back_logo.png" alt="未加载" width="64" height="64" style="position: relative;top:50px;left: 50px;"></a>
<h2 style="text-align: center;color: darkgrey;">单个导入学生</h2>
<form class="form-inline" role="form" style="text-align: center;">
    <div class="form-group">
        <label class="sr-only" for="studentid">学号</label>
        学&emsp;号：<input type="text" class="form-control" id="studentid" name="studentid"
                   placeholder="请输入学号" required autocomplete="off">
        <span id="tips" style="color: red; display: none;">学号已存在</span>
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="studentname">姓名</label>
        姓&emsp;名：<input type="text" class="form-control" id="studentname" name="studentname"
                        placeholder="请输入姓名" required autocomplete="off">
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="studentpwd">密码</label>
        密&emsp;码：<input type="password" class="form-control" id="studentpwd" name="studentpwd"
                        value="s040506" required>
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="sexm">性别</label>
        性&emsp;别：<input type="radio" class="form-control" id="sexm" name="sex"
                        value="男" checked>男
    </div>&emsp;&emsp;&emsp;
    <div class="form-group">
        <label class="sr-only" for="sexf">性别</label>
        <input type="radio" class="form-control" id="sexf" name="sex"
               value="女">女
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="college">学校</label>
        学&emsp;校：<input type="text" class="form-control" id="college" name="college"
                        value="xxx学校" style="outline: none;border:none;" readonly>
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="depart">院系</label>
        院&emsp;系：<input type="text" class="form-control" id="depart" name="depart"
                        value="<%=depart%>" style="outline: none;border:none;" readonly>
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="major">专业</label>
        专&emsp;业：<input type="text" class="form-control" id="major" name="major"
                        value="<%=major%>" style="outline: none;border:none;" readonly>
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="classselect">班级</label>
        班&emsp;级：<input type="text" class="form-control" id="classselect" name="classselect"
                        value="<%=classselect%>" style="outline: none;border:none;" readonly>
    </div>
    <br>
    <button type="button" class="btn btn-warning" style="margin-top: 30px;width: 100px;" id="importbtn">导入</button>
</form>
<div style="height: 35px;"></div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    $(function () {
        $("#studentid").blur(function () {
            $.ajax({
                url:"HasThisStudent",
                type:"post",
                dataType:"text",
                data:{
                    studentid:document.getElementById("studentid").value
                },
                success:function (flag) {
                    if(flag==="yes"){
                        document.getElementById("tips").style.display="none";
                        document.getElementById("importbtn").disabled=false;
                    }else if(flag==="no"){
                        document.getElementById("tips").style.display="block";
                        document.getElementById("importbtn").disabled=true;
                    }else if(flag==="busy"){
                        alert("服务器繁忙，稍后再试");
                    }
                },
                error:function () {
                    alert("发送数据失败，请稍后再试！");
                    location.reload();
                }
            });
        });
        $("#importbtn").click(function () {
            if($("#studentname").val()!==""&&$("#studentid").val()!==""&&$("#studentpwd").val()!==""){
                var temp=document.getElementsByName("sex");
                var sex;
                for(var i=0;i<temp.length;i++){
                    if(temp[i].checked){
                        sex=temp[i].value;
                    }
                }
                $.ajax({
                    url:"SingleImportStudent",
                    type:"post",
                    dataType:"text",
                    data:{
                        studentid:document.getElementById("studentid").value,
                        studentname:document.getElementById("studentname").value,
                        studentpwd:document.getElementById("studentpwd").value,
                        sex:sex,
                        college:document.getElementById("college").value,
                        depart:document.getElementById("depart").value,
                        major:document.getElementById("major").value,
                        classselect:document.getElementById("classselect").value
                    },
                    success:function (flag) {
                        if(flag==="yes"){
                            alert("添加成功");
                            window.location.href = document.referrer;
                        }else if(flag==="no"){
                            alert("添加失败");
                        }else if(flag==="busy"){
                            alert("服务器繁忙，稍后再试");
                        }
                    },
                    error:function () {
                        alert("发送数据失败，请稍后再试！");
                        location.reload();
                    }
                });
            }else {
                alert("请填写完整信息");
            }
        });
    });
</script>
</body>
</html>