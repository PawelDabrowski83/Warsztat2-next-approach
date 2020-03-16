<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 10.03.2020
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User details</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<p><b>Name: </b>${user.name}</p>
<p><b>Email: </b>${user.email}</p>

<table>
    <caption>Solutions by User id</caption>
    <tr>
        <th>id</th>
        <th>created</th>
        <th>description</th>
        <th>exercise id</th>
        <th>actions</th>
    </tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.description}</td>
            <td>${solution.exerciseId}</td>
            <td><a href="${pageContext.request.contextPath}/solution?id=${solution.id}">Solution details</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
