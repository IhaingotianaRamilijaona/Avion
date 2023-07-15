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

public class Servlet_Vol_Annuler extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        
        try
        {   
            ConnectionBase conn = new ConnectionBase();
            Connection connex = conn.getConnection();
            Statement state = connex.createStatement();

            Function function = new Function();
            Vol[] vols = function.get_Vol_Annuler(connex);
            req.setAttribute("Vols", vols);
            req.getRequestDispatcher("vol_annuler.jsp").forward(req, res);
            System.out.println("mi redirect");
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Tsy mi redirect");
        }
    }

}