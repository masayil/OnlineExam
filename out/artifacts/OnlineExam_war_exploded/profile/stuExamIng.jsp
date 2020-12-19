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
<jsp:useBean id="student" type="com.bean.entity.Student" scope="session"></jsp:useBean>
<div class="meal-box">
    <div class="meal-title">
        <h1>进行中的考试：</h1>
    </div>
    <div style="margin-left: 30px;"><h3>班级：</h3></div>
    <div class="meal-cut">
        <ul>
            <c:forEach var="ExamAssignlist_class" items="${requestScope.ExamAssignlist_class}">
                <li>
                    <span>
                        <img src="${pageContext.request.contextPath}/img/examlogo.png">
                        <h3>考试：${ExamAssignlist_class.examAssign_name}</h3>
                        <p>科目：
                        <c:forEach var="newlesson_class" items="${requestScope.Newlessonlist_class}">
                            <c:if test="${ExamAssignlist_class.lessonuuid eq newlesson_class.newlesson_uuid}">
                                ${newlesson_class.newlesson_name}
                            </c:if>
                        </c:forEach>
                        </p>
                                <p>开始时间：${ExamAssignlist_class.startTime}</p>
                        <p>结束时间：${ExamAssignlist_class.endTime}</p>
                        <p>总分：${ExamAssignlist_class.totalscore}</p>
                        <b></b>
                        <font>
                            <a href="javascript:void(0)" onclick="window.top.location.href='ComeToExamServlet?paperbaseuuid=${ExamAssignlist_class.paperbaseuuid}&start=${ExamAssignlist_class.startTime}&end=${ExamAssignlist_class.endTime}&examAssignuuid=${ExamAssignlist_class.examAssign_uuid}&lasttime=${ExamAssignlist_class.lasttime}&examname=${ExamAssignlist_class.examAssign_name}&totalscore=${ExamAssignlist_class.totalscore}'" style="text-decoration:none;margin-left: 80px;">开始考试</a>

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
            <c:forEach var="ExamAssignlist_other" items="${requestScope.ExamAssignlist_other}">
                <li>
                    <span>
                        <img src="${pageContext.request.contextPath}/img/examlogo.png">
                        <h3>考试：${ExamAssignlist_other.examAssign_name}</h3>
                                        <p>科目：
                        <c:forEach var="newlesson_other" items="${requestScope.Newlessonlist_other}">
                            <c:if test="${ExamAssignlist_other.lessonuuid eq newlesson_other.newlesson_uuid}">
                                ${newlesson_other.newlesson_name}
                            </c:if>
                        </c:forEach>
                        </p>
                                <p>开始时间：${ExamAssignlist_other.startTime}</p>
                        <p>结束时间：${ExamAssignlist_other.endTime}</p>
                        <p>总分：${ExamAssignlist_other.totalscore}</p>
                        <b></b>
                        <font>
                            <a href="javascript:void(0)" onclick="window.top.location.href='ComeToExamServlet?paperbaseuuid=${ExamAssignlist_other.paperbaseuuid}&start=${ExamAssignlist_other.startTime}&end=${ExamAssignlist_other.endTime}&examAssignuuid=${ExamAssignlist_other.examAssign_uuid}&lasttime=${ExamAssignlist_other.lasttime}&examname=${ExamAssignlist_other.examAssign_name}&totalscore=${ExamAssignlist_other.totalscore}'" style="text-decoration:none;margin-left: 80px;">开始考试</a>
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
</body>
</html>