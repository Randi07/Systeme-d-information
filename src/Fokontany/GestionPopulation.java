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
public class GestionPopulation {

    public ConnexionBase connexion;

    public GestionPopulation() throws Exception {     
	
        connexion = new ConnexionBase();

    }
	
    public void insert(ClassPopulation NDist) throws Exception {
        String query = "insert into population (ID_FKT,ZERO,SIX,DIXHUIT,SOIXANTE,EFFECTIF_MASCULAIN,EFFECTIF_FEMININ,EFFECTIF_TOTAL,ANNEE) values('" + NDist.ID_FKT + "','" + NDist.getZERO() + "' ,'" + NDist.getSIX() + "','" + NDist.getDIXHUIT() + "','" + NDist.getSOIXANTE() + "','" + NDist.getEFFECTIF_MASCULAIN() + "','" + NDist.getEFFECTIF_FEMININ() + "','" + NDist.getEFFECTIF_TOTAL() + "','" + NDist.getANNEE() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassPopulation obj) throws Exception {
        String query = "UPDATE population SET ZERO='" + obj.getZERO() + "', SIX='" + obj.getSIX() + "', DIXHUIT='" + obj.getDIXHUIT() + "', SOIXANTE='" + obj.getSOIXANTE() + "', EFFECTIF_MASCULAIN='" + obj.getEFFECTIF_MASCULAIN() + "', EFFECTIF_FEMININ='" + obj.getEFFECTIF_FEMININ() + "', EFFECTIF_TOTAL='" + obj.getEFFECTIF_TOTAL() + "', ANNEE='" + obj.getANNEE() + "' WHERE ID_POPULATION ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM population WHERE ID_POPULATION ='" + id + "'";
        connexion.executeUpdate(query);

    }

    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM population order by ID_FKT asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
      public ResultSet nombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT count(*) as ligne FROM population where ANNEE between '"+date1+"' AND '"+date2+"' ";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
     public ResultSet getAllnombretrouverDeuxDate(String date1, String date2) throws Exception {
        String query = "SELECT * FROM population where ANNEE between '"+date1+"' AND '"+date2+"'";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
      public ResultSet liste(ClassPopulation fou) throws Exception{
		String query="SELECT count(ID_POPULATION) as ID_POPULATION FROM population ";
                ResultSet rs = connexion.executeQuery(query);
                     return rs;
	}
 public ResultSet Alaivo(String zero, String six,String dixhuit, String soixante) throws Exception
     {
        String query = "SELECT count(ID_POPULATION) as number FROM population where ZERO = '" + zero + "' and SIX = '"+six+"' and DIXHUIT = '" + dixhuit + "' and SOIXANTE = '"+soixante+"'";
        ResultSet rs = connexion.executeQuery(query);
            return rs;

     }
public ResultSet CountEtudiant(String annee) throws Exception{
         String query = "select count(ID_POPULATION) as number from population where ID_POPULATION = '"+ annee +"'";
         ResultSet rs = connexion.executeQuery(query);
         return rs;

     }
}
