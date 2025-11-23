package vuecontrole;

import modele.Salle;
import requete.Requete;

import javax.swing.*;

public class ListeSalle extends Vue {
    private JList listSalle;
    private JPanel panel1;
    private JButton deconnexionButton;
    private JButton retourButton;

    private final Requete requete;

    public ListeSalle(int idCadre){


        this.setContentPane(panel1);
        this.initialisationFenetre();
        setTitle("ListeSalle");
        this.initConnexion();
        requete = super.getRequete();
        this.remplirListeSalle();


        //double clic sur une salle
        listSalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    dispose();
                    String nomSalle= String.valueOf(listSalle.getSelectedValue());
                    System.out.println(nomSalle);
                    Salle salle = requete.getIDSalleByNom(nomSalle);
                    System.out.println(salle);
                    PlanningSalle planningSalle = new PlanningSalle(salle,idCadre);
                }
            }
        });

        //deconnexion
        deconnexionButton.addActionListener(e -> {
            dispose();
            Connexion connexion = new Connexion();
        });
        retourButton.addActionListener(e -> {
            dispose();
            Cadre cadre = new Cadre(idCadre);
        });
    }

    //remplissage de la liste des salles
    private void remplirListeSalle() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (modele.Salle salle : this.requete.getSalles()) {
            String str = salle.getNom();
            listModel.addElement(str);
        }
        this.listSalle.setModel(listModel);

    }



}





