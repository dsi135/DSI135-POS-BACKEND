/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller.ProductoFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.Producto;
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
public class ProductoRestTest {
    
    public ProductoRestTest() {
    }
    
    ProductoREST productorest=mock(ProductoREST.class);
    ProductoFacade productofacade=mock(ProductoFacade.class);
    Producto producto=mock(Producto.class);
    List<Producto> lstprod=new ArrayList<>();
            
    
    @Before
    public void init(){
        Whitebox.setInternalState(productorest, "productoFacade", productofacade);
        producto= new Producto(1);
        lstprod.add(producto);
        lstprod.add(producto);
        lstprod.add(producto);
    }
    @Test
    public void createTest(){
        doCallRealMethod().when(productorest).create(any(Producto.class));
        when(productofacade.create(any((Producto.class)))).thenReturn(producto);
        
        assertEquals(201,productorest.create(producto).getStatus());
        
        assertEquals(404,productorest.create(null).getStatus());
    }
    
    @Test
    public void editTest(){
        doCallRealMethod().when(productorest).edit(any(Integer.class), any(Producto.class));
        when(productofacade.exist(any(String.class))).thenReturn(Boolean.TRUE);
        assertEquals(200, productorest.edit(1, producto).getStatus());
        
        assertEquals(404,productorest.edit(1,null).getStatus());
    }
    
    @Test
    public void  FinRangeTest(){
        doCallRealMethod().when(productorest).findRange(any(Integer.class), any(Integer.class));
        when(productofacade.findRange(any(Integer.class), any(Integer.class))).thenReturn(lstprod);
        
        assertEquals(200, productorest.findRange(1, 3).getStatus());
        assertEquals("[Total-Reg]", productorest.findRange(1, 3).getHeaders().keySet().toString());
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
