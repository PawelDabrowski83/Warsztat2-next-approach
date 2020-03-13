<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 11.03.2020
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage User Form</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<form action="${pageContext.request.contextPath}/manageFormUsers" method="post">
    Name: <input type="text" name="name"/><br/>
    Email: <input type="text" name="email"/><br/>
    Password: <input type="password" name="password"/><br/>
    <input type="submit" value="Save"/>
</form>



</body>
</html>
