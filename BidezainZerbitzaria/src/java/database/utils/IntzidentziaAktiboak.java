/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "intzidentziaAktiboa"
})
@XmlRootElement(name = "intzidentziaAktiboak")
public class IntzidentziaAktiboak {

    protected List<IntzidentziaAktiboa> intzidentziaAktiboa;

    
    public List<IntzidentziaAktiboa> getIntzidentziaAktiboa() {
        if (intzidentziaAktiboa == null) {
            intzidentziaAktiboa = new ArrayList<IntzidentziaAktiboa>();
        }
        return this.intzidentziaAktiboa;
    }
    
     public void setIntzidentziaAktiboa(List<IntzidentziaAktiboa> i) {
        this.intzidentziaAktiboa = i;
    }

    @Override
    public String toString() {
        
        String resp = null;
        if(intzidentziaAktiboa!=null && intzidentziaAktiboa.size()>0){
            resp = "[";
            IntzidentziaAktiboa azkena = intzidentziaAktiboa.get(intzidentziaAktiboa.size()-1);
            for(IntzidentziaAktiboa i : intzidentziaAktiboa){
                resp = resp.concat(i.toString());
                if(i != azkena)resp = resp.concat(",");
            }
            resp = resp.concat("]");
        }
        
        return resp; //To change body of generated methods, choose Tools | Templates.
    }

     
}
