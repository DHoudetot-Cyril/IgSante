package modele;

import jakarta.persistence.*;

@Entity
public class Horaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HORNO")
    private int id;
    @Column(name = "HEUREDEBUT")
    private int heureDebut;

    public Horaire(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Horaire() {

    }

    @Override
    public String toString() {
        return "Horaire{" +
                "id=" + id +
                ", heureDebut=" + heureDebut + "h" +
                '}';
    }

    public int getHeureDebut() {
        return heureDebut;
    }
}
