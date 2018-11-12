/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fokontany;
import Connexion.ConnexionBase;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author JAHYA
 */
public class GestionFokontany {

    public ConnexionBase connexion;

    public GestionFokontany() throws Exception {
        connexion = new ConnexionBase();

    }

    public void insert(ClassFokontany NDist) throws Exception {
       String query = "select CONCAT('" + NDist.getID_FKT() + "_F',cast((SELECT MAX(CAST(SUBSTRING(ID_FKT,8) AS UNSIGNED)+1) FROM fokontany WHERE SUBSTRING(ID_FKT,1,6) = '" + NDist.getID_FKT() + "') as CHAR)) as a";
        ResultSet rs = connexion.executeQuery(query);
        String ID;
        ID = NDist.getID_FKT() + "_F1";
        if (rs.next()) ID = rs.getString("a") == null ? ID:rs.getString("a");
        
        query = "insert into fokontany(ID_FKT,NOM_FKT,HISTORIQUE_FKT, SUPERFICIE) values('" + ID + "' ,'" + NDist.getNOM_FKT() + "','" + NDist.getHISTORIQUE_FKT() + "', '" + NDist.getSUPERFICIE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassFokontany obj) throws Exception {
        String query = "UPDATE fokontany SET  NOM_FKT='" + obj.getNOM_FKT() + "', HISTORIQUE_FKT='" + obj.getHISTORIQUE_FKT() + "', SUPERFICIE='" + obj.getSUPERFICIE() + "' WHERE ID_FKT ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM fokontany WHERE ID_FKT='" + id + "'";
        connexion.executeUpdate(query);

    }
    
 
    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM fokontany order by ID_FKT asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
}

