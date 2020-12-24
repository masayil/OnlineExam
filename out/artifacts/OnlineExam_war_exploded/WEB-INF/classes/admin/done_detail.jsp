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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}statics/css/fonts.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/syalert/syalert.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css" />
</head>

<body>
<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<div class="meal-box">
    <h3 style="text-align: center;color: dimgray;">职工号：${thisteacher.t_id}&emsp;&emsp;姓名：${thisteacher.t_name}</h3>
    <div class="meal-title">
        <h1>已完成的考试</h1>
    </div>
    <div class="meal-cut">
        <ul>
            <c:forEach var="ExamAssignlist_done" items="${requestScope.ExamAssignlist_done}">
                <li>
                    <span>
                        <img src="${pageContext.request.contextPath}/img/examlogo.png">
                        <h3>考试：${ExamAssignlist_done.examAssign_name}</h3>
                        <p>科目：
                        <c:forEach var="newlessons_list" items="${requestScope.newlessons_list}">
                            <c:if test="${ExamAssignlist_done.lessonuuid eq newlessons_list.newlesson_uuid}">
                                ${newlessons_list.newlesson_name}
                            </c:if>
                        </c:forEach>
                        </p>
                        <p>班级：
                               <c:forEach var="newlessons_list" items="${requestScope.newlessons_list}">
                                   <c:if test="${ExamAssignlist_done.lessonuuid eq newlessons_list.newlesson_uuid}">
                                       ${newlessons_list.newlesson_class}
                                   </c:if>
                               </c:forEach>
                        </p>
                                <p>开始时间：${ExamAssignlist_done.startTime}</p>
                        <p>结束时间：${ExamAssignlist_done.endTime}</p>
                        <p>总分：${ExamAssignlist_done.totalscore}</p>
                        <b></b>
                        <font>
                            <a href="javascript:void(0)" onclick="Lookdetails('${ExamAssignlist_done.examAssign_uuid}','${ExamAssignlist_done.examAssign_name}','${ExamAssignlist_done.lessonuuid}')" style="text-decoration:none;margin-left: 80px;">考试详情</a>
                        </font>
                    </span>
                </li>
            </c:forEach>
        </ul>

    </div>

</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script>
    function Lookdetails(examuuid,examname,lessonuuid) {
        window.open('ExamSituation?examuuid='+examuuid+'&examname='+examname+'&lessonuuid='+lessonuuid, '_blank');
    }
</script>
</body>
</html>