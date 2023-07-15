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

public class Servlet_Valider_Vol extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try
        {   
            ConnectionBase conn = new ConnectionBase();
            Connection connex = conn.getConnection();
            Statement state = connex.createStatement();

            Function function = new Function();
            Vol vol = function.get_Vol(Integer.parseInt(req.getParameter("idVol")),connex);
            vol.valider();
            res.sendRedirect("vol_dispo");
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Tsy mi redirect");
        }
    }

}