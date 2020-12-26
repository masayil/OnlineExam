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

    <title>浏览题库</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <style>
        table {

            border-collapse: collapse;

            font-family: Futura, Arial, sans-serif;
            word-break: break-all;

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

    </style>
</head>

<body onload="typecheck('${questiontype}')">
<script>
    function typecheck(questiontype) {
        if(questiontype==="none"){
            $('input:radio').eq(0).attr('checked', 'true');
        }else if(questiontype==="type1"){
            $('input:radio').eq(1).attr('checked', 'true');
        }else if(questiontype==="type2"){
            $('input:radio').eq(2).attr('checked', 'true');
        }else if(questiontype==="type3"){
            $('input:radio').eq(3).attr('checked', 'true');
        }else if(questiontype==="type4"){
            $('input:radio').eq(4).attr('checked', 'true');
        }
    }
</script>
<div style="padding-left: 10px;padding-top: 50px;">
    <table style="max-width: 1570px;text-align: center;font-size: 12px;" id="haveactiontable">
        <caption style="text-align: center;"><h2>题库（${check}）</h2><br>
            <c:choose>
                <c:when test="${category eq 'teacher_name'}">
                    <h5>检索内容：教师工号-${title}&emsp;${t_realname}&ensp;教师</h5>
                </c:when>
                <c:otherwise><h5>检索内容：${title}</h5></c:otherwise>
            </c:choose>

            <br><strong>题型</strong>:&emsp;
            <label for="q1">
                <input type="radio" value="none" name="q_type" id="q1" onclick="converttype('${category}')">全部</label>&emsp;&emsp;&emsp;
            <label for="q2">
                <input type="radio" value="type1" name="q_type"  id="q2" onclick="converttype('${category}')">单选</label>&emsp;&emsp;&emsp;
            <label for="q3">
                <input type="radio" value="type2" name="q_type" id="q3" onclick="converttype('${category}')">多选</label>&emsp;&emsp;&emsp;
            <label for="q4">
                <input type="radio" value="type3" name="q_type" id="q4" onclick="converttype('${category}')">判断</label>&emsp;&emsp;&emsp;
            <label for="q5">
                <input type="radio" value="type4" name="q_type" id="q5" onclick="converttype('${category}')">简答</label>&emsp;&emsp;&emsp;
           <!-- <br><br>自定义搜索：<input type="text" id="lin" placeholder="请输入需要搜索的内容">-->
        </caption>
        <thead>
        <tr>
            <th style="width: 80px;">出题人</th>
            <th style="width: 100px;">科目</th>
            <th style="width: 100px;">知识点</th>
            <th style="width: 70px;">题型</th>
            <th style="width: 300px;">题干</th>
            <th style="width: 150px;">选项A</th>
            <th style="width: 150px;">选项B</th>
            <th style="width: 150px;">选项C</th>
            <th style="width: 150px;">选项D</th>
            <th style="width: 250px;">答案</th>
            <th style="width: 70px;">操作</th>
        </thead>
        <tbody>
        <c:forEach var="banklist" items="${requestScope.banklist}" varStatus="status">
            <c:choose>
                <c:when test="${banklist.questionBank_type eq 1}"><tr style="background: bisque;"></c:when>
                <c:when test="${banklist.questionBank_type eq 2}"><tr style="background: bisque;"></c:when>
                <c:when test="${banklist.questionBank_type eq 3}"><tr style="background: bisque;"></c:when>
                <c:when test="${banklist.questionBank_type eq 4}"><tr style="background: bisque;"></c:when>
            </c:choose>
            <td style="width: 80px;">${banklist.questionBank_creatorID}</td>
            <td style="width: 100px;color: saddlebrown;"><strong>${banklist.questionBank_course}</strong></td>
            <td style="width: 100px;">${banklist.questionBank_point}</td>
            <td style="width: 70px;color: cornflowerblue;">
                <c:choose>
                    <c:when test="${banklist.questionBank_type eq 1}"><strong>单选题</strong></c:when>
                    <c:when test="${banklist.questionBank_type eq 2}"><strong>多选题</strong></c:when>
                    <c:when test="${banklist.questionBank_type eq 3}"><strong>判断题</strong></c:when>
                    <c:otherwise><strong>简答题</strong></c:otherwise>
                </c:choose>
            </td>
            <td  style="width: 300px;">${banklist.questionBank_title}</td>
            <td  style="width: 150px;">
                <c:choose>
                    <c:when test="${banklist.questionBank_type eq 1}">A.${banklist.questionBank_option1}</c:when>
                    <c:when test="${banklist.questionBank_type eq 2}">A.${banklist.questionBank_option1}</c:when>
                    <c:when test="${banklist.questionBank_type eq 3}">-----</c:when>
                    <c:otherwise>-----</c:otherwise>
                </c:choose>
            </td>
            <td style="width: 150px;">
                <c:choose>
                    <c:when test="${banklist.questionBank_type eq 1}">B.${banklist.questionBank_option2}</c:when>
                    <c:when test="${banklist.questionBank_type eq 2}">B.${banklist.questionBank_option2}</c:when>
                    <c:when test="${banklist.questionBank_type eq 3}">-----</c:when>
                    <c:otherwise>-----</c:otherwise>
                </c:choose>
            </td>
            <td style="width: 150px;">
                <c:choose>
                    <c:when test="${banklist.questionBank_type eq 1}">C.${banklist.questionBank_option3}</c:when>
                    <c:when test="${banklist.questionBank_type eq 2}">C.${banklist.questionBank_option3}</c:when>
                    <c:when test="${banklist.questionBank_type eq 3}">-----</c:when>
                    <c:otherwise>-----</c:otherwise>
                </c:choose>
            </td>
            <td style="width: 150px;">
                <c:choose>
                    <c:when test="${banklist.questionBank_type eq 1}">D.${banklist.questionBank_option4}</c:when>
                    <c:when test="${banklist.questionBank_type eq 2}">D.${banklist.questionBank_option4}</c:when>
                    <c:when test="${banklist.questionBank_type eq 3}">-----</c:when>
                    <c:otherwise>-----</c:otherwise>
                </c:choose>
            </td>
            <td style="width: 250px;">${banklist.questionBank_answer}</td>
            <td><button type="button" class="btn btn-danger" onclick="deletebank(this,'${banklist.questionBank_serialNumber}','${category}')">删除</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<ul class="pager">
    <li><a href="javascript:void(0)" onclick="backpage(${nowpage},${allpage},'${category}')">上一页</a></li>
    <li>&emsp;&emsp;</li>
    <li>第${nowpage}页</li>
    <li>&emsp;&emsp;</li>
    <li>共${allpage}页</li>
    <li>&emsp;&emsp;</li>
    <li><a href="javascript:void(0)" onclick="nextpage(${nowpage},${allpage},'${category}')">下一页</a></li>
</ul>
<div style="height: 50px;"></div>
<input type="hidden" id="search" readonly value=${title}>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/lin_search.js"></script>
<script>
    function nextpage(nowpage,allpage,category) {
        if(nowpage===allpage||nowpage>allpage){
            alert("目前在最后一页");
        }else {
            nowpage=nowpage+1;
            var questiontype= $("input[name='q_type']:checked").val();
            if(document.getElementById("search").value===""||document.getElementById("search").value==null) {
                var text="";
                window.location.href = "OpenBankServlet?category=" + category + "&questiontype=" + questiontype + "&curPage=" + nowpage + "&totalPage=" + allpage+"&title="+text;
            }else{
                window.location.href = "OpenBankServlet?category=" + category + "&questiontype=" + questiontype + "&curPage=" + nowpage + "&totalPage=" + allpage+"&title="+document.getElementById("search").value;
            }
        }
    }
    function backpage(nowpage,allpage,category) {
        if(nowpage===1){
            alert("目前在第一页");
        }else {
            var questiontype= $("input[name='q_type']:checked").val();
            nowpage=nowpage-1;
            if(document.getElementById("search").value===""||document.getElementById("search").value==null) {
                var text="";
                window.location.href = "OpenBankServlet?category=" + category + "&questiontype=" + questiontype + "&curPage=" + nowpage + "&totalPage=" + allpage+"&title="+text;
            }else{
                window.location.href = "OpenBankServlet?category=" + category + "&questiontype=" + questiontype + "&curPage=" + nowpage + "&totalPage=" + allpage+"&title="+document.getElementById("search").value;
            }
        }
    }
    function converttype(category) {
        var questiontype= $("input[name='q_type']:checked").val();
        if(document.getElementById("search").value===""||document.getElementById("search").value==null) {
            var text="";
            window.location.href = "OpenBankServlet?category=" + category + "&questiontype=" + questiontype+"&title="+text;
        }else{
            window.location.href = "OpenBankServlet?category=" + category + "&questiontype=" + questiontype+"&title="+document.getElementById("search").value;
        }
    }
    function deletebank(thisone,serialNumber,category) {
        var questiontype= $("input[name='q_type']:checked").val();
        var text="";
        if(confirm("确认删除？")) {
            $.ajax({
                url: "DeleteBankServlet",
                type: "post",
                dataType: "text",
                data: {
                    serialNumber: serialNumber,
                },
                success: function (flag) {
                    if (flag === "yes") {
                        alert("删除成功");
                        window.location.href="OpenBankServlet?category="+category+"&questiontype="+questiontype+"&title="+text;
                    } else if (flag === "no") {
                        alert("删除失败，再试一次！");
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
    var table_this = document.getElementById("haveactiontable");
    var input_this = document.getElementById("lin")


    input_this.onkeyup = function () {
        new Search(table_this, input_this)
    }
</script>
</body>
</html>