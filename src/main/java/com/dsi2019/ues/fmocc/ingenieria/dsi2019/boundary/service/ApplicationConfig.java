/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author kevin
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service.CategoriaREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service.DetalleOrdenREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service.OrdenREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service.ParametrosREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service.ProductoREST.class);
    }
    
}
