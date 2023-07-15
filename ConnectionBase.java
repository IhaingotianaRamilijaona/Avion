package connection;
import java.sql.*;

public class ConnectionBase
{
	public Connection getConnection() 
	{
		Connection connexion = null;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "avion", "avion");
			// System.out.println("connecté");
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return connexion;
	}
	
	
}