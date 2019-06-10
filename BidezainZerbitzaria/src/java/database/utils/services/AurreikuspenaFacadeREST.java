/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Aurreikuspena;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.Response;
import org.json.JSONArray;

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
}
