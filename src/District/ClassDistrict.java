/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package District;

/**
 *
 * @author JAHYA
 */
public class ClassDistrict {

    private String ID_DIST;
    private String NOM_DIST;
    private String HISTORIQUE_DIST;
    private String SUPERFICIE;

    public ClassDistrict() {
    }

    public ClassDistrict(String ID_DIST, String NOM_DIST, String HISTORIQUE_DIST, String SUPERFICIE) {
        this.ID_DIST = ID_DIST;
        this.NOM_DIST = NOM_DIST;
        this.HISTORIQUE_DIST = HISTORIQUE_DIST;
        this.SUPERFICIE = SUPERFICIE;
    }

    public void setID_DIST(String ID_DIST) {
        this.ID_DIST = ID_DIST;
    }

    public void setNOM_DIST(String NOM_DIST) {
        this.NOM_DIST = NOM_DIST;
    }

    public void setHISTORIQUE_DIST(String HISTORIQUE_DIST) {
        this.HISTORIQUE_DIST = HISTORIQUE_DIST;
    }

    public void setSUPERFICIE(String SUPERFICIE) {
        this.SUPERFICIE = SUPERFICIE;
    }

    public String getID_DIST() {
        return ID_DIST;
    }

    public String getNOM_DIST() {
        return NOM_DIST;
    }

    public String getHISTORIQUE_DIST() {
        return HISTORIQUE_DIST;
    }

    public String getSUPERFICIE() {
        return SUPERFICIE;
    }
    
}
