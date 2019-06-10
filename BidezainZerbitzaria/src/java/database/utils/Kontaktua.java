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
@Table(name = "kontaktua")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kontaktua.findAll", query = "SELECT k FROM Kontaktua k"),
    @NamedQuery(name = "Kontaktua.findByIDkontaktua", query = "SELECT k FROM Kontaktua k WHERE k.iDkontaktua = :iDkontaktua"),
    @NamedQuery(name = "Kontaktua.findByKontaktua", query = "SELECT k FROM Kontaktua k WHERE k.kontaktua = :kontaktua"),
    @NamedQuery(name = "Kontaktua.findByDeskribapena", query = "SELECT k FROM Kontaktua k WHERE k.deskribapena = :deskribapena")})
public class Kontaktua implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_kontaktua")
    private Short iDkontaktua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "kontaktua")
    private String kontaktua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "deskribapena")
    private String deskribapena;

    public Kontaktua() {
    }

    public Kontaktua(Short iDkontaktua) {
        this.iDkontaktua = iDkontaktua;
    }

    public Kontaktua(Short iDkontaktua, String kontaktua, String deskribapena) {
        this.iDkontaktua = iDkontaktua;
        this.kontaktua = kontaktua;
        this.deskribapena = deskribapena;
    }

    public Short getIDkontaktua() {
        return iDkontaktua;
    }

    public void setIDkontaktua(Short iDkontaktua) {
        this.iDkontaktua = iDkontaktua;
    }

    public String getKontaktua() {
        return kontaktua;
    }

    public void setKontaktua(String kontaktua) {
        this.kontaktua = kontaktua;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDkontaktua != null ? iDkontaktua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kontaktua)) {
            return false;
        }
        Kontaktua other = (Kontaktua) object;
        if ((this.iDkontaktua == null && other.iDkontaktua != null) || (this.iDkontaktua != null && !this.iDkontaktua.equals(other.iDkontaktua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.Kontaktua[ iDkontaktua=" + iDkontaktua + " ]";
    }
    
}
