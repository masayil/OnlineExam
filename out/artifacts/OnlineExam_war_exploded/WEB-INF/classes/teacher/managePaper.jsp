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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/syalert/syalert.min.css" />
    <style>
        table {

            border-collapse: collapse;

            font-family: Futura, Arial, sans-serif;

        }

        caption {

            font-size: larger;

            margin: 1em auto;

        }

        th,td {

            padding: .65em;

        }

        th {

            background: #555  ;

            border: 1px solid #777;

            color: #fff;
            text-align: center;

        }

        td {

            border: 1px solid#777;

        }
        tbody tr:nth-child(odd) {

            background: #ccc;
        }
    </style>
</head>

<body>
<jsp:useBean id="teacher" type="com.bean.entity.Teacher" scope="session"></jsp:useBean>
<div style="text-align: center;padding-top: 30px;">
    <h1>试卷管理</h1><button type="button" class="btn btn-primary" style="float: right;margin-right: 70px; width: 120px;height: 60px;" onclick="syalert.syopen('newpaper')">创建试卷</button>
</div>
<div class="sy-alert sy-alert-model animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="confirm" sy-mask="true" id="newpaper">
    <div class="sy-title">新试卷</div>
    <div class="sy-content">
        <div class="form">
            <p class="input-item">科&emsp;&emsp;目：<select id="lesson_course" style="width: 200px;">
                <option value="none">---</option>
                <c:forEach items="${requestScope.choosecourse}" var="choosecourse">
                    <option value=${choosecourse.course_name}>${choosecourse.course_name}</option>
                </c:forEach>
            </select></p>
        </div>
    </div>
    <div class="sy-btn">
        <button onClick="syalert.syhide('newpaper')">取消</button>
        <button type="button" id="createOnepaper" onclick="createpaper()">下一步</button>
    </div>
</div>
<div style="padding-left: 200px;padding-top: 50px;">
<table style="width: 900px;text-align: center;">
    <caption style="text-align: center;">我的试卷</caption>
    <thead>
    <tr>
        <th>试卷号</th>
        <th>名字</th>
        <th>科目</th>
        <th>创建时间</th>
        <th>操作1</th>
        <th>操作2</th>
    </thead>
    <tbody>
    <c:forEach var="paperlist" items="${requestScope.part_paperbaseArrayList}" varStatus="status">
    <tr>
        <td>${paperlist.paperbase_uuid}</td>
        <td>${paperlist.paperbase_name}</td>
        <td>${paperlist.course}</td>
        <td>${paperlist.paperbase_createDate}</td>
        <td><a href="ManagePaperServlet?paperuuid=${paperlist.paperbase_uuid}&method=read&course=${paperlist.course}" target="_blank">查看试卷</a></td>
        <td><a href="javascript:void(0);" onclick="removepaperbase(this,'${paperlist.paperbase_uuid}')">删除试卷</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script>
    $(function () {
    });
    function removepaperbase(thisone,paperuuid) {
        if(confirm("确认删除？")) {
            $.ajax({
                url: "RemovePaperBaseServlet",
                type: "post",
                dataType: "text",
                data: {
                    paperuuid: paperuuid,
                },
                success: function (flag) {
                    if (flag === "yes") {
                        alert("成功");
                        $(thisone).parent().parent().remove();
                    } else if (flag === "no") {
                        alert("课程码错误！");
                    } else if (flag === "busy") {
                        alert("系统繁忙稍后再试");
                    }
                },
                error: function () {
                    alert("发送数据失败，请稍后再试！");
                    location.reload();
                }
            });
        }
    }
    function createpaper() {
        var course=document.getElementById("lesson_course").value;
        if(course==="none"){
            alert("请选择科目");
        }else{
            window.location.href="CreatePaperServlet?course="+course;
        }
    }
</script>
</body>
</html>