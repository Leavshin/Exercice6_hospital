<%--
  Created by IntelliJ IDEA.
  User: ceris
  Date: 16/07/2024
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des patients</title>
</head>
<body>
<h1>Liste des patients</h1>
<form action="${pageContext.request.contextPath}/patients" method="get">
    <label for="search">Search:</label>
    <input type="text" id="search" name="search">
    <button type="submit">Search</button>
</form>
<ul>
    <c:forEach var="patient" items="${patients}">
        <li><a href="${pageContext.request.contextPath}/patient/detail?id=${patient.id}">${patient.firstName} ${patient.lastName}</a></li>
    </c:forEach>
</ul>
</body>
</html>
