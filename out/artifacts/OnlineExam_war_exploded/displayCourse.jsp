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
    <link rel="stylesheet" type="text/css" href="statics/css/style.css" />
    <link rel="stylesheet" type="text/css" href="statics/css/fonts.css" />
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/syalert/syalert.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css" />
</head>

<body>
<jsp:useBean id="student" type="com.bean.entity.Student" scope="session"></jsp:useBean>
<div class="meal-box">
    <div class="meal-title">
        <h1>我的课程：</h1><button type="button" class="btn btn-primary" style="float: right;margin-right: 30px;" onclick="syalert.syopen('alertAdd')">加入新课程</button>
    </div>
    <div class="sy-alert sy-alert-model animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="confirm" sy-mask="true" id="alertAdd">
        <div class="sy-title">加入课程</div>
        <div class="sy-content">
            <div class="form">
                <p class="input-item"><input type="text" placeholder="请输入课程号" id="lessonuuid"/></p>
            </div>
        </div>
        <div class="sy-btn">
            <button onClick="syalert.syhide('alertAdd')">取消</button>
            <button type="button" id="addlesson">确定</button>
        </div>
    </div>
    <div style="margin-left: 30px;"><h3>班级：</h3></div>
    <div class="meal-cut">
        <ul>
            <c:forEach var="Newlessonlist_class" items="${requestScope.Newlessonlist_class}">
            <li>
                    <span>
                        <img src="img/course_logo.png">
                        <h3>课程名：${Newlessonlist_class.newlesson_name}</h3>
                        <c:forEach var="teacherlist" items="${requestScope.Teacherlist}">
                            <c:if test="${Newlessonlist_class.newlesson_creatorID eq teacherlist.t_id}">
                                <p>授课教师：${teacherlist.t_name}</p>
                            </c:if>
                        </c:forEach>
                        <b></b>
                        <font>
                            <a href="javascript:void(0)" onclick="window.location.href='StuExamServlet?method=special&lessonuuid=${Newlessonlist_class.newlesson_uuid}'" style="text-decoration:none;margin-left: 80px;">参加考试</a>
                        </font>
                    </span>
            </li>
            </c:forEach>
        </ul>

    </div>
    <hr style="height:1px;border:none;border-top:1px dashed #0066CC;">
    <div style="margin-left: 30px;"><h3>其他：</h3></div>
    <div class="meal-cut">
        <ul>
            <c:forEach var="Newlessonlist_other" items="${requestScope.Newlessonlist_other}">
            <li>
                    <span>
                        <img src="img/course_logo.png">
                        <h3>课程名：${Newlessonlist_other.newlesson_name}</h3>
                                                <c:forEach var="teacherlist" items="${requestScope.Teacherlist}">
                                                    <c:if test="${Newlessonlist_other.newlesson_creatorID eq teacherlist.t_id}">
                                                        <p>授课教师：${teacherlist.t_name}</p>
                                                    </c:if>
                                                </c:forEach>
                        <b></b>
                        <font>
                            <a href="javascript:void(0)"  style="text-decoration:none"  onclick="window.location.href='ExitlessonServlet?lessonuuid=${Newlessonlist_other.newlesson_uuid}&studentid=${student.s_id}'">退出课程</a>
                            <a href="javascript:void(0)" onclick="window.location.href='StuExamServlet?method=special&lessonuuid=${Newlessonlist_other.newlesson_uuid}'"  style="text-decoration:none">参加考试</a>
                        </font>
                    </span>
            </li>
            </c:forEach>
        </ul>

    </div>
    <!--<ul class="pager">
        <li><a href="#">上一页</a></li>
        <li>&emsp;&emsp;</li>
        <li>第X页</li>
        <li>&emsp;&emsp;</li>
        <li>共X页</li>
        <li>&emsp;&emsp;</li>
        <li><a href="#">下一页</a></li>
    </ul>-->
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#addlesson").click(function () {
            var b=$("#lessonuuid").val();
            var lessonuuid=$.trim(b);
            if(null!=lessonuuid&&""!==lessonuuid){
                $.ajax({
                    url:"AddRetakeServlet",
                    type:"post",
                    dataType:"text",
                    data:{
                        lessonuuid:lessonuuid,
                        id:${student.s_id},
                    },
                    success:function (flag) {
                        if(flag==="yes"){
                            alert("成功");
                            location.href="FirstLoad?status=student";
                        }else if(flag==="no"){
                            alert("课程码错误！");
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