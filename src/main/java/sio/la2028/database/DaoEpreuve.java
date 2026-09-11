package sio.la2028.database;
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
            requeteSql = cnx.prepareStatement("select * from epreuve");
            //System.out.println("REQ="+ requeteSql);
            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()){

                Epreuve e = new Epreuve();
                e.setId(resultatRequete.getInt("id"));
                e.setNom(resultatRequete.getString("nom"));

                Sport sport = new Sport();
                sport.setId(resultatRequete.getInt("sport_id"));
                sport.setNom(resultatRequete.getString("sport_nom"));

                e.setSport(sport);

                lesEpreuves.add(e);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLespayss e généré une erreur");
        }
        return lesEpreuves;

    }
}
