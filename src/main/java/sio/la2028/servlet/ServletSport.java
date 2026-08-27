package sio.la2028.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import sio.la2028.database.DaoAthlete;
import sio.la2028.database.DaoPays;
import sio.la2028.database.DaoSport;
import sio.la2028.model.Athlete;
import sio.la2028.model.Pays;
import sio.la2028.model.Sport;

@WebServlet(name = "ServletAthlete", urlPatterns = {"/athlete/*"})
public class ServletAthlete extends HttpServlet {

    private Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("cnx");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getPathInfo();

        if (url == null || url.equals("/lister")) {
            ArrayList<Athlete> lesAthletes = DaoAthlete.getLesAthletes(cnx);
            request.setAttribute("lesAthletes", lesAthletes);
            getServletContext().getRequestDispatcher("/vues/athlete/listerAthlete.jsp").forward(request, response);

        } else if (url.equals("/ajouter")) {
            // Chargement des listes pour les menus déroulants
            ArrayList<Pays> lesPays = DaoPays.getLesPays(cnx);
            ArrayList<Sport> lesSports = DaoSport.getLesSports(cnx);

            request.setAttribute("pLesPays", lesPays);
            request.setAttribute("lesSports", lesSports);

            getServletContext().getRequestDispatcher("/vues/athlete/ajouterAthlete.jsp").forward(request, response);

        } else if (url.equals("/consulter")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Athlete a = DaoAthlete.getAthleteById(cnx, id);
            request.setAttribute("athlete", a);
            getServletContext().getRequestDispatcher("/vues/athlete/consulterAthlete.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getPathInfo();

        if (url != null && url.equals("/ajouter")) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dob = request.getParameter("date_de_naissance");
            int idPays = Integer.parseInt(request.getParameter("idPays"));
            int idSport = Integer.parseInt(request.getParameter("idSport"));

            Athlete a = new Athlete();
            a.setNom(nom);
            a.setPrenom(prenom);
            a.setDob(dob);

            Pays p = new Pays();
            p.setId(idPays);
            a.setPays(p);

            Sport s = new Sport();
            s.setId(idSport);
            a.setSport(s);

            DaoAthlete.addAthlete(cnx, a);

            response.sendRedirect(request.getContextPath() + "/athlete/lister");
        }
    }
}