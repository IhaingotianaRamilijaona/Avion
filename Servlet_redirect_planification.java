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

public class Servlet_redirect_planification extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        
        try
        {   
            ConnectionBase conn = new ConnectionBase();
            Connection connex = conn.getConnection();
            Statement state = connex.createStatement();

            Function function = new Function();
            Avion[] avions = function.get_AllAvion(state);
            Lieu[] lieux = function.get_Lieu(state);
            req.setAttribute("Avions", avions);
            req.setAttribute("Lieux", lieux);
            req.getRequestDispatcher("plannifier_vol.jsp").forward(req, res);
            System.out.println("mi redirect");
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Tsy mi redirect");
        }
    }

}