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
public class Erabiltzaileak {

	protected List<Erabiltzaileak.Erabiltzailea> erabiltzailea;


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
		 * Obtiene el valor de la propiedad abizena.
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
		 * Define el valor de la propiedad abizena.
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
		 * Obtiene el valor de la propiedad eposta.
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
		 * Define el valor de la propiedad eposta.
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
		 * Obtiene el valor de la propiedad erabiltzailea.
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
		 * Define el valor de la propiedad erabiltzailea.
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
		 * Obtiene el valor de la propiedad iDerabiltzailea.
		 * 
		 */
		public byte getIDerabiltzailea() {
			return iDerabiltzailea;
		}

		/**
		 * Define el valor de la propiedad iDerabiltzailea.
		 * 
		 */
		public void setIDerabiltzailea(byte value) {
			this.iDerabiltzailea = value;
		}

		/**
		 * Obtiene el valor de la propiedad izena.
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
		 * Define el valor de la propiedad izena.
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
		 * Obtiene el valor de la propiedad passwordHash.
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
		 * Define el valor de la propiedad passwordHash.
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
		 * Obtiene el valor de la propiedad passwordSalt.
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
		 * Define el valor de la propiedad passwordSalt.
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
		 * Obtiene el valor de la propiedad telefonoa.
		 * 
		 */
		public int getTelefonoa() {
			return telefonoa;
		}

		/**
		 * Define el valor de la propiedad telefonoa.
		 * 
		 */
		public void setTelefonoa(int value) {
			this.telefonoa = value;
		}

	}

}