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
    "erabiltzailea"
})
@XmlRootElement(name = "erabiltzaileas")
public class Erabiltzaileak {

    protected List<Erabiltzailea> erabiltzailea;

    
    public List<Erabiltzailea> getErabiltzailea() {
        if (erabiltzailea == null) {
            erabiltzailea = new ArrayList<Erabiltzailea>();
        }
        return this.erabiltzailea;
    }
    
     public void setErabiltzailea(List<Erabiltzailea> e) {
        this.erabiltzailea = e;
    }

    @Override
    public String toString() {
        
        String resp = "[";
        Erabiltzailea azkena = erabiltzailea.get(erabiltzailea.size()-1);
        for(Erabiltzailea e : erabiltzailea){
            resp = resp.concat(e.toString());
            if(e != azkena)resp = resp.concat(",");
        }
        resp = resp.concat("]");
        return resp; //To change body of generated methods, choose Tools | Templates.
    }

     
}
