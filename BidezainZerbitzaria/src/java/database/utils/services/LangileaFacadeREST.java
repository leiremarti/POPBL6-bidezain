/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Langilea;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
@Stateless
@Path("database.utils.langilea")
public class LangileaFacadeREST extends AbstractFacade<Langilea> {
    @PersistenceContext(unitName = "BidezainZerbitzariaPU")
    private EntityManager em;

    public LangileaFacadeREST() {
        super(Langilea.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Langilea entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Short id, Langilea entity) {
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
    public Langilea find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Langilea> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Langilea> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("exists")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String langileaExists(String data) throws JSONException {
        boolean erabiltzaileaExists = true;
        boolean epostaExists = true;
        JSONObject object = new JSONObject(data);
        String email = object.getString("eposta");
        String erabiltzailea = object.getString("erabiltzailea");
        
        EntityManager entitymanager = getEntityManager();
        try{            
            Object o = em.createNativeQuery("SELECT * FROM langilea WHERE erabiltzailea = '"+erabiltzailea+"'").getSingleResult();
        }catch(NoResultException e){
            erabiltzaileaExists = false;
        }catch(NonUniqueResultException e){
            erabiltzaileaExists = false;
            System.out.println("Erabiltzailea errepikatuta!");
        }
        try{            
            Object o2 = em.createNativeQuery("SELECT * FROM langilea WHERE eposta = '"+email+"'").getSingleResult();
        }catch(NoResultException e){
            epostaExists = false;
        }catch(NonUniqueResultException e){            
            epostaExists = false;
            System.out.println("Eposta errepikatuta!");
        }
        
        JSONObject o = new JSONObject();
        o.put("erabiltzailea", erabiltzaileaExists);
        o.put("eposta", epostaExists);
        
        return o.toString();
    }
    
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createNew(@Valid Langilea berria) throws JSONException {
        boolean regOK = false;
        boolean erabiltzaileaExists = true;
        boolean epostaExists = true;
        
        String message = "";
        EntityManager entitymanager = getEntityManager();
                
        try{            
            Object o = entitymanager.createNativeQuery("SELECT * FROM langilea WHERE erabiltzailea = '"+berria.getErabiltzailea()+"'").getSingleResult();
        }catch(NoResultException e){
            erabiltzaileaExists = false;
        }catch(NonUniqueResultException e){
            erabiltzaileaExists = false;
            System.out.println("Erabiltzailea errepikatuta!");
        }
        try{            
            Object o2 = entitymanager.createNativeQuery("SELECT * FROM langilea WHERE eposta = '"+berria.getEposta()+"'").getSingleResult();
        }catch(NoResultException e){
            epostaExists = false;
        }catch(NonUniqueResultException e){            
            epostaExists = false;
            System.out.println("Eposta errepikatuta!");
        }        
        
        if(!epostaExists && !erabiltzaileaExists){
            regOK=true;
            message = "Erregistroa OK!";
            String pwdSalt = BCrypt.gensalt(16);
            String pwdHashed = BCrypt.hashpw(berria.getPasswordHash(), pwdSalt);
            
            entitymanager.createNativeQuery("INSERT INTO langilea (izena,abizena,erabiltzailea,passwordHash, passwordSalt,eposta,telefonoa, ID_mota) VALUES (?,?,?,?,?,?,?,?)")
                .setParameter(1, berria.getIzena())
                .setParameter(2, berria.getAbizena())
                .setParameter(3, berria.getErabiltzailea())
                .setParameter(4, pwdHashed.getBytes())
                .setParameter(5, pwdSalt.getBytes())
                .setParameter(6, berria.getEposta())
                .setParameter(7, berria.getTelefonoa())
                .setParameter(8, 1)
                .executeUpdate();           
            
           /* em.getTransaction().begin();
            em.persist(berria);
            em.getTransaction().commit();*/
        }else if(epostaExists && erabiltzaileaExists){
            message = "Eposta eta erabiltzailea errepikatuta.";
        }else if(epostaExists){
            message = "Eposta errepikatuta.";
        }else if(erabiltzaileaExists){
            message = "Erabiltzailea errepikatuta.";
        }
        
        JSONObject o = new JSONObject();
        o.put("message", message);
        o.put("regOK", regOK);
        return o.toString();
    }
}
