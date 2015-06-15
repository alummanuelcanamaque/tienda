/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manuel Cañamaque
 */
@Entity
@Table(name = "lineaventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lineaventa.findAll", query = "SELECT l FROM Lineaventa l"),
    @NamedQuery(name = "Lineaventa.findByCodLineaVenta", query = "SELECT l FROM Lineaventa l WHERE l.codLineaVenta = :codLineaVenta"),
    @NamedQuery(name = "Lineaventa.findByCantidad", query = "SELECT l FROM Lineaventa l WHERE l.cantidad = :cantidad"),
    @NamedQuery(name = "Lineaventa.findByImporteLineaVenta", query = "SELECT l FROM Lineaventa l WHERE l.importeLineaVenta = :importeLineaVenta")})
public class Lineaventa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodLineaVenta")
    private Integer codLineaVenta;
    @Column(name = "Cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ImporteLineaVenta")
    private BigDecimal importeLineaVenta;
    @JoinColumn(name = "Venta", referencedColumnName = "CodVenta")
    @ManyToOne
    private Venta venta;
    @JoinColumn(name = "Articulo", referencedColumnName = "CodArticulo")
    @ManyToOne
    private Articulo articulo;

    public Lineaventa() {
    }

    public Lineaventa(Integer codLineaVenta) {
        this.codLineaVenta = codLineaVenta;
    }

    public Integer getCodLineaVenta() {
        return codLineaVenta;
    }

    public void setCodLineaVenta(Integer codLineaVenta) {
        this.codLineaVenta = codLineaVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getImporteLineaVenta() {
        return importeLineaVenta;
    }

    public void setImporteLineaVenta(BigDecimal importeLineaVenta) {
        this.importeLineaVenta = importeLineaVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLineaVenta != null ? codLineaVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lineaventa)) {
            return false;
        }
        Lineaventa other = (Lineaventa) object;
        if ((this.codLineaVenta == null && other.codLineaVenta != null) || (this.codLineaVenta != null && !this.codLineaVenta.equals(other.codLineaVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Lineaventa[ codLineaVenta=" + codLineaVenta + " ]";
    }
    
}
