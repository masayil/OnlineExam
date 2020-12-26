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

    <title>在线考试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <style>
        .content {
            width: 600px;
            height: 600px;
            /* background: yellow; */
            margin: 50px auto;
        }
        table {
            text-align:center;

        }
        table th{
            text-align:center;
        }

    </style>
</head>

<body>
<h1 style="text-align: center;">${examname}</h1>
<%
String picture=(String)request.getAttribute("imgName");
%>
<div style="height: 60px;"></div><button type="button" onclick="exportexcel('${examuuid}','${examname}','${lessonuuid}')" style="margin: 30px;position: absolute;left:1100px; "  class="btn btn-warning">导出成绩表</button>
<button type="button" onclick="see()" style="margin: 30px; position: absolute;left:1300px;" id="btn" class="btn btn-danger">查看成绩分布图</button>
<div id="flag1">
<h2 style="text-align: center; color: darkgrey;">成绩表</h2>
    <h3 style="text-align: center; color: darkgrey;">平均分：${average}&emsp;&emsp;&emsp;参考人数：${student_list.size()}&emsp;&emsp;&emsp;未参加人数：${wantnoGrade.size()}</h3>
<div class="container" style="display: block;text-align: center;" id="table1">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-bordered" style="width: 1200px;">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>班级</th>
                    <th>分数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="gradeslist" items="${requestScope.gradeslist}">
                    <tr class="warning">
                        <td>${gradeslist.studentID}</td>
                        <td>
                            <c:forEach items="${requestScope.student_list}" var="studentlist">
                                <c:if test="${studentlist.s_id eq gradeslist.studentID}">
                                    ${studentlist.s_name}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${requestScope.student_list}" var="studentlist">
                                <c:if test="${studentlist.s_id eq gradeslist.studentID}">
                                    ${studentlist.s_class}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${gradeslist.total}</td>
                        <td><button type="button" class="btn btn-info" onclick="checkpaper('${gradeslist.studentID}','${gradeslist.examAssignuuid}','${examname}','${gradeslist.total}')">查看试卷</button></td>
                    </tr>
                </c:forEach>
                <c:forEach var="wantnoGrade" items="${requestScope.wantnoGrade}">
                    <tr class="warning">
                        <td>${wantnoGrade.s_id}</td>
                        <td>${wantnoGrade.s_name}</td>
                        <td>${wantnoGrade.s_class}</td>
                        <td><span style="color: darkred;">未参加考试</span></td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<br>
<div style="text-align: center;display: none;" id="picture">
    <img src="DisplayChart?filename=<%=picture%>" width="700" height="500" border="0" alt="图片">
</div>
<div style="height: 35px;"></div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    function see() {
        var img=document.getElementById("picture");
        var table=document.getElementById("flag1");
        if(img.style.display==="none"){
            img.style.display="block";
            table.style.display="none";
            $("#btn").html("查看成绩表");
        }else {
            img.style.display="none";
            table.style.display="block";
            $("#btn").html("查看成绩分布图");
        }
    }
    function checkpaper(studentid,examuuid,examname,total) {
        window.open('GetStuPaperServlet?examuuid='+examuuid+'&studentid='+studentid+'&examname='+examname+'&total='+total, '_blank');
    }
    function exportexcel(examuuid,examname,lessonuuid) {
        window.location.href="ExportGrade?examuuid="+examuuid+"&examname="+examname+"&lessonuuid="+lessonuuid;
    }
</script>
</body>
</html>