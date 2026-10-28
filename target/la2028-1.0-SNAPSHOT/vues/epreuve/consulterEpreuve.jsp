<%--
    Document   : consulterEpreuves
    Created on : 25 août 2026, 12:27:51
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.la2028.model.Athlete"%>
<%@page import="sio.la2028.model.Pays"%>
<%@page import="sio.la2028.model.Sport"%>
<%@page import="sio.la2028.model.Epreuve"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOS ANGELES 2028</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <style>
            body {
                padding-top: 50px;
            }
            .special {
                padding-top: 50px;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
           <div class="container">
              <div class="navbar-header">
                 <a href="../ServletEpreuve/lister" class="navbar-brand">Système de gestion des épreuves</a>
              </div>
           </div>
        </nav>

        <div class="container special">
            <h2 class="h2">Liste des épreuves</h2>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>nom</th>
                            <th>sport</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Récupération de la liste avec le même nom d'attribut que dans la servlet ("pLesEpreuves")
                            ArrayList<Epreuve> lesEpreuves = (ArrayList<Epreuve>) request.getAttribute("pLesEpreuves");

                            if (lesEpreuves != null) {
                                for (Epreuve e : lesEpreuves) {
                                    out.println("<tr>");
                                    out.println("<td>" + e.getId() + "</td>");

                                    // Lien vers la consultation de l'épreuve par son ID
                                    out.println("<td><a href='../ServletEpreuve/consulter?idEpreuve=" + e.getId() + "'>" + e.getNom() + "</a></td>");

                                    // Affichage du nom du sport associé
                                    if (e.getSport() != null) {
                                        out.println("<td>" + e.getSport().getNom() + "</td>");
                                    } else {
                                        out.println("<td>Aucun sport</td>");
                                    }

                                    out.println("</tr>");
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

    </body>
</html>