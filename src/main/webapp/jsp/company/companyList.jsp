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
        animation:myfirst 2s;
        animation-fill-mode : forwards;
        -moz-animation:myfirst 2s; /* Firefox */
        -moz-animation-fill-mode : forwards;
        -webkit-animation:myfirst 2s; /* Safari and Chrome */
        -webkit-animation-fill-mode : forwards;
        -o-animation:myfirst 2s; /* Opera */
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

    .test{
        opacity:0;
        position: absolute;
        left: 50px;
        animation:myfirst1 1s;
        animation-fill-mode : forwards;
        -moz-animation:myfirst1 1s; /* Firefox */
        -moz-animation-fill-mode : forwards;
        -webkit-animation:myfirst1 1s; /* Safari and Chrome */
        -webkit-animation-fill-mode : forwards;
        -o-animation:myfirst1 1s; /* Opera */
        -o-animation-fill-mode : forwards;
    }
    #adsds{
        background-color: black;
        z-index: 10;
        opacity: 0;
        width: 300px;
        height: 300px;
        border: 1px solid black;
        position: absolute;
        left: 600px;
    }
    @keyframes myfirst1
    {
        0%   {opacity:0; top:0px;}
        100%   {opacity:1; top:100px;}
    }

    @-moz-keyframes myfirst1 /* Firefox */
    {
        0%   {opacity:0; top:0px;}
        100%   {opacity:1; top:100px;}
    }

    @-webkit-keyframes myfirst1 /* Safari and Chrome */
    {
        0%   {opacity:0; top:0px;}
        100%   {opacity:1; top:100px;}
    }

    @-o-keyframes myfirst1 /* Opera */
    {
        0%   {opacity:0; top:0px;}
        100%   {opacity:1; top:100px;}
    }
</style>
<body>
<div id="adsds" onclick="hide()"></div>
<div class="_table_class" style="margin-left: 10px;">
    <div class="page-header" style="margin: 0 ">
        <h3>企业
            <small>列表</small>
        </h3>
    </div>
    <div><input type="button" value="按钮" onclick="add()"/></div>
    <table class="table table-hover" style="width: 1700px; border-radius: 5%">
        <tr class="success">
            <th>排序</th>
            <th>企业名称</th>
            <th>法人</th>
            <th>地址</th>
            <th>联系方式</th>
            <th>上次交款</th>
            <th>到期时间</th>
            <th>公司类型</th>
            <th>每月金额</th>
            <th>创建时间</th>
            <th>修改时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${varList}" var="var" varStatus="vs">
            <tr class="warning">
                <td>${vs.index+1}</td>
                <td>${var.name}</td>
                <td>${var.legal.lname}</td>
                <td>${var.address}</td>
                <td>${var.phone}</td>
                <td>${var.charTerz}</td>
                <td>${var.charTerf}</td>
                <td>${var.scale}</td>
                <td>${var.money}</td>
                <td>${var.createTime}</td>
                <td>${var.updateTime}</td>
                <td>操作</td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
<script type="text/javascript">


    function add() {
        $("#adsds").addClass("test");
    }
    function hide() {
        $("#adsds").removeClass("test");
    }
</script>

</html>
