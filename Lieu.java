package information;
public class Lieu{
    int idLieu;
    String nomLieu;

    public void set_idLieu(int idLieu)
    {
        this.idLieu=idLieu;
    }
    public int get_idLieu()
    {
        return this.idLieu;
    }
    public void set_nomLieu(String nomLieu)
    {
        this.nomLieu=nomLieu;
    }
    public String get_nomLieu()
    {
        return this.nomLieu;
    }

    public Lieu()
    {}

    public Lieu(int idLieu,String nomLieu)
    {
        this.set_idLieu(idLieu);
        this.set_nomLieu(nomLieu);
    }
}