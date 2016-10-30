/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.service.BaseService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnits;

/**
 *
 * @author abdurrahmanturkeri
 */
public class BaseServiceImpl implements BaseService{
    
   @PersistenceUnit(unitName = "fetva_mongo_db_pu")
    EntityManagerFactory emf;
   EntityManager em;
    
    @Override
    public EntityManager accessEntityManager() {
        if(emf ==null ){
           emf= Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        }
        if(em!=null && em.isOpen()){
            
        }else{
            em=emf.createEntityManager();
        }
        return em;
    }
    
}
