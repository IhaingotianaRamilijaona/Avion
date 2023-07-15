import information.*;
import connection.*;
import utils.*;
import materiel.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.lang.*;
import java.util.*;
import java.text.*;

public class Servlet_insertAvion extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        
        try
        {   
            res.setContentType("text/plain"); 
            PrintWriter out = res.getWriter();
            // int pourcentageBusiness = 100-Integer.parseInt(req.getParameter("economique"));
            // int nbBusiness = pourcentageBusiness * 100 / Integer.parseInt(req.getParameter("economique"));
            // int nbEconom = Integer.parseInt(req.getParameter("economique")) * 100 / Integer.parseInt(req.getParameter("economique"));
            // Avion avion = new Avion();
            // avion.set_capacite(Integer.parseInt(req.getParameter("capacite"))); 
            // avion.set_modele((String) req.getParameter("modele")); 
            // avion.set_typeCourier((String) req.getParameter("type")); 
            // avion.set_nbBusiness(nbBusiness); 
            // avion.set_nbEconom(nbEconom); 

            // out.println(req.getParameter("capacite")); 
            // out.println(req.getParameter("modele")); 
            // out.println(req.getParameter("type")); 

            // out.println(avion.get_capacite()); 
            // out.println(avion.get_modele()); 
            // out.println(avion.get_typeCourier()); 
            // out.println(avion.get_nbBusiness()); 
            // out.println(avion.get_nbEconom()); 

            // avion.insert();
            // res.sendRedirect("listVol");
        }catch(Exception e)
        {

        }
    }

}