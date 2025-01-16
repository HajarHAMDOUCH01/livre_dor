<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil - Livre d'Or</title>
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
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 2rem;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

		.ville-slider {
		    position: relative;
		    overflow: hidden;  /* This is crucial */
		    width: 100%;
		    margin-top: 2rem;
		}
		
		.ville-container {
		    display: flex;
		    transform: translateX(0);
		    transition: transform 0.3s ease-in-out;
		    /* Removed width: max-content */
		}

		
		.ville-card {
		    flex: 0 0 300px;
		    margin: 0 10px;
		    min-width: 300px;
		    box-sizing: border-box;  /* Include padding in width calculation */
		}

        .ville-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
        }

.ville-card .description {
    margin-top: 1rem;
    display: -webkit-box;
    -webkit-line-clamp: 2; /* Limit to 2 lines */
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis; /* Adds "..." when text is truncated */
}
.add-appreciation {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 1rem;
}

.add-appreciation:hover {
    background-color: #45a049;
}
        .arrow-button {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            font-size: 2rem;
            background: none;
            border: none;
            color: #4CAF50;
            cursor: pointer;
            z-index: 10;
        }

        .arrow-button-left {
            left: 10px;
        }

        .arrow-button-right {
            right: 10px;
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
    </style>
</head>
<body>

<header>
    <h1>Bienvenue sur le Livre d'Or</h1>
</header>

<main>
    <div class="container">
        <p>Partagez vos expériences et découvrez les appréciations sur différentes villes !</p>
        <a href="BasicVues/login.jsp" class="button">Se connecter</a>
        <a href="BasicVues/register.jsp" class="button">Créer un compte</a>
    </div>

<div class="ville-slider">
    <button class="arrow-button arrow-button-left">&#8592;</button>
    <div class="ville-container" id="villeContainer">
        <c:forEach var="ville" items="${villes}">
            <div class="ville-card">
                <img src="${ville.img}" alt="${ville.nom}">
                <h3>${ville.nom}</h3>
                <p class="description">${ville.description}</p>
                <a href="${pageContext.request.contextPath}/LivreDorServlet?id=${ville.id}&nom=${ville.nom}&description=${ville.description}&img=${ville.img}&action=viewVilleAppreciations" class="view-details">Voir détails</a>
            </div>
        </c:forEach>
    </div>
    <button class="arrow-button arrow-button-right">&#8594;</button>
</div>
</main>

<footer>
    <p>&copy; 2025 Livre d'Or. Tous droits réservés.</p>
</footer>

<script>
window.onload = function() {
    console.log("Slider script loaded");
    
    var container = document.getElementById('villeContainer');
    console.log("Container found:", container); 
    
    var position = 0;
    var slideAmount = document.querySelector('.ville-card').offsetWidth + 20; 
    
    window.moveSlide = function(direction) {
        console.log("moveSlide called with direction:", direction); 
        
        if (direction > 0) {
            position -= slideAmount;
            console.log("Moving right, new position:", position);
        } else {
            position += slideAmount;
            console.log("Moving left, new position:", position);
        }
        
        var totalWidth = container.scrollWidth;
        var visibleWidth = container.offsetWidth;
        console.log("Total width:", totalWidth, "Visible width:", visibleWidth);
        
        if (position > 0) {
            position = 0;
        }
        if (Math.abs(position) > totalWidth - visibleWidth) {
            position = -(totalWidth - visibleWidth);
        }
        
        container.style.transform = 'translateX(' + position + 'px)';
        console.log("Applied transform:", container.style.transform);
    };
    
    document.querySelector('.arrow-button-left').addEventListener('click', function() {
        moveSlide(-1);
    });
    
    document.querySelector('.arrow-button-right').addEventListener('click', function() {
        moveSlide(1);
    });
};

</script>

</body>
</html>
