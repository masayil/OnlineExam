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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE;chrome=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/profile/core.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/profile/icon.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/profile/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/syalert/syalert.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css" />
</head>

<body>

<jsp:useBean id="admin" type="com.bean.entity.Administrator" scope="session"></jsp:useBean>
<a href="javascript:void (0)" title="返回" onclick="window.location.href = document.referrer">
    <img src="${pageContext.request.contextPath}/img/back_logo.png" alt="未加载" width="64" height="64" style="position: relative;top:50px;left: 50px;"></a>
<!-- right begin -->
<div class="ydc-column ydc-column-8" style="margin-left: 120px;">
    <div class="ydc-release-content">
        <div class="ydc-tabPanel ydc-tabPanel-release">
            <div class="ydc-release-tab-head">
                <ul>
                    <li class="hit">资料修改</li>
                </ul>
            </div>

            <div class="ydc-panes">
                <div class="ydc-pane ydc-pane-clear" style="display:block;">
                    <div class="ydc-reg-form-group clearfix">
                        <label>职工号</label>
                        <div class="ydc-reg-form-input">
                            <label><input type="text" id="teacherid" style="outline: none;border:none;" name="teacherid" required value=${thisteacher.t_id}></label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>姓名</label>
                        <div class="ydc-reg-form-input">
                            <label><input type="text" id="teachername" name="teachername" required value=${thisteacher.t_name}></label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>性别</label>
                        <div class="ydc-reg-form-input">
                            <label><select id="sexselect"><c:choose>
                                <c:when test="${thisteacher.t_sex eq '男'}">
                                    <option value="男" selected>男</option>
                                    <option value="女">女</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="男">男</option>
                                    <option value="女" selected>女</option>
                                </c:otherwise>
                            </c:choose></select></label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>学校</label>
                        <div class="ydc-reg-form-input">
                            <label><input type="text" id="college" style="outline: none;border:none;" name="college" readonly value=${thisteacher.t_college}></label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>学院</label>
                        <div class="ydc-reg-form-input">
                            <label><select id="departselect" style="width: 150px;"><c:choose>
                                <c:when test="${thisteacher.t_department eq '信息技术学院'}">
                                    <option value="信息技术学院" selected>信息技术学院</option>
                                    <option value="通识教育学院">通识教育学院</option>
                                </c:when>
                                <c:when test="${thisteacher.t_department eq '通识教育学院'}">
                                    <option value="信息技术学院">信息技术学院</option>
                                    <option value="通识教育学院" selected>通识教育学院</option>
                                </c:when>
                            </c:choose></select></label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-button" id="former" style="position: absolute;left: -100px;">
                        <a class="btn fl" href="javascript:void(0);" id="beforechange">修改</a>
                    </div><br>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script>
    $(function(){
        $("#beforechange").click(function () {
            var teacherid=document.getElementById("teacherid").value;
            var teachername=document.getElementById("teachername").value;
            var sex=document.getElementById("sexselect").value;
            var depart=document.getElementById("departselect").value;
            if(teachername!==""){
                $.ajax({
                    url:"TeacherHasModify",
                    type:"post",
                    dataType:"text",
                    data:{
                        teacherid:teacherid,
                        teachername:teachername,
                        sex:sex,
                        depart:depart
                    },
                    success:function (flag) {
                        if(flag==="yes"){
                            alert("修改成功");
                        }else if(flag==="no"){
                            alert("修改失败");
                        }else if(flag==="busy"){
                            alert("服务器繁忙，稍后再试");
                        }
                    },
                    error:function () {
                        alert("发送数据失败，请稍后再试！");
                        location.reload();
                    }
                });
            }else {
                alert("教师名不可为空");
            }
        });
    });
</script>
</body>
</html>