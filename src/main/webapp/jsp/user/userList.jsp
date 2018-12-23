<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        background-image: url("<%=path%>/resources/img/timg1.jpg");
    }
    ._table_class{
        opacity:0;
        position: absolute;
        left: 50px;
        animation:myfirst 1s;
        animation-fill-mode : forwards;
        -moz-animation:myfirst 1s; /* Firefox */
        -moz-animation-fill-mode : forwards;
        -webkit-animation:myfirst 1s; /* Safari and Chrome */
        -webkit-animation-fill-mode : forwards;
        -o-animation:myfirst 1s; /* Opera */
        -o-animation-fill-mode : forwards;
    }

    @keyframes myfirst
    {
        0%   {opacity:0; left:50px;}
        100%   {opacity:1; left:0px;}
    }

    @-moz-keyframes myfirst /* Firefox */
    {
        0%   {opacity:0; left:50px;}
        100%   {opacity:1; left:0px;}
    }

    @-webkit-keyframes myfirst /* Safari and Chrome */
    {
        0%   {opacity:0; left:50px;}
        100%   {opacity:1; left:0px;}
    }

    @-o-keyframes myfirst /* Opera */
    {
        0%   {opacity:0; left:50px;}
        100%   {opacity:1; left:0px;}
    }

</style>
<body>

<div class="_table_class" style="margin-left: 10px;">
    <div class="page-header" style="margin: 0 ">
        <h3>用户
            <small>列表</small>
        </h3>
    </div>
    <table class="table table-hover" style="width: 1700px; border-radius: 5%">
        <tr class="success">
            <th>姓名</th>
            <th>标题2</th>
            <th>标题3</th>
            <th>标题3</th>
            <th>标题3</th>
            <th>标题3</th>
            <th>标题3</th>
            <th>标题3</th>
            <th>标题3</th>
            <th>标题3</th>
        </tr>
        <c:forEach items="${varList}" var="var" varStatus="vs">
            <tr class="warning">
                <td>${var.username}</td>
                <td>内容1</td>
                <td>内容1</td>
                <td>内容1</td>
                <td>内容1</td>
                <td>内容1</td>
                <td>内容1</td>
                <td>内容1</td>
                <td>内容1</td>
                <td>内容1</td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
