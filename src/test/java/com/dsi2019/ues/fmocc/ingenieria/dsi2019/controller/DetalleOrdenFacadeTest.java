/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.DetalleOrden;

/**
 *
 * @author arevalo
 */
public class DetalleOrdenFacadeTest  extends AbstractTest<DetalleOrden>{

    @Override
    protected AbstractFacade<DetalleOrden> getFacade() {
        return new DetalleOrdenFacade();
    }

    @Override
    protected DetalleOrden getEntity() {
       return new DetalleOrden();
    }

    
}
