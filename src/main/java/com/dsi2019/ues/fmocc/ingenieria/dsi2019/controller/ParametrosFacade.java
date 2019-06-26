/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Parametros;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lordbryan
 */
@Stateless
public class ParametrosFacade extends AbstractFacade<Parametros> {

    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria_dsi2019_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrosFacade() {
        super(Parametros.class);
    }
    
    public boolean exist(Integer id){
        return executeQuery("SELECT COUNT(c) FROM Parametros p WHERE p.id=:id")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }
    
    public boolean Preparado(Integer id){
        return executeQuery("SELECT DISTINCT p.nombre ,d.cantidad FROM Orden o , Producto p , DetalleOrden d WHERE d.detalleOrdenPK.producto=p.id AND p.preparado=1 AND d.detalleOrdenPK.id=:id")
                .setParameter("id", id).toString().equals("SI");
    }
    
    
}
