/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtherFolder;

/**
 *
 * @author JAHYA
 */
public class ClassUtilisateur {
    
    private String NOM_UT;
    private String MOT_PASS_UT;
    
    public ClassUtilisateur(){
    
    }

    public ClassUtilisateur(String NOM_UT, String MOT_PASS_UT) {
        this.NOM_UT = NOM_UT;
        this.MOT_PASS_UT = MOT_PASS_UT;
    }

    public void setNOM_UT(String NOM_UT) {
        this.NOM_UT = NOM_UT;
    }

    public void setMOT_PASS_UT(String MOT_PASS_UT) {
        this.MOT_PASS_UT = MOT_PASS_UT;
    }

    public String getNOM_UT() {
        return NOM_UT;
    }

    public String getMOT_PASS_UT() {
        return MOT_PASS_UT;
    }
    
}
