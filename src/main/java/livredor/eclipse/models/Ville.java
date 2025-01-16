package livredor.eclipse.models;

public class Ville {
    private int id;            
    private String nom; 
    private String img;
    private String description; 

    public Ville() {}

    public Ville(int id, String nom,String img, String description) {
        this.id = id;
        this.nom = nom;
        this.img = img;
        this.description = description;
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

    public String getImg() {
    	return img;
    }

    public String getDescription() {
        return description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

