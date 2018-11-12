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
public class GestionSport {

    public ConnexionBase connexion;

    public GestionSport() throws Exception {     
	
        connexion = new ConnexionBase();

    }
	
    public void insert(ClassSport NDist) throws Exception {
        String query = "insert into sportive (ID_FKT,CATEG_SPORT,EXISTANCE_TERRAIN,ETAT_TERRAIN,NOMBRE_TERRAIN,REMARQUE_SPORT,ANNEE)values('" + NDist.ID_FKT + "','" + NDist.getCATEG_SPORT() + "' ,'" + NDist.getEXISTANCE_TERRAIN() + "' ,'" + NDist.getETAT_TERRAIN() + "','" + NDist.getNOMBRE_TERRAIN() + "','" + NDist.getREMARQUE_SPORT() + "','" + NDist.getANNEE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassSport obj) throws Exception {
        String query = "UPDATE sportive SET CATEG_SPORT='" + obj.getCATEG_SPORT() + "', EXISTANCE_TERRAIN='" + obj.getEXISTANCE_TERRAIN() + "', ETAT_TERRAIN='" + obj.getETAT_TERRAIN() + "', NOMBRE_TERRAIN='" + obj.getNOMBRE_TERRAIN() + "', REMARQUE_SPORT='" + obj.getREMARQUE_SPORT() + "', ANNEE='" + obj.getANNEE() + "' WHERE ID_SPORT ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM sportive WHERE ID_SPORT ='" + id + "'";
        connexion.executeUpdate(query);

    }

    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM sportive order by ID_COM asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet nombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT count(*) as ligne FROM sportive where ANNEE between '"+date1+"' AND '"+date2+"' ";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet getAllnombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT * FROM sportive where ANNEE between '"+date1+"' AND '"+date2+"'";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
      public ResultSet liste(ClassSport fou) throws Exception{
		String query="SELECT count(ID_SPORT) as ID_SPORT FROM sportive ";
                ResultSet rs = connexion.executeQuery(query);
                     return rs;
	}
}
