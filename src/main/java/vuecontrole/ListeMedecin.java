package vuecontrole;

import modele.Medecin;
import modele.Personne;
import requete.Requete;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

public class ListeMedecin extends Vue {
    private JLabel recherche;
    private JTextField textField1;
    private JList listeRecherche;
    private JPanel panelListMed;
    private JButton afficherPlanningButton;
    private JButton DecoButton;
    private JButton retourButton;
    private final Requete requete;

    public ListeMedecin(int idCadre){
        this.setContentPane(panelListMed);
        this.initialisationFenetre();
        setTitle("ListeMedecin");
        this.initConnexion();
        // etat de la connexion
        //boolean statut = true;
        requete = super.getRequete();
        this.remplirListeMedecins("");


        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                remplirListeMedecins(textField1.getText());
            }
        });

        afficherPlanningButton.addActionListener(e -> {
            if (listeRecherche.getSelectedIndex() != -1) { //si un client est sélectionné
                afficherPlanningMedecin(idCadre);
            }
        });

        //afficher planning medecin en double cliquant sur le medecin
        listeRecherche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    afficherPlanningMedecin(idCadre);
                }
            }
        });

        DecoButton.addActionListener(e -> {
            //fermer toutes les fenetres et revenir à la fenetre de connexion
            dispose(); //ferme la fenetre
            Connexion connexion = new Connexion();

        });
        retourButton.addActionListener(e -> {
            dispose();
            Cadre cadre = new Cadre(idCadre);
        });
    }
    private void remplirListeMedecins(String nomprenom) {

        List<Medecin> personne;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try {
            personne = this.requete.ensMedecins(nomprenom);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Medecin m : personne) {
            listModel.addElement(m.getNom() + " " + m.getPrenom());
        }
        listeRecherche.setModel(listModel);
    }

    private void afficherPlanningMedecin(int idCadre) {
        // TODO implement here
        try {

            dispose();

            String[] nomprenom = String.valueOf(listeRecherche.getSelectedValue()).split(" ");
            String nom = nomprenom[0];
            String prenom = nomprenom[1];
            System.out.println(nom + " " + prenom);
            Personne medecin = requete.getIDPersByNP(nom, prenom);
            System.out.println(medecin);
            PlanningMedecin planMed = new PlanningMedecin(medecin,idCadre);
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion à la base de données");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
