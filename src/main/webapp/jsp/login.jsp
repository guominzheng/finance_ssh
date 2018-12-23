<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/static/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css/bootstrap-theme.css"/>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    body {
        background:-moz-linear-gradient(left,#ace,#f96);/*Mozilla*/
        background:-webkit-gradient(linear,0 50%,100% 50%,from(#ace),to(#f96));/*Old gradient for webkit*/
        background:-webkit-linear-gradient(left,#ace,#f96);/*new gradient for Webkit*/
        background:-o-linear-gradient(left,#ace,#f96); /*Opera11*/
    }
    .father_div {
        background:white;
        border: 1px solid #2aabd2;
        box-shadow:0px 0px 10px whitesmoke;
        border-radius: 3%;
        width: 500px;
        height: 350px;
        margin-left:700px;
        margin-top: 200px;
    }
</style>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img alt="应龙万事屋" src="...">
            </a>
        </div>
    </div>
</nav>
<div class="father_div">
    <div style="margin-top: 30px;">
        <h3><p class="text-center">应龙万事屋</p></h3>
    </div>
    <form class="form-horizontal" style="margin-top: 50px;">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail3" placeholder="账号" style="width: 300px;">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword3" placeholder="密码" style="width: 300px;">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> 记住我
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button style="margin-left: 30px;" type="button" class="btn btn-success">登录</button>
                <button style="margin-left: 100px;" type="reset" class="btn btn-danger">重置</button>
            </div>
        </div>
    </form>
</div>
<div>

</div>
</body>
<script type="text/javascript">

</script>
</html>
