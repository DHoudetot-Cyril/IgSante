package vuecontrole;

import requete.Requete;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Vue extends JFrame {

    private Requete requete;

    public void initialisationFenetre() {
        //definit la taille de la fenetre en remplissant l'ecran
        this.setSize(800,600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //ajouter une icone a la fenetre
        this.setIconImage(new ImageIcon("src/main/resources/images/test1.png").getImage());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initConnexion() {
        try {
            this.requete = Requete.getInstance();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la bdd");
        }
    }


    public Requete getRequete() {

        return requete;
    }

}

class buttonArrondi implements Border {
    private final int radius;

    buttonArrondi(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
