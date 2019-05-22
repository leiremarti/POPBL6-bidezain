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
@Table(name = "aktibo_motak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AktiboMotak.findAll", query = "SELECT a FROM AktiboMotak a"),
    @NamedQuery(name = "AktiboMotak.findByIDaktibomota", query = "SELECT a FROM AktiboMotak a WHERE a.iDaktibomota = :iDaktibomota"),
    @NamedQuery(name = "AktiboMotak.findByAktiboMota", query = "SELECT a FROM AktiboMotak a WHERE a.aktiboMota = :aktiboMota"),
    @NamedQuery(name = "AktiboMotak.findByDeskribapena", query = "SELECT a FROM AktiboMotak a WHERE a.deskribapena = :deskribapena")})
public class AktiboMotak implements Serializable {
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
    private Collection<Aktiboak> aktiboakCollection;

    public AktiboMotak() {
    }

    public AktiboMotak(Short iDaktibomota) {
        this.iDaktibomota = iDaktibomota;
    }

    public AktiboMotak(Short iDaktibomota, String aktiboMota, String deskribapena) {
        this.iDaktibomota = iDaktibomota;
        this.aktiboMota = aktiboMota;
        this.deskribapena = deskribapena;
    }

    public Short getIDaktibomota() {
        return iDaktibomota;
    }

    public void setIDaktibomota(Short iDaktibomota) {
        this.iDaktibomota = iDaktibomota;
    }

    public String getAktiboMota() {
        return aktiboMota;
    }

    public void setAktiboMota(String aktiboMota) {
        this.aktiboMota = aktiboMota;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    @XmlTransient
    public Collection<Aktiboak> getAktiboakCollection() {
        return aktiboakCollection;
    }

    public void setAktiboakCollection(Collection<Aktiboak> aktiboakCollection) {
        this.aktiboakCollection = aktiboakCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDaktibomota != null ? iDaktibomota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AktiboMotak)) {
            return false;
        }
        AktiboMotak other = (AktiboMotak) object;
        if ((this.iDaktibomota == null && other.iDaktibomota != null) || (this.iDaktibomota != null && !this.iDaktibomota.equals(other.iDaktibomota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.AktiboMotak[ iDaktibomota=" + iDaktibomota + " ]";
    }
    
}
