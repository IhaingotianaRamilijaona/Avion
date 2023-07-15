package materiel;
import connection.*;
import java.sql.*;
import java.lang.*;
import java.util.*;
import java.text.*;

public class Avion{
    int idAvion;
    int capacite;
    int nbEconom;
    int nbBusiness;
    String modele;
    String typeCourier;

    public void set_idAvion(int idAvion)
    {
        this.idAvion=idAvion;
    }
    public int get_idAvion()
    {
        return this.idAvion;
    }

    public void set_capacite(int capacite)
    {
        this.capacite=capacite;
    }
    public  int get_capacite()
    {
        return this.capacite;
    }
    public void set_nbEconom(int nbEconom)
    {
        this.nbEconom=nbEconom;
    }
    public  int get_nbEconom()
    {
        return this.nbEconom;
    }
    public void set_nbBusiness(int nbBusiness)
    {
        this.nbBusiness=nbBusiness;
    }
    public  int get_nbBusiness()
    {
        return this.nbBusiness;
    }
    public void set_modele(String modele)
    {
        this.modele=modele;
    }
    public  String get_modele()
    {
        return this.modele;
    }
    public void set_typeCourier(String typeCourier)
    {
        this.typeCourier=typeCourier;
    }
    public  String get_typeCourier()
    {
        return this.typeCourier;
    }

    public Avion()
    {

    }

    public Avion(int idAvion,int capacite,int nbEconom,int nbBusiness,String modele,String typeCourier)
    {
        this.set_idAvion(idAvion);
        this.set_capacite(capacite);
        this.set_nbEconom(nbEconom);
        this.set_nbBusiness(nbBusiness);
        this.set_modele(modele);
        this.set_typeCourier(typeCourier);
    }

    public void insert()throws Exception
    {
        ConnectionBase conn = new ConnectionBase();
        Connection connex = conn.getConnection();
        Statement state = connex.createStatement();
        String req="insert into avion values(seq_avion.nextval,"+this.get_capacite()+","+this.get_nbEconom()+","+this.get_nbBusiness()+",'"+this.get_modele()+"','"+this.get_typeCourier()+"')";
        System.out.println(req);
        ResultSet resutl=state.executeQuery(req);
        connex.commit();
        connex.close();
    }

}