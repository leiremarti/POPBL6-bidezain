/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Embeddable
public class LangileakAktiboaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_aktiboa")
    private short iDaktiboa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_langilea")
    private short iDlangilea;

    public LangileakAktiboaPK() {
    }

    public LangileakAktiboaPK(short iDaktiboa, short iDlangilea) {
        this.iDaktiboa = iDaktiboa;
        this.iDlangilea = iDlangilea;
    }

    public short getIDaktiboa() {
        return iDaktiboa;
    }

    public void setIDaktiboa(short iDaktiboa) {
        this.iDaktiboa = iDaktiboa;
    }

    public short getIDlangilea() {
        return iDlangilea;
    }

    public void setIDlangilea(short iDlangilea) {
        this.iDlangilea = iDlangilea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDaktiboa;
        hash += (int) iDlangilea;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangileakAktiboaPK)) {
            return false;
        }
        LangileakAktiboaPK other = (LangileakAktiboaPK) object;
        if (this.iDaktiboa != other.iDaktiboa) {
            return false;
        }
        if (this.iDlangilea != other.iDlangilea) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.utils.LangileakAktiboaPK[ iDaktiboa=" + iDaktiboa + ", iDlangilea=" + iDlangilea + " ]";
    }
    
}
