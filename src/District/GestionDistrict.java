/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package District;
import Connexion.ConnexionBase;
import java.sql.ResultSet;

/**
 *
 * @author JAHYA
 */
public class GestionDistrict {

    public ConnexionBase connexion;

    public GestionDistrict() throws Exception {
        connexion = new ConnexionBase();

    }

    public void insert(ClassDistrict NDist) throws Exception {
        String query = "insert into district (ID_DIST,NOM_DIST,HISTORIQUE_DIST,SUPERFICIE)values('" + NDist.getID_DIST() + "','" + NDist.getNOM_DIST() + "','" + NDist.getHISTORIQUE_DIST() +"','"+ NDist.getSUPERFICIE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassDistrict obj) throws Exception {
        String query = "UPDATE district SET NOM_DIST='" + obj.getNOM_DIST() + "', HISTORIQUE_DIST='" + obj.getHISTORIQUE_DIST() + "', SUPERFICIE='" + obj.getSUPERFICIE() + "' WHERE ID_DIST ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM district WHERE ID_DIST='" + id + "'";
        connexion.executeUpdate(query);

    }

    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM district order by NOM_DIST asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
}
