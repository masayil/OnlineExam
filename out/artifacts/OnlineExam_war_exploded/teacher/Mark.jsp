<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <title>浏览试卷</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="${pageContext.request.contextPath}/css/paper/main.css" rel="stylesheet" type="text/css" />

    <link href="${pageContext.request.contextPath}/css/paper/test.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <style>
        .hasBeenAnswer {
            background: #5d9cec;
            color: #fff;
        }

        .reading h2 {
            width: 100%;
            margin: 20px 0 70px;
            text-align: center;
            line-height: 2;
            font-size: 20px;
            color: #59595b;
        }

        .reading h2 a {
            text-decoration: none;
            color: #59595b;
            font-size: 20px;
        }

        .reading h2 a:hover {
            color: #2183f1;
        }
    </style>
</head>

<body>
<div style="padding: 25px;position:fixed;float: right;">
    <button type="button" class="btn btn-default" style="background: cornflowerblue;color: white;" onclick="window.history.go(-1)">返回</button>
</div>
<div class="main" style="margin-left: 60px;">
    <!--nr start-->
    <div class="test_main">
        <div class="nr_left">
            <div class="test">

                <div class="test_content">
                    <div style="text-align: center;">
                        <h1>${examname}</h1><h3>答卷人:${studentname}</h3>
                    </div>
                <!--/*分割线*/-->
                <div class="test_content">
                    <div class="test_content_title">
                        <h2>简答题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${papers.size()}</i><span>题</span>
                        </p>
                    </div>
                </div>
                    <form action="UpdateGrade" method="Post">
                        <input type="hidden" name="studentid" value=${studentid}>
                        <input type="hidden" name="examname" value=${examname}>
                        <input type="hidden" name="examuuid" value=${examuuid}>
                <div class="test_content_nr">
                    <c:forEach var="paper4" items="${requestScope.papers}" varStatus="status4">
                        <ul>
                            <br>
                            <div class="test_content_nr_tt">
                                <font><span style="color: darkgoldenrod;">第${status4.count}题</span></font> &emsp;(<span style="color: crimson;">${paper4.score}分</span>)&emsp;<font><font>${paper4.paper_title}</font>
                                <br>
                                <c:if test="${(paper4.paper_titleimage ne '') and (not empty paper4.paper_titleimage)}">
                                <a href="${paper4.paper_titleimage}" target="_blank"><img src="${paper4.paper_titleimage}" height="300" width="300"></a>
                                </c:if>
                            </div>

                            <div class="test_content_nr_main">
                                <ul>
                                    <li class="option">
                                        正确答案：
                                        <br>
                                        <p class="ue" style="display: inline;color: red;">${paper4.paper_answer}</p>
                                        <br>
                                    </li>
                                    <br>
                                    <li class="option">
                                        学生答案：
                                        <br>
                                        <c:choose>
                                        <c:when test="${paper4.youranswer ne ''}">
                                        <a href="${paper4.youranswer}" target="_blank"><img src="${paper4.youranswer}" height="300" width="300"></a>
                                        </c:when>
                                            <c:otherwise>
                                                <p class="ue" style="display: inline;color: red;">未作答！</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <br>
                                    </li>
                                    <li class="option">
                                        得分：
                                        <br>
                                        <input type="number" id="myinput_${status4.count}" style="width: 80px;height: 40px;font-size: 24px;" name="myscore" onblur="checkborder(${paper4.score},${status4.count})" required>
                                        <input type="hidden" name="order" value=${paper4.paper_serialNumber}>
                                        <br>
                                    </li>
                                </ul>
                            </div>
                        </ul>
                    </c:forEach>
                    <div style="height: 30px;"></div>
                    <div style="padding-right: 40px;">
                    <input type="submit" class="btn btn-danger btn-lg" id="tijiao" style="float: right;width: 120px;" value="提交">
                    </div>
                    </form>
                    <div style="height: 100px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script>
    $(function () {
    });
    function checkborder(score,count) {
        if(parseFloat(document.getElementById("myinput_"+count).value)>score||parseFloat(document.getElementById("myinput_"+count).value)<0){
            alert("得分不正确");
            document.getElementById("myinput_"+count).focus();
            document.getElementById("tijiao").disabled=true;
        }else {
            document.getElementById("tijiao").disabled=false;
        }
    }
</script>
</body>
</html>