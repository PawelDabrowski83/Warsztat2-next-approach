<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 05.03.2020
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<p>manage:</p>
    <ul>
        <li>exercises</li>
        <li>solutions</li>
        <li><a href="${pageContext.request.contextPath}/manageUsers">users</a></li>
        <li>usergroups</li>
    </ul>

</body>
</html>
