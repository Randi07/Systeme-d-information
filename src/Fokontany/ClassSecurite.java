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
public class ClassSecurite {
    
    private String ID_SECURITE;
    public String ID_FKT;
    private String CATEG_SEC;
    private String NOMBRE;
    private String REMARQUE;
    private String ANNEE;
    
    public ClassSecurite(){}

    public ClassSecurite(String ID_SECURITE, String CATEG_SEC, String NOMBRE, String REMARQUE, String ANNEE) {
        this.ID_SECURITE = ID_SECURITE;
        this.CATEG_SEC = CATEG_SEC;
        this.NOMBRE = NOMBRE;
        this.REMARQUE = REMARQUE;
        this.ANNEE = ANNEE;
    }

    public void setID_SECURITE(String ID_SECURITE) {
        this.ID_SECURITE = ID_SECURITE;
    }

    public void setCATEG_SEC(String CATEG_SEC) {
        this.CATEG_SEC = CATEG_SEC;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public void setREMARQUE(String REMARQUE) {
        this.REMARQUE = REMARQUE;
    }

    public void setANNEE(String ANNEE) {
        this.ANNEE = ANNEE;
    }

    public String getID_SECURITE() {
        return ID_SECURITE;
    }

    public String getCATEG_SEC() {
        return CATEG_SEC;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getREMARQUE() {
        return REMARQUE;
    }

    public String getANNEE() {
        return ANNEE;
    }
    
    
    
}
