/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.ParametrosFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Parametros;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kevin
 */
@Path("parametros")
public class ParametrosREST {

    @EJB
    ParametrosFacade parametrosFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Parametros entity) {
        if (entity!=null) {
            parametrosFacade.create(entity);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Parametros entity) {
        if (parametrosFacade.exist(id)&& entity!=null) {
            parametrosFacade.edit(entity);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Parametros> findAll() {
        return parametrosFacade.findAll();
    } 
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Parametros findbyId(@PathParam("id") int id) {
        return parametrosFacade.findById(id);
    }
}
