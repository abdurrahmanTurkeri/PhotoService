package com.fetva.rest.conf;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources=new HashSet<Class<?>>();
        resources.add(com.fetva.rest.controller.FetvaRsController.class);        
               
        return resources;
    }

    
}
