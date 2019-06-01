/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzaileak;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.json.JSONArray;

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
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("inzidentziakMarkers")
    //@Produces({"application/json"})
    public Response markersFindAll() throws MalformedURLException, IOException {
       // List<Erabiltzailea> fresh = new ArrayList<>();
        
        String s = "";
        URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.intzidentziaaktiboa");
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
        Response.ResponseBuilder rb = Response.ok(s);
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
        
        //return fresh;
    }
    /*
    @GET
    @Path("inzidentziaKopuruak")
 //   @Produces({"application/json"})
    public Response inzidentziaKopuruak() throws MalformedURLException, IOException, SQLException {
       // List<Erabiltzailea> fresh = new ArrayList<>();
        
        String s = "";
        URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.intzidentziaaktiboa");
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
    
    /**
     * PUT method for updating or creating an instance of InzidentziakResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
