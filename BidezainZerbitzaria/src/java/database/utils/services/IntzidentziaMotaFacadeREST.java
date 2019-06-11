/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.IntzidentziaMota;
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

/**
 *
 * @author user
 */
@Stateless
@Path("database.utils.intzidentziamota")
public class IntzidentziaMotaFacadeREST extends AbstractFacade<IntzidentziaMota> {
    @PersistenceContext(unitName = "BidezainZerbitzariaPU")
    private EntityManager em;

    public IntzidentziaMotaFacadeREST() {
        super(IntzidentziaMota.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(IntzidentziaMota entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Short id, IntzidentziaMota entity) {
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
    public IntzidentziaMota find(@PathParam("id") Short id) {
        return super.find(id);
    }
    
    @GET
    @Path("getintzidentziamota/{mota}")
   // @Consumes("text/plain")
    @Produces("application/xml")
    public IntzidentziaMota getIntzidentziaMota(@PathParam("mota") String mota) {
        
        if(mota.contains("_")){
            mota = mota.replace("_", " ");
        }
        EntityManager entitymanager = getEntityManager();
        List<Object[]> results = em.createNativeQuery("SELECT ID_intzidentzia_mota,`intzidentzia_mota` FROM intzidentzia_mota where intzidentzia_mota = '"+mota+"'").getResultList();
        
        IntzidentziaMota in = null;
        for(int i=0; i<results.size(); i++){
                Object[] o= results.get(i);
                Integer id = (Integer)o[0];
                String mot = (String)o[1];
                in = new IntzidentziaMota(id.shortValue(), mot);
            }
        
        return in;
    }

    @GET
    @Override
    @Produces("application/json")
    public List<IntzidentziaMota> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<IntzidentziaMota> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
}
