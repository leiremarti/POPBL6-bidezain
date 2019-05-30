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
@Table(name = "langile_motak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangileMotak.findAll", query = "SELECT l FROM LangileMotak l"),
    @NamedQuery(name = "LangileMotak.findByIDlangilemota", query = "SELECT l FROM LangileMotak l WHERE l.iDlangilemota = :iDlangilemota"),
    @NamedQuery(name = "LangileMotak.findByLangileMota", query = "SELECT l FROM LangileMotak l WHERE l.langileMota = :langileMota"),
    @NamedQuery(name = "LangileMotak.findByDeskribapena", query = "SELECT l FROM LangileMotak l WHERE l.deskribapena = :deskribapena")})
public class LangileMotak implements Serializable {
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
    private Collection<Langilea> langileakCollection;

    public LangileMotak() {
    }

    public LangileMotak(Short iDlangilemota) {
        this.iDlangilemota = iDlangilemota;
    }

    public LangileMotak(Short iDlangilemota, String langileMota, String deskribapena) {
        this.iDlangilemota = iDlangilemota;
        this.langileMota = langileMota;
        this.deskribapena = deskribapena;
    }

    public Short getIDlangilemota() {
        return iDlangilemota;
    }

    public void setIDlangilemota(Short iDlangilemota) {
        this.iDlangilemota = iDlangilemota;
    }

    public String getLangileMota() {
        return langileMota;
    }

    public void setLangileMota(String langileMota) {
        this.langileMota = langileMota;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    @XmlTransient
    public Collection<Langilea> getLangileakCollection() {
        return langileakCollection;
    }

    public void setLangileakCollection(Collection<Langilea> langileakCollection) {
        this.langileakCollection = langileakCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDlangilemota != null ? iDlangilemota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangileMotak)) {
            return false;
        }
        LangileMotak other = (LangileMotak) object;
        if ((this.iDlangilemota == null && other.iDlangilemota != null) || (this.iDlangilemota != null && !this.iDlangilemota.equals(other.iDlangilemota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.LangileMotak[ iDlangilemota=" + iDlangilemota + " ]";
    }
    
}
