<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 16.03.2020
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage UserGroup</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<form action="${pageContext.request.contextPath}/manageFormUserGroups?action=${action}" method="post">
    <input type="hidden" name="id" value="${userGroup.id}"/>
    Name: <input type="text" name="name" value="${userGroup.name}"/><br/>
    <input type="submit" value="Save"/>
</form>
</body>
</html>
