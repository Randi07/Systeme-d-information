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
public class ClassSport {
   
    private String ID_SPORT;
    public String ID_FKT;
    private String CATEG_SPORT;
    private String EXISTANCE_TERRAIN;
    private String ETAT_TERRAIN;
    private String NOMBRE_TERRAIN;
    private String REMARQUE_SPORT;
    private String ANNEE;

    
    public ClassSport(){
    }

    public ClassSport(String ID_SPORT, String CATEG_SPORT, String EXISTANCE_TERRAIN, String ETAT_TERRAIN, String NOMBRE_TERRAIN, String REMARQUE_SPORT, String ANNEE) {
        this.ID_SPORT = ID_SPORT;
        this.CATEG_SPORT = CATEG_SPORT;
        this.EXISTANCE_TERRAIN = EXISTANCE_TERRAIN;
        this.ETAT_TERRAIN = ETAT_TERRAIN;
        this.NOMBRE_TERRAIN = NOMBRE_TERRAIN;
        this.REMARQUE_SPORT = REMARQUE_SPORT;
        this.ANNEE = ANNEE;
    }

    public void setID_SPORT(String ID_SPORT) {
        this.ID_SPORT = ID_SPORT;
    }


    public void setCATEG_SPORT(String CATEG_SPORT) {
        this.CATEG_SPORT = CATEG_SPORT;
    }

    public void setEXISTANCE_TERRAIN(String EXISTANCE_TERRAIN) {
        this.EXISTANCE_TERRAIN = EXISTANCE_TERRAIN;
    }

    public void setETAT_TERRAIN(String ETAT_TERRAIN) {
        this.ETAT_TERRAIN = ETAT_TERRAIN;
    }

    public void setNOMBRE_TERRAIN(String NOMBRE_TERRAIN) {
        this.NOMBRE_TERRAIN = NOMBRE_TERRAIN;
    }

    public void setREMARQUE_SPORT(String REMARQUE_SPORT) {
        this.REMARQUE_SPORT = REMARQUE_SPORT;
    }

    public void setANNEE(String ANNEE) {
        this.ANNEE = ANNEE;
    }

    public String getID_SPORT() {
        return ID_SPORT;
    }

    public String getCATEG_SPORT() {
        return CATEG_SPORT;
    }

    public String getEXISTANCE_TERRAIN() {
        return EXISTANCE_TERRAIN;
    }

    public String getETAT_TERRAIN() {
        return ETAT_TERRAIN;
    }

    public String getNOMBRE_TERRAIN() {
        return NOMBRE_TERRAIN;
    }

    public String getREMARQUE_SPORT() {
        return REMARQUE_SPORT;
    }

    public String getANNEE() {
        return ANNEE;
    }
    
}
