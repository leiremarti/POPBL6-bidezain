/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "intzidentzia_amaitua")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IntzidentziaAmaitua.findAll", query = "SELECT i FROM IntzidentziaAmaitua i"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByIDintzidentzia", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.iDintzidentzia = :iDintzidentzia"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByProbintzia", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.probintzia = :probintzia"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByKausa", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.kausa = :kausa"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByHerria", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.herria = :herria"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByIntzidentziaData", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.intzidentziaData = :intzidentziaData"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByMaila", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.maila = :maila"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByErrepidea", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.errepidea = :errepidea"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByZentzua", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.zentzua = :zentzua"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByLatitudea", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.latitudea = :latitudea"),
    @NamedQuery(name = "IntzidentziaAmaitua.findByLongitudea", query = "SELECT i FROM IntzidentziaAmaitua i WHERE i.longitudea = :longitudea")})
public class IntzidentziaAmaitua implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_intzidentzia")
    private Short iDintzidentzia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "probintzia")
    private String probintzia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "kausa")
    private String kausa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "herria")
    private String herria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "intzidentziaData")
    private String intzidentziaData;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "maila")
    private String maila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "errepidea")
    private String errepidea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "zentzua")
    private String zentzua;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitudea")
    private BigDecimal latitudea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitudea")
    private BigDecimal longitudea;
    @JoinColumn(name = "ID_mota", referencedColumnName = "ID_intzidentzia_mota")
    @ManyToOne(optional = false)
    private IntzidentziaMota iDmota;

    public IntzidentziaAmaitua() {
    }

    public IntzidentziaAmaitua(Short iDintzidentzia) {
        this.iDintzidentzia = iDintzidentzia;
    }

    public IntzidentziaAmaitua(Short iDintzidentzia, String probintzia, String kausa, String herria, String intzidentziaData, String maila, String errepidea, String zentzua, BigDecimal latitudea, BigDecimal longitudea) {
        this.iDintzidentzia = iDintzidentzia;
        this.probintzia = probintzia;
        this.kausa = kausa;
        this.herria = herria;
        this.intzidentziaData = intzidentziaData;
        this.maila = maila;
        this.errepidea = errepidea;
        this.zentzua = zentzua;
        this.latitudea = latitudea;
        this.longitudea = longitudea;
    }

    public Short getIDintzidentzia() {
        return iDintzidentzia;
    }

    public void setIDintzidentzia(Short iDintzidentzia) {
        this.iDintzidentzia = iDintzidentzia;
    }

    public String getProbintzia() {
        return probintzia;
    }

    public void setProbintzia(String probintzia) {
        this.probintzia = probintzia;
    }

    public String getKausa() {
        return kausa;
    }

    public void setKausa(String kausa) {
        this.kausa = kausa;
    }

    public String getHerria() {
        return herria;
    }

    public void setHerria(String herria) {
        this.herria = herria;
    }

    public String getIntzidentziaData() {
        return intzidentziaData;
    }

    public void setIntzidentziaData(String intzidentziaData) {
        this.intzidentziaData = intzidentziaData;
    }

    public String getMaila() {
        return maila;
    }

    public void setMaila(String maila) {
        this.maila = maila;
    }

    public String getErrepidea() {
        return errepidea;
    }

    public void setErrepidea(String errepidea) {
        this.errepidea = errepidea;
    }

    public String getZentzua() {
        return zentzua;
    }

    public void setZentzua(String zentzua) {
        this.zentzua = zentzua;
    }

    public BigDecimal getLatitudea() {
        return latitudea;
    }

    public void setLatitudea(BigDecimal latitudea) {
        this.latitudea = latitudea;
    }

    public BigDecimal getLongitudea() {
        return longitudea;
    }

    public void setLongitudea(BigDecimal longitudea) {
        this.longitudea = longitudea;
    }

    public IntzidentziaMota getIDmota() {
        return iDmota;
    }

    public void setIDmota(IntzidentziaMota iDmota) {
        this.iDmota = iDmota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDintzidentzia != null ? iDintzidentzia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntzidentziaAmaitua)) {
            return false;
        }
        IntzidentziaAmaitua other = (IntzidentziaAmaitua) object;
        if ((this.iDintzidentzia == null && other.iDintzidentzia != null) || (this.iDintzidentzia != null && !this.iDintzidentzia.equals(other.iDintzidentzia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.IntzidentziaAmaitua[ iDintzidentzia=" + iDintzidentzia + " ]";
    }
    
}
