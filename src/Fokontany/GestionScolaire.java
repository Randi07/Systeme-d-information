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
public class GestionScolaire {

    public ConnexionBase connexion;

    public GestionScolaire() throws Exception {     
	
        connexion = new ConnexionBase();

    }
	
    public void insert(ClassScolaire NDist) throws Exception {
        String query = "insert into scolaire (ID_FKT,CATEG_SCOLAIRE,NATURE_SCOLAIRE,NOMBRE_SCOLAIRE,ETAT_SCOLAIRE,ELEVE_MASCULAIN,ELEVE_FEMININ,EFFECTIF_TOTAL,ANNEE_SCOLAIRE,ANNEE) values('" + NDist.ID_FKT + "','" + NDist.getCATEG_SCOLAIRE() + "' ,'" + NDist.getNATURE_SCOLAIRE() + "','" + NDist.getNOMBRE_SCOLAIRE() + "','" + NDist.getETAT_SCOLAIRE() + "','" + NDist.getELEVE_MASCULAIN() + "','" + NDist.getELEVE_FEMININ() + "','" + NDist.getEFFECTIF_TOTAL() + "','" + NDist.getANNEE_SCOLAIRE() + "','" + NDist.getANNEE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassScolaire obj) throws Exception {
        String query = "UPDATE scolaire SET CATEG_SCOLAIRE='" + obj.getCATEG_SCOLAIRE() + "', NATURE_SCOLAIRE='" + obj.getNATURE_SCOLAIRE() + "', NOMBRE_SCOLAIRE='" + obj.getNOMBRE_SCOLAIRE() + "', ETAT_SCOLAIRE='" + obj.getETAT_SCOLAIRE() + "', ELEVE_MASCULAIN='" + obj.getELEVE_MASCULAIN() + "', ELEVE_FEMININ='" + obj.getELEVE_FEMININ() + "', EFFECTIF_TOTAL='" + obj.getEFFECTIF_TOTAL() + "', ANNEE_SCOLAIRE='" + obj.getANNEE_SCOLAIRE() + "', ANNEE='" + obj.getANNEE() + "' WHERE ID_SCOLAIRE ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM scolaire WHERE ID_SCOLAIRE ='" + id + "'";
        connexion.executeUpdate(query);

    }

    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM scolaire order by ID_FKT asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
      public ResultSet nombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT count(*) as ligne FROM scolaire where ANNEE between '"+date1+"' AND '"+date2+"' ";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet getAllnombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT * FROM scolaire where ANNEE between '"+date1+"' AND '"+date2+"'";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
      public ResultSet liste(ClassScolaire fou) throws Exception{
		String query="SELECT count(ID_SCOLAIRE) as ID_SCOLAIRE FROM scolaire ";
                ResultSet rs = connexion.executeQuery(query);
                     return rs;
	}

}
