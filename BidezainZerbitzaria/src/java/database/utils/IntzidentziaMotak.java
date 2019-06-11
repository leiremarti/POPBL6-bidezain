/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author user
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "intzidentzia_mota"
})
@XmlRootElement(name = "intzidentziaMotas")
public class IntzidentziaMotak implements Serializable {
   protected List<IntzidentziaMota> intzidentziaMota;

    
    public List<IntzidentziaMota> getIntzidentziaMota() {
        if (intzidentziaMota == null) {
            intzidentziaMota = new ArrayList<IntzidentziaMota>();
        }
        return this.intzidentziaMota;
    }
    
     public void setIntzidentziaMota(List<IntzidentziaMota> e) {
        this.intzidentziaMota = e;
    }

    @Override
    public String toString() {
        
        String resp = "[";
        IntzidentziaMota azkena = intzidentziaMota.get(intzidentziaMota.size()-1);
        for(IntzidentziaMota e : intzidentziaMota){
            resp = resp.concat(e.toString());
            if(e != azkena)resp = resp.concat(",");
        }
        resp = resp.concat("]");
        return resp; //To change body of generated methods, choose Tools | Templates.
    }

    
}
