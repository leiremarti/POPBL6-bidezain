/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

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
 *
 * @author user
 */
@Entity
@Table(name = "aktiboak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aktiboak.findAll", query = "SELECT a FROM Aktiboak a"),
    @NamedQuery(name = "Aktiboak.findByIDaktiboa", query = "SELECT a FROM Aktiboak a WHERE a.iDaktiboa = :iDaktiboa")})
public class Aktiboak implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_aktiboa")
    private Short iDaktiboa;
    @JoinTable(name = "langileak_aktiboak", joinColumns = {
        @JoinColumn(name = "ID_aktiboa", referencedColumnName = "ID_aktiboa")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_langilea", referencedColumnName = "ID_langilea")})
    @ManyToMany
    private Collection<Langileak> langileakCollection;
    @JoinColumn(name = "ID_mota", referencedColumnName = "ID_aktibo_mota")
    @ManyToOne(optional = false)
    private AktiboMotak iDmota;

    public Aktiboak() {
    }

    public Aktiboak(Short iDaktiboa) {
        this.iDaktiboa = iDaktiboa;
    }

    public Short getIDaktiboa() {
        return iDaktiboa;
    }

    public void setIDaktiboa(Short iDaktiboa) {
        this.iDaktiboa = iDaktiboa;
    }

    @XmlTransient
    public Collection<Langileak> getLangileakCollection() {
        return langileakCollection;
    }

    public void setLangileakCollection(Collection<Langileak> langileakCollection) {
        this.langileakCollection = langileakCollection;
    }

    public AktiboMotak getIDmota() {
        return iDmota;
    }

    public void setIDmota(AktiboMotak iDmota) {
        this.iDmota = iDmota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDaktiboa != null ? iDaktiboa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aktiboak)) {
            return false;
        }
        Aktiboak other = (Aktiboak) object;
        if ((this.iDaktiboa == null && other.iDaktiboa != null) || (this.iDaktiboa != null && !this.iDaktiboa.equals(other.iDaktiboa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.Aktiboak[ iDaktiboa=" + iDaktiboa + " ]";
    }
    
}
