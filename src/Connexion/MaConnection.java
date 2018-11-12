
package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JAHYANI
 */
public class MaConnection {
    public static Connection seconnecter(){
        String pilote = "org.gjt.mm.mysql.Driver";
        Connection connexion = null;
        try{
             Class.forName(pilote);
             connexion = DriverManager.getConnection("jdbc:mysql://localhost/sirmania", "root", "");   
       }
        catch(Exception ex){
           ex.printStackTrace();
        }
        return connexion;
    }
    
}