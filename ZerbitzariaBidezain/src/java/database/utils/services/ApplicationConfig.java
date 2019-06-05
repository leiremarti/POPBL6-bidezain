/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utils.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author user
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(database.utils.services.AktiboMotaFacadeREST.class);
        resources.add(database.utils.services.AktiboaFacadeREST.class);
        resources.add(database.utils.services.AurreikuspenaFacadeREST.class);
        resources.add(database.utils.services.ErabiltzaileaFacadeREST.class);
        resources.add(database.utils.services.ErabiltzaileakResource.class);
        resources.add(database.utils.services.IntzidentziaAktiboaFacadeREST.class);
        resources.add(database.utils.services.IntzidentziaAmaituaFacadeREST.class);
        resources.add(database.utils.services.IntzidentziaMotaFacadeREST.class);
        resources.add(database.utils.services.InzidentziakResource.class);
        resources.add(database.utils.services.KontaktuaFacadeREST.class);
        resources.add(database.utils.services.LangileMotaFacadeREST.class);
        resources.add(database.utils.services.LangileaFacadeREST.class);
        resources.add(database.utils.services.LoginResource.class);
        resources.add(database.utils.services.RegisterResource.class);
    }
    
}
