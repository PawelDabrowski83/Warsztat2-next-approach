<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 10.03.2020
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage UserGroups</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<table>
    <caption>Manage UserGroups</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>action</th>
    </tr>
    <c:forEach items="${userGroups}" var="userGroup">
        <tr>
            <td>${userGroup.id}</td>
            <td>${userGroup.name}</td>
            <td>Edit - Delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
