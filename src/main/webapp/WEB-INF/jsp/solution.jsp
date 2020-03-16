<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 09.03.2020
  Time: 08:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solution Details</title>
    <jsp:include page="/WEB-INF/jsp/styles.jsp"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<table>
    <caption>Solution no. ${solution.id}</caption>
    <tr>
        <th>created</th>
        <th>description</th>
        <th>user id</th>
        <th>exercise id</th>
    </tr>
    <tr>
        <td>${solution.created}</td>
        <td>${solution.description}</td>
        <td>${solution.usersId}</td>
        <td>${solution.exerciseId}</td>
    </tr>
</table>

</body>
</html>
