/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Parametros;

/**
 *
 * @author arevalo
 */
public class ParametrosFacadeTest  extends AbstractTest<Parametros>{

    @Override
    protected AbstractFacade<Parametros> getFacade() {
        return new ParametrosFacade();
    }

    @Override
    protected Parametros getEntity() {
       return new Parametros();
    }

    
}
