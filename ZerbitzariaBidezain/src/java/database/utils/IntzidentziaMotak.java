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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "intzidentzia_motak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IntzidentziaMotak.findAll", query = "SELECT i FROM IntzidentziaMotak i"),
    @NamedQuery(name = "IntzidentziaMotak.findByIDintzidentziamota", query = "SELECT i FROM IntzidentziaMotak i WHERE i.iDintzidentziamota = :iDintzidentziamota"),
    @NamedQuery(name = "IntzidentziaMotak.findByIntzidentziaMota", query = "SELECT i FROM IntzidentziaMotak i WHERE i.intzidentziaMota = :intzidentziaMota")})
public class IntzidentziaMotak implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_intzidentzia_mota")
    private Short iDintzidentziamota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "intzidentzia_mota")
    private String intzidentziaMota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDmota")
    private Collection<IntzidentziaAktiboak> intzidentziaAktiboakCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDmota")
    private Collection<IntzidentziaAmaituak> intzidentziaAmaituakCollection;

    public IntzidentziaMotak() {
    }

    public IntzidentziaMotak(Short iDintzidentziamota) {
        this.iDintzidentziamota = iDintzidentziamota;
    }

    public IntzidentziaMotak(Short iDintzidentziamota, String intzidentziaMota) {
        this.iDintzidentziamota = iDintzidentziamota;
        this.intzidentziaMota = intzidentziaMota;
    }

    public Short getIDintzidentziamota() {
        return iDintzidentziamota;
    }

    public void setIDintzidentziamota(Short iDintzidentziamota) {
        this.iDintzidentziamota = iDintzidentziamota;
    }

    public String getIntzidentziaMota() {
        return intzidentziaMota;
    }

    public void setIntzidentziaMota(String intzidentziaMota) {
        this.intzidentziaMota = intzidentziaMota;
    }

    @XmlTransient
    public Collection<IntzidentziaAktiboak> getIntzidentziaAktiboakCollection() {
        return intzidentziaAktiboakCollection;
    }

    public void setIntzidentziaAktiboakCollection(Collection<IntzidentziaAktiboak> intzidentziaAktiboakCollection) {
        this.intzidentziaAktiboakCollection = intzidentziaAktiboakCollection;
    }

    @XmlTransient
    public Collection<IntzidentziaAmaituak> getIntzidentziaAmaituakCollection() {
        return intzidentziaAmaituakCollection;
    }

    public void setIntzidentziaAmaituakCollection(Collection<IntzidentziaAmaituak> intzidentziaAmaituakCollection) {
        this.intzidentziaAmaituakCollection = intzidentziaAmaituakCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDintzidentziamota != null ? iDintzidentziamota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntzidentziaMotak)) {
            return false;
        }
        IntzidentziaMotak other = (IntzidentziaMotak) object;
        if ((this.iDintzidentziamota == null && other.iDintzidentziamota != null) || (this.iDintzidentziamota != null && !this.iDintzidentziamota.equals(other.iDintzidentziamota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.IntzidentziaMotak[ iDintzidentziamota=" + iDintzidentziamota + " ]";
    }
    
}
