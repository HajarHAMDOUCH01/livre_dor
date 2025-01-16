<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="livredor.eclipse.dao.DBDao" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appréciations des Villes</title>
    <link rel="stylesheet" href="style.css"> <!-- Ajoutez votre fichier de styles ici -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .auteur-ville {
            color: #333;
            font-weight: bold;
        }
        .contenu {
            font-style: italic;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Appréciations de la ville ${nom}</h1>
        <table>
            <thead>
                <tr>
                    <th>Auteur</th>
                    <th>Ville</th>
                    <th>Appréciation</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appreciation" items="${appreciations}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${appreciation.auteurId != null}">
                                    <%-- Assuming selectAuteurById returns the author details --%>
                                    <c:set var="auteur" value="${DBDao.selectAuteurById(appreciation.auteurId)}" />
                                    <span class="auteur-ville">${auteur.nom}</span>
                                </c:when>
                                <c:otherwise>Inconnu</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${appreciation.villeId != null}">
                                    <%-- Assuming selectVilleById returns the city details --%>
                                    <c:set var="ville" value="${DBDao.selectVilleById(appreciation.villeId)}" />
                                    <span class="auteur-ville">${ville.nom}</span>
                                </c:when>
                                <c:otherwise>Inconnue</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="contenu">${appreciation.contenu}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
