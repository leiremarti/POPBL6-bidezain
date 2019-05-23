/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.services;

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

    /**
     * Retrieves representation of an instance of database.services.RegisterResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @POST
    @Path("register")
    @Produces(MediaType.TEXT_PLAIN)
    public String register(String erabiltzaileaJson) {
        boolean register = false;
        
        System.out.println("********Data: "+erabiltzaileaJson+"*********** ");
        
        String s = "";
        /*try {
            
            URL u = new URL("http://"+SERVER_DIR+SERVICE_PATH+"/login/login");
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencode");
			String params = username+"_"+password;
			con.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			
			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
			dos.writeBytes(params);
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
			System.out.println(loginResponse.toString());
            
	} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	} catch (IOException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	}
        */
        return String.valueOf(true);
    }

    /**
     * PUT method for updating or creating an instance of RegisterResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
