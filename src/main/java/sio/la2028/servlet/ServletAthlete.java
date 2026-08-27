package sio.la2028.servlet;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import sio.la2028.database.DaoAthlete;
import sio.la2028.database.DaoPays;
import sio.la2028.database.DaoSport;
import sio.la2028.form.FormAthlete;
import sio.la2028.model.Athlete;
import sio.la2028.model.Pays;
import sio.la2028.model.Sport;

/**
 *
 * @author zakina
 */
public class ServletAthlete extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        System.out.println("SERVLET CONTEXT=" + servletContext.getContextPath());
        cnx = (Connection) servletContext.getAttribute("connection");

        try {
            System.out.println("INIT SERVLET=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(ServletAthlete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAthlete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletAthlete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();

        // Lister les athlètes
        if (url.equals("/la2028/ServletAthlete/lister")) {
            ArrayList<Athlete> lesAthletes = DaoAthlete.getLesAthletes(cnx);
            request.setAttribute("pLesAthletes", lesAthletes);
            getServletContext().getRequestDispatcher("/vues/athlete/listerAthletes.jsp").forward(request, response);
        }

        // Consulter un athlète
        if (url.equals("/la2028/ServletAthlete/consulter")) {
            int idAthlete = Integer.parseInt((String) request.getParameter("idAthlete"));
            Athlete a = DaoAthlete.getAthleteById(cnx, idAthlete);
            request.setAttribute("pAthlete", a);
            getServletContext().getRequestDispatcher("/vues/athlete/consulterAthlete.jsp").forward(request, response);
        }

        // Afficher le formulaire d'ajout
        if (url.equals("/la2028/ServletAthlete/ajouter")) {
            ArrayList<Pays> lesPays = DaoPays.getLesPays(cnx);
            ArrayList<Sport> lesSports = DaoSport.getLesSports(cnx);

            request.setAttribute("pLesPays", lesPays);
            request.setAttribute("lesSports", lesSports);

            this.getServletContext().getRequestDispatcher("/vues/athlete/ajouterAthlete.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FormAthlete form = new FormAthlete();

        /* Traitement et validation de la requête */
        Athlete ath = form.ajouterAthlete(request);

        /* Stockage du formulaire et de l'objet dans la requête */
        request.setAttribute("form", form);
        request.setAttribute("pAthlete", ath);

        if (form.getErreurs().isEmpty()) {
            Athlete athleteInsere = DaoAthlete.addAthlete(cnx, ath);
            if (athleteInsere != null) {
                request.setAttribute("pAthlete", athleteInsere);
                this.getServletContext().getRequestDispatcher("/vues/athlete/consulterAthlete.jsp").forward(request, response);
            } else {
                // Erreur d'insertion en BDD
                request.setAttribute("form", form);
                this.getServletContext().getRequestDispatcher("/vues/athlete/ajouterAthlete.jsp").forward(request, response);
            }
        } else {
            // Réaffichage du formulaire avec les erreurs et rechargement des listes
            ArrayList<Pays> lesPays = DaoPays.getLesPays(cnx);
            ArrayList<Sport> lesSports = DaoSport.getLesSports(cnx);

            request.setAttribute("pLesPays", lesPays);
            request.setAttribute("lesSports", lesSports);

            this.getServletContext().getRequestDispatcher("/vues/athlete/ajouterAthlete.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de gestion des athlètes";
    }
}