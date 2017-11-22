/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Administrador
 */
public class STOPS {
    private int ID;
    private float LATITUDE;
    private float LONGITUDE;
    private String DESCRIPTION;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(float LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public float getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(float LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

}
