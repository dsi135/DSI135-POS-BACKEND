/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.CategoriaFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Categoria;
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
@Path("categorias")
public class CategoriaREST {

    @EJB
    CategoriaFacade categoriaFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Categoria entity) {
        categoriaFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Categoria entity) {
        categoriaFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public void remove(@PathParam("id") Integer id) {
        Categoria entidad = categoriaFacade.find(id);
        categoriaFacade.remove(entidad);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Categoria find(@PathParam("id") Integer id) {
        return categoriaFacade.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findAll() {
        return categoriaFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return categoriaFacade.findRange(from, to);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer count() {
        return categoriaFacade.count();
    }

}