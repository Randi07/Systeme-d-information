/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commune;

/**
 *
 * @author JAHYA
 */
public class ClassCommune {
    
    private String ID_COM;
    private String NOM_COM;
    private String HISTORIQUE_COM;
    private String DRN7;
    private String DRN35;
    private String DRN41;
    private String DDIST;
    private String DREG;
    private String SUPERFICIE;
    
    
    public ClassCommune(){
    }

    public ClassCommune(String ID_COM, String NOM_COM, String HISTORIQUE_COM, String DRN7, String DRN35, String DRN41, String DDIST, String DREG, String SUPERFICIE) {
        this.ID_COM = ID_COM;
        this.NOM_COM = NOM_COM;
        this.HISTORIQUE_COM = HISTORIQUE_COM;
        this.DRN7 = DRN7;
        this.DRN35 = DRN35;
        this.DRN41 = DRN41;
        this.DDIST = DDIST;
        this.DREG = DREG;
        this.SUPERFICIE = SUPERFICIE;
    }

    public void setID_COM(String ID_COM) {
        this.ID_COM = ID_COM;
    }

    public void setNOM_COM(String NOM_COM) {
        this.NOM_COM = NOM_COM;
    }

    public void setHISTORIQUE_COM(String HISTORIQUE_COM) {
        this.HISTORIQUE_COM = HISTORIQUE_COM;
    }

    public void setDRN7(String DRN7) {
        this.DRN7 = DRN7;
    }

    public void setDRN35(String DRN35) {
        this.DRN35 = DRN35;
    }

    public void setDRN41(String DRN41) {
        this.DRN41 = DRN41;
    }

    public void setDDIST(String DDIST) {
        this.DDIST = DDIST;
    }

    public void setDREG(String DREG) {
        this.DREG = DREG;
    }

    public void setSUPERFICIE(String SUPERFICIE) {
        this.SUPERFICIE = SUPERFICIE;
    }

    public String getID_COM() {
        return ID_COM;
    }

    public String getNOM_COM() {
        return NOM_COM;
    }

    public String getHISTORIQUE_COM() {
        return HISTORIQUE_COM;
    }

    public String getDRN7() {
        return DRN7;
    }

    public String getDRN35() {
        return DRN35;
    }

    public String getDRN41() {
        return DRN41;
    }

    public String getDDIST() {
        return DDIST;
    }

    public String getDREG() {
        return DREG;
    }

    public String getSUPERFICIE() {
        return SUPERFICIE;
    }

   
}
