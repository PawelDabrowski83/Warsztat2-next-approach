<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 05.03.2020
  Time: 09:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main view</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<p>Hello world</p>

<table border="1" cellpadding="5">
    <caption>Recent solutions</caption>
    <tr>
        <th>id</th>
        <th>created</th>
        <th>updated</th>
        <th>description</th>
        <th>user id</th>
        <th>exercise id</th>
    </tr>
    <c:forEach items="${requestScope.recentSolutions}" var="solution">
        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${solution.description}</td>
            <td>${solution.usersId}</td>
            <td>${solution.exerciseId}</td>
        </tr>
    </c:forEach>
</table>



</body>
</html>
