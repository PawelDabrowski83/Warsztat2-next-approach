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
        <li><a href="${pageContext.request.contextPath}/manageExercises">exercises</a></li>
        <li><a href="${pageContext.request.contextPath}/manageSolutions">solutions</a></li>
        <li><a href="${pageContext.request.contextPath}/manageUsers">users</a></li>
        <li><a href="${pageContext.request.contextPath}/manageUserGroups">usergroups</a></li>
    </ul>

</body>
</html>
