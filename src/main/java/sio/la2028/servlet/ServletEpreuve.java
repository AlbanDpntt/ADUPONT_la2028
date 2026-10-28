package sio.la2028.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sio.la2028.database.DaoEpreuve;
import sio.la2028.model.Epreuve;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletEpreuve", urlPatterns = {"/ServletEpreuve/*"})
public class ServletEpreuve extends HttpServlet {

    Connection cnx ;

    @Override
    public void init()
    {
        ServletContext servletContext=getServletContext();
        cnx = (Connection)servletContext.getAttribute("connection");
        try {
            System.out.println("INIT SERVLET=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(ServletEpreuve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();
        System.out.println("URL reçue : " + url); // Pour vérifier dans la console

        if(url.equals("/la2028/ServletEpreuve/lister"))
        {
            ArrayList<Epreuve> lesEpreuves = DaoEpreuve.getLesEpreuves(cnx);
            request.setAttribute("pLesEpreuves", lesEpreuves);

            // CORRECTION ICI : "listerEpreuves.jsp" avec un "s"
            getServletContext().getRequestDispatcher("/vues/epreuve/listerEpreuves.jsp").forward(request, response);

        }
        else if(url.equals("/la2028/ServletEpreuve/consulter")){
            int idEpreuve = Integer.parseInt(request.getParameter("idEpreuve"));
            Epreuve uneEpreuve = DaoEpreuve.getEpreuveById(cnx, idEpreuve);
            request.setAttribute("pEpreuve", uneEpreuve);
            getServletContext().getRequestDispatcher("/vues/epreuve/consulterEpreuve.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}