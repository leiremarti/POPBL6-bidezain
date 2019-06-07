/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.Erabiltzailea;
import encrypt.Encrypter;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String bajanEman(String ids) throws JAXBException, JSONException {
        boolean todoOk=false;
        Encrypter en = new Encrypter("mysecretencrypter");
        JSONArray array = new JSONArray(en.decrypt(ids));
                System.out.println( "**************************"+array.toString() );
        
        EntityManager entitymanager = getEntityManager();
               
        for(int i=0; i<array.length(); i++){
            
            try {
                System.out.println( "**************************"+ array.get(i) );
                Erabiltzailea e = entitymanager.find( Erabiltzailea.class, Short.parseShort((String)array.get(i)));
                System.out.println( "**************************"+e );
                e.setAktibo(false);
            } catch (NumberFormatException e){     
                System.out.println("exception");
            }
            
        }
        todoOk=true;
                
        return String.valueOf(todoOk);
        
    }
    /*
    @GET
    @Path("noHash")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject noHash() {
        EntityManager em = getEntityManager();
        List<Erabiltzailea> o = em.createNativeQuery("SELECT izena,abizena,erabiltzailea,eposta,telefonoa,aktibo FROM erabiltzailea").getResultList();
        System.out.print("--->"+o.get(0).getAbizena());
        JSONObject object = new JSONObject(o);
        return object;
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
       // object 
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
    }*/
    
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createNew(String berria) throws JSONException {
                
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
            System.out.println("asklndcsjñ<fndskfn<ska. ->>"+o);
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
            message = "Erregistroa OK!";
            String pwdSalt = BCrypt.gensalt(16);
            String pwdHashed = BCrypt.hashpw((String)obj.get("passwordHash"), pwdSalt);
            
            entitymanager.createNativeQuery("INSERT INTO erabiltzailea (izena,abizena,erabiltzailea,passwordHash, passwordSalt,eposta,telefonoa) VALUES (?,?,?,?,?,?,?)")
                .setParameter(1, obj.get("izena"))
                .setParameter(2, obj.get("abizena"))
                .setParameter(3, obj.get("erabiltzailea"))
                .setParameter(4, pwdHashed.getBytes())
                .setParameter(5, pwdSalt.getBytes())
                .setParameter(6, obj.get("eposta"))
                .setParameter(7, obj.get("telefonoa"))
                .executeUpdate();
            
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
