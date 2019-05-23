/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.services;

import generated.Erabiltzaileas;
import generated.Erabiltzaileas.Erabiltzailea;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;
    
    private final String SERVER_DIR = "localhost:8080";
    private final String SERVICE_PATH = "/ZerbitzariaBidezain/webresources";

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of database.services.LoginResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
       // throw new UnsupportedOperationException();
        return "APA";
    }

    @POST
    @Path("login")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(String message) {
        boolean login = false;
        String[] data = message.split("_");
        String username = data[0];
        String password = data[1];
        System.out.println("********Data: "+data[0]+", "+data[1]+"*********** ");
        
        String s = "";
        try {
            URL u = new URL("http://"+SERVER_DIR+SERVICE_PATH+"/database.utils.erabiltzailea");
             URLConnection con = u.openConnection();
             Reader reader = new InputStreamReader(con.getInputStream());
             while (true) {
                 int ch = reader.read();
                 if (ch==-1) {
                     break;
                 }
                 s = s + (char)ch;
             }
	} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	} catch (IOException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	}
        
        List<Erabiltzailea> erabiltzaileak = null;
		
        try {
			
            StringReader string = new StringReader(s);
            JAXBContext jc = JAXBContext.newInstance(Erabiltzaileas.class);
			
            Unmarshaller ju = jc.createUnmarshaller();
            Erabiltzaileas result = (Erabiltzaileas) ju.unmarshal(string);
            erabiltzaileak  = result.getErabiltzailea();
							
        } catch (JAXBException e) {
            e.printStackTrace();
        }
            
        for(Erabiltzailea e : erabiltzaileak){
            if(e.getErabiltzailea().equals(username)){
                login = true;
                System.out.println("--------->"+username+"="+e.getErabiltzailea());
                break;
            }
        }
        
        return String.valueOf(login);
    }
    
        
    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
