/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binding;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Klase honetan datu baseko Aktiboa taularekin egindako binding-aren emaitzak dira, Aktiboa taulak
 * dituen atributoak definitzen dira.
 * @author user
 */
@Entity
@Table(name = "aktiboa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aktiboa.findAll", query = "SELECT a FROM Aktiboa a"),
    @NamedQuery(name = "Aktiboa.findByIDaktiboa", query = "SELECT a FROM Aktiboa a WHERE a.iDaktiboa = :iDaktiboa")})
public class Aktiboa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_aktiboa")
    private Short iDaktiboa;
    @JoinTable(name = "langileak_aktiboa", joinColumns = {
        @JoinColumn(name = "ID_aktiboa", referencedColumnName = "ID_aktiboa")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_langilea", referencedColumnName = "ID_langilea")})
    @ManyToMany
    private Collection<Langilea> langileaCollection;
    @JoinColumn(name = "ID_mota", referencedColumnName = "ID_aktibo_mota")
    @ManyToOne(optional = false)
    private AktiboMota iDmota;

    /**
     * Aktiboa klase berri bat sortzeko metodoa
     */
    public Aktiboa() {
    }

    /**
     * 
     * idAktiboa propietatea pasatuz Aktibo klase berri bat sortzerakoan zehazten duen metodoa 
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     */
    public Aktiboa(Short iDaktiboa) {
        this.iDaktiboa = iDaktiboa;
    }

    /**
     * idAktiboa propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link Short }
     */
    public Short getIDaktiboa() {
        return iDaktiboa;
    }

    /**
     * iDaktiboa propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     */
    public void setIDaktiboa(Short iDaktiboa) {
        this.iDaktiboa = iDaktiboa;
    }

    /**
     * LangileaCollection lortzen du
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
     * LangileCollection balioa definitzen du
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
     * AktiboMota klaseko iDmota lortzen du
     * 
     * @return
     *     possible object is
     *     {@link AktiboMota }
     *     
     */
    public AktiboMota getIDmota() {
        return iDmota;
    }
    
    /**
     * AktiboMota klasearen iDmota definitzen du
     * 
     * @param value
     *     allowed object is
     *     {@link AktiboMota }
     */
    public void setIDmota(AktiboMota iDmota) {
        this.iDmota = iDmota;
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
        hash += (iDaktiboa != null ? iDaktiboa.hashCode() : 0);
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
        if (!(object instanceof Aktiboa)) {
            return false;
        }
        Aktiboa other = (Aktiboa) object;
        if ((this.iDaktiboa == null && other.iDaktiboa != null) || (this.iDaktiboa != null && !this.iDaktiboa.equals(other.iDaktiboa))) {
            return false;
        }
        return true;
    }

    /**
     * iDaktiboa string baten bueltatzen duen metodoa
     *  @return
     *     possible object is
     *     {@link String }
     */
    @Override
    public String toString() {
        return "database.utils.Aktiboa[ iDaktiboa=" + iDaktiboa + " ]";
    }
    
}
