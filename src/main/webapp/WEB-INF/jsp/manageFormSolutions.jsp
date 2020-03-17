<%--
  Created by IntelliJ IDEA.
  User: paweld
  Date: 17.03.2020
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage Solutions</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<form action="${pageContext.request.contextPath}/manageFormSolutions?action=${action}" method="post">
    <input type="hidden" name="id" value="${solution.id}"/>
    <input type="hidden" name="created" value="${solution.created}"/>
    <input type="hidden" name="updated" value="${solution.updated}"/>
    Description: <input type="text" name="description" value="${solution.description}"/><br/>

    <label>User:
        <select name="userId">
            <optgroup label="current">
                <option value="${solution.usersId}" name="userId">${solution.userName}</option>
            </optgroup>
            <optgroup label="available">
            <c:forEach items="${users}" var="user">
                <option value="${user.id}" name="userId">${user.name}</option>
            </c:forEach>
            </optgroup>
        </select>
    </label>
    <label>Exercise:
        <select name="exerciseId">
            <optgroup label="current">
                <option value="${solution.exerciseId}" name="exerciseId">${solution.exerciseTitle}</option>
            </optgroup>
            <optgroup label="available">
            <c:forEach items="${exercises}" var="exercise">
                <option value="${exercise.id}" name="exerciseId">${exercise.title}</option>
            </c:forEach>
            </optgroup>
        </select>
    </label>
    <input type="submit" value="Save Solution"/>
</form>

</body>
</html>
