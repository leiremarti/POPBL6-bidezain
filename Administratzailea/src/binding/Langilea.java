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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * Klase honetan datu baseko Langilea taularekin egindako binding-aren emaitzak dira, Langilea taulak
 * dituen atributoak definitzen dira.
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
    @Column(name = "passwordHash")
    private byte[] passwordHash;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "passwordSalt")
    private byte[] passwordSalt;
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
    @ManyToMany(mappedBy = "langileaCollection")
    private Collection<Aktiboa> aktiboaCollection;
    @JoinColumn(name = "ID_mota", referencedColumnName = "ID_langile_mota")
    @ManyToOne(optional = false)
    private LangileMota iDmota;

    /**
     * Langilea klase berri bat sortzeko metodoa
     */
    public Langilea() {
    }

    /**
     * 
     * iDlangilea propietatea pasatuz Langilea klase berri bat sortzerakoan zehazten duen metodoa 
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     */
    public Langilea(Short iDlangilea) {
        this.iDlangilea = iDlangilea;
    }

    /**
     * 
     * iDlangilea, izena, abizena, erabiltzailea, passwordHash, passwordSalt eta eposta propietateak pasatuz Langilea klase berri bat sortzerakoan zehazten duen metodoa 
     * 
     * @param value
     *     allowed object is
     *     {@link int, String, String, String, string, byte[], byte[], String }
     */
    public Langilea(Short iDlangilea, String izena, String abizena, String erabiltzailea, byte[] passwordHash, byte[] passwordSalt, String eposta) {
        this.iDlangilea = iDlangilea;
        this.izena = izena;
        this.abizena = abizena;
        this.erabiltzailea = erabiltzailea;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.eposta = eposta;
    }

    /**
     * iDlangilea propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link int }
     */
    public Short getIDlangilea() {
        return iDlangilea;
    }

    /**
     * iDlangilea propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     */
    public void setIDlangilea(Short iDlangilea) {
        this.iDlangilea = iDlangilea;
    }

    /**
     * izena propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getIzena() {
        return izena;
    }

    /**
     * izena propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * abizena propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getAbizena() {
        return abizena;
    }

    /**
     * abizena propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    /**
     * erabiltzailea propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getErabiltzailea() {
        return erabiltzailea;
    }

    /**
     * erabiltzailea propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    /**
     * passwordHash propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link byte[] }
     */
    public byte[] getPasswordHash() {
        return passwordHash;
    }

    /**
     * passwordHash propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link byte[] }
     */
    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * passwordSalt propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link byte[] }
     */
    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * passwordsalt propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link byte[] }
     */
    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    /**
     * eposta propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getEposta() {
        return eposta;
    }

    /**
     * eposta propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    /**
     * telefonoa propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getTelefonoa() {
        return telefonoa;
    }

    /**
     * telefonoa propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setTelefonoa(String telefonoa) {
        this.telefonoa = telefonoa;
    }

    /**
     * aktibo propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     */
    public Boolean getAktibo() {
        return aktibo;
    }

    /**
     * aktibo propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     */
    public void setAktibo(Boolean aktibo) {
        this.aktibo = aktibo;
    }

    /**
     * aktiboaCollection lortzen du
     * 
     * @return
     *     possible object is
     *     {@link Collection<Aktiboa> }
     *     
     */
    @XmlTransient
    public Collection<Aktiboa> getAktiboaCollection() {
        return aktiboaCollection;
    }

    /**
     * aktiboaCollection balioa definitzen du
     * 
     * @param value
     *     allowed object is
     *     {@link Collection<Aktiboa> }
     *     
     */
    public void setAktiboaCollection(Collection<Aktiboa> aktiboaCollection) {
        this.aktiboaCollection = aktiboaCollection;
    }

    /**
     * LangileMota klaseko iDmota propietatea bueltatzen duen metodoa
     * 
     * @return
     *     possible object is
     *     {@link LangileMota }
     */
    public LangileMota getIDmota() {
        return iDmota;
    }

    /**
     * iDmota propietatea zehazten duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link LangileMota }
     */
    public void setIDmota(LangileMota iDmota) {
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
        hash += (iDlangilea != null ? iDlangilea.hashCode() : 0);
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
        if (!(object instanceof Langilea)) {
            return false;
        }
        Langilea other = (Langilea) object;
        if ((this.iDlangilea == null && other.iDlangilea != null) || (this.iDlangilea != null && !this.iDlangilea.equals(other.iDlangilea))) {
            return false;
        }
        return true;
    }

    /**
     * iDlangilea string baten bueltatzen duen metodoa
     *  @return
     *     possible object is
     *     {@link String }
     */
    @Override
    public String toString() {
        return "database.utils.Langilea[ iDlangilea=" + iDlangilea + " ]";
    }
    
}
