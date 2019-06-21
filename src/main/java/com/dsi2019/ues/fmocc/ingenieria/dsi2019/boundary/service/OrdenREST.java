/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.DetalleOrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.OrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Orden;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Path("ordenes")
public class OrdenREST {

    @EJB
    OrdenFacade ordenFacade;
    @EJB
    DetalleOrdenFacade detalleOrdeFacade;

    Orden entity;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Orden entity) {
        if (entity != null) {
            entity = ordenFacade.create(entity);
            return Response.status(Response.Status.CREATED)
                    .entity(entity)
                    .header("Registro Creado", entity)
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al Crear", 1).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Orden entity) {
        if (ordenFacade.existe(id) && entity != null) {
            ordenFacade.edit(entity);
            return Response.status(Response.Status.OK)
                    .entity(entity)
                    .header("Registro Modificado", id)
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al Modificar", 1).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(ordenFacade.find())
                .header("Total-Reg", ordenFacade.find().size())
                .build();
    }

    @GET
    @Path("rango")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("first") @DefaultValue("0") Integer first,
            @QueryParam("size") @DefaultValue("5") Integer size) {

        return Response.ok(ordenFacade.findRange(first, size))
                .header("Total-Reg", ordenFacade.findRange(first, size).size())
                .build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer count() {
        return ordenFacade.count();
    }

    @PUT
    @Path("finalizar/{idOrden}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Final(@PathParam("idOrden") Integer idOrden) {
        if (ordenFacade.existe(idOrden)) {
            double total = detalleOrdeFacade.Total(idOrden);
            entity=ordenFacade.findById(idOrden);
            entity.setEstado(Boolean.FALSE);
            entity.setTotal(total);
            ordenFacade.edit(entity);
            return Response.status(Response.Status.OK)
                    .entity(entity)
                    .header("Finalizar Orden", idOrden)
                    .build();

        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al Finalizar Orden", idOrden)
                .build();
    }
    
    @GET
    @Path("Ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ventas(@QueryParam("inicio")long inicio,
            @QueryParam("fin")long fin){
        System.out.println("inicio:"+inicio);
        System.out.println("fin:"+fin);
        if (inicio>0 && fin >0) {
            System.out.println("inicio : "+new Date(inicio)+"  fin : "+new Date(fin));
            return Response.status(Response.Status.OK)
                    .entity(ordenFacade.ventas(new Date(inicio), new Date(fin)))
                    .header("Datos de Ventas", 1)
                    .build();
        }
        System.out.println("fallo ----");
        System.out.println("inicio : "+new Date(inicio)+"  fin : "+new Date(fin));
        return Response.status(Response.Status.NOT_FOUND)
                    .header("Error al cargar datos de Ventas", 1)
                    .build();
        
    }

}
