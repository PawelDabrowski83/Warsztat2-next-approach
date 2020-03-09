<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 05.03.2020
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Group</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<table>
    <caption>Group Listing</caption>
    <tr>
        <th>id</th>
        <th>group name</th>
        <th>action</th>
    </tr>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td>${group.id}</td>
            <td>${group.name}</td>
            <td><a href="${pageContext.request.contextPath}/group?id="${group.id}>Show details</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
