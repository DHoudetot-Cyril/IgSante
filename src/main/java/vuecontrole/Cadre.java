package vuecontrole;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class Cadre extends Vue {
    private JPanel panelCad;
    private JButton vuePlanningGlobalButton;
    private JButton buttonPlanMed;
    private JButton vuePlanningSallesButton;
    private JButton deconnexionButton;
    private JPanel panel;
    private JPanel panel1;


    public Cadre(int id) {

        this.setContentPane(panelCad);

        this.initialisationFenetre();
        setTitle("Cadre " + id);
        this.initConnexion();

        //Pour la taille des boutons

        Dimension maxSize = this.buttonPlanMed.getSize();

        this.vuePlanningGlobalButton.setPreferredSize(maxSize);
        this.vuePlanningSallesButton.setPreferredSize(maxSize);
        this.buttonPlanMed.setPreferredSize(maxSize);


        //affichage planning global
        vuePlanningGlobalButton.addActionListener(e -> {
            try {
                System.out.println("Affichage du planning global");
                PlanningGlobal planningGlobal = new PlanningGlobal(id);
                dispose();
            } catch (Exception ex) {
                System.out.println("Erreur de connexion à la base de données");
                ex.printStackTrace();
            }
        });

        //affichage planning medecin (liste medecin)
        buttonPlanMed.addActionListener(e -> {
            try {
                ListeMedecin Lmed = new ListeMedecin(id);
                dispose();
            } catch (Exception ex) {
                System.out.println("Erreur de connexion à la base de données");
            }
        });

        //affichage planning salles
        vuePlanningSallesButton.addActionListener(e -> {
            try {
                System.out.println("Affichage du planning des salles");
                ListeSalle planningSalle = new ListeSalle(id);
                dispose();
            } catch (Exception ex) {
                System.out.println("Erreur de connexion à la base de données");
            }
        });

        //deconnexion
        deconnexionButton.addActionListener(e -> {
            dispose();
            Connexion connexion = new Connexion();
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel = new JPanel();
        panel1 = new JPanel();
        vuePlanningGlobalButton = new JButton();
        buttonPlanMed = new JButton();
        vuePlanningSallesButton = new JButton();
        deconnexionButton = new JButton();

        vuePlanningGlobalButton.setBorder(new buttonArrondi(5));
        buttonPlanMed.setBorder(new buttonArrondi(5));
        vuePlanningSallesButton.setBorder(new buttonArrondi(5));
        deconnexionButton.setBorder(new buttonArrondi(5));

        }
}


