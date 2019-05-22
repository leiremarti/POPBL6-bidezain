/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.services;

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
        resources.add(database.services.AktiboMotakFacadeREST.class);
        resources.add(database.services.AktiboakFacadeREST.class);
        resources.add(database.services.AurreikuspenakFacadeREST.class);
        resources.add(database.services.ErabiltzaileaFacadeREST.class);
        resources.add(database.services.IntzidentziaAktiboakFacadeREST.class);
        resources.add(database.services.IntzidentziaAmaituakFacadeREST.class);
        resources.add(database.services.IntzidentziaMotakFacadeREST.class);
        resources.add(database.services.KontaktuakFacadeREST.class);
        resources.add(database.services.LangileMotakFacadeREST.class);
        resources.add(database.services.LangileakFacadeREST.class);
        resources.add(database.services.LoginResource.class);
    }
    
}
