/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "langileak_aktiboa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangileakAktiboa.findAll", query = "SELECT l FROM LangileakAktiboa l"),
    @NamedQuery(name = "LangileakAktiboa.findByIDaktiboa", query = "SELECT l FROM LangileakAktiboa l WHERE l.langileakAktiboaPK.iDaktiboa = :iDaktiboa"),
    @NamedQuery(name = "LangileakAktiboa.findByIDlangilea", query = "SELECT l FROM LangileakAktiboa l WHERE l.langileakAktiboaPK.iDlangilea = :iDlangilea"),
    @NamedQuery(name = "LangileakAktiboa.findByHasieraData", query = "SELECT l FROM LangileakAktiboa l WHERE l.hasieraData = :hasieraData"),
    @NamedQuery(name = "LangileakAktiboa.findByAmaieraData", query = "SELECT l FROM LangileakAktiboa l WHERE l.amaieraData = :amaieraData")})
public class LangileakAktiboa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangileakAktiboaPK langileakAktiboaPK;
    @Size(max = 50)
    @Column(name = "hasiera_data")
    private String hasieraData;
    @Size(max = 50)
    @Column(name = "amaiera_data")
    private String amaieraData;
    @JoinColumn(name = "ID_aktiboa", referencedColumnName = "ID_aktiboa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Aktiboa aktiboa;
    @JoinColumn(name = "ID_langilea", referencedColumnName = "ID_langilea", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Langilea langilea;

    public LangileakAktiboa() {
    }

    public LangileakAktiboa(LangileakAktiboaPK langileakAktiboaPK) {
        this.langileakAktiboaPK = langileakAktiboaPK;
    }

    public LangileakAktiboa(short iDaktiboa, short iDlangilea) {
        this.langileakAktiboaPK = new LangileakAktiboaPK(iDaktiboa, iDlangilea);
    }

    public LangileakAktiboaPK getLangileakAktiboaPK() {
        return langileakAktiboaPK;
    }

    public void setLangileakAktiboaPK(LangileakAktiboaPK langileakAktiboaPK) {
        this.langileakAktiboaPK = langileakAktiboaPK;
    }

    public String getHasieraData() {
        return hasieraData;
    }

    public void setHasieraData(String hasieraData) {
        this.hasieraData = hasieraData;
    }

    public String getAmaieraData() {
        return amaieraData;
    }

    public void setAmaieraData(String amaieraData) {
        this.amaieraData = amaieraData;
    }

    public Aktiboa getAktiboa() {
        return aktiboa;
    }

    public void setAktiboa(Aktiboa aktiboa) {
        this.aktiboa = aktiboa;
    }

    public Langilea getLangilea() {
        return langilea;
    }

    public void setLangilea(Langilea langilea) {
        this.langilea = langilea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (langileakAktiboaPK != null ? langileakAktiboaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangileakAktiboa)) {
            return false;
        }
        LangileakAktiboa other = (LangileakAktiboa) object;
        if ((this.langileakAktiboaPK == null && other.langileakAktiboaPK != null) || (this.langileakAktiboaPK != null && !this.langileakAktiboaPK.equals(other.langileakAktiboaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.LangileakAktiboa[ langileakAktiboaPK=" + langileakAktiboaPK + " ]";
    }
    
}
