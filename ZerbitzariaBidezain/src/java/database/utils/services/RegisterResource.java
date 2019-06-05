/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzailea;
import database.utils.Erabiltzaileak;
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
    /*
    @GET
    @Path("getKey")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject registerGetKey() throws JAXBException, JSONException, Exception {          
        System.out.println("++++++++++++++++++++++++++");         
        PublicKey publicKey = RSAGenerator.getPublic("D:\\Users\\user\\Documents\\MONDRA\\3.MAILA\\POPBL6\\POPBL6-bidezain\\ZerbitzariaBidezain\\KeyPair\\publicKey");   
        
        JSONObject jobject = new JSONObject();
        jobject.put("serverPuiblicKey", publicKey);
        System.out.println("++++++++++++++++++++++++++"+jobject);
        return jobject;
    }
*/
    @POST
    @Path("erabiltzailea")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registerErabiltzailea(String data) throws JAXBException, JSONException, MalformedURLException, IOException {
        boolean registerOK = false;
        
        JSONObject data_json = new JSONObject(data);
        System.out.println(data_json.toString());
        
        String s="";
        URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.erabiltzailea/exists");
	HttpURLConnection con = (HttpURLConnection) u.openConnection();
	con.setRequestMethod("POST");
	con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("Content-Length", Integer.toString(data_json.toString().getBytes().length));
	con.setUseCaches(false);
	con.setDoInput(true);
	con.setDoOutput(true);

	DataOutputStream dos = new DataOutputStream(con.getOutputStream());
	dos.writeBytes(data_json.toString());
	dos.flush();
	dos.close();

	InputStream is = con.getInputStream();
	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	String line;
	StringBuffer loginResponse = new StringBuffer();
	while((line = br.readLine())!=null) {
            loginResponse.append(line);
            loginResponse.append('\r');
	}

	br.close();
        s = loginResponse.toString();
        JSONObject obj = new JSONObject(s);
        
        boolean erabiltzaileaExists = obj.getBoolean("erabiltzailea");
        boolean epostaExists = obj.getBoolean("eposta");
        
        if(!erabiltzaileaExists && !epostaExists){
            
            Erabiltzailea e = new Erabiltzailea();
            e.setIzena((String)data_json.get("izena"));
            e.setAbizena((String)data_json.get("abizena"));
            e.setErabiltzailea((String)data_json.get("erabiltzailea"));
            e.setEposta((String)data_json.get("eposta"));
            e.setTelefonoa((String)data_json.get("telefonoa"));
            String ph = (String)data_json.get("passwordHash");
            e.setPasswordHash(ph.getBytes());
            String ps = (String)data_json.get("passwordSalt");
            e.setPasswordSalt(ps.getBytes());
            JSONObject object = new JSONObject(e);
            System.out.print(object.toString());
            
            u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.erabiltzailea");
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);

            dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(object.toString());
            dos.flush();
            dos.close();

            br.close();
            registerOK = true;
        }else{
            JSONObject o = new JSONObject();
            o.put("ok", registerOK);
            String message = "";
            if(epostaExists){
                message = "E-posta hori existitzen da!";
            }else if(erabiltzaileaExists){
                message = "Erabiltzaile hori existitzen da!";
            }else if(erabiltzaileaExists && epostaExists){
                message = "E-posta eta erabiltzaile hori existitzen dira!";
            }
            o.put("msg", message);
            System.out.print("USER EXISTS");
        }
        return String.valueOf(registerOK);
    }
}
