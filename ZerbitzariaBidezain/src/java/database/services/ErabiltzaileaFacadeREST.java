/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.services;

import database.utils.Erabiltzailea;
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

/**
 *
 * @author user
 */
@Stateless
@Path("database.utils.erabiltzailea")
public class ErabiltzaileaFacadeREST extends AbstractFacade<Erabiltzailea> {
    @PersistenceContext(unitName = "ZerbitzariaBidezainPU")
    private EntityManager em;

    public ErabiltzaileaFacadeREST() {
        super(Erabiltzailea.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Erabiltzailea entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Short id, Erabiltzailea entity) {
        super.edit(entity);
    }
    
    @POST
    @Path("baja")
    public void bajanEman(String ids) {
      //super.edit(entity);
        System.err.println("EDITATUUU: "+ ids);
        String idArray[] = ids.split(";");
        System.err.println("EDITATUUU22: "+ idArray[0]+idArray[1]);
        
        EntityManager entitymanager = getEntityManager();
        entitymanager.getTransaction( ).begin( );
        for(int i=0; i<idArray.length; i++){
            Erabiltzailea employee = entitymanager.find( Erabiltzailea.class, idArray[i] );

            //before update
            System.out.println( employee );
            employee.setSalary( 46000 );
            entitymanager.getTransaction( ).commit( );

            //after update
            System.out.println( employee );
        }
        
        entitymanager.close();
        
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Short id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Erabiltzailea find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json", "application/xml"})
    public List<Erabiltzailea> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("safe")
    @Produces({"application/json"})
    public List<Erabiltzailea> safeFindAll() {
        List<Erabiltzailea> fresh = new ArrayList<>();
        for(Erabiltzailea e : super.findAll()){
            byte[] data = new byte[0];
            e.setPasswordHash(data);
            e.setPasswordSalt(data);
            fresh.add(e);            
        }
        return fresh;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Erabiltzailea> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
