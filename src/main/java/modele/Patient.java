package modele;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PAT")
public class Patient extends Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATNO")
    private int id;
    @Column(name = "DOB")
    private String dob;
    @Column(name = "NUMEROID")
    private String numeroId;
    @Column(name = "SPECIALISE")
    private boolean specialise;

    public Patient() {
    }

    public Patient(String nom, String prenom, String dob, String numeroId, boolean specialise) {
        super(nom, prenom, dob);
        this.numeroId = numeroId;
        this.specialise = specialise;
    }

    public String getNumeroId() {
        return numeroId;
    }

    @Override
    public String toString() {
        return "Patient{" + super.toString() +
                "numeroId='" + numeroId + '\'' +
                '}';
    }
}

