/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import com.google.gson.Gson;
import database.utils.Aurreikuspena;
import database.utils.Aurreikuspenak;
import database.utils.IntzidentziaAktiboa;
import database.utils.IntzidentziaMota;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@Stateless
@Path("database.utils.aurreikuspena")
public class AurreikuspenaFacadeREST extends AbstractFacade<Aurreikuspena> {
    @PersistenceContext(unitName = "BidezainZerbitzariaPU")
    private EntityManager em;

    public AurreikuspenaFacadeREST() {
        super(Aurreikuspena.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Aurreikuspena entity) {
        super.create(entity);
    }
    
    /**
     * Aurreikuspenak banaka generatu beharrean, JSON bitartezko estruktura bateko Aurreikuspen guztiak generatzen ditu.
     * 
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @POST
    @Path("createall")
    @Consumes("application/json")
    public void createAll(String entity) throws JSONException, IOException, MalformedURLException, JAXBException {
        System.out.print("--->>>"+entity);
        JSONObject o = new JSONObject(entity);
        JSONArray array = (JSONArray) o.get("aurreikuspenak");
        int length = array.length();
        for(int i=0; i<length ; i++){            
            Gson gson = new Gson();
            JSONObject ob = array.getJSONObject(i);  
            Aurreikuspena a = new Aurreikuspena();
            a.setErrepidea((String)ob.get("errepidea"));
            String probintzia = ((String)ob.get("probintzia")).toUpperCase();
            if(!probintzia.equals("null")){                
                if(probintzia.toUpperCase()!="GIPUZKOA" && probintzia.toUpperCase()!="BIZKAIA" &&probintzia.toUpperCase()!="ARABA"){
                    probintzia = "";
                }
            }else if(probintzia.equals(""))probintzia = "GIPUZKOA";
            else probintzia = "GIPUZKOA";
            a.setProbintzia(probintzia);
            a.setHerria((String)ob.get("herria"));
            a.setAstekoEguna(astekoeguna((String)ob.get("astekoEguna")));
            System.out.print("****ASTEKOEUNE*********"+astekoeguna((String)ob.get("astekoEguna")));
            a.setData((String)ob.get("data"));
            a.setLat(new BigDecimal((String)ob.get("lat")));
            a.setLng(new BigDecimal((String)ob.get("lng")));
            a.setTenperatura(Float.parseFloat((String)ob.get("tenperatura")));
            a.setHaizea(Float.parseFloat((String)ob.get("haizea")));
            a.setPrezipitazioa(Float.parseFloat((String)ob.get("prezipitazioa")));
            a.setHasieraKm(Float.parseFloat((String)ob.get("hasieraKm")));
            a.setAmaieraKm(Float.parseFloat((String)ob.get("amaieraKm")));
               
            EntityManager entitymanager = getEntityManager();
            entitymanager.createNativeQuery("insert into aurreikuspena (ID_mota,errepidea,probintzia,herria,astekoEguna,`data`,lat,lng,tenperatura,haizea,prezipitazioa,hasieraKm,amaieraKm) \n" +
                                                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?);")
                .setParameter(1, 9 )
                .setParameter(2, a.getErrepidea())
                .setParameter(3, "GIPUZKOA")
                .setParameter(4, a.getHerria())
                .setParameter(5, a.getAstekoEguna())
                .setParameter(6, a.getData())
                .setParameter(7, a.getLat())
                .setParameter(8, a.getLng())
                .setParameter(9, a.getTenperatura())
                .setParameter(10, a.getHaizea())
                .setParameter(11, a.getPrezipitazioa())
                .setParameter(12, a.getHasieraKm() )
                .setParameter(13, a.getAmaieraKm() )
                .executeUpdate();

        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Short id, Aurreikuspena entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Short id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Aurreikuspena find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Aurreikuspena> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Aurreikuspena> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    /**
     * Aurreikuspenak filtratzen ditu
     * 
     * @param value
     *     allowed object is
     *     {@link String}
     * 
     * @return
     *     possible object is
     *     {@link Response }
     */
    @GET
    @Path("aurreikuspenak")
    public Response filteredAurreikuspenak() {
        List<Object[]> filtered = new ArrayList<>();
        EntityManager entitymanager = getEntityManager();
        JSONArray jobject = new JSONArray();
        
        filtered = em.createNativeQuery("SELECT errepidea,probintzia,herria,astekoEguna,`data`,hasieraKm,amaieraKm FROM aurreikuspena WHERE itIs = 1").getResultList();
            
        int i=0;
        for(Object[] o : filtered){
            JSONArray json_array = new JSONArray();
            json_array.put(o[0]);
            json_array.put(o[1]);
            json_array.put(o[2]);
            json_array.put(astekoeguna((String)o[3]));
            json_array.put(o[4]);
            json_array.put(o[5]);
            json_array.put(o[6]);
            System.out.print(json_array);
            jobject.put(json_array);
            i++;
        }
          
       
        System.out.print(jobject);
        
        Response.ResponseBuilder rb = Response.ok(jobject.toString());
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
    }
    
/**
     * Asteko eguna bueltatzen duen metodoa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     */
    private String astekoeguna(String e) {
        
        String eguna = "AL";
        switch(e){
            case "AR":
                eguna = "Asteartea";
                break;
            case "AZ":
                eguna = "Asteazkena";
                break;
            case "OG":
                eguna = "Osteguna";
                break;
            case "OR":
                eguna = "Ostirala";
                break;
            case "LR":
                eguna = "Larunbata";
                break;
            case "IG":
                eguna = "Igandea";
                break;    
        }
        return eguna;
    }
    
    /**
     * Intzidentzia motaren arabera IntzidentziaMota klaseko objektu bat bueltatzen du.
     * 
     * @return
     *     possible object is
     *     {@link IntzidentziaMota }
     */
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
