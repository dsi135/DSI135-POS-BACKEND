/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.OrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Orden;
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
@Path("ordenes")
public class OrdenREST  {

    @EJB
    OrdenFacade ordenFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Orden entity) {
        ordenFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Orden entity) {
        ordenFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public void remove(@PathParam("id") Integer id) {
        Orden entidad = ordenFacade.find(id);
        ordenFacade.remove(entidad);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Orden find(@PathParam("id") Integer id) {
        return ordenFacade.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orden> findAll() {
        return ordenFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orden> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return ordenFacade.findRange(from, to);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer count() {
        return ordenFacade.count();
    }
    
}
