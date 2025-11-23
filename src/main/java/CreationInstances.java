import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modele.*;
import requete.Requete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CreationInstances {

    private static Requete requete;

    public static List<Medecin> readMedFile(String nomFichier) {
        List<Medecin> medecins = new ArrayList<>();
        int id = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // Affichez chaque ligne pour voir ce qui est lu

                String[] elements = ligne.split(";");
                if (elements.length == 8) {


                    String login = elements[1];
                    String password = elements[2];
                    String nom = elements[4];
                    String prenom = elements[3];
                    String dob = elements[5];
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    //__Date dateNaissance = null;
                    String adresse = elements[6];
                    //String numeroProfessionnelSante = elements[0];
                    boolean secteur = "1".equals(elements[7]);

                    Medecin medecin = new Medecin(id, login, password, nom, prenom, dob, adresse, secteur);
                    //System.out.println("Médecin créé : " + medecin);
                    medecins.add(medecin);
                    id++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return medecins;
    }

    public static List<Cadre> readCadFile(String nomFichier) {
        List<Cadre> cadres = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // Affichez chaque ligne pour voir ce qui est lu

                String[] elements = ligne.split(";");
                if (elements.length == 7) {
                    //int i=0;
                    //int id = i+1;
                    String login = elements[1];
                    String password = elements[2];
                    String prenom = elements[3];
                    String nom = elements[4];
                    String dob = elements[5];
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    //__Date dateNaissance = null;
                    String adresse = elements[6];
                    Cadre cadre = new Cadre(login, password, nom, prenom, dob, adresse);
                    //System.out.println("Cadre créé : " + cadre);
                    cadres.add(cadre);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cadres;
    }

    public static List<Salle> readSalleFile(String nomFichier) {
        List<Salle> salles = new ArrayList<>();
        int id = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {


                String[] elements = ligne.split(";");
                if (elements.length == 3) {
                    int numero = Integer.parseInt(elements[1]);
                    String nom = elements[2];

                    Salle salle = new Salle(id, numero, nom);
                    salles.add(salle);
                    id++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return salles;
    }

    public static List<Patient> readPatFile(String nomFichier) {
        List<Patient> patients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {


            String ligne;
            int idPa = 0;
            while ((ligne = br.readLine()) != null) {

                // Affichez chaque ligne pour voir ce qui est lu

                String[] elements = ligne.split(";");
                if (elements.length == 8) {
                    //int i=0;
                    idPa++;
                    String prenom = elements[3];
                    String nom = elements[4];
                    String dob = elements[5];
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    //__Date dateNaissance = null;
                    String numId = elements[6];
                    boolean visiteSpecialisee = "1".equals(elements[7]);

                    Patient patient = new Patient(nom, prenom, dob, numId, visiteSpecialisee);
                    //System.out.println("Patient créé : " + patient);
                    patients.add(patient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
    }

    //creation des rdv
    public static List<RDV> ReadRdvfile(String nomFichier, String date) throws SQLException, ClassNotFoundException {

        List<RDV> rdvs = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();

            String ligne;
            while ((ligne = br.readLine()) != null) {

                // Affichez chaque ligne pour voir ce qui est lu

                String[] elements = ligne.split(";");
                if (elements.length == 8) {
                    //int i=0;
                    //int id = i+1;
                    int noMedecin = Integer.parseInt(elements[0]);
                    int idSalle = Integer.parseInt(elements[1]);
                    String annee = date.substring(0, 4);
                    String mois = date.substring(4, 6);
                    String jour = date.substring(6, 8);
                    int horaireDeb = Integer.parseInt(elements[2]);
                    String date2 = annee + "/" + mois + "/" + jour;
                    String prenom = elements[3];
                    String nom = elements[4];
                    String dob = elements[5];
                    String numId = elements[6];
                    boolean visiteSpecialisee = "1".equals(elements[7]);

                    System.out.println("recherche id medecin : " + noMedecin);
                    List<Medecin> medecins = readMedFile("src/main/java/instances/medecins.txt");
                    Medecin mt = new Medecin();
                    for (Medecin medecin : medecins) {
                        if (medecin.getMedno() == noMedecin) {
                            mt = medecin;
                            System.out.println(mt);
                        }
                    }


                    System.out.println("recherche id salle : " + idSalle);
                    Salle st = new Salle();
                    List<Salle> salles = readSalleFile("src/main/java/instances/salles.txt");
                    for (Salle salle : salles) { //id de la salle
                        if (salle.getIdSalle() == idSalle + 1) {
                            st = salle;
                            System.out.println(st);
                        }
                    }
                    System.out.println("recherche id patient : " + numId);
                    Patient pt = new Patient();
                    List<Patient> patients = readPatFile("src/main/java/instances/" + date + ".txt");
                    for (Patient patient : patients) {
                        if (patient.getNumeroId().equals(numId)) {
                            pt = patient;
                            System.out.println(pt);
                        }
                    }

                    System.out.println("recherche horaire : " + horaireDeb);
                    int ht = horaireDeb;


                    RDV rdv = new RDV(pt, mt, ht, st, date2);

                    System.out.println("RDV créé : " + rdv);
                    System.out.println("\n---------------------------------\n");
                    rdvs.add(rdv);
                    System.out.println(rdvs);
                }
            }
            return rdvs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rdvs;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();


        et.begin();

        /* Création d'identifiant pour connexion rapide ******/

        Cadre ct = new Cadre("cad", "cad", "Delfanne", "Yohan", "19/12/2023", "17, Rue Champollion, Dunkerke");
        Medecin mt = new Medecin(0, "med", "med", "Jacques", "Secretin", "19/12/2023", "77, Rue Gustave Delory, Arras", false);
        Patient pt = new Patient("jean", "paul", "12/02/2003", "123456", true);
        int ht = 12;
        Salle st = new Salle(38, 240, "SecondPoteauPavard");
        System.out.println(st.getIdSalle());
        String brutus = "20231101";
        String annee = brutus.substring(0, 4);
        String mois = brutus.substring(4, 6);
        String jour = brutus.substring(6, 8);
        String date2 = annee + "/" + mois + "/" + jour;
        System.out.println("creation rdv");
        RDV rt = new RDV(pt, mt, ht, st, date2);


        em.persist(ct);
        em.persist(mt);
        System.out.println("persist");
        em.persist(pt);
        //em.persist(ht);
        System.out.println("persist2");
        em.persist(st);
        System.out.println("persist3");
        em.persist(rt);
        System.out.println("persist3");

        String date = "20231101";




        /* Création de cadres ******/


        List<Cadre> cadres = readCadFile("src/main/java/instances/cadres.txt");
        List<Medecin> medecins = readMedFile("src/main/java/instances/medecins.txt");
        List<Salle> salles = readSalleFile("src/main/java/instances/salles.txt");
        List<Patient> patients1 = readPatFile("src/main/java/instances/" + date + ".txt");
        List<RDV> rdvs1 = ReadRdvfile("src/main/java/instances/" + date + ".txt", date);




        for (Cadre cadre : cadres) {
            em.persist(cadre);
        }

        /* Création de medecins ******/


        for (Medecin medecin : medecins) {
            em.persist(medecin);
        }

        /* Création de salles ******/




        for (Salle salle : salles) {
            System.out.println(salle.getIdSalle());
            em.persist(salle);
        }


        System.out.println("enregistrement salles fait");


        /* Création de patients ******/




        //lecture des fichiers de patients
        for (int j = 0; j <= 2; j++) { //j represente la dizaine des jours
            for (int i = 1; i <= 6; i++) { //i represente l'unité des jours , 6 jours car ils bossent 6 jours sur 10
                date = "202312" + j + i;
                List<Patient> patients2 = readPatFile("src/main/java/instances/" + date + ".txt");
                //List<RDV> rdvs2 = ReadRdvfile("src/main/java/instances/"+date+".txt",date);
                patients1.addAll(patients2);
                //rdvs1.addAll(rdvs2);
            }
        }

        //enlever les doublons de numId
        List<Patient> patients = new ArrayList<>();
        for (Patient patient : patients1) {
            boolean doublon = false;
            for (Patient patient1 : patients) {
                if (patient.getNumeroId().equals(patient1.getNumeroId())) {
                    doublon = true;
                    break;
                }
            }
            if (!doublon) patients.add(patient);
        }

        for (Patient patient : patients) em.persist(patient);


        //lecture des fichiers de rdv

        for (RDV rdv : rdvs1) {
            em.persist(rdv);
        }

        et.commit();


    }
}
