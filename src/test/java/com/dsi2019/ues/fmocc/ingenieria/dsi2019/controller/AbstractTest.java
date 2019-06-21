/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.internal.util.reflection.Whitebox;

/**
 *
 * @author arevalo
 * @param <T>
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractTest<T> {

    @Mock
    EntityManager em;

    protected abstract AbstractFacade<T> getFacade();

    protected abstract T getEntity();
    AbstractFacade<T> cut = getFacade();
    T entity;

    private final CriteriaBuilder CB = mock(CriteriaBuilder.class);
    private final CriteriaQuery CQ = mock(CriteriaQuery.class);
    private final TypedQuery q = mock(TypedQuery.class);
    protected List<T> lstEsperado = new ArrayList<>();
    protected List<T> lstResultado = new ArrayList<>();

    @Before
    public void init() {
        entity = getEntity();
        lstEsperado.add(entity);
        lstEsperado.add(entity);
        Whitebox.setInternalState(cut, "em", em);
        when(this.em.getCriteriaBuilder()).thenReturn(CB);
        when(CB.createQuery()).thenReturn(CQ);
        when(CQ.select(Matchers.any(Selection.class))).thenReturn(CQ);
        when(this.em.createQuery(CQ)).thenReturn(q);
        when(q.getResultList()).thenReturn(lstEsperado);
    }

    /**
     * Test of create method, of class AbstractFacade.
     */
    @Test(expected = Exception.class)
    public void testCreate() {
        cut.create(entity);
        verify(em).persist(entity);
        cut.create(null);
    }

    /**
     * Test of edit method, of class AbstractFacade.
     */
    @Test(expected = Exception.class)
    public void testEdit() {
        cut.edit(entity);
        verify(em).merge(entity);
        cut.edit(null);
    }

    /**
     * Test of remove method, of class AbstractFacade.
     */
    @Test(expected = Exception.class)
    public void testRemove() {
        cut.remove(entity);
        verify(em).remove(em.merge(entity));
        cut.remove(null);
    }

    /**
     * Test of findById method, of class AbstractFacade.
     */
    @Test(expected = Exception.class)
    public void testFindById() {
        when(this.em.find(entity.getClass(), 1)).thenReturn(entity);
        T resultado = cut.findById(1);
        assertEquals(entity, resultado);
        entity = null;
        cut.findById(null);
    }

    /**
     * Test of findAll method, of class AbstractFacade.
     */
    @Test(expected = Exception.class)
    public void testFindAll() {
        lstResultado = cut.findAll();
        assertEquals(2, lstResultado.size());

        Whitebox.setInternalState(cut, "em", null);
        lstResultado = cut.findAll();
        assertEquals(0, lstResultado.size());

        Whitebox.setInternalState(cut, "em", em);
        when(CB.createQuery()).thenReturn(null);
        lstResultado = cut.findAll();
    }

    /**
     * Test of findRange method, of class AbstractFacade.
     */
    @Test(expected = Exception.class)
    public void testFindRange() {
        lstResultado = cut.findRange(0, 100);
        assertEquals(2, lstResultado.size());
        cut.findRange(0, 0);
    }

    /**
     * Test of count method, of class AbstractFacade.
     */
    @Test(expected = Exception.class)
    public void testCount() {
        int res;
        when(q.getSingleResult()).thenReturn((Long) 0l);
        res = cut.count();
        assertEquals(0, res);

        Whitebox.setInternalState(cut, "em", null);
        cut.count();
    }
}
