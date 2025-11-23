package vuecontrole;

import modele.RDV;
import requete.Requete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlanningGlobal extends Vue {
    private JPanel panel1;
    private JTable planningGlobal;
    private JButton retourButton;
    private JTable table1;
    private JComboBox comboBoxDate;
    private final Requete requete;

    private int scoreTotal;
    private int scoreDeplacements;
    private int scoreCreneauxVides;
    private int scoreTrous;


    private final Ordonnancement ordonnancement;

    public PlanningGlobal(int idCadre) throws SQLException, ClassNotFoundException {
        ordonnancement = Ordonnancement.getInstance();
        this.scoreDeplacements = 0;
        this.scoreCreneauxVides = 0;
        this.scoreTrous = 0;
        this.scoreTotal = 0;


        this.setContentPane(panel1);
        this.initialisationFenetre();
        setTitle("Planning global");
        this.requete = Requete.getInstance();
        this.initConnexion();
        this.remplirPlanningGlobal();


        retourButton.addActionListener(e -> {
            dispose();
            Cadre cadre = new Cadre(idCadre);
        });


        comboBoxDate.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                //ajouter les dates dans la comboBox
                List<String> dates = new ArrayList<>();
                dates.add("01/11/2023");
                dates.add("01/12/2023");
                dates.add("02/12/2023");
                for (String date : dates) {
                    comboBoxDate.addItem(date);
                }
            }
        });
    }

    public void remplirPlanningGlobal() {
        //TODO : Afficher le planning global
        List<RDV> rdvs2 = this.requete.getRDVGlobal();
        afficherPlanningGlobal(rdvs2);

        comboBoxDate.addActionListener(e -> {
            //TODO : Afficher le planning global pour la date sélectionnée
            List<RDV> rdvs;
            String date = (String) comboBoxDate.getSelectedItem();
            //afficher la date sélectionnée
            System.out.println(date);
            if (date == "Dates"){
                rdvs = this.requete.getRDVGlobal();
                afficherPlanningGlobal(rdvs);
            }
            else {
                rdvs = this.requete.getRDVGlobal(date);
                afficherPlanningGlobal(rdvs);
            }
        });


    }


    private void afficherPlanningGlobal(List<RDV> rdvs) {
        int i = 0, j = 0, k = 0;

        String dateCalculNb = null;
        String datePrec = rdvs.get(0).getDate();

        for (RDV rdv : rdvs) {
            if (!Objects.equals(dateCalculNb, rdv.getDate())) {
                dateCalculNb = rdv.getDate();
                k++;
            }
        }

        int nbRDV = rdvs.size();
        String[][] donnees = new String[nbRDV][7];
        String[][] donnees2 = new String[k][5];


        String medecinPrec = rdvs.get(0).getMedecin().getNom() + " " + rdvs.get(0).getMedecin().getPrenom();

        List<String> listeRDVh = new ArrayList<>();

        DefaultTableModel tableModel = new DefaultTableModel();
        DefaultTableModel tableModel2 = new DefaultTableModel();

        for (RDV rdv : rdvs) {

            donnees[i][0] = String.valueOf(rdv.getId());
            donnees[i][1] = rdv.getHoraire() + "h00";
            donnees[i][2] = rdv.getPatient().getNom() + " " + rdv.getPatient().getPrenom();
            donnees[i][3] = rdv.getMedecin().getNom() + " " + rdv.getMedecin().getPrenom();
            donnees[i][4] = rdv.getDate();
            donnees[i][5] = rdv.getSalle().getNom();
            //TODO : Ajouter si c'est une visite spé ou pas
            i++;


            if (!Objects.equals(datePrec, rdv.getDate()) || i == nbRDV) {

                if (i == nbRDV) listeRDVh.add(rdv.getDate() + "@" + rdv.getHoraire() + "@" + rdv.getSalle().getNom());

                if(!Objects.equals(medecinPrec,rdv.getMedecin().getNom() + " " + rdv.getMedecin().getPrenom())){

                    ordonnancement.setListeRDVh(listeRDVh); //ordonnancement du rdv du jour
                    ordonnancement.calculScores();

                    this.scoreDeplacements = ordonnancement.getScoreDeplacements();
                    this.scoreCreneauxVides = ordonnancement.getScoreCreneauxVides();
                    this.scoreTrous = ordonnancement.getScoreTrous();
                    this.scoreTotal = ordonnancement.getScoreTotal();

                    System.out.println("Score total : " + ordonnancement.getScoreTotal());
                    System.out.println("Score deplacements : " + ordonnancement.getScoreDeplacements());
                    System.out.println("Score creneaux vides : " + ordonnancement.getScoreCreneauxVides());
                    System.out.println("Score trous : " + ordonnancement.getScoreTrous());


                    //Vider la table listeRDVh pour passer au jour suivant
                    listeRDVh.clear();
                    medecinPrec = rdv.getMedecin().getNom() + " " + rdv.getMedecin().getPrenom();

                }

                //Ajout d'une entrée dans la table des scores

                donnees2[j][0] = datePrec;
                donnees2[j][1] = String.valueOf(this.scoreCreneauxVides);
                donnees2[j][2] = String.valueOf(this.scoreDeplacements);
                donnees2[j][3] = String.valueOf(this.scoreTrous);
                donnees2[j][4] = String.valueOf(this.scoreTotal);

                j++;
                System.out.println(datePrec);
                datePrec = rdv.getDate(); //ordonnancement du prochain jour a la prochaine itération où il y a changement de jour
            }

        listeRDVh.add(rdv.getDate()+"@"+rdv.getHoraire()+"@"+rdv.getSalle().getNom());

        }

        String[] titreColonnes2 = {"Journee", "Score Creneaux Vides", "Score Deplacements", "Score Trous", "Score Total"};
        tableModel2.setDataVector(donnees2, titreColonnes2);
        table1.setEnabled(false);
        table1.setModel(tableModel2);

        String[] titreColonnes = {"id", "Horaire", "Patient", "Medecin", "Date", "Salle", "Spécialisée"};
        tableModel.setDataVector(donnees, titreColonnes);
        //faire en sorte que l'utilisateur ne puisse pas modifier les données
        planningGlobal.setEnabled(false);
        planningGlobal.setModel(tableModel);
    }

}




