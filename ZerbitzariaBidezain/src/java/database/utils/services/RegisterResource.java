/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzailea;
import database.utils.Erabiltzaileak;
import database.utils.IntzidentziaAktiboa;
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
import main.RSAGenerator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("register")
public class RegisterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
    }
    
    @POST
    @Path("erabiltzailea")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registerErabiltzailea(String data) throws JAXBException, JSONException, MalformedURLException, IOException {
        boolean registerOK = false;
            System.out.println("**********************");
        
        JSONObject data_json = new JSONObject(data);
        create(data_json, "erabiltzailea");
        registerOK = true;
         
        return String.valueOf(registerOK);
    }
    
    @POST
    @Path("langilea")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registerLangilea(String data) throws JAXBException, JSONException, MalformedURLException, IOException {
              
        JSONObject data_json = new JSONObject(data);
        
        JSONObject erantzuna = create(data_json, "langilea");
                
        return erantzuna.toString();
    }
    
    private JSONObject create(JSONObject object, String type) throws ProtocolException, IOException, JSONException{
        
       URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils."+type+"/create");

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
