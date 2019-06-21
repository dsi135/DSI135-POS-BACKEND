/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Orden;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author lordbryan
 */
@Stateless
public class OrdenFacade extends AbstractFacade<Orden> {

    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria_dsi2019_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenFacade() {
        super(Orden.class);
    }

    public boolean existe(Integer id) {
        return executeQuery("SELECT COUNT(r) FROM Orden r WHERE r.id=:id").setParameter("id", id)
                .getSingleResult().toString().equals("1");
    }
    
    public List find(){
        return executeQuery("SELECT o FORM Orden o WHERE o.estado=true").getResultList();
    }
    
    public List<Orden> ventas(Date inicio, Date fin){
        return executeQuery("SELECT o FROM Orden o WHERE o.fecha BETWEEN :inicio AND :fin")
                .setParameter("inicio", inicio, TemporalType.DATE)
                .setParameter("fin", fin, TemporalType.DATE)
                .getResultList();
    }

}
