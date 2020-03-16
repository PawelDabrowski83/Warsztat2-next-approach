<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 10.03.2020
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage Solutions</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<table>
    <caption>Manage Solutions</caption>
    <tr>
        <th>id</th>
        <th>created</th>
        <th>updated</th>
        <th>description</th>
        <th>user id</th>
        <th>exercise id</th>
        <th>actions</th>
    </tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${solution.description}</td>
            <td>${solution.usersId}</td>
            <td>${solution.exerciseId}</td>
            <td>Edit - Delete</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
