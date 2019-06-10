/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "erabiltzailea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Erabiltzailea.findAll", query = "SELECT e FROM Erabiltzailea e"),
    @NamedQuery(name = "Erabiltzailea.findByIDerabiltzailea", query = "SELECT e FROM Erabiltzailea e WHERE e.iDerabiltzailea = :iDerabiltzailea"),
    @NamedQuery(name = "Erabiltzailea.findByIzena", query = "SELECT e FROM Erabiltzailea e WHERE e.izena = :izena"),
    @NamedQuery(name = "Erabiltzailea.findByAbizena", query = "SELECT e FROM Erabiltzailea e WHERE e.abizena = :abizena"),
    @NamedQuery(name = "Erabiltzailea.findByErabiltzailea", query = "SELECT e FROM Erabiltzailea e WHERE e.erabiltzailea = :erabiltzailea"),
    @NamedQuery(name = "Erabiltzailea.findByEposta", query = "SELECT e FROM Erabiltzailea e WHERE e.eposta = :eposta"),
    @NamedQuery(name = "Erabiltzailea.findByTelefonoa", query = "SELECT e FROM Erabiltzailea e WHERE e.telefonoa = :telefonoa"),
    @NamedQuery(name = "Erabiltzailea.findByAktibo", query = "SELECT e FROM Erabiltzailea e WHERE e.aktibo = :aktibo")})
public class Erabiltzailea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_erabiltzailea")
    private Short iDerabiltzailea;
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
    @Lob
    @Size(max = 2147483647)
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

    public Erabiltzailea() {
    }

    public Erabiltzailea(Short iDerabiltzailea) {
        this.iDerabiltzailea = iDerabiltzailea;
    }

    public Erabiltzailea(Short iDerabiltzailea, String izena, String abizena, String erabiltzailea, String passwordHash, String eposta) {
        this.iDerabiltzailea = iDerabiltzailea;
        this.izena = izena;
        this.abizena = abizena;
        this.erabiltzailea = erabiltzailea;
        this.passwordHash = passwordHash;
        this.eposta = eposta;
    }

    public Short getIDerabiltzailea() {
        return iDerabiltzailea;
    }

    public void setIDerabiltzailea(Short iDerabiltzailea) {
        this.iDerabiltzailea = iDerabiltzailea;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDerabiltzailea != null ? iDerabiltzailea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Erabiltzailea)) {
            return false;
        }
        Erabiltzailea other = (Erabiltzailea) object;
        if ((this.iDerabiltzailea == null && other.iDerabiltzailea != null) || (this.iDerabiltzailea != null && !this.iDerabiltzailea.equals(other.iDerabiltzailea))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        String alta = this.aktibo?("<input type='checkbox' name='button_clicked' value='"+this.iDerabiltzailea+"'>Altan"):("<p style='color:red'>Bajan</p>");
        String resp = "[\""+this.izena+"\",\""+this.abizena+"\",\""+this.erabiltzailea+"\",\""+this.eposta+"\",\""+this.telefonoa+"\",\""+alta+"\"]";
        return resp; 
    }
}
