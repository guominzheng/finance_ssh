<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/static/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-3.3.1.js"></script>
</head>
<body>
<h2>Hello World!1231</h2>
</body>
<script  type="text/javascript">
    $(function () {
        window.location.href="<%=path%>/jsp/login.jsp";
    });
</script>
</html>
