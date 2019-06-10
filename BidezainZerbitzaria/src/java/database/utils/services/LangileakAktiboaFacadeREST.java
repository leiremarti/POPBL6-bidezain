/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import database.utils.LangileakAktiboa;
import database.utils.LangileakAktiboaPK;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author user
 */
@Stateless
@Path("database.utils.langileakaktiboa")
public class LangileakAktiboaFacadeREST extends AbstractFacade<LangileakAktiboa> {
    @PersistenceContext(unitName = "BidezainZerbitzariaPU")
    private EntityManager em;

    private LangileakAktiboaPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;iDaktiboa=iDaktiboaValue;iDlangilea=iDlangileaValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        database.utils.LangileakAktiboaPK key = new database.utils.LangileakAktiboaPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> iDaktiboa = map.get("iDaktiboa");
        if (iDaktiboa != null && !iDaktiboa.isEmpty()) {
            key.setIDaktiboa(new java.lang.Short(iDaktiboa.get(0)));
        }
        java.util.List<String> iDlangilea = map.get("iDlangilea");
        if (iDlangilea != null && !iDlangilea.isEmpty()) {
            key.setIDlangilea(new java.lang.Short(iDlangilea.get(0)));
        }
        return key;
    }

    public LangileakAktiboaFacadeREST() {
        super(LangileakAktiboa.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(LangileakAktiboa entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, LangileakAktiboa entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        database.utils.LangileakAktiboaPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public LangileakAktiboa find(@PathParam("id") PathSegment id) {
        database.utils.LangileakAktiboaPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<LangileakAktiboa> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<LangileakAktiboa> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
