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

public class Servlet_PlannifierVol extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        
        try
        {   
            ConnectionBase conn = new ConnectionBase();
            Connection connex = conn.getConnection();
            Statement state = connex.createStatement();

            Function function = new Function();      
            Avion avion = function.get_Avion(Integer.parseInt( req.getParameter("idAvion")),state);
            Lieu lieuArrive = function.get_Lieu(Integer.parseInt( req.getParameter("lieu_arrive")),state);
            Lieu lieuDepart = function.get_Lieu(Integer.parseInt( req.getParameter("lieu_depart")),state);
            Timestamp dateHeureDepart = Timestamp.valueOf(req.getParameter("date_heure_depart"));

            Vol vol = new Vol();
            vol.set_avion(avion);
            vol.set_LieuArrive(lieuArrive);
            vol.set_LieuDepart(lieuDepart);
            vol.set_DateHeureDepart(dateHeureDepart);
            vol.plannifier();

            res.sendRedirect("index.jsp");
            System.out.println("mi redirect");
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Tsy mi redirect");
        }
    }

}