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
<h2 style="text-align: center;color: darkgrey;">导入教师</h2>
<form class="form-inline" role="form" style="text-align: center;" action="SingleImportTea" method="post">
    <div class="form-group">
        <label class="sr-only" for="teacherid">职工号</label>
        职工号：<input type="text" class="form-control" id="teacherid" name="teacherid"
               placeholder="请输入职工号" required autocomplete="off"><br>
        <span id="tips" style="color: red; display: none;">职工号已存在</span>
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="teachername">姓名</label>
        姓&emsp;名：<input type="text" class="form-control" id="teachername" name="teachername"
               placeholder="请输入姓名" required autocomplete="off">
    </div>
    <br>
    <div class="form-group">
        <label class="sr-only" for="teacherpwd">密码</label>
        密&emsp;码：<input type="password" class="form-control" id="teacherpwd" name="teacherpwd"
               value="t040506" required>
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
        院&emsp;系：<select id="depart" name="depart" style="width: 200px;height: 30px;">
        <option value="学院1" selected>学院1</option>
        <option value="学院2">学院2</option>
        <option value="学院3">学院3</option>
        <option value="学院4">学院4</option>
    </select>
    </div>
    <br>
    <button type="submit" class="btn btn-warning" style="margin-top: 30px;width: 100px;" id="importbtn">导入</button>
</form>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    $(function () {
        $("#teacherid").blur(function () {
            $.ajax({
                url:"HasThisTeacher",
                type:"post",
                dataType:"text",
                data:{
                    teacherid:document.getElementById("teacherid").value
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
    });
</script>
</body>
</html>