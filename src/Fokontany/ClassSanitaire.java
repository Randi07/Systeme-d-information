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
public class ClassSanitaire {
    
    private String ID_SANITAIRE;
    public String ID_FKT;
    private String INFRAS_SANITAIRE;
    private String NOMBRE_INFRAS_SANITAIRE;
    private String NATURE_INFRAS_SANITAIRE;
    private String EMPLOYES;
    private String NOMBRE_EMPLOYES;
    private String ETAT_INFRAS_SANITAIRE;
    private String REMARQUE;
    private String ANNEE;
    
    public ClassSanitaire(){}

    public ClassSanitaire(String ID_SANITAIRE, String INFRAS_SANITAIRE, String NOMBRE_INFRAS_SANITAIRE, String NATURE_INFRAS_SANITAIRE, String EMPLOYES, String NOMBRE_EMPLOYES, String ETAT_INFRAS_SANITAIRE, String REMARQUE, String ANNEE) {
        this.ID_SANITAIRE = ID_SANITAIRE;
        this.INFRAS_SANITAIRE = INFRAS_SANITAIRE;
        this.NOMBRE_INFRAS_SANITAIRE = NOMBRE_INFRAS_SANITAIRE;
        this.NATURE_INFRAS_SANITAIRE = NATURE_INFRAS_SANITAIRE;
        this.EMPLOYES = EMPLOYES;
        this.NOMBRE_EMPLOYES = NOMBRE_EMPLOYES;
        this.ETAT_INFRAS_SANITAIRE = ETAT_INFRAS_SANITAIRE;
        this.REMARQUE = REMARQUE;
        this.ANNEE = ANNEE;
    }

    public void setID_SANITAIRE(String ID_SANITAIRE) {
        this.ID_SANITAIRE = ID_SANITAIRE;
    }

    public void setID_FKT(String ID_FKT) {
        this.ID_FKT = ID_FKT;
    }


    public void setINFRAS_SANITAIRE(String INFRAS_SANITAIRE) {
        this.INFRAS_SANITAIRE = INFRAS_SANITAIRE;
    }

    public void setNOMBRE_INFRAS_SANITAIRE(String NOMBRE_INFRAS_SANITAIRE) {
        this.NOMBRE_INFRAS_SANITAIRE = NOMBRE_INFRAS_SANITAIRE;
    }

    public void setNATURE_INFRAS_SANITAIRE(String NATURE_INFRAS_SANITAIRE) {
        this.NATURE_INFRAS_SANITAIRE = NATURE_INFRAS_SANITAIRE;
    }

    public void setEMPLOYES(String EMPLOYES) {
        this.EMPLOYES = EMPLOYES;
    }

    public void setNOMBRE_EMPLOYES(String NOMBRE_EMPLOYES) {
        this.NOMBRE_EMPLOYES = NOMBRE_EMPLOYES;
    }

    public void setETAT_INFRAS_SANITAIRE(String ETAT_INFRAS_SANITAIRE) {
        this.ETAT_INFRAS_SANITAIRE = ETAT_INFRAS_SANITAIRE;
    }

    public void setREMARQUE(String REMARQUE) {
        this.REMARQUE = REMARQUE;
    }

    public void setANNEE(String ANNEE) {
        this.ANNEE = ANNEE;
    }

    public String getID_SANITAIRE() {
        return ID_SANITAIRE;
    }

    public String getID_FKT() {
        return ID_FKT;
    }


    public String getINFRAS_SANITAIRE() {
        return INFRAS_SANITAIRE;
    }

    public String getNOMBRE_INFRAS_SANITAIRE() {
        return NOMBRE_INFRAS_SANITAIRE;
    }

    public String getNATURE_INFRAS_SANITAIRE() {
        return NATURE_INFRAS_SANITAIRE;
    }

    public String getEMPLOYES() {
        return EMPLOYES;
    }

    public String getNOMBRE_EMPLOYES() {
        return NOMBRE_EMPLOYES;
    }

    public String getETAT_INFRAS_SANITAIRE() {
        return ETAT_INFRAS_SANITAIRE;
    }

    public String getREMARQUE() {
        return REMARQUE;
    }

    public String getANNEE() {
        return ANNEE;
    }

    
}
