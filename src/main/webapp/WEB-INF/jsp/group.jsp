<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 09.03.2020
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

</body>
</html>