import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modele.*;

import java.sql.SQLException;


public class Test {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IGSantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();


        et.begin();

        /*Création d'identifiant pour connexion rapide ******/

        Cadre ct = new Cadre("cad", "cad", "Delfanne", "Yohan", "19/12/2023", "17, Rue Champollion, Dunkerke");
        Medecin mt = new Medecin(0, "med", "med", "Jacques", "Secretin", "19/12/2023", "77, Rue Gustave Delory, Arras", false);
        Patient pt = new Patient("jean", "paul", "12/02/2003", "123456", true);
        int ht = 12;
        Salle st = new Salle(38, 240, "SecondPoteauPavard");
        System.out.println(st.getIdSalle());
        String brutus = "01/11/2023";
        System.out.println("creation rdv");
        RDV rt = new RDV(pt, mt, ht, st, brutus);


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


        //création de cadre
        Cadre cadre1 = new Cadre("gasparda", "AlzaGard", "Gaspard", "Alizane", "06/05/1975", "171, Rue Champollion, Dunkerque");
        Cadre cadre2 = new Cadre("colettes", "SteColte", "Colette", "Stéroles", "21/09/1986", "139, Rue de la Vignette, Cambrai");
        Cadre cadre3 = new Cadre("clementt", "CleetTin", "Clément", "Tin", "10/04/1973", "58, Rue d'Alger, Bruay-la-Buissière");

        //création de médecins
        Medecin medecin0 = new Medecin(0, "candicee", "tfoouene", "Candice", "Etdefondouzes", "27/06/1984", "77, Rue Gustave Delory, Arras", false);
        Medecin medecin1 = new Medecin(1, "gerardme", "Menoierr", "Gérard", "Menjou", "06/08/1980", "168, Rue Cauchy, Villeneuve-d'Ascq", false);
        Medecin medecin2 = new Medecin(2, "jeantito", "eaitolin", "Jean", "Titoupline", "30/09/1979", "56, Rue Saint Jean, Coudekerque-Branche", false);
        Medecin medecin3 = new Medecin(3, "douglaso", "ogashcoa", "Douglas", "O’Chocolat", "21/03/1977", "146, Rue Jean Zay, Valenciennes", false);
        Medecin medecin4 = new Medecin(4, "fidelean", "AnoueFde", "Fidèle", "Anamoures", "19/06/1983", "186, Rue Adolphe Defrenne, Douai", false);
        Medecin medecin5 = new Medecin(5, "dandelio", "DlionDan", "Dan", "Delione", "07/04/1989", "194, Rue Léon Blum, Calais", false);
        Medecin medecin6 = new Medecin(6, "gilbertm", "MoaiGlbr", "Gilbert", "Monlatine", "21/11/1969", "60, Rue Marx Dormoy, Maubeuge", false);
        Medecin medecin7 = new Medecin(7, "prudence", "eesePdee", "Prudence", "Emairedesuretés", "25/08/1969", "99, Rue du Docteur Verhaeghe, Lens", false);
        Medecin medecin8 = new Medecin(8, "larasure", "aurelemo", "Lara", "Suretilenamoure", "06/11/1995", "112, Rue de la Digue, Cambrai", false);
        Medecin medecin9 = new Medecin(9, "sandykil", "KilSandy", "Sandy", "Kilos", "25/11/1979", "118, Rue Antoine Joseph Lernould, Lens", false);
        Medecin medecin10 = new Medecin(10, "sheilazi", "elaZizan", "Sheila", "Zizanies", "27/02/1975", "122, Rue D, Wattrelos", false);
        Medecin medecin11 = new Medecin(11, "bowieken", "KntBowie", "Bowie", "Kente", "04/04/1967", "85, Rue du Sabot, Wattrelos", false);
        Medecin medecin12 = new Medecin(12, "sandrade", "DeessSnd", "Sandra", "Dedesssouss", "27/02/1967", "49, Rue Bobillot, Coudekerque-Branche", false);
        Medecin medecin13 = new Medecin(13, "fidelass", "Assmciel", "Fidel", "Assonmek", "21/01/1991", "169, Rue d'Amiens, Cambrai", false);
        Medecin medecin14 = new Medecin(14, "jeannema", "NémarJea", "Jean", "Némarde", "21/01/1984", "81, Rue Saint Marc, Hénin-Beaumont", false);
        Medecin medecin15 = new Medecin(15, "sheilalu", "utinlShi", "Sheila", "Lutfinal", "29/08/1967", "109, Rue du Moulin Saint Andre, Liévin", false);
        Medecin medecin16 = new Medecin(16, "jeanrepr", "renrumrJ", "Jean", "Reprendraisbienunptitouimercis", "24/05/1990", "33, Rue Maracci, Lille", false);
        Medecin medecin17 = new Medecin(17, "pathibul", "ilaiePat", "Pat", "Hibulaires", "07/05/1963", "22, Rue du Docteur Huart, Villeneuve-d'Ascq", false);
        Medecin medecin18 = new Medecin(18, "renekiko", "neKikoul", "René", "Kikoule", "04/03/1971", "103, Rue Daguerre, Liévin", false);
        Medecin medecin19 = new Medecin(19, "pitzaxxx", "PitZa", "Pit", "Zaz", "05/08/1979", "86, Rue de l'Entente Cordiale, Roubaix", false);
        Medecin medecin20 = new Medecin(20, "igordeho", "DehosIgr", "Igor", "Dehorss", "14/03/2004", "107, Rue Richard Wagner, Lambersart", true);
        Medecin medecin21 = new Medecin(21, "bricegla", "GlaeBrce", "Brice", "Glaces", "31/05/1995", "26, Rue du Ballon, Valenciennes", true);
        Medecin medecin22 = new Medecin(22, "ottograf", "GrafOtto", "Otto", "Graff", "02/02/2001", "185, Rue de Châteaudun, Lille", true);
        Medecin medecin23 = new Medecin(23, "hakimmem", "aimMemie", "Hakim", "Membient", "14/08/1997", "113, Rue des Villas, Hénin-Beaumont", true);
        Medecin medecin24 = new Medecin(24, "rolandde", "RoadDgou", "Roland", "Dégoute", "20/09/1999", "127, Rue A Claques, Tourcoing", true);
        Medecin medecin25 = new Medecin(25, "dicktece", "DickTece", "Dick", "Técenfot", "01/07/1995", "84, Rue du Docteur Roux, Douai", true);
        Medecin medecin26 = new Medecin(26, "samantha", "oSmantha", "Samantha", "Loo", "08/09/2005", "145, Rue Nicolas Leblanc, Armentières", true);
        Medecin medecin27 = new Medecin(27, "kellydio", "DiosKell", "Kelly", "Diossy", "28/12/1997", "180, Rue Philippe de Girard, Lille", true);
        Medecin medecin28 = new Medecin(28, "jamespat", "mesPtgee", "James", "Patagueul", "21/02/1996", "51, Rue de Jussieu, Hénin-Beaumont", true);
        Medecin medecin29 = new Medecin(29, "regissoe", "eurdeoRi", "Régis", "Soeurdéimpot", "05/04/2003", "54, Rue Newton, Lille", true);

        //création de salle

        Salle salle0 = new Salle(0, 11, "bleu");
        Salle salle1 = new Salle(1, 12, "rouge");
        Salle salle2 = new Salle(2, 13, "orange");
        Salle salle3 = new Salle(3, 14, "verte");
        Salle salle4 = new Salle(4, 21, "jaune");
        Salle salle5 = new Salle(5, 22, "marron");
        Salle salle6 = new Salle(6, 23, "noire");
        Salle salle7 = new Salle(7, 24, "grise");
        Salle salle8 = new Salle(8, 31, "violette");
        Salle salle9 = new Salle(9, 32, "blanche");

        //création de patient rdv le 01/11/2023

        String date = "01/11/2023";

        // Patients
        Patient patient0339 = new Patient("Marcel", "Cénana", "30/05/1949", "0339", false);
        Patient patient0187 = new Patient("Georgette", "Tousskjebouf", "08/06/2016", "0187", false);
        Patient patient0480 = new Patient("Sylvain", "Ebon", "05/09/1958", "0480", true);
        Patient patient0034 = new Patient("Amanda", "Ble", "28/06/1930", "0034", false);
        Patient patient0363 = new Patient("Milène", "Micoton", "27/10/1943", "0363", true);
        Patient patient0133 = new Patient("Édith", "Orial", "08/05/2002", "0133", true);
        Patient patient0505 = new Patient("Tom", "Égérie", "19/08/1963", "0505", false);
        Patient patient0148 = new Patient("Ella", "Lagingrat", "18/07/2003", "0148", false);
        Patient patient0122 = new Patient("Denis", "Chon", "21/11/2000", "0122", false);
        Patient patient0338 = new Patient("Marc", "Dépoint", "27/03/2020", "0338", false);
        Patient patient0067 = new Patient("Aude", "Vesselle", "28/03/2004", "0067", false);
        Patient patient0069 = new Patient("Aurore", "Polaire", "22/09/1934", "0069", false);

// Rendez-vous

        RDV rdv1 = new RDV(patient0339, medecin0, 14, salle1, date);
        RDV rdv2 = new RDV(patient0187, medecin0, 15, salle1, date);
        RDV rdv3 = new RDV(patient0480, medecin1, 8, salle1, date);
        RDV rdv4 = new RDV(patient0034, medecin1, 9, salle0, date);
        RDV rdv5 = new RDV(patient0363, medecin1, 12, salle0, date);
        RDV rdv6 = new RDV(patient0133, medecin1, 13, salle0, date);
        RDV rdv7 = new RDV(patient0505, medecin1, 14, salle0, date);
        RDV rdv8 = new RDV(patient0148, medecin1, 19, salle1, date);
        RDV rdv9 = new RDV(patient0122, medecin20, 9, salle1, date);
        RDV rdv10 = new RDV(patient0338, medecin20, 13, salle1, date);
        RDV rdv11 = new RDV(patient0067, medecin20, 16, salle1, date);
        RDV rdv12 = new RDV(patient0069, medecin20, 18, salle1, date);


        em.persist(cadre1);
        em.persist(cadre2);
        em.persist(cadre3);
        em.persist(medecin0);
        em.persist(medecin1);
        em.persist(medecin2);
        em.persist(medecin3);
        em.persist(medecin4);
        em.persist(medecin5);
        em.persist(medecin6);
        em.persist(medecin7);
        em.persist(medecin8);
        em.persist(medecin9);
        em.persist(medecin10);
        em.persist(medecin11);
        em.persist(medecin12);
        em.persist(medecin13);
        em.persist(medecin14);
        em.persist(medecin15);
        em.persist(medecin16);
        em.persist(medecin17);
        em.persist(medecin18);
        em.persist(medecin19);
        em.persist(medecin20);
        em.persist(medecin21);
        em.persist(medecin22);
        em.persist(medecin23);
        em.persist(medecin24);
        em.persist(medecin25);
        em.persist(medecin26);
        em.persist(medecin27);
        em.persist(medecin28);
        em.persist(medecin29);
        em.persist(salle0);
        em.persist(salle1);
        em.persist(salle2);
        em.persist(salle3);
        em.persist(salle4);
        em.persist(salle5);
        em.persist(salle6);
        em.persist(salle7);
        em.persist(salle8);
        em.persist(salle9);
        em.persist(patient0339);
        em.persist(patient0187);
        em.persist(patient0480);
        em.persist(patient0034);
        em.persist(patient0363);
        em.persist(patient0133);
        em.persist(patient0505);
        em.persist(patient0148);
        em.persist(patient0122);
        em.persist(patient0338);
        em.persist(patient0067);
        em.persist(patient0069);
        em.persist(rdv1);
        em.persist(rdv2);
        em.persist(rdv3);
        em.persist(rdv4);
        em.persist(rdv5);
        em.persist(rdv6);
        em.persist(rdv7);
        em.persist(rdv8);
        em.persist(rdv9);
        em.persist(rdv10);
        em.persist(rdv11);
        em.persist(rdv12);

        /*

        0;1;13;Gilles;Haidesovetaj;28/09/2000;0197;1;
        1;1;9;Astérix;Épéril;17/11/2018;0063;0;
        1;0;13;Eva;Pissé;10/12/1969;0166;1;
        1;2;19;Aurore;Boréale;30/11/1939;0068;1;
        2;0;16;Ella;Bienhu;15/06/1975;0145;0;
        2;2;18;Inès;Alamaternité;06/08/1976;0235;1;
        20;1;8;Xavier;Kapaètrela;12/09/1949;0511;0;
        20;2;9;Clémence;Dedieu;03/01/1939;0108;0;
        20;2;10;Eve;Vitlézembrouilles;21/08/2001;0171;0;
        20;0;11;Ella;Danloss;05/12/2022;0146;0;
        20;2;12;Cécile;Hon;25/04/1998;0097;0;
        21;2;8;Jean;Bave;09/03/1942;0250;0;
        21;0;9;Jean-Phil;Monslip;09/12/2014;0269;0;
        21;1;11;Philémon;Codesecret;14/07/1991;0410;0;
        21;1;12;Guy;Demichelin;13/11/1970;0205;0;
        21;1;14;Pénélope;Solette;29/08/1943;0404;0;
        21;2;16;Lydie;Commandements;06/05/1989;0334;0;
        21;1;19;Eléonor;Marolex;19/09/1940;0140;0;

        nomedecin;idsalle;horaire;prenom;nom;datenaissance;idpatient;booleenrdvspécialisé;

         */

        date = "01/12/2023";

        Patient patient0197 = new Patient("Gilles", "Haidesovetaj", "28/09/2000", "0197", true);
        Patient patient0063 = new Patient("Astérix", "Épéril", "17/11/2018", "0063", false);
        Patient patient0166 = new Patient("Eva", "Pissé", "10/12/1969", "0166", true);
        Patient patient0068 = new Patient("Aurore", "Boréale", "30/11/1939", "0068", true);
        Patient patient0145 = new Patient("Ella", "Bienhu", "15/06/1975", "0145", false);
        Patient patient0235 = new Patient("Inès", "Alamaternité", "06/08/1976", "0235", true);
        Patient patient0511 = new Patient("Xavier", "Kapaètrela", "12/09/1949", "0511", false);
        Patient patient0108 = new Patient("Clémence", "Dedieu", "03/01/1939", "0108", false);
        Patient patient0171 = new Patient("Eve", "Vitlézembrouilles", "21/08/2001", "0171", false);
        Patient patient0146 = new Patient("Ella", "Danloss", "05/12/2022", "0146", false);
        Patient patient0097 = new Patient("Cécile", "Hon", "25/04/1998", "0097", false);
        Patient patient0250 = new Patient("Jean", "Bave", "09/03/1942", "0250", false);
        Patient patient0269 = new Patient("Jean-Phil", "Monslip", "09/12/2014", "0269", false);
        Patient patient0410 = new Patient("Philémon", "Codesecret", "14/07/1991", "0410", false);
        Patient patient0205 = new Patient("Guy", "Demichelin", "13/11/1970", "0205", false);
        Patient patient0404 = new Patient("Pénélope", "Solette", "29/08/1943", "0404", false);
        Patient patient0334 = new Patient("Lydie", "Commandements", "06/05/1989", "0334", false);
        Patient patient0140 = new Patient("Eléonor", "Marolex", "19/09/1940", "0140", false);

        RDV rdv13 = new RDV(patient0197, medecin0, 13, salle1, date);
        RDV rdv14 = new RDV(patient0063, medecin1, 9, salle1, date);
        RDV rdv15 = new RDV(patient0166, medecin1, 13, salle0, date);
        RDV rdv16 = new RDV(patient0068, medecin1, 19, salle2, date);
        RDV rdv17 = new RDV(patient0145, medecin2, 16, salle0, date);
        RDV rdv18 = new RDV(patient0235, medecin2, 18, salle2, date);
        RDV rdv19 = new RDV(patient0511, medecin20, 8, salle1, date);
        RDV rdv20 = new RDV(patient0108, medecin20, 9, salle2, date);
        RDV rdv21 = new RDV(patient0171, medecin20, 10, salle2, date);
        RDV rdv22 = new RDV(patient0146, medecin20, 11, salle0, date);
        RDV rdv23 = new RDV(patient0097, medecin20, 12, salle2, date);
        RDV rdv24 = new RDV(patient0250, medecin21, 8, salle2, date);
        RDV rdv25 = new RDV(patient0269, medecin21, 9, salle0, date);
        RDV rdv26 = new RDV(patient0410, medecin21, 11, salle1, date);
        RDV rdv27 = new RDV(patient0205, medecin21, 12, salle1, date);
        RDV rdv28 = new RDV(patient0404, medecin21, 14, salle1, date);
        RDV rdv29 = new RDV(patient0334, medecin21, 16, salle2, date);
        RDV rdv30 = new RDV(patient0140, medecin21, 19, salle1, date);


        em.persist(patient0197);
        em.persist(patient0063);
        em.persist(patient0166);
        em.persist(patient0068);
        em.persist(patient0145);
        em.persist(patient0235);
        em.persist(patient0511);
        em.persist(patient0108);
        em.persist(patient0171);
        em.persist(patient0146);
        em.persist(patient0097);
        em.persist(patient0250);
        em.persist(patient0269);
        em.persist(patient0410);
        em.persist(patient0205);
        em.persist(patient0404);
        em.persist(patient0334);
        em.persist(patient0140);
        em.persist(rdv13);
        em.persist(rdv14);
        em.persist(rdv15);
        em.persist(rdv16);
        em.persist(rdv17);
        em.persist(rdv18);
        em.persist(rdv19);
        em.persist(rdv20);
        em.persist(rdv21);
        em.persist(rdv22);
        em.persist(rdv23);
        em.persist(rdv24);
        em.persist(rdv25);
        em.persist(rdv26);
        em.persist(rdv27);
        em.persist(rdv28);
        em.persist(rdv29);
        em.persist(rdv30);

        /*
        0;0;9;Lazare;Fébienléchose;26/06/1967;0320;1;
        0;1;10;Solange;Eulure;25/05/1949;0479;0;
        0;1;11;Sandra;Delit;25/05/1969;0453;1;
        0;2;12;Michel;Demesse;09/04/2002;0360;0;
        0;1;14;Marcel;Cénana;30/05/1949;0339;0;
        0;0;15;Laure;Voirrémerci;18/11/2008;0315;1;
        0;1;17;Helmut;Tardeellepique;23/02/1995;0218;1;
        1;2;9;Esmée;Valda;06/07/1939;0161;1;
        1;0;10;Jessica;Netofrigo;21/12/1995;0277;1;
        1;1;13;Sandra;Nicouverture;18/07/1981;0455;0;
        1;1;18;Pacôme;Léautre;19/10/1980;0384;0;
        1;2;19;Sim;K.;15/01/1978;0476;1;
        20;1;12;Alphonse;Patamob;24/04/1955;0033;0;
        20;2;14;Hyppolite;Hic;26/04/2009;0231;0;
        20;2;16;Xavier;Kapaètrela;12/09/1949;0511;0;
        20;2;17;Perrine;Héale;08/06/2014;0406;0;
        20;0;18;John;Duff;12/01/1992;0284;0;
        20;0;19;Jacques;Ouzi;07/06/1963;0241;0;

        nomedecin;idsalle;horaire;prenom;nom;datenaissance;idpatient;booleenrdvspécialisé;

         */

        date = "02/12/2023";

        Patient patient0320 = new Patient("Lazare", "Fébienléchose", "26/06/1967", "0320", true);
        Patient patient0479 = new Patient("Solange", "Eulure", "25/05/1949", "0479", false);
        Patient patient0453 = new Patient("Sandra", "Delit", "25/05/1969", "0453", true);
        Patient patient0360 = new Patient("Michel", "Demesse", "09/04/2002", "0360", false);
        //Patient patient0339 = new Patient("Marcel", "Cénana", "30/05/1949", "0339", false);
        Patient patient0315 = new Patient("Laure", "Voirrémerci", "18/11/2008", "0315", true);
        Patient patient0218 = new Patient("Helmut", "Tardeellepique", "23/02/1995", "0218", true);
        Patient patient0161 = new Patient("Esmée", "Valda", "06/07/1939", "0161", true);
        Patient patient0277 = new Patient("Jessica", "Netofrigo", "21/12/1995", "0277", true);
        Patient patient0455 = new Patient("Sandra", "Nicouverture", "18/07/1981", "0455", false);
        Patient patient0384 = new Patient("Pacôme", "Léautre", "19/10/1980", "0384", false);
        Patient patient0476 = new Patient("Sim", "K.", "15/01/1978", "0476", true);
        Patient patient0033 = new Patient("Alphonse", "Patamob", "24/04/1955", "0033", false);
        Patient patient0231 = new Patient("Hyppolite", "Hic", "26/04/2009", "0231", false);
        //Patient patient0511 = new Patient("Xavier", "Kapaètrela", "12/09/1949", "0511", false);
        Patient patient0406 = new Patient("Perrine", "Héale", "08/06/2014", "0406", false);
        Patient patient0284 = new Patient("John", "Duff", "12/01/1992", "0284", false);
        Patient patient0241 = new Patient("Jacques", "Ouzi", "07/06/1963", "0241", false);

        RDV rdv31 = new RDV(patient0320, medecin0, 9, salle0, date);
        RDV rdv32 = new RDV(patient0479, medecin0, 10, salle1, date);
        RDV rdv33 = new RDV(patient0453, medecin0, 11, salle1, date);
        RDV rdv34 = new RDV(patient0360, medecin0, 12, salle2, date);
        RDV rdv35 = new RDV(patient0339, medecin0, 14, salle1, date);
        RDV rdv36 = new RDV(patient0315, medecin0, 15, salle0, date);
        RDV rdv37 = new RDV(patient0218, medecin0, 17, salle1, date);
        RDV rdv38 = new RDV(patient0161, medecin1, 9, salle2, date);
        RDV rdv39 = new RDV(patient0277, medecin1, 10, salle0, date);
        RDV rdv40 = new RDV(patient0455, medecin1, 13, salle1, date);
        RDV rdv41 = new RDV(patient0384, medecin1, 18, salle1, date);
        RDV rdv42 = new RDV(patient0476, medecin1, 19, salle2, date);
        RDV rdv43 = new RDV(patient0033, medecin20, 12, salle1, date);
        RDV rdv44 = new RDV(patient0231, medecin20, 14, salle2, date);
        RDV rdv45 = new RDV(patient0511, medecin20, 16, salle2, date);
        RDV rdv46 = new RDV(patient0406, medecin20, 17, salle2, date);
        RDV rdv47 = new RDV(patient0284, medecin20, 18, salle0, date);
        RDV rdv48 = new RDV(patient0241, medecin20, 19, salle0, date);

        em.persist(patient0320);
        em.persist(patient0479);
        em.persist(patient0453);
        em.persist(patient0360);
        //em.persist(patient0339);
        em.persist(patient0315);
        em.persist(patient0218);
        em.persist(patient0161);
        em.persist(patient0277);
        em.persist(patient0455);
        em.persist(patient0384);
        em.persist(patient0476);
        em.persist(patient0033);
        em.persist(patient0231);
        //em.persist(patient0511);
        em.persist(patient0406);
        em.persist(patient0284);
        em.persist(patient0241);
        em.persist(rdv31);
        em.persist(rdv32);
        em.persist(rdv33);
        em.persist(rdv34);
        em.persist(rdv35);
        em.persist(rdv36);
        em.persist(rdv37);
        em.persist(rdv38);
        em.persist(rdv39);
        em.persist(rdv40);
        em.persist(rdv41);
        em.persist(rdv42);
        em.persist(rdv43);
        em.persist(rdv44);
        em.persist(rdv45);
        em.persist(rdv46);
        em.persist(rdv47);
        em.persist(rdv48);


        et.commit();
        em.close();
        emf.close();


    }
}
