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
    <title>应龙主页</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css/bootstrap-theme.css"/>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    .navbar-collapse {
        padding-left: 5px;
        padding-right: 5px;
    }

    .nav > li {
        text-align: center;
    }

    .nav > li > a {
        color: #444;
        margin: 0 5px;
    }

    .nav-pills > li.active > a, .nav-pills > li.active > a:focus, .nav-pills > li.active > a:hover {
        background-color: #222222;
    }

    .dropdown-menu {
        min-width: 200px;
        margin-left: 40px;
        background-color: #E3E3E3;
    }

    .dropdown-menu > li > a {
        padding: 10px 15px;
    }

    .pageSidebar {
        width: 200px;
        height: 100%;
        padding-bottom: 30px;
        overflow: auto;
        background-color: white;
        border-right: 1px solid gainsboro;
    }

    .splitter {
        width: 5px;
        height: 100%;
        bottom: 0;
        left: 240px;
        position: absolute;
        overflow: hidden;
        background-color: #fff;
    }

    .pageContent {
        height: 100%;
        min-width: 768px;
        left: 200px;
        top: 0;
        right: 0;
        z-index: 3;
        padding-bottom: 30px;
        position: absolute;
    }

    .pageContainer {
        bottom: 0;
        left: 0;
        right: 0;
        top: 53px;
        overflow: auto;
        position: absolute;
        width: 100%;
    }

    .footer {
        width: 100%;
        height: 30px;
        line-height: 30px;
        margin-top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        position: absolute;
        z-index: 10;
        background-color: #2b669a;
    }

    body {
        width: 100%;
        height: 100%;
        margin: 0;
        overflow: hidden;
        background-color: white;
        font-family: "Microsoft YaHei", sans-serif;
    }
    ul li{
        border-bottom: 1px solid gainsboro;
        box-shadow: 0px 0px 5px white;
        border-radius: 2%;
    }
</style>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid" style="background-color: #2b669a">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="<%=path%>/resources/img/yl.jpg" alt="" class="img-circle" width="30px;">
            </a>
            <p class="navbar-text" style="margin: 0px; margin-top: 18px; color: white">应龙万事屋  (YL Everything House)</p>

        </div>
        <p class="navbar-text navbar-right" style="color: white;padding-right: 30px;">Active User &nbsp;&nbsp; 赵真</p>
    </div>
</nav>
<div class="pageContainer"> <!-- 左侧导航栏 -->
    <div class="pageSidebar" style="background-image: url('<%=path%>/resources/img/timg.jpg')">
        <ul class="nav nav-stacked nav-pills" style="background-color: white" >
            <li role="presentation"><a href="<%=path%>/jsp/user/loadinguser.jsp" target="mainFrame">首页</a></li>
            <li role="presentation"><a href="<%=path%>/jsp/user/loadinguser.jsp" target="mainFrame">用户列表</a></li>
            <li role="presentation"><a href="<%=path%>/jsp/company/loadingcompany.jsp" target="mainFrame">企业列表</a></li>
            <li role="presentation"><a href="nav2.html" target="mainFrame">收入账单</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> 导航链接4<span
                    class="caret"></span> </a>
                <ul class="dropdown-menu">
                    <li><a href="nav1.html" target="mainFrame">导航链接4-1</a></li>
                    <li><a href="nav2.html" target="mainFrame">导航链接4-2</a></li>
                    <li><a href="nav3.html" target="mainFrame">导航链接4-3</a></li>
                </ul>
            </li>
        </ul>
    </div> <!-- 左侧导航和正文内容的分隔线 -->
    <div class="splitter"></div> <!-- 正文内容部分 -->
    <div class="pageContent">
        <iframe src="<%=path%>/jsp/login.jsp" id="mainFrame" name="mainFrame" frameborder="0" width="100%;" height="100%"
                frameBorder="0"></iframe>
    </div>
</div> <!-- 底部页脚部分 -->
<div class="footer"><p class="text-center"> 2017 &copy; NeoYang. </p></div>
</body>
<script type="text/javascript">

</script>
</html>
