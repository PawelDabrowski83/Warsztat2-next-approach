<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 16.03.2020
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Exercises</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<form action="${pageContext.request.contextPath}/manageFormExercises?action=${action}" method="post">
    <input type="hidden" name="id" value="${exercise.id}"/>
    Title: <input type="text" name="title" value="${exercise.title}"/><br/>
    Description: <input type="text" name="description" value="${exercise.description}"/><br/>
    <input type="submit" value="Save Exercise"/>
</form>

</body>
</html>
