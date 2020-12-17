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
        .form-group{
            margin-top: 30px;
        }
        input:required:invalid{
            border-color: darkred;
        }
    </style>
</head>

<body>
<h2 style="text-align: center; color: grey;">创建试卷</h2>
<h3 style="text-align: center;">科目：${course}</h3>
<form class="form-inline" role="form" style="text-align: center;" id="newpaper" action="FinishPaperServlet" method="post">
    <div class="form-group">
        <label class="sr-only" for="papername">试卷名字</label>
        试卷名字：<input type="text" class="form-control" id="papername" name="papername" style="width: 350px;" placeholder="请输入试卷名字" autocomplete="off">
    </div>
    <br>
    <div class="form-group">
        单选题：
    <select style="width: 100px; height: 30px;" id="danxuan" name="danxuan">
        <%
    int type1=(int)request.getAttribute("type1");
    for(int i=0;i<=type1;i++){
    %>
        <option value="<%=i%>"><%=i%></option>
        <%
            }
        %>
    </select>&emsp;道
    </div>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <div class="form-group">
        <label class="sr-only" for="score1">分数</label>
        分数：<input type="text" class="form-control floatNum" id="score1" name="score1" placeholder="请输入单选题分数" value="0" autocomplete="off">
    </div>
    <br>
    <div class="form-group">
        多选题：
        <select style="width: 100px; height: 30px;" id="duoxuan" name="duoxuan">
            <%
                int type2=(int)request.getAttribute("type2");
                for(int i=0;i<=type2;i++){
            %>
            <option value="<%=i%>"><%=i%></option>
            <%
                }
            %>
        </select>&emsp;道
    </div>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <div class="form-group">
        <label class="sr-only" for="score2">分数</label>
        分数：<input type="text" class="form-control floatNum" id="score2" name="score2" placeholder="请输入多选题分数" value="0" autocomplete="off">
    </div>
    <br>
    <div class="form-group">
        判断题：
        <select style="width: 100px; height: 30px;" id="panduan" name="panduan">
            <%
                int type3=(int)request.getAttribute("type3");
                for(int i=0;i<=type3;i++){
            %>
            <option value="<%=i%>"><%=i%></option>
            <%
                }
            %>
        </select>&emsp;道
    </div>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <div class="form-group">
        <label class="sr-only" for="score3">分数</label>
        分数：<input type="text" class="form-control floatNum" id="score3" name="score3" placeholder="请输入判断题分数" value="0" autocomplete="off">
    </div>
    <br>
    <div class="form-group">
        简答题：
        <select style="width: 100px; height: 30px;" id="jianda" name="jianda">
            <%
                int type4=(int)request.getAttribute("type4");
                for(int i=0;i<=type4;i++){
            %>
            <option value="<%=i%>"><%=i%></option>
            <%
                }
            %>
        </select>&emsp;道
    </div>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <div class="form-group">
        <label class="sr-only" for="score4">分数</label>
        分数：<input type="text" class="form-control floatNum" id="score4" name="score4" placeholder="请输入简答题分数" value="0" autocomplete="off">
    </div>
    <br>
    <br>
    <h4 style="color: gray;">试卷总分：<input type="text" style="color: crimson; width: 50px;outline: none; border: none;" id="totalscore" name="totalscore" value="0" readonly><span style="color: crimson;">分</span></h4>
    <input type="button" class="btn btn-success" value="确认提交" style="margin-top: 30px;" onclick="check()">
</form>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    $(function () {
        $(".floatNum").click(function () {
            $(this).val("");
        });
        $(".floatNum").blur(function () {
            if($(this).val()===""){
                $(this).focus();
            }
        });
        $("#score1").keyup(function () {
            var a=$("#danxuan").val();
            var b=$("#score1").val();
            if(b===""){
                $("#score1").val("0");
            }
            var c=parseFloat(a)*parseFloat(b);
            var d=parseFloat($("#totalscore").val());
            d=d+c;
            document.getElementById("totalscore").value=d;
        });
        $("#score2").keyup(function () {
            var a=$("#duoxuan").val();
            var b=$("#score2").val();
            if(b===""){
                $("#score2").val("0");
            }
            var c=parseFloat(a)*parseFloat(b);
            var d=parseFloat($("#totalscore").val());
            d=d+c;
            document.getElementById("totalscore").value=d;
        });
        $("#score3").keyup(function () {
            var a=$("#panduan").val();
            var b=$("#score3").val();
            if(b===""){
                $("#score3").val("0");
            }
            var c=parseFloat(a)*parseFloat(b);
            var d=parseFloat($("#totalscore").val());
            d=d+c;
            document.getElementById("totalscore").value=d;
        });
        $("#score4").keyup(function () {
            var a=$("#jianda").val();
            var b=$("#score4").val();
            if(b===""){
                $("#score4").val("0");
            }
            var c=parseFloat(a)*parseFloat(b);
            var d=parseFloat($("#totalscore").val());
            d=d+c;
            document.getElementById("totalscore").value=d;
        });
    });
    function check() {
        var a=parseFloat($("#danxuan").val())*parseFloat($("#score1").val())+parseFloat($("#duoxuan").val())*parseFloat($("#score2").val())+parseFloat($("#panduan").val())*parseFloat($("#score3").val())+parseFloat($("#jianda").val())*parseFloat($("#score4").val());
        document.getElementById("totalscore").value=a;
        if(!$("#papername").val()){
            alert("输入试卷名字");
            return false;
        }
        if(a!==100){
            alert("试卷总分应为100,目前总分："+a);
            location.reload();
            return false;
        }
        if(confirm("是否确认创建？")){
            document.getElementById("newpaper").submit();

        }else {
            location.reload();
            return false;
        }

    }
</script>
</body>
</html>