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
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
    Response envio;
    Orden orden;
    DetalleOrden entity;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@QueryParam("mesero") String mesero,
            @QueryParam("mesa") int mesa,
            @QueryParam("cliente") String cliente,
            @QueryParam("observaciones") String observaciones,
            @QueryParam("idcuenta") @DefaultValue("0") int idcuenta,
            List<DetalleOrden> lst) {
        if (!mesero.equals("") && mesero != null && mesa > 0 && lst != null) {
            orden = new Orden();
            if (idcuenta == 0) {
                orden.setEstado(true);
                orden.setCliente(cliente);
                orden.setMesa(mesa);
                orden.setMesero(mesero);
                orden.setObservaciones(observaciones);
                orden.setFecha(new Date());
                orden = ordenFacade.crear(orden);
            } else {
                orden = ordenFacade.findById(idcuenta);
                orden.setObservaciones(observaciones);
                ordenFacade.edit(orden);
                detalleOrdenFacade.removeDetalle(idcuenta);
            }
            idcuenta = orden.getId();
            for (DetalleOrden lst1 : lst) {
                if (productoFacade.exist(lst1.getProducto1().getId())) {
                    entity = new DetalleOrden();
                    entity.setCantidad(lst1.getCantidad());
                    entity.setPrecio(lst1.getCantidad() * lst1.getProducto1().getPrecio());
                    entity.setDetalleOrdenPK(new DetalleOrdenPK(orden.getId(), lst1.getProducto1().getId()));
                }
                detalleOrdenFacade.create(entity);
            }
            orden.setTotal(detalleOrdenFacade.Total(idcuenta));
            ordenFacade.edit(orden);
            return Response.status(Response.Status.OK)
                    .header("Registro Creado", lst.size())
                    .build();

        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al crear", lst.size())
                .build();

    }

    @GET
    @Path("stats")
    @Produces(MediaType.APPLICATION_JSON)
    public List stats() {
        return detalleOrdenFacade.estadisticas();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetalleOrden> findAll() {
        System.out.println(detalleOrdenFacade.findAll());
        return detalleOrdenFacade.findAll();
    }

}
