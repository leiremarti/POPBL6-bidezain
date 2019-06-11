/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzailea;
import database.utils.Erabiltzaileak;
import database.utils.Langilea;
import database.utils.Langileak;
import encrypt.Encrypter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 * REST Web Service Login erabiltzen diren zerbitzuak
 *
 * @author user
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;
    private static final String encrypterKey = "mysecretencrypter";

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Json String bat bueltatzen du
     * @return
     *     possible object is
     *     {@link String }
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * Erabiltzailea logeatzeko erabiltzen den zerbitzua
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
    public String loginErabiltzailea(String data) throws JAXBException, JSONException {
        boolean loginOK = false;
        
        JSONObject myjson = new JSONObject(data); 
        
        Encrypter en = new Encrypter(encrypterKey);                
        
        String username = en.decrypt(myjson.getString("erabiltzailea"));
        String password = en.decrypt(myjson.getString("password"));
        
        System.out.println("********USERNAME: "+username+"***********PASSWORD: "+password);
       
        if(username!=null && password!=null){
             String s = "";
            try {

                URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.erabiltzailea");
                URLConnection con = u.openConnection();
                Reader reader = new InputStreamReader(con.getInputStream());
                while (true) {
                    int ch = reader.read();
                    if (ch==-1) {
                        break;
                    }
                    s = s + (char)ch;
                }

                Erabiltzaileak erabiltzaileak = null;
                StringReader string = new StringReader(s);
                JAXBContext jc = JAXBContext.newInstance(Erabiltzaileak.class);

                Unmarshaller ju = jc.createUnmarshaller();
                erabiltzaileak = (Erabiltzaileak) ju.unmarshal(string);

                List<Erabiltzailea> erabiltzaile_list = erabiltzaileak.getErabiltzailea();

                for(Erabiltzailea e : erabiltzaile_list){
                    if(username!=null && password!=null && e.getAktibo() && e.getErabiltzailea().equals(username) && e.getPasswordHash().equals(BCrypt.hashpw(password, e.getPasswordSalt().toString()))){
                        loginOK = true;
                        System.out.println("OK!");
                        break;
                    }
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
            
        }
       
        return en.encrypt(String.valueOf(loginOK));//String.valueOf(loginOK);//
    }
    
    /**
     * Langilea logeatzeko erabiltzen den zerbitzua
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
    @Produces(MediaType.TEXT_PLAIN)
    public String loginLangilea(String data) throws JAXBException, JSONException {
        boolean loginOK = false;
        
        JSONObject myjson = new JSONObject(data);          
        
        Encrypter en = new Encrypter(encrypterKey);                
        
        String username = en.decrypt(myjson.getString("erabiltzailea"));
        String password = en.decrypt(myjson.getString("password"));
        System.out.println("********USERNAME: "+username+"***********PASSWORD: "+password);
       
        if(username!=null && password!=null){
            String s = "";
            try {
            
            URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.langilea");
            URLConnection con = u.openConnection();
	    Reader reader = new InputStreamReader(con.getInputStream());
	    while (true) {
	        int ch = reader.read();
	        if (ch==-1) {
	            break;
	        }
	        s = s + (char)ch;
	    } 
            
            Langileak langileak = null;
            StringReader string = new StringReader(s);
            JAXBContext jc = JAXBContext.newInstance(Langileak.class);

            Unmarshaller ju = jc.createUnmarshaller();
            langileak = (Langileak) ju.unmarshal(string);
            
            List<Langilea> langile_list = langileak.getLangilea();
            
            for(Langilea l : langile_list){
                if(username!=null && password!=null && l.getAktibo() && l.getErabiltzailea().equals(username) && l.getPasswordHash().equals(BCrypt.hashpw(password, l.getPasswordSalt().toString()))){
                    loginOK = true;
                    System.out.println("OK!");
                    break;
                }
            }
            
	} catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}  
        }
        
        return en.encrypt(String.valueOf(loginOK));
    }
    
}


