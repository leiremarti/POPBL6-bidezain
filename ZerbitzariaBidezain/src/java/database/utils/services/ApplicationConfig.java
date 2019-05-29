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
        resources.add(database.utils.services.AktiboMotakFacadeREST.class);
        resources.add(database.utils.services.AktiboakFacadeREST.class);
        resources.add(database.utils.services.AurreikuspenakFacadeREST.class);
        resources.add(database.utils.services.ErabiltzaileaFacadeREST.class);
        resources.add(database.utils.services.ErabiltzaileakResource.class);
        resources.add(database.utils.services.IntzidentziaAktiboakFacadeREST.class);
        resources.add(database.utils.services.IntzidentziaAmaituakFacadeREST.class);
        resources.add(database.utils.services.IntzidentziaMotakFacadeREST.class);
        resources.add(database.utils.services.KontaktuakFacadeREST.class);
        resources.add(database.utils.services.LangileMotakFacadeREST.class);
        resources.add(database.utils.services.LangileakFacadeREST.class);
        resources.add(database.utils.services.LoginResource.class);
    }
    
}
