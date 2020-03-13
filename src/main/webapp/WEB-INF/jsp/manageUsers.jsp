<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 10.03.2020
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage users</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<p><a href="${pageContext.request.contextPath}/manageFormUsers">Add new User</a></p>
<table>
    <caption>Manage users</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>email</th>
        <th>solutions</th>
        <th>group id</th>
        <th>action</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.solutions}</td>
            <td>${user.userGroupId}</td>
            <td>Edit - Delete</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
