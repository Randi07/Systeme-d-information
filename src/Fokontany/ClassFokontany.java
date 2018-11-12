/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fokontany;

/**
 *
 * @author JAHYA
 */
public class ClassFokontany {
   
    private String ID_FKT;
    private String NOM_FKT;
    private String HISTORIQUE_FKT;
     private String SUPERFICIE;
    
    
    public ClassFokontany(){}

    public ClassFokontany(String ID_FKT, String NOM_FKT, String HISTORIQUE_FKT, String SUPERFICIE) {
        this.ID_FKT = ID_FKT;
        this.NOM_FKT = NOM_FKT;
        this.HISTORIQUE_FKT = HISTORIQUE_FKT;
        this.SUPERFICIE = SUPERFICIE;
    }

    public void setID_FKT(String ID_FKT) {
        this.ID_FKT = ID_FKT;
    }

    public void setNOM_FKT(String NOM_FKT) {
        this.NOM_FKT = NOM_FKT;
    }

    public void setHISTORIQUE_FKT(String HISTORIQUE_FKT) {
        this.HISTORIQUE_FKT = HISTORIQUE_FKT;
    }

    public void setSUPERFICIE(String SUPERFICIE) {
        this.SUPERFICIE = SUPERFICIE;
    }


    public String getID_FKT() {
        return ID_FKT;
    }

    public String getNOM_FKT() {
        return NOM_FKT;
    }

    public String getHISTORIQUE_FKT() {
        return HISTORIQUE_FKT;
    }

    
    public String getSUPERFICIE() {
        return SUPERFICIE;
    }

    
}
