/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binding;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Klase honetan datu baseko AktiboMota taularekin egindako binding-aren emaitzak dira, AktiboMota taulak
 * dituen atributoak definitzen dira.
 * @author user
 */
@Entity
@Table(name = "aktibo_mota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AktiboMota.findAll", query = "SELECT a FROM AktiboMota a"),
    @NamedQuery(name = "AktiboMota.findByIDaktibomota", query = "SELECT a FROM AktiboMota a WHERE a.iDaktibomota = :iDaktibomota"),
    @NamedQuery(name = "AktiboMota.findByAktiboMota", query = "SELECT a FROM AktiboMota a WHERE a.aktiboMota = :aktiboMota"),
    @NamedQuery(name = "AktiboMota.findByDeskribapena", query = "SELECT a FROM AktiboMota a WHERE a.deskribapena = :deskribapena")})
public class AktiboMota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_aktibo_mota")
    private Short iDaktibomota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "aktibo_mota")
    private String aktiboMota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "deskribapena")
    private String deskribapena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDmota")
    private Collection<Aktiboa> aktiboaCollection;

    /**
     * AktiboMota klase berri bat sortzeko metodoa
     */
    public AktiboMota() {
    }

    /**
     * 
     * iDaktibomota propietatea AktiboMota klase berri bat sortzerakoan zehazten duen metodoa 
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     */
    public AktiboMota(Short iDaktibomota) {
        this.iDaktibomota = iDaktibomota;
    }

    /**
     * 
     * iDaktibomota, aktiboMota eta deskribapena propietateak AktiboMota klase berri bat sortzerakoan zehazten duen metodoa 
     * 
     @param value
     *     allowed object is
     *     {@link Short, String, String }
     */
    public AktiboMota(Short iDaktibomota, String aktiboMota, String deskribapena) {
        this.iDaktibomota = iDaktibomota;
        this.aktiboMota = aktiboMota;
        this.deskribapena = deskribapena;
    }

    /**
     * iDaktibomota propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link Short }
     */
    public Short getIDaktibomota() {
        return iDaktibomota;
    }

    /**
     * iDaktibomota propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     */
    public void setIDaktibomota(Short iDaktibomota) {
        this.iDaktibomota = iDaktibomota;
    }

    /**
     * aktiboMota propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getAktiboMota() {
        return aktiboMota;
    }

    /**
     * aktiboMota propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     */
    public void setAktiboMota(String aktiboMota) {
        this.aktiboMota = aktiboMota;
    }

    /**
     * deskribapena propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getDeskribapena() {
        return deskribapena;
    }

    /**
     * deskribapena propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    /**
     * aktiboaCollection lortzen du
     * 
     * @return
     *     possible object is
     *     {@link Collection<Aktiboa> }
     *     
     */
    @XmlTransient
    public Collection<Aktiboa> getAktiboaCollection() {
        return aktiboaCollection;
    }

    /**
     * aktiboaCollection balioa definitzen du
     * 
     * @param value
     *     allowed object is
     *     {@link Collection<Aktiboa> }
     *     
     */
    public void setAktiboaCollection(Collection<Aktiboa> aktiboaCollection) {
        this.aktiboaCollection = aktiboaCollection;
    }

    /**
     * Zehaztutako hashCode-a lortzen usten duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link int }
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDaktibomota != null ? iDaktibomota.hashCode() : 0);
        return hash;
    }

    /**
     * Hash estrukturak konparatzeko erabiltzen da 
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AktiboMota)) {
            return false;
        }
        AktiboMota other = (AktiboMota) object;
        if ((this.iDaktibomota == null && other.iDaktibomota != null) || (this.iDaktibomota != null && !this.iDaktibomota.equals(other.iDaktibomota))) {
            return false;
        }
        return true;
    }

    /**
     * iDaktibomota string baten bueltatzen duen metodoa
     *  @return
     *     possible object is
     *     {@link String }
     */
    @Override
    public String toString() {
        return "database.utils.AktiboMota[ iDaktibomota=" + iDaktibomota + " ]";
    }
    
}
