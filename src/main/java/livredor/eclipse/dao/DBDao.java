package livredor.eclipse.dao;

import livredor.eclipse.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDao {
    private static Connection con = null;
    
    public static Connection getConnection() {
    	if(con == null) {
    		try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/LivreDor", "root", "123hajar");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	}
		return con;
    }
    
    
    // Requêtes pour la table Auteur
    private static final String INSERT_AUTEUR = "INSERT INTO auteur (nom, email, mot_de_passe) VALUES (?, ?, ?)";
    private static final String UPDATE_AUTEUR = "UPDATE auteur SET nom = ?, email = ?, mot_de_passe = ? WHERE id = ?";
    private static final String DELETE_AUTEUR = "DELETE FROM auteur WHERE id = ?";
    private static final String SELECT_AUTEUR_BY_ID = "SELECT * FROM auteur WHERE id = ?";
    private static final String SELECT_ALL_AUTEURS = "SELECT * FROM auteur";

    // Requêtes pour la table Appreciation
    private static final String INSERT_APPRECIATION = "INSERT INTO appreciation (contenu, date_publication, auteur_id, ville_id) VALUES (?, NOW(), ?, ?)";
    private static final String UPDATE_APPRECIATION = "UPDATE appreciation SET contenu = ?, date_publication = NOW() WHERE id = ?";
    private static final String DELETE_APPRECIATION = "DELETE FROM appreciation WHERE id = ?";
    private static final String SELECT_APPRECIATION_BY_ID = "SELECT * FROM appreciation WHERE id = ?";
    private static final String SELECT_ALL_APPRECIATIONS = "SELECT * FROM appreciation";
    private static final String SELECT_APPRECIATIONS_BY_VILLE = "SELECT * FROM APPRECIATION WHERE ville_id = ?";

    // Requêtes pour la table Ville
    private static final String INSERT_VILLE = "INSERT INTO ville (nom, img, description) VALUES (?, ?, ?)";
    private static final String UPDATE_VILLE = "UPDATE ville SET nom = ?, img = ?, description = ? WHERE id = ?";
    private static final String DELETE_VILLE = "DELETE FROM ville WHERE id = ?";
    private static final String SELECT_VILLE_BY_ID = "SELECT * FROM ville WHERE id = ?";
    private static final String SELECT_ALL_VILLES = "SELECT * FROM ville";
    

    public DBDao(Connection con) {
        this.con = con;
    }

    // Gestion des auteurs
    public static void insertAuteur(Auteur auteur) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(INSERT_AUTEUR)) {
            ps.setString(1, auteur.getNom());
            ps.setString(2, auteur.getEmail());
            ps.setString(3, auteur.getMotDePasse());
            ps.executeUpdate();
        }
    }

    public static void updateAuteur(Auteur auteur) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(UPDATE_AUTEUR)) {
            ps.setString(1, auteur.getNom());
            ps.setString(2, auteur.getEmail());
            ps.setString(3, auteur.getMotDePasse());
            ps.setInt(4, auteur.getId());
            ps.executeUpdate();
        }
    }

    public static void deleteAuteur(int id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(DELETE_AUTEUR)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static Auteur selectAuteurById(int id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SELECT_AUTEUR_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Auteur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("mot_de_passe")
                );
            }
        }
        return null;
    }

    public static List<Auteur> selectAllAuteurs() throws SQLException {
        List<Auteur> auteurs = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(SELECT_ALL_AUTEURS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                auteurs.add(new Auteur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("mot_de_passe")
                ));
            }
        }
        return auteurs;
    }

    // Gestion des appréciations
    public static void insertAppreciation(Appreciation appreciation) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(INSERT_APPRECIATION)) {
            ps.setString(1, appreciation.getContenu());
            ps.setInt(2, appreciation.getAuteurId());
            ps.setInt(3, appreciation.getVilleId());
            ps.executeUpdate();
        }
    }

    public static void updateAppreciation(Appreciation appreciation) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(UPDATE_APPRECIATION)) {
            ps.setString(1, appreciation.getContenu());
            ps.setInt(2, appreciation.getId());
            ps.executeUpdate();
        }
    }

    public static void deleteAppreciation(int id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(DELETE_APPRECIATION)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static Appreciation selectAppreciationById(int id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SELECT_APPRECIATION_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Appreciation(
                    rs.getInt("id"),
                    rs.getString("contenu"),
                    rs.getTimestamp("date_publication").toLocalDateTime(),
                    rs.getInt("auteur_id"),
                    rs.getInt("ville_id")
                );
            }
        }
        return null;
    }

    public static List<Appreciation> selectAllAppreciations() throws SQLException {
        List<Appreciation> appreciations = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(SELECT_ALL_APPRECIATIONS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                appreciations.add(new Appreciation(
                    rs.getInt("id"),
                    rs.getString("contenu"),
                    rs.getTimestamp("date_publication").toLocalDateTime(),
                    rs.getInt("auteur_id"),
                    rs.getInt("ville_id")
                ));
            }
        }
        return appreciations;
    }
    
    public static List<Appreciation> selectAppreciationsByVille(int villeId) throws SQLException {
    	List<Appreciation> villeAppreciations = new ArrayList<>();
    	try(PreparedStatement ps = con.prepareStatement(SELECT_APPRECIATIONS_BY_VILLE);){
    		ps.setInt(1, villeId);
    		try(ResultSet rs = ps.executeQuery()){
	    		while(rs.next()) {
	    			villeAppreciations.add(new Appreciation(
	    					rs.getInt("id"),
	    					rs.getString("contenu"),
	    					rs.getTimestamp("date_publication").toLocalDateTime(),
	                        rs.getInt("auteur_id"),
	                        rs.getInt("ville_id")
	    					));
	    		}
    		}
    	}
    	return villeAppreciations;
    }
    
    // Gestion des villes
    public static void insertVille(Ville ville) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(INSERT_VILLE)) {
            ps.setString(1, ville.getNom());
            ps.setString(2, ville.getImg());
            ps.setString(3, ville.getDescription());
            ps.executeUpdate();
        }
    }

    public static void updateVille(Ville ville) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(UPDATE_VILLE)) {
            ps.setString(1, ville.getNom());
            ps.setString(2, ville.getImg());
            ps.setString(3, ville.getDescription());
            ps.setInt(4, ville.getId());
            ps.executeUpdate();
        }
    }

    public static void deleteVille(int id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(DELETE_VILLE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static Ville selectVilleById(int id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SELECT_VILLE_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ville(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("img"),
                    rs.getString("description")
                );
            }
        }
        return null;
    }

    public static List<Ville> selectAllVilles() throws SQLException {
        List<Ville> villes = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(SELECT_ALL_VILLES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                villes.add(new Ville(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("img"),
                    rs.getString("description")
                ));
            }
        }
        return villes;
    }
}