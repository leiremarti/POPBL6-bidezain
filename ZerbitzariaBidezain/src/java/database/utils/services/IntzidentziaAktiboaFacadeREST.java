/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.IntzidentziaAktiboa;
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
import javax.ws.rs.core.MediaType;

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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @POST
    @Path("kopuruak")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({"application/x-www-form-urlencode"})
    public String bajanEman() throws JAXBException {
        boolean todoOk=false;
        String idArray[] = new String[1];
        idArray[0] = ids;
        try{
            System.err.println("KOPURUAK: "+ ids);
            idArray = ids.split(";");
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Bakarrik balore bat dau");
        }
        
        EntityManager entitymanager = getEntityManager();
               
        for(int i=0; i<idArray.length; i++){
            
            try {
                Erabiltzailea e = entitymanager.find( Erabiltzailea.class, Short.parseShort(idArray[i]));
                System.out.println( "**************************"+e );
                e.setAktibo(false);
            } catch (NumberFormatException e){     
                System.out.println("exception");
            }
            
        }
        todoOk=true;
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
        return map.toString();
        
    }
    
}
