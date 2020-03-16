<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 09.03.2020
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Group Details</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<table>
    <caption>Group Details</caption>
    <tr>
        <th>id</th>
        <th>group name</th>
        <th>action</th>
    </tr>
    <tr>
        <td>${group.id}</td>
        <td>${group.name}</td>
        <td>Edit Delete</td>
    </tr>
</table>

<table>
    <caption>Users of group - ${group.name}</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>email</th>
        <th></th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td><a href="${pageContext.request.contextPath}/user?id=${user.id}">Show details</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>