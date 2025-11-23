package vuecontrole;

import modele.RDV;
import modele.Salle;
import requete.Requete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlanningSalle extends Vue {

    private JPanel panel1;
    private JLabel nomSalle;
    private JTable tableRDVSalle;
    private JButton retourButton;
    private Requete requete;

    public PlanningSalle(Salle salle, int idCadre) {
        this.setContentPane(panel1);
        this.initialisationFenetre();
        setTitle("Planning de la salle " + salle.getNom());
        this.initConnexion();
        requete = super.getRequete();
        this.remplirListePlanningSalle(salle);



        retourButton.addActionListener(e -> {
            dispose();
            ListeSalle Ls = new ListeSalle(idCadre);
        });
    }

    public void remplirListePlanningSalle(Salle salle) {
        int i = 0;
        List<RDV> rdvs = requete.getRDVbyIDSalle(salle);

        this.nomSalle.setText("Planning de la salle " + salle.getNom());

        int nbRDV = rdvs.size();
        String[][] donnees = new String[nbRDV][4];

        DefaultTableModel tableModel = new DefaultTableModel();
        for (modele.RDV rdv : rdvs) {

            donnees[i][0] = rdv.getHoraire() + "h00";
            donnees[i][1] = rdv.getPatient().getNom() + " " + rdv.getPatient().getPrenom();
            donnees[i][2] = rdv.getMedecin().getNom() + " " + rdv.getMedecin().getPrenom();
            donnees[i][3] = rdv.getDate();
            i++;

        }

        String[] titreColonnes = {"Horaire", "Patient", "Medecin", "Date"};

        tableModel.setDataVector(donnees, titreColonnes);
        tableRDVSalle.setEnabled(false);
        tableRDVSalle.setModel(tableModel);

    }

}
