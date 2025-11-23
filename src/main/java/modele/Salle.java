package modele;

import jakarta.persistence.*;

@Entity
@Table(name = "SALLE")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALNO")
    private int id;
    @Column(name = "NUMERO", unique = true)
    private int numero;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "ID")
    private int idSalle;

    public Salle() {
    }

    public Salle(int idSalle, int no, String nom) {
        this.numero = no;
        this.nom = nom;
        this.idSalle = idSalle;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "id=" + id +
                ", numero=" + numero +
                ", nom='" + nom + '\'' + ", idSalle='" + idSalle + '\'' +
                '}' + "\n";
    }

    public int getIdSalle() {
        return idSalle;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }
}
