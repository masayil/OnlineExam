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
<div class="main" style="margin-left: 60px;">
    <!--nr start-->
    <div class="test_main">
        <div class="nr_left">
            <div class="test">

                <div class="test_content">
                    <div style="text-align: center;">
                        <h1>${examname}</h1><h3>总分:${totalscore}</h3><h3 style="color: red;">科目:${course}</h3>
                    </div>
                    <div class="test_content_title">
                        <h2>单选题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${paperbase1.size()}</i><span>题</span>
                        </p>
                    </div>
                </div>
                <div class="test_content_nr">
                    <c:forEach var="paper1" items="${requestScope.paperbase1}" varStatus="status1">
                        <ul>

                            <div class="test_content_nr_tt">
                                <font><span style="color: darkgoldenrod;">第${status1.count}题</span></font>&emsp;(<span style="color: crimson;">${paper1.score}分</span>)&emsp;<font>${paper1.paperbase_title}</font>
                                <c:if test="${(paper1.paperbase_titleimage ne '') and (not empty paper1.paperbase_titleimage)}">
                                    <br>
                                    <a href="${paper1.paperbase_titleimage}" target="_blank"><img src="${paper1.paperbase_titleimage}" height="300" width="300"></a>
                                </c:if>
                            </div>

                            <div class="test_content_nr_main">
                                <ul>

                                    <li class="option">
                                        A.
                                        <p class="ue" style="display: inline;">${paper1.paperbase_option1}</p>
                                    </li>

                                    <li class="option">
                                        B.
                                        <p class="ue" style="display: inline;">${paper1.paperbase_option2}</p>
                                    </li>

                                    <li class="option">
                                        C.
                                        <p class="ue" style="display: inline;">${paper1.paperbase_option3}</p>
                                    </li>

                                    <li class="option">
                                        D.
                                        <p class="ue" style="display: inline;">${paper1.paperbase_option4}</p>
                                    </li>
                                    <li class="option">
                                        正确答案：
                                        <p class="ue" style="display: inline;color: red;">${paper1.paperbase_answer}</p>
                                    </li>

                                </ul>

                            </div>
                        </ul>
                    </c:forEach>
                </div>
                <!--/*分割线*/-->
                <div class="test_content">
                    <div class="test_content_title">
                        <h2>多选题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${paperbase2.size()}</i><span>题</span>
                        </p>
                    </div>
                </div>
                <div class="test_content_nr">
                    <c:forEach var="paper2" items="${requestScope.paperbase2}" varStatus="status2">
                        <ul>
                            <div class="test_content_nr_tt">
                                <font><span style="color: darkgoldenrod;">第${status2.count}题</span></font>&emsp;(<span style="color: crimson;">${paper2.score}分</span>)&emsp;<font>${paper2.paperbase_title}</font>
                                <c:if test="${(paper2.paperbase_titleimage ne '') and (not empty paper2.paperbase_titleimage)}">
                                    <br>
                                    <a href="${paper2.paperbase_titleimage}" target="_blank"><img src="${paper2.paperbase_titleimage}" height="300" width="300"></a>
                                </c:if>
                            </div>

                            <div class="test_content_nr_main">
                                <ul>

                                    <li class="option">
                                        A.
                                        <p class="ue" style="display: inline;">${paper2.paperbase_option1}</p>
                                    </li>

                                    <li class="option">
                                        B.
                                        <p class="ue" style="display: inline;">${paper2.paperbase_option2}</p>
                                    </li>

                                    <li class="option">
                                        C.
                                        <p class="ue" style="display: inline;">${paper2.paperbase_option3}</p>
                                    </li>

                                    <li class="option">
                                        D.
                                        <p class="ue" style="display: inline;">${paper2.paperbase_option4}</p>
                                    </li>
                                    <li class="option">
                                        正确答案：
                                        <p class="ue" style="display: inline;color: red;">${paper2.paperbase_answer}</p>
                                    </li>
                                </ul>
                            </div>
                        </ul>
                    </c:forEach>
                </div>
                <!--/*分割线*/-->
                <div class="test_content">
                    <div class="test_content_title">
                        <h2>判断题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${paperbase3.size()}</i><span>题</span>
                        </p>
                    </div>
                </div>
                <div class="test_content_nr">
                    <c:forEach var="paper3" items="${requestScope.paperbase3}" varStatus="status3">
                        <ul>
                            <div class="test_content_nr_tt">
                                <font><span style="color: darkgoldenrod;">第${status3.count}题</span></font>&emsp;(<span style="color: crimson;">${paper3.score}分</span>)&emsp;<font>${paper3.paperbase_title}</font>
                                <c:if test="${(paper3.paperbase_titleimage ne '') and (not empty paper3.paperbase_titleimage)}">
                                <br>
                                <a href="${paper3.paperbase_titleimage}" target="_blank"><img src="${paper3.paperbase_titleimage}" height="300" width="300"></a>
                                </c:if>
                            </div>

                            <div class="test_content_nr_main">
                                <ul>

                                    <li class="option">
                                        对.
                                    </li>

                                    <li class="option">
                                        错.
                                    </li>
                                    <li class="option">
                                        正确答案：
                                        <p class="ue" style="display: inline;color: red;">${paper3.paperbase_answer}</p>
                                    </li>
                                </ul>
                            </div>
                        </ul>
                    </c:forEach>
                </div>
                <!--/*分割线*/-->
                <div class="test_content">
                    <div class="test_content_title">
                        <h2>简答题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${paperbase4.size()}</i><span>题</span>
                        </p>
                    </div>
                </div>
                <div class="test_content_nr">
                    <c:forEach var="paper4" items="${requestScope.paperbase4}" varStatus="status4">
                        <ul>
                            <div class="test_content_nr_tt">
                                <font><span style="color: darkgoldenrod;">第${status4.count}题</span></font>&emsp;(<span style="color: crimson;">${paper4.score}分</span>)&emsp;<font>${paper4.paperbase_title}</font>
                                <br>
                                <c:if test="${(paper4.paperbase_titleimage ne '') and (not empty paper4.paperbase_titleimage)}">
                                <a href="${paper4.paperbase_titleimage}" target="_blank"><img src="${paper4.paperbase_titleimage}" height="300" width="300"></a>
                                </c:if>
                            </div>

                            <div class="test_content_nr_main">
                                <ul>
                                    <li class="option">
                                        正确答案：
                                        <br>
                                        <p class="ue" style="display: inline;color: red;">${paper4.paperbase_answer}</p>
                                        <br>
                                    </li>
                                </ul>
                            </div>
                        </ul>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

</body>
</html>