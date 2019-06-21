/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.DetalleOrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.OrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.ProductoFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.DetalleOrden;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.DetalleOrdenPK;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Path("detalleorden")
public class DetalleOrdenREST {

    @PathParam("idOrden")
    Integer idOrden;

    @EJB
    DetalleOrdenFacade detalleOrdenFacade;
    @EJB
    OrdenFacade ordenFacade;
    @EJB
    ProductoFacade productoFacade;
    DetalleOrden entity;
    Orden orden;
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@QueryParam("mesero")String mesero,
            @QueryParam("mesa") int mesa,
            @QueryParam("cliente") String cliente,
            @QueryParam("Observaciones")String Observaciones,
            List<DetalleOrden> lst) {
        if (!mesero.equals("") && mesero!=null 
            && mesa>0 
            &&!cliente.equals("") && cliente!=null
            &&!Observaciones.equals("") && Observaciones!=null
                && lst != null) {
            orden= new Orden();
            orden.setCliente(cliente);
            orden.setMesa(mesa);
            orden.setMesero(mesero);
            orden.setObservaciones(Observaciones);
            orden=ordenFacade.create(orden);

            for (DetalleOrden lst1 : lst) {
                if (productoFacade.exist(lst1.getProducto1().getId())) {
                    entity = new DetalleOrden();
                    entity.setCantidad(lst1.getCantidad());
                    entity.setPrecio(lst1.getCantidad() * lst1.getProducto1().getPrecio());
                    entity.setDetalleOrdenPK(new DetalleOrdenPK(orden.getId(), lst1.getProducto1().getId()));

                }
                detalleOrdenFacade.create(entity);
            }
            return Response.status(Response.Status.OK)
                    .header("Registro Creado", lst.size())
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al crear", lst.size())
                .build();

    }

    @PUT
    @Path("edit/{idOrden}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(List<DetalleOrden> lst) {
        if (ordenFacade.existe(idOrden) && lst != null) {
            List<DetalleOrden> esperado=detalleOrdenFacade.entidad(idOrden);
            if (!esperado.equals("") && esperado!= null) {
                for (DetalleOrden esperado1 : esperado) {
                    entity = new DetalleOrden();
                    entity.setDetalleOrdenPK(new DetalleOrdenPK(idOrden, esperado1.getProducto1().getId()));
                    detalleOrdenFacade.remove(entity);
                }
                
                for (DetalleOrden lst1 : lst) {
                    if (productoFacade.exist(lst1.getProducto1().getId())) {
                        entity = new DetalleOrden();
                        entity.setCantidad(lst1.getCantidad());
                        entity.setPrecio(lst1.getCantidad() * lst1.getProducto1().getPrecio());
                        entity.setDetalleOrdenPK(new DetalleOrdenPK(idOrden, lst1.getProducto1().getId()));
                    }
                    detalleOrdenFacade.edit(entity);
                }
                return Response.status(Response.Status.ACCEPTED)
                        .header("Registro Editado", lst)
                        .build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Fallo Editado de Registro", lst)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetalleOrden> findAll() {
        System.out.println(detalleOrdenFacade.findAll());
        return detalleOrdenFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetalleOrden> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return detalleOrdenFacade.findRange(from, to);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer count() {
        return detalleOrdenFacade.count();
    }

}
