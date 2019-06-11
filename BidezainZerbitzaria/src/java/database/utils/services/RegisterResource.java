/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzailea;
import database.utils.Erabiltzaileak;
import database.utils.IntzidentziaAktiboa;
import database.utils.Langilea;
import encrypt.Encrypter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service Registratzeko erabiltzen diren zerbitzuak
 *
 * @author user
 */
@Path("register")
public class RegisterResource {

    @Context
    private UriInfo context;
    private static final String encrypterKey = "mysecretencrypter";

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
    }
    
    
    /**
     * Erabiltzailea erregistratzerako erabiltzen den zerbitzua
     * 
     * @param value
     *     allowed object is
     *     {@link String}
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    @POST
    @Path("erabiltzailea")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registerErabiltzailea(String data) throws JAXBException, JSONException, MalformedURLException, IOException {
        JSONObject data_json = new JSONObject(data);         
        Encrypter e = new Encrypter(encrypterKey);
        
        data_json.put("erabiltzailea", e.decrypt(data_json.getString("erabiltzailea")));
        data_json.put("izena", e.decrypt(data_json.getString("izena")));
        data_json.put("abizena", e.decrypt(data_json.getString("abizena")));
        data_json.put("passwordHash", e.decrypt(data_json.getString("passwordHash")));
        data_json.put("eposta", e.decrypt(data_json.getString("eposta")));
        data_json.put("telefonoa", e.decrypt(data_json.getString("telefonoa")));
        
        JSONObject erantzuna = create(data_json, "erabiltzailea");
        String value = String.valueOf((boolean)erantzuna.get("regOK"));                        
        erantzuna.put("regOK", String.valueOf(e.encrypt(value)));
        erantzuna.put("message", e.encrypt((String)erantzuna.get("message")));
				System.out.println(erantzuna.toString());
         
        return String.valueOf(String.valueOf(e.encrypt(value)));
    }
    
     /**
     * Langilea erregistratzerako erabiltzen den zerbitzua
     * 
     * @param value
     *     allowed object is
     *     {@link String}
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    @POST
    @Path("langilea")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registerLangilea(/*@Valid Langilea*/String data) throws JAXBException, JSONException, MalformedURLException, IOException {
        
        JSONObject data_json = new JSONObject(data);
        Encrypter e = new Encrypter(encrypterKey);
        
        data_json.put("erabiltzailea", e.decrypt(data_json.getString("erabiltzailea")));
        data_json.put("izena", e.decrypt(data_json.getString("izena")));
        data_json.put("abizena", e.decrypt(data_json.getString("abizena")));
        data_json.put("passwordHash", e.decrypt(data_json.getString("passwordHash")));
        data_json.put("eposta", e.decrypt(data_json.getString("eposta")));
        data_json.put("telefonoa", e.decrypt(data_json.getString("telefonoa")));
        JSONObject erantzuna = create(data_json, "langilea");
        String value = String.valueOf((boolean)erantzuna.get("regOK"));                        
        erantzuna.put("regOK", String.valueOf(e.encrypt(value)));
        erantzuna.put("message", e.encrypt((String)erantzuna.get("message")));
				System.out.println(erantzuna.toString());
                
        return String.valueOf(erantzuna.toString());
    }
    
     /**
     * Langilea edo erabiltzailea bere REST zerbitzura zuzenduz erregistroa sortzen duen metodoa
     * 
     * @param value
     *     allowed object is
     *     {@link String}
     * 
     * @return
     *     possible object is
     *     {@link JSONObject }
     */
    private JSONObject create(JSONObject object, String type) throws ProtocolException, IOException, JSONException{
        
       URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils."+type+"/create");

	HttpURLConnection con = (HttpURLConnection) u.openConnection();
	con.setRequestMethod("POST");
	con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("Content-Length", Integer.toString(object.toString().getBytes().length));
	con.setUseCaches(false);
	con.setDoInput(true);
	con.setDoOutput(true);

	DataOutputStream dos = new DataOutputStream(con.getOutputStream());
	dos.writeBytes(object.toString());
	dos.flush();
	dos.close();
        
        String s="";
	InputStream is = con.getInputStream();
	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	String line;
	StringBuffer loginResponse = new StringBuffer();
	while((line = br.readLine())!=null) {
            loginResponse.append(line);
            loginResponse.append('\r');
	}

	br.close();
        s = loginResponse.toString().replaceAll("\\r|\\n", "");
        JSONObject obj = new JSONObject(s);
        return obj;
    }
       
}
