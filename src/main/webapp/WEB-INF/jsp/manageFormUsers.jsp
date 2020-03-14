<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 11.03.2020
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage User Form</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<form action="${pageContext.request.contextPath}/manageFormUsers?action=${action}" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    Name: <input type="text" name="name" value="${user.name}"/><br/>
    Email: <input type="text" name="email" value="${user.email}"/><br/>
    Password: <input type="password" name="password"/><br/>
    <input type="submit" value="Save"/>
</form>



</body>
</html>
