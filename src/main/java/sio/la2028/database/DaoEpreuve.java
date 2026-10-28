package sio.la2028.database;
import sio.la2028.model.Athlete;
import sio.la2028.model.Epreuve;
import sio.la2028.model.Sport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoEpreuve {

    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Epreuve> getLesEpreuves(Connection cnx){

        ArrayList<Epreuve> lesEpreuves = new ArrayList<Epreuve>();
        try{
            requeteSql = cnx.prepareStatement(
                    "SELECT e.id as e_id, e.nom as e_nom, s.id as sport_id, s.nom as sport_nom " +
                            "FROM epreuve e " +
                            "INNER JOIN sport s ON e.sport_id = s.id"
            );
            //System.out.println("REQ="+ requeteSql);
            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()){

                Epreuve e = new Epreuve();
                e.setId(resultatRequete.getInt("e_id"));
                e.setNom(resultatRequete.getString("e_nom"));

                Sport sport = new Sport();
                sport.setId(resultatRequete.getInt("sport_id"));
                sport.setNom(resultatRequete.getString("sport_nom"));

                e.setSport(sport);

                lesEpreuves.add(e);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesEpreuves a généré une erreur");
        } finally {
            try { if (resultatRequete != null) resultatRequete.close(); } catch (SQLException ignored) {}
            try { if (requeteSql != null) requeteSql.close(); } catch (SQLException ignored) {}
        }
        return lesEpreuves;
    }

    public static ArrayList<Athlete> getAthleteByEpreuveId(Connection cnx, int idEpreuve) {
        Athlete a = null;
        ArrayList<Athlete> lesAthletes = new ArrayList<>();

        try{
            requeteSql = cnx.prepareStatement(
                    "SELECT a.id as a_id, a.prenom as a_prenom, a.nom as a_nom, a.date_de_naissance as a_dob, " +
                            "p.id as p_id, p.nom as p_nom " +
                            "FROM athlete a " +
                            "INNER JOIN pays p ON a.pays_id = p.id " +
                            "INNER JOIN ... ON ... " +
                            "WHERE ... = ?"
            );
            requeteSql.setInt(1, idEpreuve);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()){
                a = new Athlete();
                a.setId(resultatRequete.getInt("a_id"));
                a.setPrenom(resultatRequete.getString("a_prenom"));
                a.setNom(resultatRequete.getString("a_nom"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête getAthleteByEpreuveId a généré une erreur");
        } finally {
            try { if (resultatRequete != null) resultatRequete.close(); } catch (SQLException ignored) {}
            try { if (requeteSql != null) requeteSql.close(); } catch (SQLException ignored) {}
        }

        return lesAthletes;
    }

    public static Epreuve getEpreuveById(Connection cnx, int idEpreuve) {
        Epreuve e = null;
        try {
            requeteSql = cnx.prepareStatement(
                    "SELECT e.id as e_id, e.nom as e_nom, s.id as sport_id, s.nom as sport_nom " +
                            "FROM epreuve e " +
                            "INNER JOIN sport s ON e.sport_id = s.id " +
                            "WHERE e.id = ?"
            );
            requeteSql.setInt(1, idEpreuve);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                e = new Epreuve();
                e.setId(resultatRequete.getInt("e_id"));
                e.setNom(resultatRequete.getString("e_nom"));

                Sport sport = new Sport();
                sport.setId(resultatRequete.getInt("sport_id"));
                sport.setNom(resultatRequete.getString("sport_nom"));

                e.setSport(sport);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("La requête getEpreuveById a généré une erreur");
        } finally {
            try { if (resultatRequete != null) resultatRequete.close(); } catch (SQLException ignored) {}
            try { if (requeteSql != null) requeteSql.close(); } catch (SQLException ignored) {}
        }
        return e;
    }
}
