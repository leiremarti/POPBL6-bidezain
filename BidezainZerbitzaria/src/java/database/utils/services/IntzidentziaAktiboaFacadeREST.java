/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import com.google.gson.Gson;
import database.utils.IntzidentziaAktiboa;
import database.utils.IntzidentziaAktiboak;
import database.utils.IntzidentziaMota;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import utils.Raiz;

/**
 *
 * @author user
 */
@Stateless
@Path("database.utils.intzidentziaaktiboa")
public class IntzidentziaAktiboaFacadeREST extends AbstractFacade<IntzidentziaAktiboa> {
    @PersistenceContext(unitName = "BidezainZerbitzariaPU")
    private EntityManager em;

    public IntzidentziaAktiboaFacadeREST() {
        super(IntzidentziaAktiboa.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(IntzidentziaAktiboa entity) {
            System.out.print("CREATE:"+entity.toString());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Short id, IntzidentziaAktiboa entity) {
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
    public IntzidentziaAktiboa find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces("application/json")
    public List<IntzidentziaAktiboa> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<IntzidentziaAktiboa> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * 
     * Intzidentzia mota bakoitza datu basean zenbat aldiz dauden bueltatzen du.
     * 
     */
    @GET
    @Path("countmotak")
    public Response inzidentziaKopurua() throws MalformedURLException, IOException {
        boolean todoOk=false;
        
        Map<Integer, Long> map = new HashMap<>();
        EntityManager entitymanager = getEntityManager();
                
        try {
                
            List<Object[]> results = em.createNativeQuery("SELECT ID_mota, COUNT(ID_intzidentzia) as kont FROM intzidentzia_aktiboa group by ID_mota").getResultList();
                       
            for(int i=0; i<results.size(); i++){
                Object[] o= results.get(i);
                Integer id = (Integer)o[0];
                Long kop = (Long)o[1];
                map.put(id, kop);
            }
            
            Object result = em.createNativeQuery("SELECT COUNT(ID_aurreikuspena) as kont FROM aurreikuspenagit commit").getSingleResult();
            System.out.println(result);
            Long aKop = (Long)result;
            if(aKop>0){                
                map.put(9, aKop);
            }
            
            
        } catch (NumberFormatException e){     
            System.out.println("exception");
        }
            
        
        todoOk=true;
                
        JSONObject jsonResponse = new JSONObject(map);
        System.out.println(jsonResponse.toString());
        Response.ResponseBuilder rb = Response.ok(jsonResponse.toString());
        Response response = rb.header("Access-Control-Allow-Origin", "http://localhost:8081")
                                .header("origin", "*")
                            .build();
        return response;
        
    }
    
    /**
     * 
     * JSON bidez pasatako Intzidentzia talde bat datu basera gordetzen du.
     * @return
     *     possible object is
     *     {@link Response }
     */
    @POST
    @Path("createall")
    @Consumes("application/json")
    public void createAll(String inzidentziaAktiboak) throws JAXBException, JSONException{
        JSONObject o = new JSONObject(inzidentziaAktiboak);
        JSONArray array = (JSONArray) o.get("intzidentziak");
        int length = array.length();
        for(int i=0; i<length ; i++){            
            Gson gson = new Gson();
            JSONObject ob = array.getJSONObject(i);
            JSONObject motaOb = new JSONObject(ob.get("IDmota").toString());
            IntzidentziaMota m = new IntzidentziaMota();
            Integer id = (Integer)motaOb.get("IDintzidentziamota");
            m.setIDintzidentziamota(id.shortValue());
            m.setIntzidentziaMota((String)motaOb.get("intzidentziaMota"));
            String s = ob.toString();            
            IntzidentziaAktiboa ia = gson.fromJson(s, IntzidentziaAktiboa.class);
            ia.setIDmota(m);
            System.out.println(ia.toString());
            create(ia);

        }
        
    }
}
