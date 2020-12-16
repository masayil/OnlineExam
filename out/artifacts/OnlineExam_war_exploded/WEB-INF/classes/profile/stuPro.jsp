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

<jsp:useBean id="student" type="com.bean.entity.Student" scope="session"></jsp:useBean>
<!-- right begin -->
<div class="ydc-column ydc-column-8" style="margin-left: 90px;">
    <div class="ydc-release-content">
        <div class="ydc-tabPanel ydc-tabPanel-release">
            <div class="ydc-release-tab-head">
                <ul>
                    <li class="hit">个人中心</li>
                </ul>
            </div>

            <div class="ydc-panes">
                <div class="ydc-pane ydc-pane-clear" style="display:block;">
                    <div class="ydc-reg-form-group clearfix">
                        <label>学号</label>
                        <div class="ydc-reg-form-input">
                            <label>${student.s_id}</label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>姓名</label>
                        <div class="ydc-reg-form-input">
                            <label>${student.s_name}</label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>性别</label>
                        <div class="ydc-reg-form-input">
                            <label>${student.s_sex}</label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>学校</label>
                        <div class="ydc-reg-form-input">
                            <label>${student.s_college}</label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>学院</label>
                        <div class="ydc-reg-form-input">
                            <label>${student.s_department}</label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>专业</label>
                        <div class="ydc-reg-form-input">
                            <label>${student.s_major}</label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>班级</label>
                        <div class="ydc-reg-form-input">
                            <label>${student.s_class}</label>
                        </div>
                    </div>
                    <br>
                    <div class="ydc-reg-form-group clearfix">
                        <label>密码</label>
                        <div class="ydc-reg-form-input">
                            <input type="hidden" value="${student.s_password}" id="mypassword">
                            <div class="ydc-reg-form-button" id="former" style="margin-left:25px;">
                                <a class="btn fl" href="javascript:void(0);" id="beforechange">修改</a>
                            </div>
                            <div class="ydc-reg-form-group clearfix" style="display: none;" id="tips">
                                <label style="color: red;">请输入相关信息</label>
                            </div>
                        </div>
                    </div>
                    <div class="ydc-reg-form-group clearfix" id="changing" style="display:none;height: 30px;line-height: 30px;margin-left: 205px;margin-top: 20px;">
                        <span>请输入原密码：</span><input type="password" name="oldpwd" id="oldpwd">
                        <span>请输入新密码：</span><input type="password" name="newpwd" id="newpwd">
                        <button type="button" id="changepwd">修改</button>
                    </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--  消息提示窗-->
<div class="sy-alert sy-alert-alert animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="alert" sy-mask="true" id="alert2">
    <div class="sy-title">温馨提示</div>
    <div class="sy-content">原密码错误</div>
    <div class="sy-btn">
        <button onClick="formaterror('alert2')">确定</button>
    </div>
</div>
<div class="sy-alert sy-alert-alert animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="alert" sy-mask="true" id="alert3">
    <div class="sy-title">温馨提示</div>
    <div class="sy-content">修改成功！</div>
    <div class="sy-btn">
        <button onClick="ok('alert3')">确定</button>
    </div>
</div>
<div class="sy-alert sy-alert-alert animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="alert" sy-mask="true" id="alert4">
    <div class="sy-title">温馨提示</div>
    <div class="sy-content">修改失败！</div>
    <div class="sy-btn">
        <button onClick="ok('alert4')">确定</button>
    </div>
</div>
<div class="sy-alert sy-alert-alert animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="alert" sy-mask="true" id="alert5">
    <div class="sy-title">温馨提示</div>
    <div class="sy-content">服务器繁忙稍后再试！</div>
    <div class="sy-btn">
        <button onClick="formaterror('alert5')">确定</button>
    </div>
</div>
<div class="sy-alert sy-alert-alert animated" sy-enter="zoomIn" sy-leave="zoomOut" sy-type="alert" sy-mask="true" id="alert6">
    <div class="sy-title">温馨提示</div>
    <div class="sy-content">新密码为空或超过12个字符</div>
    <div class="sy-btn">
        <button onClick="formaterror('alert6')">确定</button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/syalert/syalert.min.js"></script>
<script>
    function ok(id){
        syalert.syhide(id);
        location.reload();
    }
    function formaterror(id){
        syalert.syhide(id);
    }
</script>
<script type="text/javascript">
    $(function(){
        $("#beforechange").click(function () {
            document.getElementById("former").style.display="none";
            document.getElementById("changing").style.display="block";
            document.getElementById("tips").style.display="block";
        });
        $("#changepwd").click(function () {
            var oldpwd=$("#oldpwd").val();
            var newpwd=$("#newpwd").val();
            var mypwd=$("#mypassword").val();
            if(oldpwd!==mypwd){
                syalert.syopen("alert2");
            }else{
            var password=$.trim(newpwd);
            if(null!=password&&""!==password&&password.length<=12){
                $.ajax({
                    url:"ChangePwdServlet",
                    type:"post",
                    dataType:"text",
                    data:{
                        id:${student.s_id},
                        pwd:password,
                        shenfen:"student"
                    },
                    success:function (flag) {
                        if(flag==="yes"){
                            syalert.syopen("alert3");
                        }else if(flag==="no"){
                            syalert.syopen("alert4");
                        }else if(flag==="busy"){
                            syalert.syopen("alert5");
                        }
                    },
                    error:function () {
                        alert("发送数据失败，请稍后再试！");
                        location.reload();
                    }
                });
            }else{
                syalert.syopen("alert6");
            }
            }
        });
    });
</script>
</body>
</html>