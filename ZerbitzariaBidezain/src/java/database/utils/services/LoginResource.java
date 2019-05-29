/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzailea;
import database.utils.Erabiltzaileak;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
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

/**
 * REST Web Service
 *
 * @author user
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of database.utils.services.LoginResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({"application/x-www-form-urlencode"})
    public String login(String data) throws JAXBException {
        boolean loginOK = false;
        
        String username = null;
        String password = null;
        try{
            String[] datasplit = data.split("_");
            username = datasplit[0];
            password = datasplit[1];
            
            System.out.println("********USERNAME: "+username+"***********PASSWORD: "+password);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        
        String s = "";
        try {
            
            URL u = new URL("http://localhost:8080/ZerbitzariaBidezain2/webresources/database.utils.erabiltzailea");
            URLConnection con = u.openConnection();
	    Reader reader = new InputStreamReader(con.getInputStream());
	    while (true) {
	        int ch = reader.read();
	        if (ch==-1) {
	            break;
	        }
	        s = s + (char)ch;
	    }
            System.out.println(s);      
            
            Erabiltzaileak erabiltzaileak = null;
            StringReader string = new StringReader(s);
            JAXBContext jc = JAXBContext.newInstance(Erabiltzaileak.class);

            Unmarshaller ju = jc.createUnmarshaller();
            erabiltzaileak = (Erabiltzaileak) ju.unmarshal(string);
            
            List<Erabiltzailea> erabiltzaile_list = erabiltzaileak.getErabiltzailea();
            
            for(Erabiltzailea e : erabiltzaile_list){
                if(username!=null && password!=null && e.getErabiltzailea().equals(username)/* && e.getPasswordHash().equals(password)*/){
                    loginOK = true;
                }
                System.out.print(e.getAbizena());
            }
            
	} catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}  
        return String.valueOf(loginOK);
    }
    
    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
