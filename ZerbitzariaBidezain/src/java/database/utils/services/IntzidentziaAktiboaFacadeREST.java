/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.IntzidentziaAktiboa;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@Stateless
@Path("database.utils.intzidentziaaktiboa")
public class IntzidentziaAktiboaFacadeREST extends AbstractFacade<IntzidentziaAktiboa> {
    @PersistenceContext(unitName = "ZerbitzariaBidezainPU")
    private EntityManager em;

    public IntzidentziaAktiboaFacadeREST() {
        super(IntzidentziaAktiboa.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(IntzidentziaAktiboa entity) {
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
    @Produces({"application/json"})
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
                System.out.println(o[0]+" "+o[1]);
                map.put(id, kop);
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    
}
