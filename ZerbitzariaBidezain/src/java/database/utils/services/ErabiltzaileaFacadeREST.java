/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzailea;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
import javax.xml.bind.JAXBException;
import org.json.JSONException;
import org.json.JSONObject;

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
    @Consumes("application/json")
    public void create(Erabiltzailea entity) {
        System.out.println("****************"+entity.toString());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Short id, Erabiltzailea entity) {
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
    public Erabiltzailea find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({ "application/json", "application/xml"})
    public List<Erabiltzailea> findAll() {
        return super.findAll();
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
    
    
    @POST
    @Path("baja")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({"application/x-www-form-urlencode"})
    public String bajanEman(String ids) throws JAXBException {
        boolean todoOk=false;
        String idArray[] = new String[1];
        idArray[0] = ids;
        try{
            System.err.println("EDITATUUU: "+ ids);
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
                
        return String.valueOf(todoOk);
        
    }
    
    @POST
    @Path("pwdHash")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getHash(String username) {
        JSONObject object = new JSONObject();
        EntityManager em = getEntityManager();
        Object o = em.createNativeQuery("SELECT passwordHash FROM erabiltzailea WHERE erabiltzailea = '"+username+"'").getSingleResult();
        System.out.print(o);
        return object;
    }
    
    @POST
    @Path("exists")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String erabiltzaileaExists(String data) throws JSONException {
        boolean erabiltzaileaExists = true;
        boolean epostaExists = true;
        JSONObject object = new JSONObject(data);
        String email = object.getString("eposta");
        String erabiltzailea = object.getString("erabiltzailea");
        
        EntityManager entitymanager = getEntityManager();
        try{            
            Object o = entitymanager.createNativeQuery("SELECT * FROM erabiltzailea WHERE erabiltzailea = '"+erabiltzailea+"'").getSingleResult();
        }catch(NoResultException e){
            erabiltzaileaExists = false;
        }catch(NonUniqueResultException e){
            erabiltzaileaExists = false;
            System.out.println("Erabiltzailea errepikatuta!");
        }
        try{            
            Object o2 = entitymanager.createNativeQuery("SELECT * FROM erabiltzailea WHERE eposta = '"+email+"'").getSingleResult();
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
    public String createNew(String berria) throws JSONException {
                
        System.out.println("****************"+berria);
        boolean regOK = false;
        boolean erabiltzaileaExists = true;
        boolean epostaExists = true;
        JSONObject obj = new JSONObject(berria);
        String email = obj.getString("eposta");
        String erabiltzailea = obj.getString("erabiltzailea");
        String message = "";
        EntityManager entitymanager = getEntityManager();
                
        try{            
            Object o = entitymanager.createNativeQuery("SELECT * FROM erabiltzailea WHERE erabiltzailea = '"+erabiltzailea+"'").getSingleResult();
        }catch(NoResultException e){
            erabiltzaileaExists = false;
        }catch(NonUniqueResultException e){
            erabiltzaileaExists = false;
            System.out.println("Erabiltzailea errepikatuta!");
        }
        try{            
            Object o2 = entitymanager.createNativeQuery("SELECT * FROM erabiltzailea WHERE eposta = '"+email+"'").getSingleResult();
        }catch(NoResultException e){
            epostaExists = false;
        }catch(NonUniqueResultException e){            
            epostaExists = false;
            System.out.println("Eposta errepikatuta!");
        }        
        
        if(!epostaExists && !erabiltzaileaExists){
            regOK=true;
            entitymanager.createNativeQuery("INSERT INTO erabiltzailea (izena,abizena,erabiltzailea,passwordHash, passwordSalt,eposta,telefonoa) VALUES (?,?,?,?,?,?,?)")
                .setParameter(1, obj.get("izena"))
                .setParameter(2, obj.get("abizena"))
                .setParameter(3, obj.get("erabiltzailea"))
                .setParameter(4, obj.get("passwordHash"))
                .setParameter(5, obj.get("passwordHash"))
                .setParameter(6, obj.get("eposta"))
                .setParameter(7, obj.get("telefonoa"))
                .executeUpdate();
            
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
