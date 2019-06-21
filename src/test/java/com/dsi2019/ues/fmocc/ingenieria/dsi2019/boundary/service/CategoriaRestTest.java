/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.CategoriaFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.internal.util.reflection.Whitebox;

/**
 *
 * @author arevalo
 */
public class CategoriaRestTest {

    public CategoriaRestTest() {
    }
    CategoriaREST catrest = mock(CategoriaREST.class);
    CategoriaFacade catfacade = mock(CategoriaFacade.class);
    Categoria cat = mock(Categoria.class);
    List<Categoria> lstcat = new ArrayList<>();

    @Before
    public void init() {
        Whitebox.setInternalState(catrest, "categoriaFacade", catfacade);
        cat = new Categoria(1);
        lstcat.add(cat);
        lstcat.add(cat);
        lstcat.add(cat);
    }

    @Test
    public void createTest() {
        doCallRealMethod().when(catrest).create(any(Categoria.class));
        when(catfacade.create(any((Categoria.class)))).thenReturn(cat);

        assertEquals(201, catrest.create(cat).getStatus());

        assertEquals(404, catrest.create(null).getStatus());
    }

    @Test
    public void editTest() {
        doCallRealMethod().when(catrest).edit(any(Integer.class), any(Categoria.class));
        when(catfacade.exist(any(Integer.class))).thenReturn(Boolean.TRUE);
        assertEquals(200, catrest.edit(1, cat).getStatus());

        assertEquals(404, catrest.edit(1, null).getStatus());
    }

    @Test
    public void FindRangeTest() {
        doCallRealMethod().when(catrest).findRange(any(Integer.class), any(Integer.class));
        when(catfacade.findRange(any(Integer.class), any(Integer.class))).thenReturn(lstcat);

        assertEquals(200, catrest.findRange(1, 3).getStatus());
        assertEquals("[Total-Reg]", catrest.findRange(1, 3).getHeaders().keySet().toString());
    }
}
