<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Appréciation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }

        header {
            background-color: #4CAF50;
            color: white;
            padding: 1rem 0;
            text-align: center;
        }

        main {
            padding: 2rem;
            text-align: center;
        }

        footer {
            background-color: #333;
            color: white;
            padding: 1rem 0;
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 2rem;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 1.5rem;
            text-align: left;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 0.5rem;
        }

        .form-group input, .form-group textarea, .form-group select {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1rem;
        }

        .form-group textarea {
            resize: vertical;
            height: 150px;
        }

        .button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 0.75rem 1.5rem;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
        }

        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<header>
    <h1>Ajouter une Appréciation</h1>
</header>

<main>
    <div class="container">
        <form action="addAppreciationServlet" method="post">
            <div class="form-group">
                <label for="contenu">Contenu de l'appréciation :</label>
                <textarea id="contenu" name="contenu" required></textarea>
            </div>

            <button type="submit" class="button">Ajouter</button>
        </form>
    </div>
</main>

<footer>
    <p>&copy; 2025 Livre d'Or. Tous droits réservés.</p>
</footer>

</body>
</html>
