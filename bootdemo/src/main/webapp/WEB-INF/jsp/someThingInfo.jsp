<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>东西</title>

    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <script>
        var i = 0;
        var addStudents = [];
        function addOne() {
            var id = $("#stuId").val();
            var name = $("#stuName").val();
            var stForm = $("#studentInfo");
            var stInputId = $("<input type='text'/>");
            stInputId.attr("value",id);
            var stInputName = $("<input type='text'/>");
            stInputName.attr("value",name);
            stForm.append(stInputId);
            stForm.append(stInputName);

            var student = new Object();
            student.id = id;
            student.name = name;
            addStudents.push(student);

            i++;
        }
        
        function submitFn() {
            var subForm = $("#stForm");
            var json = JSON.stringify(addStudents);
            var addInput = $("<input name='addStudents' type='hidden'/>");
            addInput.attr("value",json);
            subForm.append(addInput);
            subForm.submit();
        }
    </script>
</head>
<body>

<form:form modelAttribute="someThing" action="${pageContext.request.contextPath}/hello/getSomeThing" method="post" id="stForm">
    <form:input path="id" ></form:input>
    <form:input path="name" ></form:input>
    <form:input path="name2" ></form:input>
    <br/>
    <c:forEach items="${students}" var="student" varStatus="i">
        <form:input path="students[${i.index}].id"></form:input>
        <form:input path="students[${i.index}].name"></form:input>
        <form:input path="students[${i.index}].birthday"></form:input>
    </c:forEach>
    <br/>
    <div id="studentInfo">

    </div>
    <input type="text" id="stuId">
    <input type="text" id="stuName">
    <%--<form:input path="students[${2}].birthday"></form:input>--%>
    <input type="button" value="增加" onclick="addOne()">
    <input type="button" value="提交" onclick="submitFn()">
</form:form>
</body>
</html>
