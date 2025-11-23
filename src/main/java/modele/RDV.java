package modele;

import jakarta.persistence.*;

@Entity
public class RDV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RDVNO")
    private int id;
    @OneToOne
    @JoinColumn(name = "PATNO")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "MEDNO")
    private Medecin medecin;
    //@OneToOne
    @JoinColumn(name = "HORNO")
    @Column(name = "HORNO")

    private int horaire;
    @OneToOne
    @JoinColumn(name = "SALNO")
    private Salle salle;
    @Column(name = "DATE")
    private String date;
    //TODO : constructeurs

    public RDV(Patient patient, Medecin medecin, int horaire, Salle salle, String date) {
        this.patient = patient;
        this.medecin = medecin;
        this.horaire = horaire;
        this.salle = salle;
        this.date = date;
    }

    public RDV() {

    }

    @Override
    public String toString() {
        return "RDV{" +
                "id=" + id +
                ", patient=" + patient +
                ", medecin=" + medecin +
                ", horaire=" + horaire +
                ", salle=" + salle +
                ", date='" + date + '\'' +
                '}';
    }

    public int getHoraire() {
        return horaire;
    }

    public Personne getPatient() {
        return patient;
    }

    public Salle getSalle() {
        return salle;
    }

    public String getDate() {
        return date;
    }

    public Personne getMedecin() {
        return medecin;
    }

    public int getId() { return id;
    }
}
