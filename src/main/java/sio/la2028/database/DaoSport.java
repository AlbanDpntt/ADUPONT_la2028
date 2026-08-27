package sio.la2028.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sio.la2028.model.Sport;

public class DaoSport {

    public static ArrayList<Sport> getLesSports(Connection cnx) {
        ArrayList<Sport> lesSports = new ArrayList<>();
        String sql = "SELECT id, nom FROM sport ORDER BY nom";

        try (PreparedStatement requeteSql = cnx.prepareStatement(sql);
             ResultSet resultatRequete = requeteSql.executeQuery()) {

            while (resultatRequete.next()) {
                Sport s = new Sport();
                s.setId(resultatRequete.getInt("id"));
                s.setNom(resultatRequete.getString("nom"));

                lesSports.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête getLesSports a généré une erreur");
        }
        return lesSports;
    }
}