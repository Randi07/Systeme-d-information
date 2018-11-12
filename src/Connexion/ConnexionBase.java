package Connexion;
import java.sql.*;
/**
 *
 
 */
public class ConnexionBase {
  	public Connection con;
	public Statement stat;

	public ConnexionBase() throws Exception {
		Class.forName("org.gjt.mm.mysql.Driver");
		con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWD) ;
		stat = con.createStatement();
	}

	public ResultSet executeQuery (String query) throws Exception {
		ResultSet res=null;
		res=  stat.executeQuery(query);
		return(res);
	}
	
	public int executeUpdate (String query) throws Exception {
		int res=0;
		res=stat.executeUpdate(query);
		return(res);
	}
	public void close() throws Exception {
		stat.close();
		con.close();
	}

    public Statement createStatement() throws SQLException {
       stat=con.createStatement();
              return stat;
    }

    
}

