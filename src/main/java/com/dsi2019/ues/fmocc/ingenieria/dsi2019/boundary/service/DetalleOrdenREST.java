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
            if (idcuenta == 0) {
                orden = new Orden();
                orden.setEstado(true);
                orden.setCliente(cliente);
                orden.setMesa(mesa);
                orden.setMesero(mesero);
                orden.setObservaciones(observaciones);
                orden.setFecha(new Date());
                orden = ordenFacade.crear(orden);
                idcuenta = orden.getId();
            } else {
                System.out.println("SE BORRO");
                detalleOrdenFacade.removeDetalle(idcuenta);
            }
            System.out.println("orden :" + orden);

            for (DetalleOrden lst1 : lst) {
                lst1.setDetalleOrdenPK(new DetalleOrdenPK(idcuenta, lst1.getProducto1().getId()));
                detalleOrdenFacade.create(lst1);
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
    public Response edit(@PathParam("idOrden") Integer id,
            @QueryParam("observaciones") @DefaultValue("")String observaciones,
            List<DetalleOrden> lst) {
        if (ordenFacade.existe(id) && lst!=null && !lst.equals("")) {
            orden=ordenFacade.findById(id);
            orden.setObservaciones(observaciones);
            ordenFacade.edit(orden);
            detalleOrdenFacade.elim(orden.getId());
            envio=detalleOrdenFacade.EditarOrden(orden, lst);
            return envio;
        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Fallo editarOrden", 1)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetalleOrden> findAll() {
        System.out.println(detalleOrdenFacade.findAll());
        return detalleOrdenFacade.findAll();
    }
    
}
