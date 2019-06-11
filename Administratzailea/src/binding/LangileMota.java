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
 * Klase honetan datu baseko LangileMota taularekin egindako binding-aren emaitzak dira, LangileMota taulak
 * dituen atributoak definitzen dira.
 * @author user
 */
@Entity
@Table(name = "langile_mota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangileMota.findAll", query = "SELECT l FROM LangileMota l"),
    @NamedQuery(name = "LangileMota.findByIDlangilemota", query = "SELECT l FROM LangileMota l WHERE l.iDlangilemota = :iDlangilemota"),
    @NamedQuery(name = "LangileMota.findByLangileMota", query = "SELECT l FROM LangileMota l WHERE l.langileMota = :langileMota"),
    @NamedQuery(name = "LangileMota.findByDeskribapena", query = "SELECT l FROM LangileMota l WHERE l.deskribapena = :deskribapena")})
public class LangileMota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_langile_mota")
    private Short iDlangilemota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "langile_mota")
    private String langileMota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "deskribapena")
    private String deskribapena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDmota")
    private Collection<Langilea> langileaCollection;

    /**
     * LangileMota klase berri bat sortzeko metodoa
     */
    public LangileMota() {
    }

    /**
     * 
     * iDlangilemota propietatea pasatuz LangileMota klase berri bat sortzerakoan zehazten duen metodoa 
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     */
    public LangileMota(Short iDlangilemota) {
        this.iDlangilemota = iDlangilemota;
    }

    /**
     * 
     * iDlangilemota, langileMota eta deskribapena propietateak pasatuz LangileMota klase berri bat sortzerakoan zehazten duen metodoa 
     * 
     * @param value
     *     allowed object is
     *     {@link int, String, String }
     */
    public LangileMota(Short iDlangilemota, String langileMota, String deskribapena) {
        this.iDlangilemota = iDlangilemota;
        this.langileMota = langileMota;
        this.deskribapena = deskribapena;
    }

    /**
     * iDlangilemota propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link int }
     */
    public Short getIDlangilemota() {
        return iDlangilemota;
    }

    /**
     * iDlangilemota propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     */
    public void setIDlangilemota(Short iDlangilemota) {
        this.iDlangilemota = iDlangilemota;
    }

    /**
     * langileMota propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getLangileMota() {
        return langileMota;
    }

    /**
     * langileMota propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setLangileMota(String langileMota) {
        this.langileMota = langileMota;
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
     * langileaCollection lortzen du
     * 
     * @return
     *     possible object is
     *     {@link Collection<Langilea> }
     *     
     */
    @XmlTransient
    public Collection<Langilea> getLangileaCollection() {
        return langileaCollection;
    }

    /**
     * langileaCollection balioa definitzen du
     * 
     * @param value
     *     allowed object is
     *     {@link Collection<Langilea> }
     *     
     */
    public void setLangileaCollection(Collection<Langilea> langileaCollection) {
        this.langileaCollection = langileaCollection;
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
        hash += (iDlangilemota != null ? iDlangilemota.hashCode() : 0);
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
        if (!(object instanceof LangileMota)) {
            return false;
        }
        LangileMota other = (LangileMota) object;
        if ((this.iDlangilemota == null && other.iDlangilemota != null) || (this.iDlangilemota != null && !this.iDlangilemota.equals(other.iDlangilemota))) {
            return false;
        }
        return true;
    }

    /**
     * iDlangilemota string baten bueltatzen duen metodoa
     *  @return
     *     possible object is
     *     {@link String }
     */
    @Override
    public String toString() {
        return "database.utils.LangileMota[ iDlangilemota=" + iDlangilemota + " ]";
    }
    
}
