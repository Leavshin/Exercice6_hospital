<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un patient</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Ajouter un patient</h1>
    <form action="${pageContext.request.contextPath}/patient/add" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="nom" class="form-label">Nom :</label>
            <input type="text" class="form-control" id="nom" name="nom" required>
        </div>
        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom :</label>
            <input type="text" class="form-control" id="prenom" name="prenom" required>
        </div>
        <div class="mb-3">
            <label for="dateNaissance" class="form-label">Date de naissance :</label>
            <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" required>
        </div>
        <div class="mb-3">
            <label for="photo" class="form-label">Photo :</label>
            <input type="file" class="form-control" id="photo" name="photo" required>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
</div>
</body>
</html>
