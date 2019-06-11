/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author user
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "aurreikuspena"
})
@XmlRootElement(name = "aurreikuspenak")
public class Aurreikuspenak implements Serializable {
    
    protected List<Aurreikuspena> aurreikuspena;

    
    public List<Aurreikuspena> getAurreikuspena() {
        if (aurreikuspena == null) {
            aurreikuspena = new ArrayList<Aurreikuspena>();
        }
        return this.aurreikuspena;
    }
    
     public void setAurreikuspena(List<Aurreikuspena> i) {
        this.aurreikuspena = i;
    }

    @Override
    public String toString() {
        
        String resp = null;
        if(aurreikuspena!=null && aurreikuspena.size()>0){
            resp = "[";
            Aurreikuspena azkena = aurreikuspena.get(aurreikuspena.size()-1);
            for(Aurreikuspena i : aurreikuspena){
                resp = resp.concat(i.toString());
                if(i != azkena)resp = resp.concat(",");
            }
            resp = resp.concat("]");
        }
        
        return resp; 
    }
    
}
