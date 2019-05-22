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
@Table(name = "aurreikuspenak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aurreikuspenak.findAll", query = "SELECT a FROM Aurreikuspenak a"),
    @NamedQuery(name = "Aurreikuspenak.findByIDaurreikuspena", query = "SELECT a FROM Aurreikuspenak a WHERE a.iDaurreikuspena = :iDaurreikuspena"),
    @NamedQuery(name = "Aurreikuspenak.findByAzalera", query = "SELECT a FROM Aurreikuspenak a WHERE a.azalera = :azalera"),
    @NamedQuery(name = "Aurreikuspenak.findByProbabilitatea", query = "SELECT a FROM Aurreikuspenak a WHERE a.probabilitatea = :probabilitatea")})
public class Aurreikuspenak implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_aurreikuspena")
    private Short iDaurreikuspena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "azalera")
    private String azalera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "probabilitatea")
    private float probabilitatea;

    public Aurreikuspenak() {
    }

    public Aurreikuspenak(Short iDaurreikuspena) {
        this.iDaurreikuspena = iDaurreikuspena;
    }

    public Aurreikuspenak(Short iDaurreikuspena, String azalera, float probabilitatea) {
        this.iDaurreikuspena = iDaurreikuspena;
        this.azalera = azalera;
        this.probabilitatea = probabilitatea;
    }

    public Short getIDaurreikuspena() {
        return iDaurreikuspena;
    }

    public void setIDaurreikuspena(Short iDaurreikuspena) {
        this.iDaurreikuspena = iDaurreikuspena;
    }

    public String getAzalera() {
        return azalera;
    }

    public void setAzalera(String azalera) {
        this.azalera = azalera;
    }

    public float getProbabilitatea() {
        return probabilitatea;
    }

    public void setProbabilitatea(float probabilitatea) {
        this.probabilitatea = probabilitatea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDaurreikuspena != null ? iDaurreikuspena.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aurreikuspenak)) {
            return false;
        }
        Aurreikuspenak other = (Aurreikuspenak) object;
        if ((this.iDaurreikuspena == null && other.iDaurreikuspena != null) || (this.iDaurreikuspena != null && !this.iDaurreikuspena.equals(other.iDaurreikuspena))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.Aurreikuspenak[ iDaurreikuspena=" + iDaurreikuspena + " ]";
    }
    
}
