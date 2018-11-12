package Commune;

import Connexion.ConnexionBase;
import java.sql.ResultSet;

/**
 *
 * @author JAHYA
 */
public class GestionCommune {

    public ConnexionBase connexion;

    public GestionCommune() throws Exception {
        connexion = new ConnexionBase();

    }

    public void insert(ClassCommune NDist) throws Exception {
        String query = "select CONCAT('" + NDist.getID_COM() + "_C',cast((SELECT MAX(CAST(SUBSTRING(ID_COM,6) AS UNSIGNED)+1) FROM commune WHERE SUBSTRING(ID_COM,1,3) = '" + NDist.getID_COM() + "') as CHAR)) as a";
        ResultSet rs = connexion.executeQuery(query);
        String ID = NDist.getID_COM() + "_C1";
        if (rs.next()) ID = rs.getString("a") == null ? ID:rs.getString("a");
        query = "insert into commune (ID_COM,NOM_COM,HISTORIQUE_COM,DRN7,DRN35,DRN41,DDIST,DREG,SUPERFICIE)values('"+ ID +"','" + NDist.getNOM_COM() + "','" + NDist.getHISTORIQUE_COM() + "' ,'" + NDist.getDRN7() + "' ,'" + NDist.getDRN35() + "','" + NDist.getDRN41() + "','" + NDist.getDDIST() + "','" + NDist.getDREG() + "','" + NDist.getSUPERFICIE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassCommune obj) throws Exception {
        String query = "UPDATE commune SET ID_COM='" + obj.getID_COM() + "', NOM_COM='" + obj.getNOM_COM() + "', HISTORIQUE_COM='" + obj.getHISTORIQUE_COM() + "' ,DRN7='" + obj.getDRN7() + "' ,DRN35='" + obj.getDRN35() + "',DRN41='" + obj.getDRN41() + "',DDIST='" + obj.getDDIST() + "',DREG='" + obj.getDREG() + "',SUPERFICIE='" + obj.getSUPERFICIE() + "' WHERE ID_COM ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM commune WHERE ID_COM='" + id + "'";
        connexion.executeUpdate(query);

    }

    /*public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM commune order by ID_DIST asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }*/
}
