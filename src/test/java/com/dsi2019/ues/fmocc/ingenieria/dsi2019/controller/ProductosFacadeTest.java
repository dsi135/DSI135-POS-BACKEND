/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Producto;

/**
 *
 * @author arevalo
 */
public class ProductosFacadeTest  extends AbstractTest<Producto>{

    @Override
    protected AbstractFacade<Producto> getFacade() {
        return new ProductoFacade();
    }

    @Override
    protected Producto getEntity() {
       return new Producto();
    }

    
}
