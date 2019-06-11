package binding;

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
/**
 * Klase honetan Erabiltzaileak.Erabiltzailea lista bat sortzeaz arduratzen da
 * @author user 
 */
public class Erabiltzaileak {

    protected List<Erabiltzaileak.Erabiltzailea> erabiltzailea;

    /**
     * Erabiltzaileak.Erabiltzailea lista bat bueltatzen duen metodoa. Sortuta ez badago, lista berria sortuko du.
     * 
     * @return
     *     possible object is
     *     {@link List<Erabiltzaileak.Erabiltzailea> }
     */
    public List<Erabiltzaileak.Erabiltzailea> getErabiltzailea() {
        if (erabiltzailea == null) {
            erabiltzailea = new ArrayList<Erabiltzaileak.Erabiltzailea>();
        }
        return this.erabiltzailea;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "abizena",
        "eposta",
        "erabiltzailea",
        "iDerabiltzailea",
        "izena",
        "passwordHash",
        "passwordSalt",
        "telefonoa"
    })
    
    /**
     * Klase honetan datu baseko Erabiltzailea taularekin egindako binding-aren emaitzak dira, Erabiltzailea taulak
     * dituen atributoak definitzen dira.
     * @author user 
     */
    public static class Erabiltzailea {

        @XmlElement(required = true)
        protected String abizena;
        @XmlElement(required = true)
        protected String eposta;
        @XmlElement(required = true)
        protected String erabiltzailea;
        @XmlElement(name = "IDerabiltzailea")
        protected byte iDerabiltzailea;
        @XmlElement(required = true)
        protected String izena;
        @XmlElement(required = true)
        protected String passwordHash;
        @XmlElement(required = true)
        protected String passwordSalt;
        protected int telefonoa;

        /**
         *  abizena propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAbizena() {
            return abizena;
        }

        /**
         * abizena propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAbizena(String value) {
            this.abizena = value;
        }

        /**
         * eposta propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEposta() {
            return eposta;
        }

        /**
         * eposta propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEposta(String value) {
            this.eposta = value;
        }

        /**
         * erabiltzailea propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErabiltzailea() {
            return erabiltzailea;
        }

        /**
         * erabiltzailea propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErabiltzailea(String value) {
            this.erabiltzailea = value;
        }

        /**
         * iDerabiltzailea propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link byte }
         *  
         */
        public byte getIDerabiltzailea() {
            return iDerabiltzailea;
        }

        /**
         * iDerabiltzailea propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link byte }
         */
        public void setIDerabiltzailea(byte value) {
            this.iDerabiltzailea = value;
        }

        /**
         * izena propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIzena() {
            return izena;
        }

        /**
         * izena propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIzena(String value) {
            this.izena = value;
        }

        /**
         * passwordHash propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPasswordHash() {
            return passwordHash;
        }

        /**
         * passwordHash propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPasswordHash(String value) {
            this.passwordHash = value;
        }

        /**
         * passwordSalt propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPasswordSalt() {
            return passwordSalt;
        }

        /**
         * passwordSalt propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPasswordSalt(String value) {
            this.passwordSalt = value;
        }

        /**
         * passwordHash propietatea bueltatzen duen metodoa.
         * 
         * @return
         *     possible object is
         *     {@link int }
         *     
         */
        public int getTelefonoa() {
            return telefonoa;
        }

        /**
         * telefonoa propietatea zehazten duen metodoa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTelefonoa(int value) {
            this.telefonoa = value;
        }

    }

}