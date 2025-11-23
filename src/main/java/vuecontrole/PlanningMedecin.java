package vuecontrole;

import modele.Personne;
import modele.RDV;
import requete.Requete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlanningMedecin extends Vue {
    private JPanel panel1;
    private JLabel nomMedecin;
    private JTable tableRDVMedecin;
    private JButton retourButton;
    private JTable table1;
    private JScrollPane tableScores;
    private JButton ordonnancerButton;
    private JComboBox comboBox1;
    private final Requete requete;
    private Cadre cadre;
    private final Ordonnancement ordonnancement;

    private Requete requetebis;

    public PlanningMedecin(Personne personne,int idCadre) throws SQLException, ClassNotFoundException {

        ordonnancement = Ordonnancement.getInstance();

        if(idCadre==-1){
            this.ordonnancerButton.setVisible(false);
        }
        else{
            this.ordonnancerButton.setVisible(true);
        }

        this.setContentPane(panel1);
        this.initialisationFenetre();
        setTitle("Planning du medecin " + personne.getNom() + " " + personne.getPrenom()); //TODO faire correctement l'affichage du medecin
        this.initConnexion();
        requete = super.getRequete();
        requetebis = super.getRequete();
        //Vider les tableaux
        this.remplirListePlanningMedecin(personne);
        retourButton.addActionListener(e -> {
            dispose();
            if(idCadre==-1){
                Connexion connexion = new Connexion();
            }
            else {
                ListeMedecin Lmed = new ListeMedecin(idCadre);
            }
        });

        ordonnancerButton.addActionListener(e -> {
            System.out.println("Ordonnancement du medecin " + personne.getNom() + " " + personne.getPrenom());
            List<RDV> rdvs2 = requetebis.getRDVbyID(personne);
            List<String> rdvhs = new ArrayList<>();

            for (RDV rdv : rdvs2) {
                rdvhs.add(rdv.getDate() + "@" + rdv.getHoraire() + "@" + rdv.getSalle().getNom() + "@" + rdv.getId());
            }
            System.out.println(rdvhs);
            ordonnancement.ordonnancer(rdvhs);
            //this.remplirListePlanningMedecin(personne);

        });
    }

    private void remplirListePlanningMedecin(Personne medecin) {

        this.nomMedecin.setText("Planning du medecin " + medecin.getNom() + " " + medecin.getPrenom());

        List<RDV> rdvs = requete.getRDVbyID(medecin);

        afficherPlanningMedecin(rdvs);


    }

    private void afficherPlanningMedecin(List<RDV> rdvs) {
        int i = 0,j=0,nbJours=0;

        //I = nb de rdv
        //J = nb de jours différents pour implémenter dans la table des scores
        //K = nb de jours différents pour créer le tableau des scores

        int nbRDV = rdvs.size();

        if (nbRDV == 0) return;

        //Calcul du nb de jours différents

        String dateCalculNb = null;

        for (RDV rdv : rdvs) {
            if(!Objects.equals(dateCalculNb, rdv.getDate())){
                dateCalculNb = rdv.getDate();
                nbJours++;
            }
        }

        //Création des vecteurs de données pour le tableau des scores et le tableau des rdvs

        String[][] donnees = new String[nbRDV][4];
        String [][] donnees2 = new String[nbJours][5];
        String datePrec = rdvs.get(0).getDate();

        List<String> listeRDVh = new ArrayList<>();

        DefaultTableModel tableModel = new DefaultTableModel();
        DefaultTableModel tableModel2 = new DefaultTableModel();

        for (RDV rdv : rdvs) {

            donnees[i][0] = rdv.getHoraire() + "h00";
            donnees[i][3] = rdv.getSalle().getNom();
            donnees[i][1] = rdv.getPatient().getNom() + " " + rdv.getPatient().getPrenom();
            donnees[i][2] = rdv.getDate();

            i++;

            if (!Objects.equals(datePrec, rdv.getDate()) || i == nbRDV) {

                if (i == nbRDV) listeRDVh.add(rdv.getDate() + "@" + rdv.getHoraire() + "@" + rdv.getSalle().getNom());


                ordonnancement.setListeRDVh(listeRDVh); //ordonnancement du rdv du jour
                ordonnancement.calculScores();

                //Ajout d'une entrée dans la table des scores
                donnees2[j][0] = datePrec;
                donnees2[j][1] = String.valueOf(ordonnancement.getScoreCreneauxVides());
                donnees2[j][2] = String.valueOf(ordonnancement.getScoreDeplacements());
                donnees2[j][3] = String.valueOf(ordonnancement.getScoreTrous());
                donnees2[j][4] = String.valueOf(ordonnancement.getScoreTotal());

                //Vider la table listeRDVh pour passer au jour suivant
                listeRDVh.clear();

                j++;

                datePrec = rdv.getDate(); //ordonnancement du prochain jour a la prochaine itération où il y a changement de jour
            }
            listeRDVh.add(rdv.getDate() + "@" + rdv.getHoraire() + "@" + rdv.getSalle().getNom());



        }


        //Affectation des données dans les tableaux

        String[] titreColonnes2 = {"Journee","Score Creneaux Vides","Score Deplacements","Score Trous","Score Total"};
        tableModel2.setDataVector(donnees2, titreColonnes2);
        table1.setEnabled(false);
        table1.setModel(tableModel2);

        String[] titreColonnes = {"Horaire", "Patient", "Date", "Salle","Spécialisé"};
        tableModel.setDataVector(donnees, titreColonnes);
        tableRDVMedecin.setEnabled(false);
        tableRDVMedecin.setModel(tableModel);
    }

}

