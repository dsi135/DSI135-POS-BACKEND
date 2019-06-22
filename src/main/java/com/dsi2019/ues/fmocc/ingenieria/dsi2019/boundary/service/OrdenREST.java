/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.DetalleOrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.OrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Orden;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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


    @PUT
    @Path("finalizar/{idOrden}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Final(@PathParam("idOrden") Integer idOrden) {
        if (ordenFacade.existe(idOrden)) {
            double total = detalleOrdeFacade.Total(idOrden);
            entity = ordenFacade.findById(idOrden);
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearOrden(Orden orden) {
        if (ordenFacade.existe(orden.getId())) {
             return Response.status(Response.Status.CONFLICT).build();
        }
            ordenFacade.crear(orden);
            return Response.status(Response.Status.CREATED)
                    .entity(orden)
                    .build();
    }

    @GET
    @Path("ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ventas(@QueryParam("init") String init, @QueryParam("fina") String fina) {
        int anio, mes, dia;
        String[] fechas;
        Date inicio = new Date();
        Date fin = new Date();
        Calendar s = new GregorianCalendar();
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                fechas = init.split("-");
            } else {
                fechas = fina.split("-");
            }
            anio = Integer.parseInt(fechas[0]);
            mes = Integer.parseInt(fechas[1]) - 1;
            dia = Integer.parseInt(fechas[2]);
            s.set(anio, mes, dia);
            if (i == 0) {
                inicio = s.getTime();
            } else {
                fin = s.getTime();
                if (fin.after((new Date(System.currentTimeMillis())))) {
                    fin = (new Date(System.currentTimeMillis()));
                }
            }
        }
        if (inicio != null && fin != null) {
            return Response.status(Response.Status.OK)
                    .entity(ordenFacade.ventas(inicio, fin))
                    .header("Datos de Ventas", 1)
                    .build();
        }
        System.out.println("fallo ----");
        System.out.println("inicio : " + inicio + "  fin : " + fin);
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al cargar datos de Ventas", 1)
                .build();

    }

    @GET
    @Path("ventas/producto")
    public Response producto(@QueryParam("fecha") String fecha) {
        if (fecha != null && !fecha.equals("")) {
            Date fech;
            String[] fechas = fecha.split("-");
            Calendar s = new GregorianCalendar();
            s.set(Integer.parseInt(fechas[0]), Integer.parseInt(fechas[1])-1, Integer.parseInt(fechas[2]));
            fech = s.getTime();
            return Response.status(Response.Status.OK)
                    .entity(ordenFacade.ventaProducto(fech))
                    .header("Rango de Productos", 1)
                    .build();

        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error en el rango de Productos", 1)
                .build();
    }

}
