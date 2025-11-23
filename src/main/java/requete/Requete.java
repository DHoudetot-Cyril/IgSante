package requete;

import jakarta.persistence.*;
import modele.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Requete {

    private String log;
    private String pas;
    private Connection connexionBDD;


    public Requete() throws SQLException, ClassNotFoundException {
        connect();
        //rendre la connexion plus rapide
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
    }

    public static Requete getInstance() throws SQLException, ClassNotFoundException {

        return new Requete();
        
    }

    public void connect() throws ClassNotFoundException {
        String nomClasseDriver = "com.mysql.cj.jdbc.Driver";
        String urlBDD = "jdbc:mysql://localhost:3306/igsante";
        String utilisateur = "root";
        String motDePasse = "";
        Class.forName(nomClasseDriver);
        try {
            this.connexionBDD = DriverManager.getConnection(urlBDD, utilisateur, motDePasse);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
        }

    }

    public boolean requeteConnexion(String log, String pas) throws SQLException { //requete pour la connexion
        this.log = log;
        this.pas = pas;

        final String strQuery = "SELECT login,password FROM Personne WHERE login = :login AND password = :pass";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(strQuery);
        query.setParameter("login", log);
        query.setParameter("pass", pas);
        List<Object[]> res = query.getResultList();

        return res.size() == 1;
    }

    public boolean requeteMedecin(String log, String pas) throws SQLException {
        this.log = log;
        this.pas = pas;

        String requete = "SELECT login,password,persno FROM Personne WHERE PERS_TYPE = 'MED' AND login = ? AND password = ?";
        PreparedStatement stat = connexionBDD.prepareStatement(requete);
        stat.setString(1, log);
        stat.setString(2, pas);
        ResultSet res = stat.executeQuery();
        if (res.next()) {
            stat.close();
            return true;
        } else {
            stat.close();
            return false;
        }

    }

    public int getIdPersonne(String log, String pass) throws SQLException {

        String requete = "SELECT PERSNO FROM Personne WHERE login = ? AND password = ?";
        PreparedStatement stat = connexionBDD.prepareStatement(requete);
        stat.setString(1, log);
        stat.setString(2, pass);
        ResultSet res = stat.executeQuery();
        if (res.next()) {
            int id = res.getInt(1);
            stat.close();
            return id;
        } else {
            stat.close();
            return -1;
        }
    }


    public Personne getIDPersByNP(String no, String prenom) throws SQLException { //requete pour la connexion
        final String strQuery = "SELECT p FROM Personne p WHERE nom = :login AND prenom = :pass";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(strQuery);
        query.setParameter("login", no);
        query.setParameter("pass", prenom);
        List<Object> res = query.getResultList();

        if (res.size() == 1) {
            return (Personne) res.get(0);
        } else {
            return null;
        }
    }


    public List<Medecin> ensMedecins(String nomprenom) throws SQLException {
        List<Medecin> medecins = new ArrayList<>();

        final String strQuery = "SELECT p FROM Personne p WHERE p.nom LIKE :nom OR p.prenom LIKE :prenom";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(strQuery);
        query.setParameter("nom", "%" + nomprenom + "%");
        query.setParameter("prenom", "%" + nomprenom + "%");
        List<Object[]> res = query.getResultList();

        //System.out.println(res);
        for (Object m : res) {
            if (m instanceof Medecin) {

                medecins.add((Medecin) m);
            }
        }
        return medecins;
    }

    public List<RDV> getRDVbyID(Personne medecin){

        List<RDV> rdv = new ArrayList<>();

        final String requete = "SELECT r FROM RDV r WHERE medecin = :medecin";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);
        query.setParameter("medecin", medecin );
        List<Object> res = query.getResultList();

        for (Object r : res) {
            if (r instanceof RDV) {
                rdv.add((RDV) r);
            }
        }

        return rdv;
    }


    public List<Salle> getSalles() {


        List<Salle> salles = new ArrayList<>();

        final String requete = "SELECT s FROM Salle s";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);
        List<Object> res = query.getResultList();

        for (Object s : res) {
            if (s instanceof Salle) {
                salles.add((Salle)s);
            }
        }

        return salles;
    }

    public Salle getIDSalleByNom(String nom) {
        final String requete = "SELECT s FROM Salle s WHERE nom like :nom";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);
        query.setParameter("nom",nom);
        return (Salle)query.getSingleResult();
    }

    public List<RDV> getRDVbyIDSalle(Salle salle) {
        List<RDV> rdv = new ArrayList<>();

        final String requete = "SELECT r FROM RDV r WHERE salle = :salle ORDER BY horaire";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);
        query.setParameter("salle", salle );
        List<Object> res = query.getResultList();

        for (Object r : res) {
            if (r instanceof RDV) {
                rdv.add((RDV) r);
            }
        }

        return rdv;
    }

    public void closeConnexion() {
        try {
            connexionBDD.close();
        } catch (SQLException e) {
            System.out.println("Erreur de fermeture de la connexion");
        }
    }

    public List<RDV> getRDVGlobal() {

        List<RDV> rdv = new ArrayList<>();
        //trié par salle et par horaire
        final String requete = "SELECT r FROM RDV r";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);
        List<Object> res = query.getResultList();

        for (Object r : res) {
            if (r instanceof RDV) {
                rdv.add((RDV) r);
            }
        }

        return rdv;

    }

    public List<RDV> getRDVGlobal(String date) {

        List<RDV> rdv = new ArrayList<>();
        //trié par date et par horaire
        final String requete = "SELECT r FROM RDV r WHERE date = :date";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);
        query.setParameter("date", date);
        List<Object> res = query.getResultList();

        for (Object r : res) {
            if (r instanceof RDV) {
                rdv.add((RDV) r);
            }
        }

        return rdv;

    }



    public Personne getPersonneById(int id) {
        final String requete = "SELECT p FROM Personne p WHERE id = :id";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);
        query.setParameter("id", id);
        return (Personne) query.getSingleResult();
    }


    public boolean getRDVLibreBySalleHeure(String sallePrec, int horaire, String s) {
        final String requete = "SELECT r FROM RDV r WHERE salle = :salle AND horaire = :horaire AND date = :date";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);

        Salle salle = getSalleByNom(sallePrec);


        query.setParameter("salle", salle);
        query.setParameter("horaire", horaire);
        query.setParameter("date", s);

        System.out.println("Requete getRDVLibreBySalleHeure ");

        System.out.println(query.getResultList());
        List<Object> res = query.getResultList();

        System.out.println(res);

        if (res.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Salle getSalleByNom(String nom) {
        final String requete = "SELECT s FROM Salle s WHERE nom = :nom";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(requete);

        query.setParameter("nom", nom);
        return (Salle) query.getSingleResult();
    }

    public void ravancerRDV(String s, int i) {
        System.out.println("Ravancer le rdv " + s + " à " + i);
        final String requete = "UPDATE RDV r SET r.horaire = :horaire WHERE r.id = :id";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(requete);
        query.setParameter("horaire", i);
        query.setParameter("id", s);
        query.executeUpdate();
        em.getTransaction().commit(); //Inchallah ca marche
    }
}

