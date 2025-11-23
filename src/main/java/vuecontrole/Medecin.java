package vuecontrole;

import modele.Personne;
import requete.Requete;

import javax.swing.*;
import java.sql.SQLException;

public class Medecin extends Vue {
    int idMedecin;
    private JPanel panel1;
    private JLabel med;

    private Requete requete;


    public Medecin(int id) throws SQLException, ClassNotFoundException {
        this.idMedecin = id;
        this.setContentPane(panel1);
        this.initialisationFenetre();
        setTitle("Medecin " + this.idMedecin);
        this.initConnexion();
        med.setText("Je suis medecin, j'ai suivi la formation " + id);
        this.requete = super.getRequete();
        //Récup la personne
        Personne medecin = requete.getPersonneById(id);
        PlanningMedecin planMed = new PlanningMedecin(medecin,-1);
        dispose();

    }


}
