/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.stops;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class stopsDAO {

    Conexion con;

    public stopsDAO() {
        con = new Conexion();
    }

    public ArrayList<STOPS> getStops() {
        ArrayList<STOPS> lis = new ArrayList<>();
        try {
            STOPS item = null;
            Conexion Conexion = new Conexion();
            PreparedStatement ps = Conexion.getConnection().prepareStatement("SELECT * FROM stops");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = new STOPS();
                item.setID(Integer.parseInt(rs.getString(1)));
                item.setLATITUDE(Float.parseFloat(rs.getString(2)));
                item.setLONGITUDE(Float.parseFloat(rs.getString(3)));
                item.setDESCRIPTION(rs.getString(4));
                lis.add(item);
            }
        } catch (Exception ex) {
        }
        return lis;
    }

    public Boolean saveStops(STOPS _stops) {
        try {
            PreparedStatement ps = con.getConnection().prepareStatement("INSERT INTO `stops`( `LATITUDE`, `LONGITUDE`, `DESCRIPTION`) VALUES(?,?,?)");
            ps.setFloat(1, _stops.getLATITUDE());
            ps.setFloat(2, _stops.getLONGITUDE());
            ps.setString(3, _stops.getDESCRIPTION());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            String a = ex.getMessage();
            return false;
        }
    }

}
