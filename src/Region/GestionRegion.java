/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Region;

import Connexion.ConnexionBase;
import java.sql.ResultSet;

/**
 *
 * @author JAHYA
 */
public class GestionRegion {

    public ConnexionBase connexion;

    public GestionRegion() throws Exception {
        connexion = new ConnexionBase();

    }

    public void insert(ClassRegion NDist) throws Exception {
        String query = "insert into region (ID_REG,NOM_REG,HISTORIQUE_REG,NOM_CHEF_REG,PRENOM_CHEF_REG,SEXE_CHEF_REG,CONTACT_CHEF_REG,ADRESSE_CHEF_REG,DRN7,DRN35,DRN41,DDIST,SIEGE,SUPERFICIE,DATE_REC)values('" + NDist.getID_REG() + "','" + NDist.getNOM_REG() + "','" + NDist.getHISTORIQUE_REG() + "' ,'" + NDist.getNOM_CHEF_REG() + "','" + NDist.getPRENOM_CHEF_REG() + "','" + NDist.getSEXE_CHEF_REG() + "','" + NDist.getCONTACT_CHEF_REG() + "','" + NDist.getADRESSE_CHEF_REG() + "','" + NDist.getDRN7() + "','" + NDist.getDRN35() + "' ,'" + NDist.getDRN41() + "','" + NDist.getDDIST() + "','" + NDist.getSIEGE() + "','" + NDist.getSUPERFICIE() + "','" + NDist.getDATE_REC() + "')";
        connexion.executeUpdate(query);
    }

    public void update(String id, ClassRegion obj) throws Exception {
        String query = "UPDATE region SET ID_REG='" + obj.getID_REG() + "', NOM_REG='" + obj.getNOM_REG() + "', HISTORIQUE_REG='" + obj.getHISTORIQUE_REG() + "', NOM_CHEF_REG='" + obj.getNOM_CHEF_REG() + "', PRENOM_CHEF_REG='" + obj.getPRENOM_CHEF_REG() + "', SEXE_CHEF_REG='" + obj.getSEXE_CHEF_REG() + "', CONTACT_CHEF_REG='" + obj.getCONTACT_CHEF_REG() + "', ADRESSE_CHEF_REG='" + obj.getADRESSE_CHEF_REG() + "', DRN7='" + obj.getDRN7() + "', DRN35='" + obj.getDRN35() + "', DRN41='" + obj.getDRN41() + "', DDIST='" + obj.getDDIST() + "', SIEGE='" + obj.getSIEGE() + "', SUPERFICIE='" + obj.getSUPERFICIE() + "', DATE_REC='" + obj.getDATE_REC() + "' WHERE ID_REG ='" + id + "'";
        connexion.executeUpdate(query);
    }

    public void Supprimer(String id) throws Exception {
        String query = "DELETE FROM region WHERE ID_REG='" + id + "'";
        connexion.executeUpdate(query);

    }

    public ResultSet getAll() throws Exception {
        String query = "SELECT * FROM region order by NOM_REG asc";
        ResultSet rs = connexion.executeQuery(query);
        return rs;
    }
}
