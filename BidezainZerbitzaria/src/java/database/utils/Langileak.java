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
    "langilea"
})
@XmlRootElement(name = "langileas")
public class Langileak {

    protected List<Langilea> langilea;

    
    public List<Langilea> getLangilea() {
        if (langilea == null) {
            langilea = new ArrayList<Langilea>();
        }
        return this.langilea;
    }
    
     public void setLangilea(List<Langilea> e) {
        this.langilea = e;
    }

    @Override
    public String toString() {
        
        String resp = "[";
        Langilea azkena = langilea.get(langilea.size()-1);
        for(Langilea e : langilea){
            resp = resp.concat(e.toString());
            if(e != azkena)resp = resp.concat(",");
        }
        resp = resp.concat("]");
        return resp; //To change body of generated methods, choose Tools | Templates.
    }

     
}
