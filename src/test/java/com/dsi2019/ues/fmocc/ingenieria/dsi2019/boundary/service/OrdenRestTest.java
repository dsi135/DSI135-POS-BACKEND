/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.OrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Orden;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.internal.util.reflection.Whitebox;

/**
 *
 * @author arevalo
 */
public class OrdenRestTest {
    OrdenREST ordenRest= mock(OrdenREST.class);
    OrdenFacade ordenFacade=mock(OrdenFacade.class);
    Orden orden=mock(Orden.class);
    List<Orden> list= new ArrayList<>();
    @Before
    public void init(){
        when(ordenFacade.existe(any(Integer.class))).thenReturn(Boolean.TRUE);
        Whitebox.setInternalState(ordenRest, "ordenFacade", ordenFacade);
        orden= new Orden(1);
        list.add(orden);
        list.add(orden);
        list.add(orden);
    }

    @Test
    public void findAllTest(){
        doCallRealMethod().when(ordenRest).findAll();
        when(ordenFacade.find()).thenReturn(list);
        
        assertEquals(200, ordenRest.findAll().getStatus());
        assertEquals("[Total-Reg]", ordenRest.findAll().getHeaders().keySet().toString());
        
    }
    
    @Test
    public void FindRangeTest(){
        
    }
    
}
