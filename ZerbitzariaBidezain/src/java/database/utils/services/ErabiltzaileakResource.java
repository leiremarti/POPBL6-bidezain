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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("erabiltzaileak")
public class ErabiltzaileakResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ErabiltzaileakResource
     */
    public ErabiltzaileakResource() {
    }

    @GET
    @Path("safe")
  //  @Produces({"application/json"})
    public Response safeFindAll() {
        List<Erabiltzailea> fresh = new ArrayList<>();
        
        String s = "";
        try {
            
            URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.erabiltzailea");
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
            byte[] data = new byte[0];
            e.setPasswordHash(data);
            e.setPasswordSalt(data);
            fresh.add(e);            
        }
            
	} catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	} catch (JAXBException ex) {  
            Logger.getLogger(ErabiltzaileakResource.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        Erabiltzaileak erabiltzaileak = new Erabiltzaileak();
        erabiltzaileak.setErabiltzailea(fresh);
        
        Response.ResponseBuilder rb = Response.ok(erabiltzaileak.toString());
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
        
        //return fresh;
    }
    
    /**
     * PUT method for updating or creating an instance of ErabiltzaileakResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
