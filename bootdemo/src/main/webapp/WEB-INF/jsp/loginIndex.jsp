<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/talkPage/login">
    用户ID：<input type="text" name="playerIndex">
    <input type="submit">
</form>
</body>
</html>
