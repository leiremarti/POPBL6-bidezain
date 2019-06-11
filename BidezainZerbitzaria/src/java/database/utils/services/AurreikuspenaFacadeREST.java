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
    
    @POST
    @Path("createall")
    @Consumes("application/json")
    public void createAll(/*@Valid Aurreikuspenak*/String entity) throws JSONException, IOException, MalformedURLException, JAXBException {
       /* System.out.print("****************CREATEALLLLLLL**************************"+entity);
        JSONObject o = new JSONObject(entity);
        JSONArray array = (JSONArray) o.get("aurreikuspenak");
        int length = array.length();
        for(int i=0; i<length ; i++){            
            Gson gson = new Gson();
            JSONObject ob = array.getJSONObject(i);
        System.out.println("------>>>>>"+ob.toString());
        
        Long aKm = Long.parseLong((String)ob.get("amaieraKm"));
        String ae = astekoeguna((String)ob.get("astekoEguna"));
        String data = (String)ob.get("data");
        String e = (String)ob.get("errepidea");
        Long hz = Long.parseLong((String)ob.get("haizea"));
        Long hkm = Long.parseLong((String)ob.get("hasieraKm"));
        String hr = (String)ob.get("herria");
        String mota = (String)ob.get("ID_mota");
        IntzidentziaMota im = null;
        if(!mota.equals("null")){                
            im = getIntzidentziaMota(mota);
        }
        BigDecimal lat = new BigDecimal((String)ob.get("lat"));
        BigDecimal lon = new BigDecimal((String)ob.get("lng"));
        Long p = Long.parseLong((String)ob.get("prezipitazioa"));
        String probintzia = ((String)ob.get("probintzia")).toUpperCase();
        if(!mota.equals("null")){                
            if(probintzia.toUpperCase()!="GIPUZKOA" && probintzia.toUpperCase()!="BIZKAIA" &&probintzia.toUpperCase()!="ARABA"){
            probintzia = null;
            }
        }else probintzia = "";
        
        Long tenp = Long.parseLong((String)ob.get("tenperatura"));
        
        
        
        System.out.println(1+" "+ e+" "+probintzia+" "+hr+" "+ ae+" "+data+" "+lat+" "+lon+" "+tenp+" "+hz+" "+p+" "+hkm+" "+aKm);
        
        EntityManager entitymanager = getEntityManager();
        entitymanager.createNativeQuery("insert into aurreikuspena (ID_mota,errepidea,probintzia,herria,astekoEguna,`data`,lat,lng,tenperatura,haizea,prezipitazioa,hasieraKm,amaieraKm) \n" +
                                                "values (?,?,?,?,?,?,?,?,?,?,?,?,?);")
      .setParameter(1, 1)
      .setParameter(2, e)
      .setParameter(3, probintzia)
      .setParameter(4, hr)
      .setParameter(5,ae)
      .setParameter(6, data)
      .setParameter(7, lat)
      .setParameter(8,lon)
      .setParameter(9, tenp)
      .setParameter(10, hz)
      .setParameter(11, p)
      .setParameter(12, hkm )
      .setParameter(13, aKm )
      .executeUpdate();
        
         /*   String s = ob.toString();            
            Aurreikuspena au = gson.fromJson(s, Aurreikuspena.class);*/
           /* 
            Aurreikuspena a = new Aurreikuspena();
            a.setAmaieraKm(Long.parseLong((String)ob.get("amaieraKm")));
            a.setAstekoEguna(astekoeguna((String)ob.get("astekoEguna")));
            a.setData((String)ob.get("data"));
            a.setErrepidea((String)ob.get("errepidea"));
            a.setHaizea(Long.parseLong((String)ob.get("haizea")));
            a.setHasieraKm(Long.parseLong((String)ob.get("hasieraKm")));
            a.setHerria((String)ob.get("herria"));
            String mota = (String)ob.get("ID_mota");
            if(!mota.equals("null")){                
                a.setIDmota(getIntzidentziaMota(mota));
            }
            a.setLat(new BigDecimal((String)ob.get("lat")));
            a.setLng(new BigDecimal((String)ob.get("lng")));
            a.setPrezipitazioa(Long.parseLong((String)ob.get("prezipitazioa")));
            String probintzia = (String)ob.get("probintzia");
            if(probintzia.toUpperCase()!="GIPUZKOA" && probintzia.toUpperCase()!="BIZKAIA" &&probintzia.toUpperCase()!="ARABA"){
                probintzia = null;
            }
            else a.setProbintzia(probintzia.toUpperCase());
            System.out.println(probintzia);
            
            a.setTenperatura(Long.parseLong((String)ob.get("tenperatura")));
            
            
            System.out.println(a.toString());
            create(a);
        */

        //}
       /* List<Aurreikuspena> list = entity.getAurreikuspena();
        for(Aurreikuspena a : list){
        System.out.print(a.toString());
            create(a);
        }*/
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
    
    @GET
    @Path("aurreikuspenak")
  //  @Produces({"application/json"})
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
            /*String eguna = a.getAstekoEguna(1);
            a.setAstekoEguna(astekoeguna(eguna));*/
            jobject.put(json_array);
            i++;
        }
          
       
        System.out.print(jobject);
        
        Response.ResponseBuilder rb = Response.ok(jobject.toString());
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
        
        //return fresh;
    }

    private String astekoeguna(String e) {
        
        String eguna = "Astelehena";
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
