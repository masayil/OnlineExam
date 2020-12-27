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

    <title>在线考试系统</title>
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
    </style>
</head>

<body>
<h2 style="text-align: center;">${papername}&emsp;&emsp;&emsp;科目：${course}</h2>
<form action="CreateManMadePaper" method="post">
    <input type="hidden" name="papername" value=${papername}>
<div class="table-responsive">
    <table class="table">
        <caption style="text-align: center;">单选题</caption>
        <thead>
        <tr>
            <th>勾选</th>
            <th>出题人</th>
            <th>题型</th>
            <th style="width: 800px;">题目</th>
            <th style="width: 100px;">知识点</th>
            <th>分值</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="questionBanks_manmade" items="${requestScope.questionBanks_manmade}" varStatus="status1">
            <c:if test="${questionBanks_manmade.questionBank_type eq 1}">
        <tr>
            <td><input type="checkbox" name="danxuan" id="danxuan_${status1.count}" onclick="danxuan1(${status1.count})" value=${questionBanks_manmade.questionBank_serialNumber}></td>
            <td>
                <c:forEach var="teachers_manmade" items="${requestScope.teachers_manmade}">
                    <c:if test="${questionBanks_manmade.questionBank_creatorID eq teachers_manmade.t_id}">
                        ${teachers_manmade.t_name}
                    </c:if>
                </c:forEach>
            </td>
            <td>单选题</td>
            <td style="width: 800px;">${questionBanks_manmade.questionBank_title}</td>
            <td style="width: 100px;">${questionBanks_manmade.questionBank_point}</td>
            <td><input type="text" name="danxuanscore"  style="width: 30px;" id="danxuanscore_${status1.count}" disabled></td>
        </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

    <table class="table">
        <caption style="text-align: center;">多选题</caption>
        <thead>
        <tr>
            <th>勾选</th>
            <th>出题人</th>
            <th>题型</th>
            <th style="width: 800px;">题目</th>
            <th style="width: 100px;">知识点</th>
            <th>分值</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="questionBanks_manmade" items="${requestScope.questionBanks_manmade}" varStatus="status2">
            <c:if test="${questionBanks_manmade.questionBank_type eq 2}">
                <tr>
                    <td><input type="checkbox" name="duoxuan" id="duoxuan_${status2.count}" onclick="duoxuan1(${status2.count})" value=${questionBanks_manmade.questionBank_serialNumber}></td>
                    <td>
                        <c:forEach var="teachers_manmade" items="${requestScope.teachers_manmade}">
                            <c:if test="${questionBanks_manmade.questionBank_creatorID eq teachers_manmade.t_id}">
                                ${teachers_manmade.t_name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>多选题</td>
                    <td style="width: 800px;">${questionBanks_manmade.questionBank_title}</td>
                    <td style="width: 100px;">${questionBanks_manmade.questionBank_point}</td>
                    <td><input type="text" name="duoxuanscore" style="width: 30px;" id="duoxuanscore_${status2.count}" disabled></td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <table class="table">
        <caption style="text-align: center;">判断题</caption>
        <thead>
        <tr>
            <th>勾选</th>
            <th>出题人</th>
            <th>题型</th>
            <th style="width: 800px;">题目</th>
            <th style="width: 100px;">知识点</th>
            <th>分值</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="questionBanks_manmade" items="${requestScope.questionBanks_manmade}" varStatus="status3">
            <c:if test="${questionBanks_manmade.questionBank_type eq 3}">
                <tr>
                    <td><input type="checkbox" name="panduan" id="panduan_${status3.count}" onclick="panduan1(${status3.count})" value=${questionBanks_manmade.questionBank_serialNumber}></td>
                    <td>
                        <c:forEach var="teachers_manmade" items="${requestScope.teachers_manmade}">
                            <c:if test="${questionBanks_manmade.questionBank_creatorID eq teachers_manmade.t_id}">
                                ${teachers_manmade.t_name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>判断题</td>
                    <td style="width: 800px;">${questionBanks_manmade.questionBank_title}</td>
                    <td style="width: 100px;">${questionBanks_manmade.questionBank_point}</td>
                    <td><input type="text" name="panduanscore" style="width: 30px;" id="panduanscore_${status3.count}" disabled></td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <table class="table">
        <caption style="text-align: center;">简答题</caption>
        <thead>
        <tr>
            <th>勾选</th>
            <th>出题人</th>
            <th>题型</th>
            <th style="width: 800px;">题目</th>
            <th style="width: 100px;">知识点</th>
            <th>分值</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="questionBanks_manmade" items="${requestScope.questionBanks_manmade}" varStatus="status4">
            <c:if test="${questionBanks_manmade.questionBank_type eq 4}">
                <tr>
                    <td><input type="checkbox" name="jianda" id="jianda_${status4.count}" onclick="jianda1(${status4.count})" value=${questionBanks_manmade.questionBank_serialNumber}></td>
                    <td>
                        <c:forEach var="teachers_manmade" items="${requestScope.teachers_manmade}">
                            <c:if test="${questionBanks_manmade.questionBank_creatorID eq teachers_manmade.t_id}">
                                ${teachers_manmade.t_name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>简答题</td>
                    <td style="width: 800px;">${questionBanks_manmade.questionBank_title}</td>
                    <td style="width: 100px;">${questionBanks_manmade.questionBank_point}</td>
                    <td><input type="text" name="jiandascore" style="width: 30px;" id="jiandascore_${status4.count}" disabled></td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style=" left:120px;line-height:50px;height: 50px;width:1100px;background: bisque;position: fixed;bottom: 0;">单选题:<span id="total_danxuan">0</span>题&emsp;分数：<span id="score_danxuan">0</span>&emsp;&emsp;
    多选题:<span id="total_duoxuan">0</span>题&emsp;分数：<span id="score_duoxuan">0</span>&emsp;&emsp;
    判断题:<span id="total_panduan">0</span>题&emsp;分数：<span id="score_panduan">0</span>&emsp;&emsp;
    简答题:<span id="total_jianda">0</span>题&emsp;分数：<span id="score_jianda">0</span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    总分：<span id="score_zongfen">0</span>&emsp;&emsp;
    <input type="reset" value="重置" id="" onclick="resetthis()" style="background: chocolate;color: darkred;width: 100px;">
    <input type="submit" value="提交" style=" display:none;float:right;width: 100px;background: blue;color: white;" id="btn2">
    <button type="button" onclick="checkscore()" style="float: right;background: cadetblue;color: darkred;width: 100px;" id="btn1">下一步</button>

</div>
</form>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function resetthis() {
        location.reload();
    }
    function checkscore() {
        var danxuanscore=document.getElementsByName("danxuanscore");
        var duoxuanscore=document.getElementsByName("duoxuanscore");
        var panduanscore=document.getElementsByName("panduanscore");
        var jiandascore=document.getElementsByName("jiandascore");
        var score=0;
        var score1=0;
        var score2=0;
        var score3=0;
        var score4=0;
        for(var i1=0;i1<danxuanscore.length;i1++){
            if(!danxuanscore[i1].disabled){
                score1+=parseFloat(danxuanscore[i1].value);
            }
        }
        for(var i2=0;i2<duoxuanscore.length;i2++){
            if(!duoxuanscore[i2].disabled){
                score2+=parseFloat(duoxuanscore[i2].value);
            }
        }
        for(var i3=0;i3<panduanscore.length;i3++){
            if(!panduanscore[i3].disabled){
                score3+=parseFloat(panduanscore[i3].value);
            }
        }
        for(var i4=0;i4<jiandascore.length;i4++){
            if(!jiandascore[i4].disabled){
                score4+=parseFloat(jiandascore[i4].value);
            }
        }
        score=score1+score2+score3+score4;
        document.getElementById("score_danxuan").innerHTML=score1;
        document.getElementById("score_duoxuan").innerHTML=score2;
        document.getElementById("score_panduan").innerHTML=score3;
        document.getElementById("score_jianda").innerHTML=score4;
        document.getElementById("score_zongfen").innerHTML=score;
        if(score===100){
            document.getElementById("btn1").style.display="none";
            document.getElementById("btn2").style.display="block";
        }else {
            alert("试卷总分应为100分");
            location.reload();
        }
    }
    function danxuan1(thisone) {
        if(document.getElementById("danxuanscore_"+thisone).disabled===false){
            document.getElementById("danxuanscore_"+thisone).disabled=true;
        }else {
            document.getElementById("danxuanscore_"+thisone).disabled=false;
        }
        var danxuan=document.getElementsByName("danxuan");
        var num_danxuan=0;
        for(var i=0;i<danxuan.length;i++){
            if(danxuan[i].checked){
                num_danxuan++;
            }
        }
        document.getElementById("total_danxuan").innerHTML=num_danxuan;
    }
    function duoxuan1(thisone) {
        if(document.getElementById("duoxuanscore_"+thisone).disabled===false){
            document.getElementById("duoxuanscore_"+thisone).disabled=true;
        }else {
            document.getElementById("duoxuanscore_"+thisone).disabled=false;
        }
        var duoxuan=document.getElementsByName("duoxuan");
        var num_duoxuan=0;
        for(var i=0;i<duoxuan.length;i++){
            if(duoxuan[i].checked){
                num_duoxuan++;
            }
        }
        document.getElementById("total_duoxuan").innerHTML=num_duoxuan;
    }
    function panduan1(thisone) {
        if(document.getElementById("panduanscore_"+thisone).disabled===false){
            document.getElementById("panduanscore_"+thisone).disabled=true;
        }else {
            document.getElementById("panduanscore_"+thisone).disabled=false;
        }
        var panduan=document.getElementsByName("panduan");
        var num_panduan=0;
        for(var i=0;i<panduan.length;i++){
            if(panduan[i].checked){
                num_panduan++;
            }
        }
        document.getElementById("total_panduan").innerHTML=num_panduan;
    }
    function jianda1(thisone) {
        if(document.getElementById("jiandascore_"+thisone).disabled===false){
            document.getElementById("jiandascore_"+thisone).disabled=true;
        }else {
            document.getElementById("jiandascore_"+thisone).disabled=false;
        }
        var jianda=document.getElementsByName("jianda");
        var num_jianda=0;
        for(var i=0;i<jianda.length;i++){
            if(jianda[i].checked){
                num_jianda++;
            }
        }
        document.getElementById("total_jianda").innerHTML=num_jianda;
    }
</script>
</body>
</html>