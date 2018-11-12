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
public class ClassScolaire {

    private String ID_SCOLAIRE;
    public String ID_FKT;
    private String CATEG_SCOLAIRE;
    private String NATURE_SCOLAIRE;
    private String NOMBRE_SCOLAIRE;
    private String ETAT_SCOLAIRE;
    private String ELEVE_MASCULAIN;
    private String ELEVE_FEMININ;
    private String EFFECTIF_TOTAL;
    private String ANNEE_SCOLAIRE;
    private String ANNEE;

    private ClassScolaire() {
    }

    public ClassScolaire(String ID_SCOLAIRE, String CATEG_SCOLAIRE, String NATURE_SCOLAIRE, String NOMBRE_SCOLAIRE, String ETAT_SCOLAIRE, String ELEVE_MASCULAIN, String ELEVE_FEMININ, String EFFECTIF_TOTAL, String ANNEE_SCOLAIRE, String ANNEE) {
        this.ID_SCOLAIRE = ID_SCOLAIRE;
        this.CATEG_SCOLAIRE = CATEG_SCOLAIRE;
        this.NATURE_SCOLAIRE = NATURE_SCOLAIRE;
        this.NOMBRE_SCOLAIRE = NOMBRE_SCOLAIRE;
        this.ETAT_SCOLAIRE = ETAT_SCOLAIRE;
        this.ELEVE_MASCULAIN = ELEVE_MASCULAIN;
        this.ELEVE_FEMININ = ELEVE_FEMININ;
        this.EFFECTIF_TOTAL = EFFECTIF_TOTAL;
        this.ANNEE_SCOLAIRE = ANNEE_SCOLAIRE;
        this.ANNEE = ANNEE;
    }

    public void setID_SCOLAIRE(String ID_SCOLAIRE) {
        this.ID_SCOLAIRE = ID_SCOLAIRE;
    }

    public void setCATEG_SCOLAIRE(String CATEG_SCOLAIRE) {
        this.CATEG_SCOLAIRE = CATEG_SCOLAIRE;
    }

    public void setNATURE_SCOLAIRE(String NATURE_SCOLAIRE) {
        this.NATURE_SCOLAIRE = NATURE_SCOLAIRE;
    }

    public void setNOMBRE_SCOLAIRE(String NOMBRE_SCOLAIRE) {
        this.NOMBRE_SCOLAIRE = NOMBRE_SCOLAIRE;
    }

    public void setETAT_SCOLAIRE(String ETAT_SCOLAIRE) {
        this.ETAT_SCOLAIRE = ETAT_SCOLAIRE;
    }

    public void setELEVE_MASCULAIN(String ELEVE_MASCULAIN) {
        this.ELEVE_MASCULAIN = ELEVE_MASCULAIN;
    }

    public void setELEVE_FEMININ(String ELEVE_FEMININ) {
        this.ELEVE_FEMININ = ELEVE_FEMININ;
    }

    public void setEFFECTIF_TOTAL(String EFFECTIF_TOTAL) {
        this.EFFECTIF_TOTAL = EFFECTIF_TOTAL;
    }

    public void setANNEE_SCOLAIRE(String ANNEE_SCOLAIRE) {
        this.ANNEE_SCOLAIRE = ANNEE_SCOLAIRE;
    }

    public void setANNEE(String ANNEE) {
        this.ANNEE = ANNEE;
    }

    public String getID_SCOLAIRE() {
        return ID_SCOLAIRE;
    }

    public String getCATEG_SCOLAIRE() {
        return CATEG_SCOLAIRE;
    }

    public String getNATURE_SCOLAIRE() {
        return NATURE_SCOLAIRE;
    }

    public String getNOMBRE_SCOLAIRE() {
        return NOMBRE_SCOLAIRE;
    }

    public String getETAT_SCOLAIRE() {
        return ETAT_SCOLAIRE;
    }

    public String getELEVE_MASCULAIN() {
        return ELEVE_MASCULAIN;
    }

    public String getELEVE_FEMININ() {
        return ELEVE_FEMININ;
    }

    public String getEFFECTIF_TOTAL() {
        return EFFECTIF_TOTAL;
    }

    public String getANNEE_SCOLAIRE() {
        return ANNEE_SCOLAIRE;
    }

    public String getANNEE() {
        return ANNEE;
    }

}