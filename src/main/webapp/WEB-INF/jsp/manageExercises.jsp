<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 10.03.2020
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage Exercises</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<p><a href="${pageContext.request.contextPath}/manageFormExercises?action=new">Add new Exercise</a></p>

<table>
    <caption>Manage Exercises</caption>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>actions</th>
    </tr>
    <c:forEach items="${exercises}" var="exercise">
        <tr>
            <td>${exercise.id}</td>
            <td>${exercise.title}</td>
            <td>${exercise.description}</td>
            <td><a href="${pageContext.request.contextPath}/manageFormExercises?action=edit&id=${exercise.id}">Edit</a> - <a href="${pageContext.request.contextPath}/manageFormExercises?action=delete&id=${exercise.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
