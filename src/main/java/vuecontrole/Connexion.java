package vuecontrole;

import requete.Requete;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class Connexion extends Vue {
    //private static final int MAXTENTATIVES = 3;
    private JPanel loginScreen;
    private JLabel connexion;
    private JTextField textFieldLog;
    private JLabel login;
    private JPasswordField textFieldPass;
    private JLabel password;
    private JButton connexionButton;
    private JLabel etatConnexion;
    private String log;
    private String pass;
    private final Requete requete;
    private int tentatives;
    //private JLabel etatConnexion;
    private JPanel medecinScreen;
    private JLabel medecin;

    public Connexion() {

        this.setContentPane(loginScreen);
        this.initialisationFenetre();
        setTitle("Docto2i");
        this.initConnexion();
        //tempo
        requete=super.getRequete();
        this.tentatives = 0;



        /*clic sur le bouton connexion *****/

        connexionButton.addActionListener(e -> {
            log = textFieldLog.getText();
            pass = textFieldPass.getText();

            try {
                if (requete.requeteConnexion(log, pass)) {

                    etatConnexion.setBackground(Color.green);
                    etatConnexion.setText("Connexion réussie");
                    System.out.println(getTentatives());
                    dispose();

                    if (requete.requeteMedecin(log, pass)) {
                        Medecin medecin = new Medecin(requete.getIdPersonne(log, pass));
                        System.out.println("Je suis un médecin");
                    } else {
                        Cadre cadre = new Cadre(requete.getIdPersonne(log, pass));
                        System.out.println("Je suis un cadre");
                    }
                } else {
                    etatConnexion.setBackground(Color.red);
                    //ouverture d'une fenetre pop-up
                    JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);

                    System.out.println(log + " " + pass);

                    //TODO (Bonus) : bloquer la connexion après 3 tentatives pendant 10s

                    /*
                    setTentatives(getTentatives()+1);
                    if(getTentatives()==MAXTENTATIVES) {
                        etatConnexion.setText("Trop de tentatives de connection, attendre 10s");
                        //Connexion.this.wait(10000);
                        etatConnexion.setText(null);
                        setTentatives(0);
                    }*/
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        /* Appuyer sur entrée pour se connecter *****/

        // appel de la fonction actionPerformed utilisée pour le bouton connexion
        textFieldLog.addActionListener(e -> connexionButton.doClick());

        // appel de la fonction actionPerformed utilisée pour le bouton connexion
        textFieldPass.addActionListener(e -> connexionButton.doClick());

        //TODO: verifier le statut de la connexion cadre ou medecin

    }

    public static void main(String[] args){
        new Connexion();
    }

    public int getTentatives() {
        return tentatives;
    }

    public void setTentatives(int tentatives) {
        this.tentatives = tentatives;
    }

}

