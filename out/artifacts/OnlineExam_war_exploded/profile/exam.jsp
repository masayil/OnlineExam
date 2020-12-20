<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <title>考试中</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="${pageContext.request.contextPath}/css/paper/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/timeTo.css" type="text/css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/paper/test.css" rel="stylesheet" type="text/css"/>
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
<div class="main">
    <div style="position: relative; left: -100px;">
    <h2>${examname}</h2>&emsp;<h3>满分：${totalscore}</h3>
    </div>
    <!--nr start-->
    <div class="test_main">
        <div class="nr_left">
            <div class="test">
                <form action="${pageContext.request.contextPath}/MarkExamServlet" id="exam_stu" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="paper1size" value=${size1}>
                    <input type="hidden" name="paper2size" value=${size2}>
                    <input type="hidden" name="paper3size" value=${size3}>
                    <input type="hidden" name="paper4size" value=${size4}>
                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>单选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${size1}</i><span>题</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <c:forEach var="paper1" items="${requestScope.paper1}" varStatus="status1">
                        <ul>
                            <li id="qu_1_${status1.count}">
                                <div class="test_content_nr_tt">
                                    <font>第${status1.count}题</font>&emsp;<span style="color: red;">(${paper1.score}分)</span>&emsp;<font>${paper1.paper_title}</font>
                                    <c:if test="${(paper1.paper_titleimage ne '') and (not empty paper1.paper_titleimage)}">
                                        <br>
                                        <a href="${paper1.paper_titleimage}" target="_blank"><img src="${paper1.paper_titleimage}" height="300" width="300"></a>
                                    </c:if>
                                </div>

                                <div class="test_content_nr_main">
                                    <ul>
                                        <input type="hidden" name="1_serialNumber" value=${paper1.paper_serialNumber}>
                                        <li class="option">

                                            <input type="radio" class="radioOrCheck" name="1_answer_${status1.count}"
                                                   id="1_answer_1_option_1_${status1.count}" value="A"/>


                                            <label for="1_answer_1_option_1_${status1.count}">
                                                A.
                                                <p class="ue" style="display: inline;">${paper1.paper_option1}</p>
                                            </label>
                                        </li>

                                        <li class="option">

                                            <input type="radio" class="radioOrCheck" name="1_answer_${status1.count}"
                                                   id="1_answer_1_option_2_${status1.count}" value="B"/>


                                            <label for="1_answer_1_option_2_${status1.count}">
                                                B.
                                                <p class="ue" style="display: inline;">${paper1.paper_option2}</p>
                                            </label>
                                        </li>

                                        <li class="option">

                                            <input type="radio" class="radioOrCheck" name="1_answer_${status1.count}"
                                                   id="1_answer_1_option_3_${status1.count}" value="C"/>


                                            <label for="1_answer_1_option_3_${status1.count}">
                                                C.
                                                <p class="ue" style="display: inline;">${paper1.paper_option3}</p>
                                            </label>
                                        </li>

                                        <li class="option">

                                            <input type="radio" class="radioOrCheck" name="1_answer_${status1.count}"
                                                   id="1_answer_1_option_4_${status1.count}" value="D"/>


                                            <label for="1_answer_1_option_4_${status1.count}">
                                                D.
                                                <p class="ue" style="display: inline;">${paper1.paper_option4}</p>
                                            </label>
                                        </li>

                                    </ul>
                                </div>
                            </li>

                        </ul>
                        </c:forEach>
                    </div>

                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>多选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${size2}</i><span>题</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <c:forEach var="paper2" items="${requestScope.paper2}" varStatus="status2">
                        <ul>
                            <li id="qu_2_${status2.count}">
                                <div class="test_content_nr_tt">
                                    <font>第${status2.count}题</font> &emsp;<span style="color: red;">(${paper2.score}分)</span>&emsp;<font>${paper2.paper_title}</font>
                                    <c:if test="${(paper2.paper_titleimage ne '') and (not empty paper2.paper_titleimage)}">
                                        <br>
                                        <a href="${paper2.paper_titleimage}" target="_blank"><img src="${paper2.paper_titleimage}" height="300" width="300"></a>
                                    </c:if>
                                </div>

                                <div class="test_content_nr_main">
                                    <ul>
                                        <input type="hidden" name="2_serialNumber" value=${paper2.paper_serialNumber}>
                                        <li class="option">


                                            <input type="checkbox" class="radioOrCheck" name="2_answer2_${status2.count}"
                                                   id="2_answer_2_option_1_${status2.count}" value="A"/>

                                            <label for="2_answer_2_option_1_${status2.count}">
                                                A.
                                                <p class="ue" style="display: inline;">${paper2.paper_option1}</p>
                                            </label>
                                        </li>

                                        <li class="option">


                                            <input type="checkbox" class="radioOrCheck" name="2_answer2_${status2.count}"
                                                   id="2_answer_2_option_2_${status2.count}" value="B"/>

                                            <label for="2_answer_2_option_2_${status2.count}">
                                                B.
                                                <p class="ue" style="display: inline;">${paper2.paper_option2}</p>
                                            </label>
                                        </li>

                                        <li class="option">


                                            <input type="checkbox" class="radioOrCheck" name="2_answer2_${status2.count}"
                                                   id="2_answer_2_option_3_${status2.count}" value="C"/>

                                            <label for="2_answer_2_option_3_${status2.count}">
                                                C.
                                                <p class="ue" style="display: inline;">${paper2.paper_option3}</p>
                                            </label>
                                        </li>

                                        <li class="option">


                                            <input type="checkbox" class="radioOrCheck" name="2_answer2_${status2.count}"
                                                   id="2_answer_2_option_4_${status2.count}" value="D"/>

                                            <label for="2_answer_2_option_4_${status2.count}">
                                                D.
                                                <p class="ue" style="display: inline;">${paper2.paper_option4}</p>
                                            </label>
                                        </li>

                                    </ul>
                                </div>
                            </li>

                        </ul>
                        </c:forEach>
                    </div>

                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>判断题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${size3}</i><span>题</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <c:forEach var="paper3" items="${requestScope.paper3}" varStatus="status3">
                            <ul>
                                <li id="qu_3_${status3.count}">
                                    <div class="test_content_nr_tt">
                                        <font>第${status3.count}题</font> &emsp;<span style="color: red;">(${paper3.score}分)</span>&emsp;<font>${paper3.paper_title}</font>
                                        <c:if test="${(paper3.paper_titleimage ne '') and (not empty paper3.paper_titleimage)}">
                                            <br>
                                            <a href="${paper3.paper_titleimage}" target="_blank"><img src="${paper3.paper_titleimage}" height="300" width="300"></a>
                                        </c:if>
                                    </div>

                                    <div class="test_content_nr_main">
                                        <ul>
                                            <input type="hidden" name="3_serialNumber" value=${paper3.paper_serialNumber}>
                                            <li class="option">


                                                <input type="radio" class="radioOrCheck" name="3_answer3_${status3.count}"
                                                       id="3_answer_3_option_1_${status3.count}" value="对"/>

                                                <label for="3_answer_3_option_1_${status3.count}">
                                                    对
                                                    <p class="ue" style="display: inline;">${paper3.paper_option1}</p>
                                                </label>
                                            </li>

                                            <li class="option">


                                                <input type="radio" class="radioOrCheck" name="3_answer3_${status3.count}"
                                                       id="3_answer_3_option_2_${status3.count}" value="错"/>

                                                <label for="3_answer_3_option_2_${status3.count}">
                                                    错
                                                    <p class="ue" style="display: inline;">${paper3.paper_option2}</p>
                                                </label>
                                            </li>

                                        </ul>
                                    </div>
                                </li>

                            </ul>
                        </c:forEach>
                    </div>

                                <div class="test_content">
                                    <div class="test_content_title">
                                        <h2>简答题</h2>
                                        <p>
                                            <span>共</span><i class="content_lit">${size4}</i><span>题</span>
                                        </p>
                                        <h3 style="color: red;">上传图片格式为jpg</h3>
                                    </div>
                                </div>
                                <div class="test_content_nr">
                                    <c:forEach var="paper4" items="${requestScope.paper4}" varStatus="status4">
                                    <ul>
                                        <li id="qu_4_${status4.count}">
                                            <div class="test_content_nr_tt">
                                                <font>第${status4.count}题</font> &emsp;<span style="color: red;">(${paper4.score}分)</span>&emsp;<font><font>${paper4.paper_title}</font>
                                                <c:if test="${(paper4.paper_titleimage ne '') and (not empty paper4.paper_titleimage)}">
                                                <br>
                                                <a href="${paper4.paper_titleimage}" target="_blank"><img src="${paper4.paper_titleimage}" height="300" width="300"></a>
                                                </c:if>
                                            </div>
                                        <br>
                                            <label for="4_answer_4_option_1_${status4.count}">
                                        上传图片：<input type="file"  name="4_answer4_${status4.count}"
                                               id="4_answer_4_option_1_${status4.count}"/>
                                            </label>
                                        </li>

                        </ul>
                                        <br>
                            </c:forEach>
                    </div>
                    <br>
                    <div class="test_title">
                        <p class="test_time">
                            <input type="hidden" id="lasttime" value=${lasttime}>
                            <span style="font-size: 24px;">考试时间：</span><span class="countdown-1"></span>
                        </p>
                        <font><input type="submit" name="test_jiaojuan" value="交卷" onclick=" return check()"></font>
                    </div>
                </form>
            </div>
        </div>
        <div class="nr_right">
            <div class="nr_rt_main">
                <div class="rt_nr1">
                    <div class="rt_nr1_title">
                        <h1>
                            答题卡
                        </h1>
                        <p class="test_time">
                        </p>
                    </div>
                    <div >
                    <div class="rt_content">
                        <div class="rt_content_tt">
                            <h2>单选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${size1}</i><span>题</span>
                            </p>
                        </div>
                        <div class="rt_content_nr answerSheet" style="overflow: auto; height: 100px;">
                            <ul>
                                <%
                                    int qu1_1=(int)request.getAttribute("size1");
                                    for(int i=0;i<qu1_1;i++){
                                %>
                                <li><a href="#qu_1_<%=i+1%>"><%=i+1%></a></li>
                                <%
                                    }%>
                            </ul>
                        </div>
                    </div>

                    <div class="rt_content">
                        <div class="rt_content_tt">
                            <h2>多选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${size2}</i><span>题</span>
                            </p>
                        </div>
                        <div class="rt_content_nr answerSheet" style="overflow: auto; height: 100px;">
                            <ul>

                                <%
                                    int qu2_1=(int)request.getAttribute("size2");
                                    for(int i=0;i<qu2_1;i++){
                                %>
                                <li><a href="#qu_2_<%=i+1%>"><%=i+1%></a></li>
                                <%
                                    }%>

                            </ul>
                        </div>
                    </div>

                    <div class="rt_content">
                        <div class="rt_content_tt">
                            <h2>判断题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${size3}</i><span>题</span>
                            </p>
                        </div>
                        <div class="rt_content_nr answerSheet" style="overflow: auto; height: 100px;">
                            <ul>
                                <%
                                    int qu3_1=(int)request.getAttribute("size3");
                                    for(int i=0;i<qu3_1;i++){
                                %>
                                <li><a href="#qu_3_<%=i+1%>"><%=i+1%></a></li>
                                <%
                                    }%>
                            </ul>
                        </div>
                    </div>

                    <div class="rt_content">
                        <div class="rt_content_tt">
                            <h2>简答题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${size4}</i><span>题</span>
                            </p>
                        </div>
                        <div class="rt_content_nr answerSheet" style="overflow: auto; height: 100px;">
                            <ul>

                                <%
                                    int qu4_1=(int)request.getAttribute("size4");
                                    for(int i=0;i<qu4_1;i++){
                                %>
                                <li><a href="#qu_4_<%=i+1%>"><%=i+1%></a></li>
                                <%
                                    }%>
                            </ul>
                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!--nr end-->


    <div class="foot"></div>


</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.time-to.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script>
    $(function () {
        $('li.option input').click(function () {
            debugger;
            var examId = $(this).closest('.test_content_nr_main').closest('li').attr('id'); /*得到题目ID*/
            var cardLi = $('a[href=#' + examId + ']'); /*根据题目ID找到对应答题卡*/
            /*设置已答题*/
            if (!cardLi.hasClass('hasBeenAnswer')) {
                cardLi.addClass('hasBeenAnswer');
            }

        });

        /**
         * Set timer countdown in seconds with callback
         */
        var time=$("#lasttime").val();
        var time1=parseInt(time);
        $('.countdown-1').timeTo(time1, function () {
            document.getElementById("exam_stu").submit();

        });

    });

    function check() {
        for(var i1=1;i1<=${size1};i1++){
            if(document.getElementsByName("1_answer_"+i1)[0].checked===false&&document.getElementsByName("1_answer_"+i1)[1].checked===false&&document.getElementsByName("1_answer_"+i1)[2].checked===false&&document.getElementsByName("1_answer_"+i1)[3].checked===false){
                alert("有未完成的单选题");
                return false;
            }
        }
        for(var i2=1;i2<=${size2};i2++){
            if(document.getElementsByName("2_answer2_"+i2)[0].checked===false&&document.getElementsByName("2_answer2_"+i2)[1].checked===false&&document.getElementsByName("2_answer2_"+i2)[2].checked===false&&document.getElementsByName("2_answer2_"+i2)[3].checked===false){
                alert("有未完成的多选题");
                return false;
            }
        }
        for(var i3=1;i3<=${size3};i3++){
            if(document.getElementsByName("3_answer3_"+i3)[0].checked===false&&document.getElementsByName("3_answer3_"+i3)[1].checked===false){
                alert("有未完成的判断题");
                return false;
            }
        }
        for(var i4=1;i4<=${size4};i4++){
                var fileTypes = ".jpg";
                var filePath= document.getElementById("4_answer_4_option_1_"+i4).value;
                var fileEnd = filePath.substring(filePath.indexOf("."));
                if (fileTypes !== fileEnd) {
                    alert("简答题图片格式为jpg"+fileEnd+" filepath"+filePath);
                    return false;
                }
            }
        return true;
    }
</script>
</body>
</html>