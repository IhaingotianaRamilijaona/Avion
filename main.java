package affichage;
import utils.*;
import information.*;
import materiel.*;
import connection.*;
import java.sql.*;
import java.lang.*;
import java.util.*;
import java.text.*;

public class main{

    public static void main(String[] args)throws Exception
    {
        ConnectionBase conn = new ConnectionBase();
        Connection connex = conn.getConnection();
        Statement state = connex.createStatement();

        Function function = new Function();
        Vol[] vol = function.get_ListVol(connex);

        Avion[] avions = function.get_AllAvion(state);

        Vol[] vols = function.get_Vol_dispo(connex);

        Lieu[] lieux = function.get_Lieu(state);

        System.out.println(vols.length);

        for(int i=0;i<lieux.length;i++)
        {
            // System.out.println(avions.length);            
            System.out.println(lieux[i].get_nomLieu());            
            // System.out.println(avions[i].get_modele());            
            // System.out.println(avions[i]);            
        }
        for(int i=0;i<vol.length;i++)
        {            
            System.out.println(vol[i].get_avion().get_modele());                      
            System.out.println(vol[i].get_avion().get_idAvion());                      
        }
        // System.out.println(avions.length);            
        // System.out.println(avions[1]);            
        connex.close();
    }
    
}