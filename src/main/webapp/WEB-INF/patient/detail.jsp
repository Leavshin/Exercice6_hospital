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
    <title>Détail du patient</title>
</head>
<body>
<h1>Détail du patient</h1>
<p>Nom: ${patient.firstName} ${patient.lastName}</p>
<p>Date de naissance: ${patient.birthDate}</p>
<img src="${patient.photoPath}" alt="Photo de ${patient.firstName}">
<h2>Consultations</h2>
<ul>
    <c:forEach var="consultation" items="${consultations}">
        <li><a href="${pageContext.request.contextPath}/consultation/detail?id=${consultation.id}">${consultation.date}</a></li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/consultation/add?patientId=${patient.id}">Ajouter une consultation</a>
</body>
</html>
