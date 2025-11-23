package modele;

import jakarta.persistence.*;

//TODO : Faire hériter Medecins et Cadres de Personnel(abstract) qui hérite lui meme de Personnes et faire hériter les patients de Personnes directement
@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERS_TYPE", discriminatorType = DiscriminatorType.STRING)
//TODO : NOM PRENOM UNIQUE
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSNO")
    private int id;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "DATENAISSANCE")
    private String dob;
    @Column(name = "ADDRESS")
    private String adresse;

    public Personne() {
    }

    public Personne(String prenom, String nom, String dob) { //Pour patients
        this.prenom = prenom.toUpperCase();
        this.nom = nom.toUpperCase();
        this.dob = dob;
    }

    public Personne(String nom, String prenom, String login, String password, String dob, String adresse) { //Pour medecins et cadres
        this.nom = nom.toUpperCase();
        this.prenom = prenom.toUpperCase();
        this.login = login;
        this.password = password;
        this.dob = dob;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", adresse='" + adresse + '\'' +
                '}' + ", ";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}









