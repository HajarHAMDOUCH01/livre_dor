package livredor.eclipse.models;

import java.time.LocalDateTime;

public class Appreciation {
    private int id;                     
    private String contenu;             
    private LocalDateTime datePublication; 
    private int auteurId;               
    private int villeId;                

    public Appreciation() {}

    public Appreciation(int id, String contenu, LocalDateTime datePublication, int auteurId, int villeId) {
        this.id = id;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.auteurId = auteurId;
        this.villeId = villeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public int getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(int auteurId) {
        this.auteurId = auteurId;
    }

    public int getVilleId() {
        return villeId;
    }

    public void setVilleId(int villeId) {
        this.villeId = villeId;
    }

    @Override
    public String toString() {
        return "Appreciation{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", datePublication=" + datePublication +
                ", auteurId=" + auteurId +
                ", villeId=" + villeId +
                '}';
    }
}
