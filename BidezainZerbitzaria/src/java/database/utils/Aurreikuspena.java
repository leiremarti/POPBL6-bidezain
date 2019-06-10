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
@Table(name = "aurreikuspena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aurreikuspena.findAll", query = "SELECT a FROM Aurreikuspena a"),
    @NamedQuery(name = "Aurreikuspena.findByIDaurreikuspena", query = "SELECT a FROM Aurreikuspena a WHERE a.iDaurreikuspena = :iDaurreikuspena"),
    @NamedQuery(name = "Aurreikuspena.findByErrepidea", query = "SELECT a FROM Aurreikuspena a WHERE a.errepidea = :errepidea"),
    @NamedQuery(name = "Aurreikuspena.findByProbintzia", query = "SELECT a FROM Aurreikuspena a WHERE a.probintzia = :probintzia"),
    @NamedQuery(name = "Aurreikuspena.findByHerria", query = "SELECT a FROM Aurreikuspena a WHERE a.herria = :herria"),
    @NamedQuery(name = "Aurreikuspena.findByAstekoEguna", query = "SELECT a FROM Aurreikuspena a WHERE a.astekoEguna = :astekoEguna"),
    @NamedQuery(name = "Aurreikuspena.findByData", query = "SELECT a FROM Aurreikuspena a WHERE a.data = :data"),
    @NamedQuery(name = "Aurreikuspena.findByLat", query = "SELECT a FROM Aurreikuspena a WHERE a.lat = :lat"),
    @NamedQuery(name = "Aurreikuspena.findByLng", query = "SELECT a FROM Aurreikuspena a WHERE a.lng = :lng"),
    @NamedQuery(name = "Aurreikuspena.findByTenperatura", query = "SELECT a FROM Aurreikuspena a WHERE a.tenperatura = :tenperatura"),
    @NamedQuery(name = "Aurreikuspena.findByHaizea", query = "SELECT a FROM Aurreikuspena a WHERE a.haizea = :haizea"),
    @NamedQuery(name = "Aurreikuspena.findByPrezipitazioa", query = "SELECT a FROM Aurreikuspena a WHERE a.prezipitazioa = :prezipitazioa"),
    @NamedQuery(name = "Aurreikuspena.findByHasieraKm", query = "SELECT a FROM Aurreikuspena a WHERE a.hasieraKm = :hasieraKm"),
    @NamedQuery(name = "Aurreikuspena.findByAmaieraKm", query = "SELECT a FROM Aurreikuspena a WHERE a.amaieraKm = :amaieraKm"),
    @NamedQuery(name = "Aurreikuspena.findByItIs", query = "SELECT a FROM Aurreikuspena a WHERE a.itIs = :itIs")})
public class Aurreikuspena implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_aurreikuspena")
    private Short iDaurreikuspena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "errepidea")
    private String errepidea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "probintzia")
    private String probintzia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "herria")
    private String herria;
    @Size(max = 3)
    @Column(name = "astekoEguna")
    private String astekoEguna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "data")
    private String data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "lat")
    private BigDecimal lat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lng")
    private BigDecimal lng;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tenperatura")
    private float tenperatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haizea")
    private float haizea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prezipitazioa")
    private float prezipitazioa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hasieraKm")
    private float hasieraKm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amaieraKm")
    private float amaieraKm;
    @Column(name = "itIs")
    private Boolean itIs;
    @JoinColumn(name = "ID_mota", referencedColumnName = "ID_intzidentzia_mota")
    @ManyToOne(optional = false)
    private IntzidentziaMota iDmota;

    public Aurreikuspena() {
    }

    public Aurreikuspena(Short iDaurreikuspena) {
        this.iDaurreikuspena = iDaurreikuspena;
    }

    public Aurreikuspena(Short iDaurreikuspena, String errepidea, String probintzia, String herria, String data, BigDecimal lat, BigDecimal lng, float tenperatura, float haizea, float prezipitazioa, float hasieraKm, float amaieraKm) {
        this.iDaurreikuspena = iDaurreikuspena;
        this.errepidea = errepidea;
        this.probintzia = probintzia;
        this.herria = herria;
        this.data = data;
        this.lat = lat;
        this.lng = lng;
        this.tenperatura = tenperatura;
        this.haizea = haizea;
        this.prezipitazioa = prezipitazioa;
        this.hasieraKm = hasieraKm;
        this.amaieraKm = amaieraKm;
    }

    public Short getIDaurreikuspena() {
        return iDaurreikuspena;
    }

    public void setIDaurreikuspena(Short iDaurreikuspena) {
        this.iDaurreikuspena = iDaurreikuspena;
    }

    public String getErrepidea() {
        return errepidea;
    }

    public void setErrepidea(String errepidea) {
        this.errepidea = errepidea;
    }

    public String getProbintzia() {
        return probintzia;
    }

    public void setProbintzia(String probintzia) {
        this.probintzia = probintzia;
    }

    public String getHerria() {
        return herria;
    }

    public void setHerria(String herria) {
        this.herria = herria;
    }

    public String getAstekoEguna() {
        return astekoEguna;
    }

    public void setAstekoEguna(String astekoEguna) {
        this.astekoEguna = astekoEguna;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public float getTenperatura() {
        return tenperatura;
    }

    public void setTenperatura(float tenperatura) {
        this.tenperatura = tenperatura;
    }

    public float getHaizea() {
        return haizea;
    }

    public void setHaizea(float haizea) {
        this.haizea = haizea;
    }

    public float getPrezipitazioa() {
        return prezipitazioa;
    }

    public void setPrezipitazioa(float prezipitazioa) {
        this.prezipitazioa = prezipitazioa;
    }

    public float getHasieraKm() {
        return hasieraKm;
    }

    public void setHasieraKm(float hasieraKm) {
        this.hasieraKm = hasieraKm;
    }

    public float getAmaieraKm() {
        return amaieraKm;
    }

    public void setAmaieraKm(float amaieraKm) {
        this.amaieraKm = amaieraKm;
    }

    public Boolean getItIs() {
        return itIs;
    }

    public void setItIs(Boolean itIs) {
        this.itIs = itIs;
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
        hash += (iDaurreikuspena != null ? iDaurreikuspena.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aurreikuspena)) {
            return false;
        }
        Aurreikuspena other = (Aurreikuspena) object;
        if ((this.iDaurreikuspena == null && other.iDaurreikuspena != null) || (this.iDaurreikuspena != null && !this.iDaurreikuspena.equals(other.iDaurreikuspena))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.Aurreikuspena[ iDaurreikuspena=" + iDaurreikuspena + " ]";
    }
    
}
