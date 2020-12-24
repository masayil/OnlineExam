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
        <h1>已发布的考试</h1>
    </div>
    <div style="margin-left: 30px;"><h3>尚未开始：</h3></div>
    <div class="meal-cut">
        <ul>
            <c:forEach var="ExamAssignlist_before" items="${requestScope.ExamAssignlist_before}">
                <li>
                    <span>
                        <img src="${pageContext.request.contextPath}/img/examlogo.png">
                        <h3>考试：${ExamAssignlist_before.examAssign_name}</h3>
                        <p>科目：
                        <c:forEach var="newlessons_list1" items="${requestScope.newlessons_list}">
                            <c:if test="${ExamAssignlist_before.lessonuuid eq newlessons_list1.newlesson_uuid}">
                                ${newlessons_list1.newlesson_name}
                            </c:if>
                        </c:forEach>
                        </p>
                                   <p>班级：
                               <c:forEach var="newlessons_list2" items="${requestScope.newlessons_list}">
                                   <c:if test="${ExamAssignlist_before.lessonuuid eq newlessons_list2.newlesson_uuid}">
                                       ${newlessons_list2.newlesson_class}
                                   </c:if>
                               </c:forEach>
                        </p>
                                <p>开始时间：${ExamAssignlist_before.startTime}</p>
                        <p>结束时间：${ExamAssignlist_before.endTime}</p>
                        <p>总分：${ExamAssignlist_before.totalscore}</p>
                        <b></b>
                        <font>
                            <a href="javascript:void(0)" onclick="deleteexam('${ExamAssignlist_before.examAssign_uuid}')" style="text-decoration:none;margin-left: 80px;">撤销考试</a>

                        </font>
                    </span>
                </li>
            </c:forEach>
        </ul>

    </div>
    <hr style="height:1px;border:none;border-top:1px dashed #0066CC;">
    <div style="margin-left: 30px;"><h3>进行中的：</h3></div>
    <div class="meal-cut">
        <ul>
            <c:forEach var="ExamAssignlist_ing" items="${requestScope.ExamAssignlist_ing}">
                <li>
                    <span>
                        <img src="${pageContext.request.contextPath}/img/examlogo.png">
                        <h3>考试：${ExamAssignlist_ing.examAssign_name}</h3>
                                        <p>科目：
                        <c:forEach var="newlessons_list3" items="${requestScope.newlessons_list}">
                            <c:if test="${ExamAssignlist_ing.lessonuuid eq newlessons_list3.newlesson_uuid}">
                                ${newlessons_list3.newlesson_name}
                            </c:if>
                        </c:forEach>
                        </p>
                                   <p>班级：
                               <c:forEach var="newlessons_list4" items="${requestScope.newlessons_list}">
                                   <c:if test="${ExamAssignlist_ing.lessonuuid eq newlessons_list4.newlesson_uuid}">
                                       ${newlessons_list4.newlesson_class}
                                   </c:if>
                               </c:forEach>
                        </p>
                                <p>开始时间：${ExamAssignlist_ing.startTime}</p>
                        <p>结束时间：${ExamAssignlist_ing.endTime}</p>
                        <p>总分：${ExamAssignlist_ing.totalscore}</p>
                        <b></b>
                    </span>
                </li>
            </c:forEach>
        </ul>

    </div>

</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script>
    function deleteexam(examuuid) {
        if(confirm("是否确认撤销考试？")){
            $.ajax({
                url:"DeleteExamA",
                type:"post",
                dataType:"text",
                data:{
                    examuuid:examuuid
                },
                success:function (flag) {
                    if(flag==="yes"){
                        alert("考试成功撤销");
                        location.reload();
                    }else if(flag==="no"){
                        alert("考试撤销失败，请再试一次");
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
</script>
</body>
</html>