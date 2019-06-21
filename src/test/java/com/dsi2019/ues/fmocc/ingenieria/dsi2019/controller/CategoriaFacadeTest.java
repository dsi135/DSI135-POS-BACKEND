/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Categoria;

/**
 *
 * @author arevalo
 */
public class CategoriaFacadeTest extends AbstractTest<Categoria>{

    @Override
    protected AbstractFacade<Categoria> getFacade() {
        return new CategoriaFacade();
    }

    @Override
    protected Categoria getEntity() {
       return new Categoria();
    }

    
}
