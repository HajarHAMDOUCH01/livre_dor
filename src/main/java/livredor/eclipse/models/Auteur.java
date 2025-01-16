package livredor.eclipse.models;
import org.mindrot.jbcrypt.BCrypt;


public class Auteur {
    private int id; 
    private String nom; 
    private String email; 
    private String motDePasse; 

    public Auteur() {}

    public Auteur(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = BCrypt.hashpw(motDePasse, BCrypt.gensalt());
    }
    
    public boolean checkMotDePasse(String motDePasse) {
    	return BCrypt.checkpw(motDePasse, this.motDePasse);
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}

