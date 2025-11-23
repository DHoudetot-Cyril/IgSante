package modele;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("CAD")
public class Cadre extends Personne {

    public Cadre() {

    }

    public Cadre(String login, String password, String nom, String prenom, String dob, String adresse) {
        super(nom, prenom, login, password, dob, adresse);


    }

}
