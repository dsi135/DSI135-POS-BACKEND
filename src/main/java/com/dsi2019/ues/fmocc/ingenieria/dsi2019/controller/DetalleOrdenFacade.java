/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.DetalleOrden;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lordbryan
 */
@Stateless
public class DetalleOrdenFacade extends AbstractFacade<DetalleOrden> {

    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria_dsi2019_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleOrdenFacade() {
        super(DetalleOrden.class);
    }
    
    public  boolean existe(Integer id){
        return executeQuery("SELECT COUNT(d) FROM DetalleOrden d WHERE d")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }
    
}
