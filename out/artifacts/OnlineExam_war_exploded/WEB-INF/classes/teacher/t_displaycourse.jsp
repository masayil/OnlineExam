<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <title>在线考试系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/fonts.css" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/syalert/syalert.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css" />
</head>

<body>
<jsp:useBean id="teacher" type="com.bean.entity.Teacher" scope="session"></jsp:useBean>
<div class="meal-box">
    <div class="meal-title">
        <h1>我管理的课程：</h1><button type="button" class="btn btn-primary" style="float: right;margin-right: 30px;" onclick="syalert.syopen('newlesson')">发布新课程</button>
    </div>
    <div class="sy-alert sy-alert-model animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="confirm" sy-mask="true" id="newlesson">
        <div class="sy-title">新课程</div>
        <div class="sy-content">
            <div class="form">
                <p class="input-item">科&emsp;&emsp;目：<select id="lesson_course" style="width: 200px;">
                    <option value="">---</option>
                    <c:forEach var="courselist" items="${requestScope.courselist}">
                        <option value=${courselist.course_name}>${courselist.course_name}</option>
                    </c:forEach>
                </select></p>
                <p class="input-item" >教授班级：<select id="lesson_class" style="width: 200px;">
                    <option value="">---</option>
                    <c:forEach var="classlist" items="${requestScope.classlist}">
                        <option value=${classlist.class_1}>${classlist.class_1}</option>
                    </c:forEach>
                </select></p>
            </div>
        </div>
        <div class="sy-btn">
            <button onClick="syalert.syhide('newlesson')">取消</button>
            <button type="button" id="addnewlesson">确定</button>
        </div>
    </div>
    <div class="meal-cut">
        <ul>
            <c:forEach var="teacherlesson" items="${requestScope.teacherlesson}">
                <li>
                    <span>
                        <img src="${pageContext.request.contextPath}/img/course_logo.png" alt="图片">
                        <h5 style="color: darkgoldenrod;">科目：${teacherlesson.newlesson_name}</h5>
                        <h5 style="color: cornflowerblue;">授课班级：${teacherlesson.newlesson_class}</h5>
                        <h5>开设时间：${teacherlesson.newlesson_createDate}</h5>
                        课程号：<input type="text"    value=${teacherlesson.newlesson_uuid} readonly style="border:none; width:250px;font-size: 8px;">
                        <b></b>
                        <font>
                            <a href="javascript:void(0)" onclick="window.location.href='readMemberServlet?lessonuuid=${teacherlesson.newlesson_uuid}&stuclass=${teacherlesson.newlesson_class}'"  style="text-decoration:none">成员详情</a>
                            <a href="javascript:void(0)" onclick="window.location.href='PrepareExamServlet?lessonuuid=${teacherlesson.newlesson_uuid}&course=${teacherlesson.newlesson_name}&thisclass=${teacherlesson.newlesson_class}'"  style="text-decoration:none">发布考试</a>
                        </font>
                        <font>
                            <a href="javascript:void(0)" onclick="window.location.href='DeletelessonServlet?lessonuuid=${teacherlesson.newlesson_uuid}'"  style="text-decoration:none;margin-left: 75px;">撤销课程</a>
                        </font>
                    </span>
                </li>
            </c:forEach>
        </ul>

    </div>

</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#addnewlesson").click(function () {
            var lesson_course=document.getElementById("lesson_course");
            var lesson_class=document.getElementById("lesson_class");
            if(lesson_course.value!==""&&lesson_class.value!==""){
                $.ajax({
                    url:"AddnewlessonServlet",
                    type:"post",
                    dataType:"text",
                    data:{
                        lesson_course:lesson_course.value,
                        lesson_class:lesson_class.value,
                        teacherid:${teacher.t_id},
                    },
                    success:function (flag) {
                        if(flag==="yes"){
                            alert("成功");
                            location.reload();
                        }else if(flag==="no"){
                            alert("此课程已经存在！");
                        }else if(flag==="busy"){
                            alert("系统繁忙稍后再试");
                        }
                    },
                    error:function () {
                        alert("发送数据失败，请稍后再试！");
                        location.reload();
                    }
                });
            }
        });
    });
</script>
</body>
</html>