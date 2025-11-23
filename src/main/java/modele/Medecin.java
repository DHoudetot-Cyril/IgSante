package modele;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("MED")
public class Medecin extends Personne {
    @Column(name = "SECTEUR")
    private boolean secteur;
    @Column(name = "MEDNO")
    private int medno;

    public Medecin() {
    }

    public Medecin(int id, String login, String password, String nom, String prenom, String date, String adresse, Boolean secteur) {
        super(nom, prenom, login, password, date, adresse);
        this.secteur = secteur;
        this.medno = id;
    }

    public String getPrenom() {
        return super.getPrenom();
    }

    public String getNom() {
        return super.getNom();
    }

    @Override
    public String toString() {
        return "Medecins{" +
                super.toString() +
                ", secteur=" + secteur +
                ", medno=" + medno +
                '}';
    }

    public int getMedno() {
        return medno;
    }

    public void setMedno(int medno) {
        this.medno = medno;
    }

    public void setSecteur(boolean secteur) {
        this.secteur = secteur;
    }
}

