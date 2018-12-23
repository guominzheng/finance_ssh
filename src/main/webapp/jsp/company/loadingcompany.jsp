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
<body>

</body>
<script type="text/javascript">
    $(function () {
        window.location.href="<%=path%>/company/list.do"
    })
</script>
</html>
