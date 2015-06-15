/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Manuel Cañamaque
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByCodVenta", query = "SELECT v FROM Venta v WHERE v.codVenta = :codVenta"),
    @NamedQuery(name = "Venta.findByFechaVenta", query = "SELECT v FROM Venta v WHERE v.fechaVenta = :fechaVenta"),
    @NamedQuery(name = "Venta.findByIva", query = "SELECT v FROM Venta v WHERE v.iva = :iva"),
    @NamedQuery(name = "Venta.findByImporteVenta", query = "SELECT v FROM Venta v WHERE v.importeVenta = :importeVenta"),
    @NamedQuery(name = "Venta.findByImporteVentaConIva", query = "SELECT v FROM Venta v WHERE v.importeVentaConIva = :importeVentaConIva")})
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodVenta")
    private Integer codVenta;
    @Column(name = "FechaVenta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    @Column(name = "Iva")
    private Integer iva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ImporteVenta")
    private BigDecimal importeVenta;
    @Column(name = "ImporteVentaConIva")
    private BigDecimal importeVentaConIva;
    @OneToMany(mappedBy = "venta")
    private Collection<Lineaventa> lineaventaCollection;
    @JoinColumn(name = "Cliente", referencedColumnName = "CodCliente")
    @ManyToOne
    private Cliente cliente;

    public Venta() {
    }

    public Venta(Integer codVenta) {
        this.codVenta = codVenta;
    }

    public Integer getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(Integer codVenta) {
        this.codVenta = codVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public BigDecimal getImporteVenta() {
        return importeVenta;
    }

    public void setImporteVenta(BigDecimal importeVenta) {
        this.importeVenta = importeVenta;
    }

    public BigDecimal getImporteVentaConIva() {
        return importeVentaConIva;
    }

    public void setImporteVentaConIva(BigDecimal importeVentaConIva) {
        this.importeVentaConIva = importeVentaConIva;
    }

    @XmlTransient
    public Collection<Lineaventa> getLineaventaCollection() {
        return lineaventaCollection;
    }

    public void setLineaventaCollection(Collection<Lineaventa> lineaventaCollection) {
        this.lineaventaCollection = lineaventaCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVenta != null ? codVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.codVenta == null && other.codVenta != null) || (this.codVenta != null && !this.codVenta.equals(other.codVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Venta[ codVenta=" + codVenta + " ]";
    }
    
}
