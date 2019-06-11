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
 * REST Web Service
 *
 * @author user
 */
@Path("inzidentziak")
public class InzidentziakResource {

    @Context
    private UriInfo context;
    private Object JDBCTutorialUtilities;

    /**
     * Creates a new instance of InzidentziakResource
     */
    public InzidentziakResource() {
    }

    /**
     * Retrieves representation of an instance of database.utils.services.InzidentziakResource
     * @return an instance of java.lang.String
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
        Encrypter en = new Encrypter("mysecretencrypter");
        return inzidentziak_json.toString();//en.encrypt(inzidentziak_json.toString());
    }
    
    /**
     * Retrieves representation of an instance of database.utils.services.InzidentziakResource
     * @return an instance of java.lang.String
     */
    @POST
    @Path("todatabase")
    @Produces("application/json")
    public String toDatabase() throws JAXBException, IOException, MalformedURLException, KeyManagementException, NoSuchAlgorithmException, JSONException {
        
        String xmlString = getXml();
        
        JSONObject object = new JSONObject(xmlString);
        JSONArray array_inzidentziak = object.getJSONArray("inzidentziaAktiboak");
               
      //  List<IntzidentziaAktiboa> intzidentziak_list = new ArrayList<>();
        
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
           // intzidentziak_list.add(ia);
            array_send.put(o);
        }
        
        JSONObject send = new JSONObject();
        send.put("intzidentziak", array_send);
        
       /* IntzidentziaAktiboak iak = new IntzidentziaAktiboak();
        iak.setIntzidentziaAktiboa(intzidentziak_list);
        */
      /*  JAXBContext jaxbContext = JAXBContext.newInstance(IntzidentziaAktiboak.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(iak, sw);
        String xmlContent = sw.toString();*/
        
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
    
    @GET
    @Path("inzidentziakMarkers")
    //@Produces({"application/json"})
    public Response markersFindAll() throws MalformedURLException, IOException {
       // List<Erabiltzailea> fresh = new ArrayList<>();
        
        String send = getAllIntzidentziaAktiboak();
        System.out.println(getAllAurreikuspenak());
        Response.ResponseBuilder rb = Response.ok(send);
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
        
        //return fresh;
    }
    
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
        System.out.print(s);
        return s;
    }
    
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
        System.out.print(s);
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
    
    /*
    @POST
    @Path("refresh")
    @Produces("text/plain")
    public String refreshIntzidentziak() throws JSONException, JAXBException, IOException, MalformedURLException, KeyManagementException, NoSuchAlgorithmException {
        System.out.print("++++++++");
        JSONObject obj = new JSONObject(getXml());
        System.out.println(obj.getJSONArray("inzidentziaAktiboak"));
        
        URL url = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.intzidentziaaktiboa/deleteAll");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        
        return "aaa";
    }
    */
    
    /*
    @GET
    @Path("inzidentziaKopuruak")
 //   @Produces({"application/json"})
    public Response inzidentziaKopuruak() throws MalformedURLException, IOException, SQLException {
       // List<Erabiltzailea> fresh = new ArrayList<>();
        
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
        System.out.print(s);
        
        //mota bakoitza bere kopurukin biali
        /*
        QUERY:
        SELECT ID_mota, COUNT(ID_intzidentzia) FROM intzidentzia_aktiboa GROUP BY ID_mota
        https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        
        
        Statement stmt = null;
        String query = "SELECT ID_mota, COUNT(ID_intzidentzia) FROM intzidentzia_aktiboa GROUP BY ID_mota";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + "\t" + supplierID +
                                   "\t" + price + "\t" + sales +
                                   "\t" + total);
            }
        } catch (SQLException e ) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 18);
        map.put(2, 18);
        map.put(3, 18);
        map.put(4, 18);
        map.put(5, 18);
        map.put(6, 18);
        map.put(7, 18);
        map.put(8, 18);
        map.put(9, 18);
        List<Integer> list = new ArrayList<>();
        list.add(18);
        
        Response.ResponseBuilder rb = Response.ok(map.toString());
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
        
        //return fresh;
    }
    
    public Connection getConnection() throws SQLException {

    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", this.userName);
    connectionProps.put("password", this.password);

    if (this.dbms.equals("mysql")) {
        conn = DriverManager.getConnection(
                   "jdbc:" + this.dbms + "://" +
                   this.serverName +
                   ":" + this.portNumber + "/",
                   connectionProps);
    } else if (this.dbms.equals("derby")) {
        conn = DriverManager.getConnection(
                   "jdbc:" + this.dbms + ":" +
                   this.dbName +
                   ";create=true",
                   connectionProps);
    }
    System.out.println("Connected to database");
    return conn;
}
    
    */
    
   
}
