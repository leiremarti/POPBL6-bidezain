/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
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
    @JoinColumn(name = "ID_mota", referencedColumnName = "ID_aktibo_mota")
    @ManyToOne(optional = false)
    private AktiboMota iDmota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aktiboa")
    private Collection<LangileakAktiboa> langileakAktiboaCollection;

    public Aktiboa() {
    }

    public Aktiboa(Short iDaktiboa) {
        this.iDaktiboa = iDaktiboa;
    }

    public Short getIDaktiboa() {
        return iDaktiboa;
    }

    public void setIDaktiboa(Short iDaktiboa) {
        this.iDaktiboa = iDaktiboa;
    }

    public AktiboMota getIDmota() {
        return iDmota;
    }

    public void setIDmota(AktiboMota iDmota) {
        this.iDmota = iDmota;
    }

    @XmlTransient
    public Collection<LangileakAktiboa> getLangileakAktiboaCollection() {
        return langileakAktiboaCollection;
    }

    public void setLangileakAktiboaCollection(Collection<LangileakAktiboa> langileakAktiboaCollection) {
        this.langileakAktiboaCollection = langileakAktiboaCollection;
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
        if (!(object instanceof Aktiboa)) {
            return false;
        }
        Aktiboa other = (Aktiboa) object;
        if ((this.iDaktiboa == null && other.iDaktiboa != null) || (this.iDaktiboa != null && !this.iDaktiboa.equals(other.iDaktiboa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.Aktiboa[ iDaktiboa=" + iDaktiboa + " ]";
    }
    
}
