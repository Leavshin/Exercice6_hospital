<%--
  Created by IntelliJ IDEA.
  User: ceris
  Date: 16/07/2024
  Time: 09:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter une consultation</title>
</head>
<body>
<h1>Ajouter une consultation</h1>
<form action="${pageContext.request.contextPath}/consultation/add" method="post">
    <input type="hidden" name="patientId" value="${param.patientId}">
    <label for="doctorName">Nom du médecin:</label>
    <input type="text" id="doctorName" name="doctorName" required>
    <label for="consultationDate">Date de la consultation:</label>
    <input type="date" id="consultationDate" name="consultationDate" required>
    <button type="submit">Ajouter</button>
</form>
</body>
</html>

