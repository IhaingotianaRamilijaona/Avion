package utils;
import information.*;
import materiel.*;
import java.sql.*;
import java.lang.*;
import java.util.*;
import java.text.*;

public class Function{

    public Vol get_Vol(int idVol,Connection connex)throws Exception
    {
        Statement state = connex.createStatement();
        ResultSet result=state.executeQuery("select * from vol where idVol="+idVol);
        Vol vol = new Vol();
        while(result.next())
        {
            Statement state1 = connex.createStatement();
            Statement state2 = connex.createStatement();
            Statement state3 = connex.createStatement();
            
            vol.set_idVol(result.getInt(1));
            vol.set_avion(this.get_Avion(result.getInt(2),state1)); 
            vol.set_LieuDepart(this.get_Lieu(result.getInt(3),state2));
            vol.set_LieuArrive(this.get_Lieu(result.getInt(4),state3)); 
            Timestamp DateHeureDepart = Timestamp.valueOf(result.getString(5));  
            vol.set_DateHeureDepart(DateHeureDepart);  
        }
        return vol;
        // connex.close();
    }

    

    public Vol[] get_Vol_dispo(Connection connex)throws Exception
    {
        Statement state = connex.createStatement();
        Vector <Vol> v= new Vector <Vol>();
        ResultSet result=state.executeQuery("select * from vol_dispo");
        while(result.next())
        {
            Vol vol = this.get_Vol(result.getInt(1),connex);
            v.add(vol);
        }
        Vol[] tab = new Vol[v.size()];
        v.copyInto(tab);
        return tab;
        // connex.close();
    }

    public Vol[] get_ListVol(Connection connex)throws Exception
    {
        Statement state = connex.createStatement();
        Vector <Vol> v= new Vector <Vol>();
        ResultSet result=state.executeQuery("select * from vol");
        while(result.next())
        {
            Statement state1 = connex.createStatement();
            Statement state2 = connex.createStatement();
            Statement state3 = connex.createStatement();

            Avion avion = this.get_Avion(result.getInt(2),state1);
            Lieu LieuDepart = this.get_Lieu(result.getInt(3),state2);
            Lieu lieuArrive = this.get_Lieu(result.getInt(4),state3);
            Timestamp DateHeureDepart = Timestamp.valueOf(result.getString(5));
            Vol vol=new Vol(avion,LieuDepart,lieuArrive,DateHeureDepart,result.getInt(1));
            v.add(vol);
        }

        Vol[] tab = new Vol[v.size()];
        v.copyInto(tab);
        // connex.close();
        return tab;
    }    
    public Avion get_Avion(int idAvion,Statement state)throws Exception
    {
        Avion avion = new Avion();
        ResultSet result=state.executeQuery("select * from get_Avion where idAvion ="+ idAvion);
        while(result.next())
        {
            avion.set_idAvion(result.getInt(1));
            avion.set_capacite(result.getInt(2));
            avion.set_nbEconom(result.getInt(3));
            avion.set_nbBusiness(result.getInt(4));
            avion.set_modele(result.getString(5));
            avion.set_typeCourier(result.getString(7));
        }
        return avion;
    }
    public Avion[] get_AllAvion(Statement state)throws Exception
    {
        Vector <Avion> v= new Vector <Avion>();
        ResultSet result=state.executeQuery("select  * from get_Avion");
        while(result.next())
        {
            // System.out.println(result.getInt(1));  
            Avion avion = new Avion(result.getInt(1),result.getInt(2),result.getInt(3),result.getInt(4),result.getString(5),result.getString(7));
            v.add(avion);
        }

        Avion[] tab = new Avion[v.size()];
        v.copyInto(tab);
        return tab;
    }
    public Lieu get_Lieu(int idLieu,Statement state)throws Exception
    {
        Lieu lieu = new Lieu();
        ResultSet result=state.executeQuery("select  * from Lieu where idLieu ="+ idLieu);
        while(result.next())
        {
            lieu.set_idLieu(result.getInt(1));
            lieu.set_nomLieu(result.getString(2));
        }
        return lieu;
    }

    public Lieu[] get_Lieu(Statement state)throws Exception
    {
        Vector <Lieu> v= new Vector <Lieu>();
        ResultSet result=state.executeQuery("select * from Lieu");
        while(result.next())
        {
            Lieu lieu = new Lieu(result.getInt(1),result.getString(2));
            v.add(lieu);
        }
        Lieu[] tab = new Lieu[v.size()];
        v.copyInto(tab);
        return tab;
    }

    public Vol[] get_Vol_Non_valide(Connection connex)throws Exception
    {
        Statement state = connex.createStatement(); 
        Vector <Vol> v= new Vector <Vol>();
        ResultSet result=state.executeQuery("select idVol from get_Vol_and_Tarif WHERE DateValidation is NULL group by idVol");
        while(result.next())
        {
            Vol vol = this.get_Vol(result.getInt(1),connex);
            v.add(vol);
        }
        Vol[] tab = new Vol[v.size()];
        v.copyInto(tab);
        return tab;
    }

    public Vol[] get_Vol_Annuler(Connection connex)throws Exception
    {
        Statement state = connex.createStatement(); 
        Vector <Vol> v= new Vector <Vol>();
        ResultSet result=state.executeQuery("select idVol from get_Vol_and_Tarif WHERE DateAnnulation is NOT NULL group by idVol");
        while(result.next())
        {
            Vol vol = this.get_Vol(result.getInt(1),connex);
            v.add(vol);
        }
        Vol[] tab = new Vol[v.size()];
        v.copyInto(tab);
        return tab;
    }

}