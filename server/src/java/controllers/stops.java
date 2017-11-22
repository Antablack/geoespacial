/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.*;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 *
 * @author Administrador
 */
@Path("/stops")
public class stops {
    
     @Context
    private HttpServletResponse servletResponse;

    private void allowCrossDomainAccess() {
        if (servletResponse != null){
            servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        }
    }
    

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<STOPS> lisStops() throws ClassNotFoundException {
        allowCrossDomainAccess();
         stopsDAO stopsDAO =new stopsDAO();
        return stopsDAO.getStops();
    }

    @POST
    @Path("/add")
   // @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean addStops(@FormParam("lat")String lat,@FormParam("long")String lon,@FormParam("descrip")String descrip) {
        allowCrossDomainAccess();
        STOPS item = new STOPS();
        item.setLATITUDE( Float.parseFloat(lat));
        item.setLONGITUDE(Float.parseFloat(lon));
        item.setDESCRIPTION(descrip);
        stopsDAO stopsDAO =new stopsDAO();
        return stopsDAO.saveStops(item);
    }

}
