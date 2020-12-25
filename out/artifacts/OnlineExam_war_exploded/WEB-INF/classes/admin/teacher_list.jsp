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
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/syalert/syalert.min.css" rel="stylesheet" type="text/css">
</head>

<body>
<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<div style="padding-left: 150px;margin-top: 100px;">
<table class="table table-hover" style="word-break: break-all;width: 1230px;text-align: center;">
    <caption style="text-align: center;"><h3>${depart}-教师列表</h3></caption>
    <thead>
    <tr>
        <th style="text-align: center;width: 50px;">序号</th>
        <th style="text-align: center;width: 100px;">职工号</th>
        <th style="text-align: center;width: 150px;">姓名</th>
        <th style="text-align: center;width: 50px;">性别</th>
        <th style="text-align: center;width: 200px;">学院</th>
        <th style="text-align: center;width: 150px;">密码</th>
        <th style="text-align: center;width: 180px;">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.teacher_list_manage}" var="teacher_list_manage" varStatus="status">
    <tr class="warning">
        <td style="height: 40px;line-height: 40px;width: 50px;">${status.count}</td>
        <td style="height: 40px;line-height: 40px;width: 100px;">${teacher_list_manage.t_id}</td>
        <td style="height: 40px;line-height: 40px;width: 150px;">${teacher_list_manage.t_name}</td>
        <td style="height: 40px;line-height: 40px;width: 50px;">${teacher_list_manage.t_sex}</td>
        <td style="height: 40px;line-height: 40px;width: 200px;">${teacher_list_manage.t_department}</td>
        <td style="height: 40px;line-height: 40px;width: 150px;"><button type="button" class="btn btn-info" style="width: 90px;" onclick="resetPwd('${teacher_list_manage.t_id}')">重置密码</button></td>
        <td style="height: 40px;line-height: 40px;width: 180px;"><button type="button" class="btn btn-danger" style="width: 90px;" onclick="modify('${teacher_list_manage.t_id}','${depart}','${type}')">修改资料</button>
            <button type="button" class="btn btn-danger" style="width: 90px;" onclick="deleteTea('${teacher_list_manage.t_id}','alert4')">删除</button></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<input type="hidden" value="" id="deleteFlag">
<div class="sy-alert sy-alert-model animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="confirm" sy-mask="true" id="alert4">
    <div class="sy-title">身份确认</div>
    <div class="sy-content">
        <div class="form">
            <p class="input-item"><input type="password" placeholder="请输入管理员密码" id="checkstatus"/></p>
        </div>
    </div>
    <div class="sy-btn">
        <button onClick="syalert.syhide('alert4')">取消</button>
        <button onClick="ok('alert4','${admin.a_password}')">确定</button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script>
    function modify(teacherid,depart,type) {
        window.location.href="TeacherModify?teacherid="+teacherid+"&depart="+depart+"&type="+type;
    }
    function resetPwd(teacherid) {
        if(confirm("是否重置其密码？")){
            $.ajax({
                url:"PwdChange",
                type:"post",
                dataType:"text",
                data:{
                    teacherid:teacherid
                },
                success:function (flag) {
                    if(flag==="yes"){
                        alert("密码重置成功");
                    }else if(flag==="no"){
                        alert("请再试一次");
                    }else if(flag==="busy"){
                        alert("服务器繁忙请稍后再试");
                    }
                },
                error:function () {
                    alert("发送数据失败，请稍后再试！");
                    location.reload();
                }
            });
        }
    }
    function deleteTea(teacherid,which) {
        document.getElementById("deleteFlag").value=teacherid;
        syalert.syopen(which);
    }
    function ok(which,adminpwd) {
        if(adminpwd===document.getElementById("checkstatus").value){
            $.ajax({
                url:"DeleteTea_admin",
                type:"post",
                dataType:"text",
                data:{
                    teacherid:document.getElementById("deleteFlag").value
                },
                success:function (flag) {
                    if(flag==="yes"){
                        alert("教师删除成功");
                        location.reload();
                    }else if(flag==="no"){
                        alert("请再试一次");
                    }else if(flag==="busy"){
                        alert("服务器繁忙请稍后再试");
                    }
                },
                error:function () {
                    alert("发送数据失败，请稍后再试！");
                    location.reload();
                }
            });
        }else {
            alert("管理员密码错误");
        }
    }
</script>
</body>
</html>