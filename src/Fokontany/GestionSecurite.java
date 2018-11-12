/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fokontany;

import Connexion.ConnexionBase;
import java.sql.ResultSet;

/**
 *
 * @author JAHYA
 */
public class GestionSecurite {

    public ConnexionBase connexion;

    public GestionSecurite() throws Exception {     
	
        connexion = new ConnexionBase();

    }
	
    public void insert(ClassSecurite NDist) throws Exception {
        String query = "insert into securite (ID_FKT,CATEG_SEC,NOMBRE,REMARQUE,ANNEE)values('" + NDist.ID_FKT + "','" + NDist.getCATEG_SEC() + "' ,'" + NDist.getNOMBRE() + "' ,'" + NDist.getREMARQUE() + "','" + NDist.getANNEE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassSecurite obj) throws Exception {
        String query = "UPDATE securite SET ID_SECURITE='" + obj.getID_SECURITE() + "',ID_FKT='" + obj.ID_FKT + "', CATEG_SEC='" + obj.getCATEG_SEC() + "', NOMBRE='" + obj.getNOMBRE() + "', REMARQUE='" + obj.getREMARQUE() + "', ANNEE='" + obj.getANNEE() + "' WHERE ID_SECURITE ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM securite WHERE ID_SECURITE ='" + id + "'";
        connexion.executeUpdate(query);

    }

    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM securite order by ID_COM asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet nombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT count(*) as ligne FROM securite where ANNEE between '"+date1+"' AND '"+date2+"' ";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet getAllnombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT * FROM securite where ANNEE between '"+date1+"' AND '"+date2+"'";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
      public ResultSet liste(ClassSecurite fou) throws Exception{
		String query="SELECT count(ID_SECURITE) as ID_SECURITE FROM securite ";
                ResultSet rs = connexion.executeQuery(query);
                     return rs;
	}
}
