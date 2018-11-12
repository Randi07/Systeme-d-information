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
public class GestionSanitaire {

    public ConnexionBase connexion;

    public GestionSanitaire() throws Exception {     
	
        connexion = new ConnexionBase();

    }
	
    public void insert(ClassSanitaire NDist) throws Exception {
        String query = "insert into sanitaire (ID_FKT,INFRAS_SANITAIRE,NOMBRE_INFRAS_SANITAIRE,NATURE_INFRAS_SANITAIRE,EMPLOYES,NOMBRE_EMPLOYES,ETAT_INFRAS_SANITAIRE,REMARQUE,ANNEE) values('" + NDist.ID_FKT + "','" + NDist.getINFRAS_SANITAIRE() + "' ,'" + NDist.getNOMBRE_INFRAS_SANITAIRE() + "','" + NDist.getNATURE_INFRAS_SANITAIRE() + "','" + NDist.getEMPLOYES() + "','" + NDist.getNOMBRE_EMPLOYES() + "','" + NDist.getETAT_INFRAS_SANITAIRE() + "','" + NDist.getREMARQUE() + "','" + NDist.getANNEE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassSanitaire obj) throws Exception {
        String query = "UPDATE sanitaire SET INFRAS_SANITAIRE='" + obj.getINFRAS_SANITAIRE() + "', NOMBRE_INFRAS_SANITAIRE='" + obj.getNOMBRE_INFRAS_SANITAIRE() + "', NATURE_INFRAS_SANITAIRE='" + obj.getNATURE_INFRAS_SANITAIRE() + "', EMPLOYES='" + obj.getEMPLOYES() + "', NOMBRE_EMPLOYES='" + obj.getNOMBRE_EMPLOYES() + "', ETAT_INFRAS_SANITAIRE='" + obj.getETAT_INFRAS_SANITAIRE() + "', REMARQUE='" + obj.getREMARQUE() + "', ANNEE='" + obj.getANNEE() + "' WHERE ID_SANITAIRE ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM sanitaire WHERE ID_SANITAIRE ='" + id + "'";
        connexion.executeUpdate(query);

    }

    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM sanitaire order by ID_SANITAIRE asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet nombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT count(*) as ligne FROM sanitaire where ANNEE between '"+date1+"' AND '"+date2+"' ";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet getAllnombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT * FROM sanitaire where ANNEE between '"+date1+"' AND '"+date2+"'";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
      public ResultSet liste(ClassSanitaire fou) throws Exception{
		String query="SELECT count(ID_SANITAIRE) as ID_SANITAIRE FROM sanitaire ";
                ResultSet rs = connexion.executeQuery(query);
                     return rs;
	}
}
