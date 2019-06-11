/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzaileak;
import database.utils.IntzidentziaAktiboa;
import database.utils.IntzidentziaAktiboak;
import database.utils.IntzidentziaMota;
import database.utils.IntzidentziaMotak;
import database.utils.Langilea;
import database.utils.Langileak;
import encrypt.Encrypter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
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
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import utils.Raiz;
import utils.Raiz.IncidenciaGeolocalizada;

/**
 * REST Web Service Intzidentziak kargatzeko erabiltzen diren zerbitzuak
 *
 * @author user
 */
@Path("inzidentziak")
public class InzidentziakResource {

    @Context
    private UriInfo context;
    private Object JDBCTutorialUtilities;
    private static final String encrypterKey = "mysecretencrypter";

    /**
     * Creates a new instance of InzidentziakResource
     */
    public InzidentziakResource() {
    }

   /**
     * Json String bat bueltatzen du
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    @GET
    @Produces("application/json")
    public String getXml() throws JAXBException, IOException, MalformedURLException, KeyManagementException, NoSuchAlgorithmException, JSONException {
        
        String xmlString = getXMLFromOutside();
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Raiz.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader sreader = new StringReader(xmlString);
        Raiz person = (Raiz) unmarshaller.unmarshal(sreader);
        
        JSONObject inzidentziak_json = getInzidentziakOK(person.getIncidenciaGeolocalizada());
        Encrypter en = new Encrypter(encrypterKey);
        return inzidentziak_json.toString();//en.encrypt(inzidentziak_json.toString());
    }
    
    /**
     * Eusko Jaurlaritzaren web orritik XML bat irakurri eta datubasean ordetzen du.
     * @return an instance of java.lang.String
     */
    @POST
    @Path("todatabase")
    @Consumes("text/plain")
    @Produces("application/json")
    public String toDatabase(String s) throws JAXBException, IOException, MalformedURLException, KeyManagementException, NoSuchAlgorithmException, JSONException {
        
        String xmlString = getXml();
        
        JSONObject object = new JSONObject(xmlString);
        JSONArray array_inzidentziak = object.getJSONArray("inzidentziaAktiboak");
        JSONArray array_send = new JSONArray();
        int length = array_inzidentziak.length();
        for(int i=0; i<(length-1); i++){
            
            JSONObject o = array_inzidentziak.getJSONObject(i);            
            IntzidentziaAktiboa ia = new IntzidentziaAktiboa();
            ia.setErrepidea((String)o.get("errepidea"));
            ia.setZentzua((String)o.get("zentzua"));
            ia.setIntzidentziaData((String)o.get("intzidentziaData"));
            ia.setKausa((String)o.get("kausa"));
            ia.setMaila(maila((String)o.get("maila")));
            ia.setProbintzia((String)o.get("probintzia"));
            ia.setHerria((String)o.get("herria"));
            ia.setLatitudea(new BigDecimal((String)o.get("latitudea")));
            ia.setLongitudea(new BigDecimal((String)o.get("longitudea")));
            String mota = (String)o.get("tipo");
            if(mota.contains(" ")){
                mota = mota.replace(" ", "_");
            }
            ia.setIDmota(getIntzidentziaMota(mota));            
            o = new JSONObject(ia);
            array_send.put(o);
        }
        
        JSONObject send = new JSONObject();
        send.put("intzidentziak", array_send);
        
        System.out.print("->:"+send.toString());
        URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.intzidentziaaktiboa/createall");

        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Content-Length", Integer.toString(send.toString().getBytes().length));
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setDoOutput(true);
        
        DataOutputStream dos = new DataOutputStream(con.getOutputStream());
        dos.writeBytes(send.toString());
        dos.flush();
        dos.close();
       
        
        return xmlString;
    }
    
     /**
     * Datu basean valido dauden intzidentziak JSONObject bezala batean bueltatzen ditu
     * @param value
     *     allowed object is
     *     {@link List<IncidenciaGeolocalizada>}
     * 
     * @return
     *     possible object is
     *     {@link JSONObject }
     */
    private JSONObject getInzidentziakOK(List<IncidenciaGeolocalizada> list) throws JSONException{
        boolean ok = true;
        List<IntzidentziaAktiboa> new_list = new ArrayList<>();
        JSONArray array = new JSONArray();
        for(IncidenciaGeolocalizada ig : list){
            
            if(ig.getTipo().equals("Vialidad invernal tramos")) ok = false;
            if(ig.getTipo().equals("Puertos de montaÃ±a") && ig.getNivel().contains("Abierto")){
                ok = false;
            }
            
            if(ok){
                 IntzidentziaAktiboa i = new IntzidentziaAktiboa();
                        i.setErrepidea(ig.getCarretera());
                        i.setHerria(ig.getPoblacion());
                        i.setIDmota(null);
                        i.setIntzidentziaData(ig.getFechahoraIni());
                        i.setKausa(ig.getCausa());
                        i.setLatitudea(new BigDecimal(Float.toString(ig.getLatitud())));
                        i.setLongitudea(new BigDecimal(Float.toString(ig.getLongitud())));
                        i.setMaila(ig.getNivel());
                        i.setProbintzia(ig.getProvincia());
                        i.setZentzua(ig.getSentido());
                        new_list.add(i);
                        JSONObject o = new JSONObject(i);
                        o.put("tipo", ig.getTipo());
                        array.put(o);
            }
        }
        
         JSONObject inzidentziak_json = new JSONObject();
         inzidentziak_json.put("inzidentziaAktiboak", array);
         return inzidentziak_json;
        
    }

    /**
     * Trafikoko web orrialdetik intzidentzien xml hartzen du 
     * @return
     *     possible object is
     *     {@link String }
     */
    private String getXMLFromOutside() throws MalformedURLException, IOException, KeyManagementException, NoSuchAlgorithmException{
        URL u = new URL("https://www.trafikoa.eus/servicios/IncidenciasTDT/IncidenciasTrafikoTDTGeo");
        TrustManager[] trustAllCerts = new TrustManager[] {
                       new X509TrustManager() {
                          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                          }

                          public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

                          public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

                       }
                    };

                    SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, trustAllCerts, new java.security.SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                    // Create all-trusting host name verifier
                    HostnameVerifier allHostsValid = new HostnameVerifier() {
                        public boolean verify(String hostname, SSLSession session) {
                          return true;
                        }
                    };
                    // Install the all-trusting host verifier
                    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
                    /*
                     * end of the fix
                     */

                    URL url = u;
                    URLConnection con = url.openConnection();
                    Reader reader = new InputStreamReader(con.getInputStream());
                    String s = "";
                    while (true) {
                      int ch = reader.read();
                      if (ch==-1) {
                        break;
                      }
                      s = s + (char)ch;

                    }
                    return s;
    }
    
    /**
     * Mapan markers-ak jartzeko
     * @return
     *     possible object is
     *     {@link Response }
     */
    @GET
    @Path("inzidentziakMarkers")
    public Response markersFindAll() throws MalformedURLException, IOException {
        
        String send = getAllIntzidentziaAktiboak();
       // send += getAllAurreikuspenak();
        System.out.println(getAllAurreikuspenak());
        Response.ResponseBuilder rb = Response.ok(send);
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
        
    }
    
    /**
     * Intzidentzia aktibo guztiak bueltatzen ditu
     * @return
     *     possible object is
     *     {@link String }
     */
    private String getAllIntzidentziaAktiboak() throws MalformedURLException, IOException{
        String s = "";
        URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.intzidentziaaktiboa");
        URLConnection con = u.openConnection();
	Reader reader = new InputStreamReader(con.getInputStream());
	while (true) {
	    int ch = reader.read();
	    if (ch==-1) {
	        break;
            }
            s = s + (char)ch;
	}
        return s;
    }
    
    /**
     * Aurreikuspen guztiak bueltatzen dituen metodoak
     * @return
     *     possible object is
     *     {@link String }
     */
    private String getAllAurreikuspenak() throws MalformedURLException, IOException{
        String s = "";
        URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.aurreikuspena");
        URLConnection con = u.openConnection();
	Reader reader = new InputStreamReader(con.getInputStream());
	while (true) {
	    int ch = reader.read();
	    if (ch==-1) {
	        break;
            }
            s = s + (char)ch;
	}
        return s;
    }
    
    private String maila(String m) {
        
        String eguna = "Astelehena";
        switch(m){
            case "Negro":
                eguna = "Beltza";
                break;
            case "Amarillo":
                eguna = "Horia";
                break;
            case "Blanco":
                eguna = "Zuria";
                break;  
        }
        return eguna;
    }
    
    private IntzidentziaMota getIntzidentziaMota(String mota) throws MalformedURLException, IOException, JAXBException, JSONException {
        
        String s="";
        URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.intzidentziamota/getintzidentziamota/"+mota);
        URLConnection con = u.openConnection();
	    Reader reader = new InputStreamReader(con.getInputStream());
	    while (true) {
	        int ch = reader.read();
	        if (ch==-1) {
	            break;
	        }
	        s = s + (char)ch;
	    } 
                
        JAXBContext jaxbContext = JAXBContext.newInstance(IntzidentziaMota.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader sreader = new StringReader(s);
        IntzidentziaMota intzidentziaMota = (IntzidentziaMota) unmarshaller.unmarshal(sreader);
        
        return intzidentziaMota;
    }
       
}
