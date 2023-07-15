package information;
import materiel.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import connection.*;

public class Vol{
    int idVol;
    Avion avion;
    Lieu LieuDepart;
    Lieu LieuArrive;
    Timestamp DateHeureDepart;

    public void set_avion(Avion avion)
    {
        this.avion=avion;
    }
    public  Avion get_avion()
    {
        return this.avion;
    }
    public void set_LieuDepart(Lieu Lieu)
    {
        this.LieuDepart=Lieu;
    }
    public Lieu get_LieuDepart()
    {
        return this.LieuDepart;
    }
    public void set_LieuArrive(Lieu Lieu)
    {
        this.LieuArrive=Lieu;
    }
    public Lieu get_LieuArrive()
    {
        return this.LieuArrive;
    }
    public void set_DateHeureDepart(Timestamp DateHeureDepart)
    {
        this.DateHeureDepart=DateHeureDepart;
    }
    public Timestamp get_DateHeureDepart()
    {
        return this.DateHeureDepart;
    }
    public int get_idVol() {
		return this.idVol;
	}

	public void set_idVol(int idVol) {
		this.idVol = idVol;
	}

    public Vol()
    {}

    public Vol(Avion avion,Lieu LieuDepart,Lieu LieuArrive,Timestamp DateHeureDepart,int idVol)
    {
        this.set_avion(avion);    
        this.set_LieuDepart(LieuDepart);   
        this.set_LieuArrive(LieuArrive);   
        this.set_DateHeureDepart(DateHeureDepart);   
        this.set_idVol(idVol);
    }

    public void plannifier()throws Exception
    {
        ConnectionBase conn = new ConnectionBase();
        Connection connex = conn.getConnection();
        Statement state = connex.createStatement();

        String sql = "insert into vol values(seq_vol.nextval,"+this.get_avion().get_idAvion()+","+this.get_LieuDepart().get_nomLieu()+","+this.get_LieuArrive().get_nomLieu()+",TO_TIMESTAMP('"+this.get_DateHeureDepart()+"', 'YYYY-MM-DD HH24:MI:SS'),null,null)";
        ResultSet result=state.executeQuery(sql);

    }

    public void valider()throws Exception{
        ConnectionBase conn = new ConnectionBase();
        Connection connex = conn.getConnection();
        Statement state = connex.createStatement();

        String sql = "update vol set DATEVALIDATION = SYSTIMESTAMP where idVol ="+this.get_idVol();
        ResultSet result=state.executeQuery(sql);
    }

    public void Annuler()throws Exception{
        ConnectionBase conn = new ConnectionBase();
        Connection connex = conn.getConnection();
        Statement state = connex.createStatement();

        String sql = "update vol set DATEANNULATION = SYSTIMESTAMP where idVol ="+this.get_idVol();
        ResultSet result=state.executeQuery(sql);
    }


}