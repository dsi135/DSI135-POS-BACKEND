/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arevalo
 */
@Entity
@Table(name = "detalle_orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleOrden.findAll", query = "SELECT d FROM DetalleOrden d")
   , @NamedQuery(name = "DetalleOrden.findById", query = "SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.id = :id")
   , @NamedQuery(name = "DetalleOrden.findByProducto", query = "SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.producto = :producto")
   , @NamedQuery(name = "DetalleOrden.findByCantidad", query = "SELECT d FROM DetalleOrden d WHERE d.cantidad = :cantidad")
   , @NamedQuery(name = "DetalleOrden.findByPrecio", query = "SELECT d FROM DetalleOrden d WHERE d.precio = :precio")})
public class DetalleOrden implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleOrdenPK detalleOrdenPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orden orden;
    @JoinColumn(name = "producto", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Producto producto1;

    public DetalleOrden() {
    }

    public DetalleOrden(DetalleOrdenPK detalleOrdenPK) {
        this.detalleOrdenPK = detalleOrdenPK;
    }

    public DetalleOrden(int id, int producto) {
        this.detalleOrdenPK = new DetalleOrdenPK(id, producto);
    }

    public DetalleOrdenPK getDetalleOrdenPK() {
        return detalleOrdenPK;
    }

    public void setDetalleOrdenPK(DetalleOrdenPK detalleOrdenPK) {
        this.detalleOrdenPK = detalleOrdenPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleOrdenPK != null ? detalleOrdenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrden)) {
            return false;
        }
        DetalleOrden other = (DetalleOrden) object;
        if ((this.detalleOrdenPK == null && other.detalleOrdenPK != null) || (this.detalleOrdenPK != null && !this.detalleOrdenPK.equals(other.detalleOrdenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dsi2019.ues.fmocc.ingenieria.dsi2019.entity.DetalleOrden[ detalleOrdenPK=" + detalleOrdenPK + " ]";
    }
    
}
