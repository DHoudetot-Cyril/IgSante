package vuecontrole;

import modele.RDV;
import requete.Requete;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

public class Ordonnancement {
    private final Requete requete;
    List<String> listeRDVh;
    private int scoreTotal;
    private int scoreDeplacements;
    private int scoreCreneauxVides;
    private int scoreTrous;

    public Ordonnancement() throws SQLException, ClassNotFoundException {
        this.listeRDVh = new ArrayList<>();
        this.requete = Requete.getInstance();
        // JJ/MM/AAAA@HH@NOMSALLE

    }

    public static Ordonnancement getInstance() throws SQLException, ClassNotFoundException {
        return new Ordonnancement();
    }

    public void calculScores() { //Pour les medecins du jour

        this.scoreCreneauxVides = 0; //pas de créneaux vides si un seul rdv
        this.scoreDeplacements = 0; //pas de déplacements si un seul rdv
        this.scoreTrous = 0; //pas de trous si un seul rdv*
        this.scoreTotal = 0;

        if (listeRDVh.size() <= 1) {
            return;
        } else {

            int heurePrec = Integer.parseInt(listeRDVh.get(0).split("@")[1]);
            String sallePrec = listeRDVh.get(0).split("@")[2];


            //Calcul des scores

            for (String rdv : listeRDVh) {

                String[] rdvh = rdv.split("@");
                int horaire = Integer.parseInt(rdvh[1]);

                if (horaire - heurePrec > 1) {
                    this.scoreTrous++;
                    this.scoreCreneauxVides += horaire - heurePrec;
                }

                if (!rdvh[2].equals(sallePrec)) {
                    this.scoreDeplacements++;
                }

                heurePrec = horaire;
                sallePrec = rdvh[2];
            }
        }
        scoreTotal = scoreCreneauxVides + scoreDeplacements + scoreTrous;
    }

    public void setListeRDVh(List<String> listeRDVh) {
        this.listeRDVh = listeRDVh;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public int getScoreDeplacements() {
        return scoreDeplacements;
    }

    public int getScoreCreneauxVides() {
        return scoreCreneauxVides;
    }

    public int getScoreTrous() {
        return scoreTrous;
    }

    public void ordonnancer(List<String> listeRDVh) { //Pour les medecins du jour

        System.out.println("Ordonnancer");
        if (listeRDVh.size() <= 1) {
            System.out.println("Pas assez de rdv pour ordonnancer");
            return;
        } else {

            int heurePrec = Integer.parseInt(listeRDVh.get(0).split("@")[1]);
            String sallePrec = listeRDVh.get(0).split("@")[2];

            System.out.println(heurePrec);
            System.out.println(sallePrec);


            //Calcul des scores

            for (String rdv : listeRDVh) {

                String[] rdvh = rdv.split("@");
                int horaire = Integer.parseInt(rdvh[1]);
                String salle = rdvh[2];
                if (horaire - heurePrec > 1) { //If trou / créneau vide faire requete pour trouver un rdv à mettre dans le trou
                    System.out.println("Trou de " + (horaire - heurePrec) + "h");
                    /*
                    RDV MAX = 8/J
                    RDV DEPLACABLE +- 2H

                    REGARDER POUR RECULER LES RDVS

                    REGARDER POUR AVANCER LES RDVS

                    SI CHANGEMENT DE SALLE, REGARDER POUR RESTER DANS LA SALLE

                    SI IMPOSSIBLE, REGARDER POUR ALLER DANS LA SALLE DU RDV SUIVANT
                     */


                    if (horaire - heurePrec >= 2 && requete.getRDVLibreBySalleHeure(salle, horaire - 2,rdvh[0])) {//On regarde pour ravancer le rdv
                        //Ravancer le rdv de 2h
                        requete.ravancerRDV(rdvh[3], horaire - 2);
                        System.out.println("Ravancer de 2h Le rdv de " + horaire);
                    }
                    if (horaire - heurePrec >= 1 && requete.getRDVLibreBySalleHeure(salle, horaire - 1,rdvh[0])) {
                        //Ravancer le rdv d'1h
                        requete.ravancerRDV(rdvh[3], horaire - 1);
                        System.out.println("Ravancer de 1h Le rdv de " + horaire);
                    }
                    if(!requete.getRDVLibreBySalleHeure(salle, horaire - 2,rdvh[0])) System.out.println("Impossible de ravancer le rdv de " + horaire);
                    if(!requete.getRDVLibreBySalleHeure(salle, horaire - 1,rdvh[0])) System.out.println("Impossible de ravancer le rdv de " + horaire);

                    if (!rdvh[2].equals(sallePrec)) {
                        this.scoreDeplacements++;
                    }

                    heurePrec = horaire;
                    sallePrec = rdvh[2];
                }
            }
        }
    }
}
