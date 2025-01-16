package livredor.eclipse.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import livredor.eclipse.dao.DBDao;
import livredor.eclipse.models.*;

@WebServlet("/LivreDorServlet")
public class LivreDorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void init() {
    	Connection con = DBDao.getConnection();
    }

    public LivreDorServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                viewAllCities(request, response);
                return;
            }

            switch (action) {
                case "addAppreciation":
                    if (!isAuthenticated(request)) {
                        response.sendRedirect("BasicVues/login.jsp");
                        return;
                    }
                    request.getRequestDispatcher("addAppreciation.jsp").forward(request, response);
                    break;
                case "modifyAppreciation":
                    request.getRequestDispatcher("modifyAppreciation.jsp").forward(request, response);
                    break;
                case "deleteAppreciation":
                    request.getRequestDispatcher("deleteAppreciation.jsp").forward(request, response);
                    break;
                case "insertAppreciation":
                    handleInsertAppreciation(request, response);
                    break;
                case "updateAppreciation":
                    handleUpdateAppreciation(request, response);
                    break;
                case "dropAppreciationById":
                    handleDeleteAppreciation(request, response);
                    break;
                case "viewVilleAppreciations":
                	viewVilleAppreciations(request, response);
                    break;
                case "register":
                    handleAddAuteur(request, response);
                    break;
                case "login":
                    handleLoginAuteur(request, response);
                    break;
                case "modifyProfile":
                    handleUpdateAuteur(request, response);
                    break;
                case "dropProfile":
                    handleDeleteAuteur(request, response);
                    break;
                default:
                    viewAllCities(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("BasicVues/errorPage.jsp").forward(request, response);
        }
    }

    private void handleInsertAppreciation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        insertAppreciation(request);
        response.sendRedirect("LivreDorServlet?action=viewAllAppreciations");
    }

    private void handleUpdateAppreciation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        updateAppreciation(request);
        response.sendRedirect("LivreDorServlet?action=viewAllAppreciations");
    }

    private void handleDeleteAppreciation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        dropAppreciation(request);
        response.sendRedirect("LivreDorServlet?action=viewAllAppreciations");
    }

    private void handleAddAuteur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        insertAuteur(request);
        response.sendRedirect("BasicVues/login.jsp");
    }

    private void handleLoginAuteur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        loginAuteur(request, response);
    }

    private void handleUpdateAuteur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        updateAuteur(request);
        response.sendRedirect("LivreDorServlet?action=viewProfile");
    }

    private void handleDeleteAuteur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        dropAuteur(request);
        response.sendRedirect("LivreDorServlet?action=logout");
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("user") != null;
    }

    private void insertAuteur(HttpServletRequest request) throws SQLException {
        String name = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("mot_de_passe");

        Auteur auteur = new Auteur();
        auteur.setNom(name);
        auteur.setEmail(email);
        auteur.setMotDePasse(password);

        DBDao.insertAuteur(auteur);
    }

    private void loginAuteur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("mot_de_passe");

        ArrayList<Auteur> auteurs = (ArrayList<Auteur>) DBDao.selectAllAuteurs();
        for (Auteur auteur : auteurs) {
            if (auteur.getEmail().equals(email) && auteur.checkMotDePasse(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", auteur);
                response.sendRedirect("BasicVues/index.jsp");
                return;
            }
        }

        response.sendRedirect("BasicVues/login.jsp?error=Invalid credentials");
    }

    private void updateAuteur(HttpServletRequest request) throws SQLException {
        int id = ((Auteur) request.getSession().getAttribute("user")).getId();
        String name = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("mot_de_passe");

        Auteur auteur = new Auteur();
        auteur.setId(id);
        auteur.setNom(name);
        auteur.setEmail(email);
        auteur.setMotDePasse(password);

        DBDao.updateAuteur(auteur);
    }

    private void dropAuteur(HttpServletRequest request) throws SQLException {
        int id = ((Auteur) request.getSession().getAttribute("user")).getId();
        DBDao.deleteAuteur(id);
        request.getSession().invalidate();
    }

    private void insertAppreciation(HttpServletRequest request) throws SQLException {
        String content = request.getParameter("content");
        int authorId = ((Auteur) request.getSession().getAttribute("user")).getId();
        int cityId = Integer.parseInt(request.getParameter("cityId"));

        Appreciation appreciation = new Appreciation();
        appreciation.setContenu(content);
        appreciation.setAuteurId(authorId);
        appreciation.setVilleId(cityId);

        DBDao.insertAppreciation(appreciation);
    }

    private void updateAppreciation(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");

        Appreciation appreciation = new Appreciation();
        appreciation.setId(id);
        appreciation.setContenu(content);

        DBDao.updateAppreciation(appreciation);
    }

    private void dropAppreciation(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        DBDao.deleteAppreciation(id);
    }

    private void viewVilleAppreciations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	int villeId = Integer.parseInt(request.getParameter("id"));
    	request.setAttribute("nom", request.getParameter("nom"));
        ArrayList<Appreciation> appreciations = (ArrayList<Appreciation>) DBDao.selectAppreciationsByVille(villeId);
        request.setAttribute("appreciations", appreciations);
        request.getRequestDispatcher("villeAppreciation.jsp").forward(request, response);
    }

    private void viewAllCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ArrayList<Ville> cities = (ArrayList<Ville>) DBDao.selectAllVilles();
        request.setAttribute("villes", cities);
        request.getRequestDispatcher("BasicVues/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
