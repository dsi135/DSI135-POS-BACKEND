/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.DetalleOrden;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.DetalleOrdenPK;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Orden;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.jpa.JpaHelper;

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

    public boolean existe(Integer id) {
        return executeQuery("SELECT COUNT(d) FROM DetalleOrden d WHERE d")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }

    public double Total(Integer id) {
        double total = Double.parseDouble(executeQuery("SELECT SUM(d.precio) FROM DetalleOrden d WHERE d.detalleOrdenPK.id=:id").setParameter("id", id).getSingleResult().toString());
        return total;
    }

    public List<DetalleOrden> entidad(Integer id) {
        return executeQuery("SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.id=:id")
                .setParameter("id", id).getResultList();
    }

    public void removeDetalle(int id) {
        executeQuery("DELETE FROM DetalleOrden a WHERE a.detalleOrdenPK.id= :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
