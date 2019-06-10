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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "langilea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Langilea.findAll", query = "SELECT l FROM Langilea l"),
    @NamedQuery(name = "Langilea.findByIDlangilea", query = "SELECT l FROM Langilea l WHERE l.iDlangilea = :iDlangilea"),
    @NamedQuery(name = "Langilea.findByIzena", query = "SELECT l FROM Langilea l WHERE l.izena = :izena"),
    @NamedQuery(name = "Langilea.findByAbizena", query = "SELECT l FROM Langilea l WHERE l.abizena = :abizena"),
    @NamedQuery(name = "Langilea.findByErabiltzailea", query = "SELECT l FROM Langilea l WHERE l.erabiltzailea = :erabiltzailea"),
    @NamedQuery(name = "Langilea.findByEposta", query = "SELECT l FROM Langilea l WHERE l.eposta = :eposta"),
    @NamedQuery(name = "Langilea.findByTelefonoa", query = "SELECT l FROM Langilea l WHERE l.telefonoa = :telefonoa"),
    @NamedQuery(name = "Langilea.findByAktibo", query = "SELECT l FROM Langilea l WHERE l.aktibo = :aktibo")})
public class Langilea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_langilea")
    private Short iDlangilea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "izena")
    private String izena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "abizena")
    private String abizena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "erabiltzailea")
    private String erabiltzailea;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "passwordHash")
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "passwordSalt")
    private String passwordSalt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "eposta")
    private String eposta;
    @Size(max = 9)
    @Column(name = "telefonoa")
    private String telefonoa;
    @Column(name = "aktibo")
    private Boolean aktibo;
    @JoinColumn(name = "ID_mota", referencedColumnName = "ID_langile_mota")
    @ManyToOne(optional = false)
    private LangileMota iDmota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "langilea")
    private Collection<LangileakAktiboa> langileakAktiboaCollection;

    public Langilea() {
    }

    public Langilea(Short iDlangilea) {
        this.iDlangilea = iDlangilea;
    }

    public Langilea(Short iDlangilea, String izena, String abizena, String erabiltzailea, String passwordHash, String passwordSalt, String eposta) {
        this.iDlangilea = iDlangilea;
        this.izena = izena;
        this.abizena = abizena;
        this.erabiltzailea = erabiltzailea;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.eposta = eposta;
    }

    public Short getIDlangilea() {
        return iDlangilea;
    }

    public void setIDlangilea(Short iDlangilea) {
        this.iDlangilea = iDlangilea;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena() {
        return abizena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    public String getErabiltzailea() {
        return erabiltzailea;
    }

    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getTelefonoa() {
        return telefonoa;
    }

    public void setTelefonoa(String telefonoa) {
        this.telefonoa = telefonoa;
    }

    public Boolean getAktibo() {
        return aktibo;
    }

    public void setAktibo(Boolean aktibo) {
        this.aktibo = aktibo;
    }

    public LangileMota getIDmota() {
        return iDmota;
    }

    public void setIDmota(LangileMota iDmota) {
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
        hash += (iDlangilea != null ? iDlangilea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Langilea)) {
            return false;
        }
        Langilea other = (Langilea) object;
        if ((this.iDlangilea == null && other.iDlangilea != null) || (this.iDlangilea != null && !this.iDlangilea.equals(other.iDlangilea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.Langilea[ iDlangilea=" + iDlangilea + " ]";
    }
    
}
