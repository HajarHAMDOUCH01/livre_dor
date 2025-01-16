<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription - Livre d'Or</title>
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
            max-width: 400px;
            margin: auto;
            background: white;
            padding: 2rem;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        .button:hover {
            background-color: #45a049;
        }

        .link {
            display: block;
            margin-top: 1rem;
            color: #4CAF50;
            text-decoration: none;
        }

        .link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<header>
    <h1>Inscription</h1>
</header>

<main>
    <div class="container">
	<form action="${pageContext.request.contextPath}/LivreDorServlet" method="post">
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
            </div>
            <input type="hidden" id="action" name="action" value="register">
            <div class="form-group">
                <label for="email">Adresse Email :</label>
                <input type="text" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="mot_de_passe">Mot de passe :</label>
                <input type="password" id="mot_de_passe" name="mot_de_passe" required>
            </div>

            <button type="submit" class="button">S'inscrire</button>
        </form>

        <a href="login.jsp" class="link">Déjà un compte ? Se connecter</a>
    </div>
</main>

<footer>
    <p>&copy; 2025 Livre d'Or. Tous droits réservés.</p>
</footer>

</body>
</html>
