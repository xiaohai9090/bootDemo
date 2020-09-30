<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>

<form action="${pageContext.request.contextPath}/hello/input" method="post">
    <input type="checkbox" name="clearType" value="1">AA
    <input type="checkbox" name="clearType" value="2">BB
    <input type="checkbox" name="clearType" value="3">CC
    <input type="checkbox" name="clearType" value="4">DD

    <input type="submit" value="提交">
</form>
</body>
</html>
