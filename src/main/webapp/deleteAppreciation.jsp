<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation de Suppression</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            margin: 50px auto;
            padding: 20px;
            max-width: 500px;
            background: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #333;
        }
        p {
            margin: 20px 0;
        }
        .button-group {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            color: white;
        }
        .button-confirm {
            background-color: #e74c3c;
        }
        .button-cancel {
            background-color: #3498db;
        }
        .button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Confirmer la Suppression</h1>
    <%
        String appreciationId = request.getParameter("appreciationId");
    %>
    <p>Voulez-vous vraiment supprimer l'appréciation avec l'ID <strong><%= appreciationId %></strong> ?</p>

    <div class="button-group">
        <!-- Bouton pour confirmer la suppression -->
        <form action="/jakartaee-livre-dor/LivreDorServlet" method="post" style="display: inline;">
            <input type="hidden" name="action" value="dropAppreciationById">
            <input type="hidden" name="appreciationId" value="<%= request.getParameter("appreciationId") %>">
            <button type="submit" class="button button-confirm">Confirmer</button>
        </form>

        <!-- Bouton pour annuler la suppression -->
        <a href="villeAppreciations.jsp" class="button button-cancel">Annuler</a>
    </div>
</div>

</body>
</html>
