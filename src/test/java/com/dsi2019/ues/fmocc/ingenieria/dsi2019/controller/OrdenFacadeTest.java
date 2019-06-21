/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Orden;

/**
 *
 * @author arevalo
 */
public class OrdenFacadeTest  extends AbstractTest<Orden>{

    @Override
    protected AbstractFacade<Orden> getFacade() {
        return new OrdenFacade();
    }

    @Override
    protected Orden getEntity() {
       return new Orden();
    }

    
}
