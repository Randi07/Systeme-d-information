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
public class ClassPopulation {

    private String ID_POPULATION;
    public String ID_FKT;
    private String ZERO;
    private String SIX;
    private String DIXHUIT;
    private String SOIXANTE;
    private String EFFECTIF_MASCULAIN;
    private String EFFECTIF_FEMININ;
    private String EFFECTIF_TOTAL;
    private String ANNEE;

    public ClassPopulation() {
    }

    public ClassPopulation(String ID_POPULATION, String ZERO, String SIX, String DIXHUIT, String SOIXANTE, String EFFECTIF_MASCULAIN, String EFFECTIF_FEMININ, String EFFECTIF_TOTAL, String ANNEE) {
        this.ID_POPULATION = ID_POPULATION;
        this.ZERO = ZERO;
        this.SIX = SIX;
        this.DIXHUIT = DIXHUIT;
        this.SOIXANTE = SOIXANTE;
        this.EFFECTIF_MASCULAIN = EFFECTIF_MASCULAIN;
        this.EFFECTIF_FEMININ = EFFECTIF_FEMININ;
        this.EFFECTIF_TOTAL = EFFECTIF_TOTAL;
        this.ANNEE = ANNEE;
    }

    public void setID_POPULATION(String ID_POPULATION) {
        this.ID_POPULATION = ID_POPULATION;
    }

    public void setID_FKT(String ID_FKT) {
        this.ID_FKT = ID_FKT;
    }

    public void setZERO(String ZERO) {
        this.ZERO = ZERO;
    }

    public void setSIX(String SIX) {
        this.SIX = SIX;
    }

    public void setDIXHUIT(String DIXHUIT) {
        this.DIXHUIT = DIXHUIT;
    }

    public void setSOIXANTE(String SOIXANTE) {
        this.SOIXANTE = SOIXANTE;
    }

    public void setEFFECTIF_MASCULAIN(String EFFECTIF_MASCULAIN) {
        this.EFFECTIF_MASCULAIN = EFFECTIF_MASCULAIN;
    }

    public void setEFFECTIF_FEMININ(String EFFECTIF_FEMININ) {
        this.EFFECTIF_FEMININ = EFFECTIF_FEMININ;
    }

    public void setEFFECTIF_TOTAL(String EFFECTIF_TOTAL) {
        this.EFFECTIF_TOTAL = EFFECTIF_TOTAL;
    }

    public void setANNEE(String ANNEE) {
        this.ANNEE = ANNEE;
    }

    public String getID_POPULATION() {
        return ID_POPULATION;
    }

    public String getID_FKT() {
        return ID_FKT;
    }

    public String getZERO() {
        return ZERO;
    }

    public String getSIX() {
        return SIX;
    }

    public String getDIXHUIT() {
        return DIXHUIT;
    }

    public String getSOIXANTE() {
        return SOIXANTE;
    }

    public String getEFFECTIF_MASCULAIN() {
        return EFFECTIF_MASCULAIN;
    }

    public String getEFFECTIF_FEMININ() {
        return EFFECTIF_FEMININ;
    }

    public String getEFFECTIF_TOTAL() {
        return EFFECTIF_TOTAL;
    }

    public String getANNEE() {
        return ANNEE;
    }

}
