/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.types.FetvaCategory;
import com.fetva.types.SiteUser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author abdurrahmanturkeri
 */
public class DummyMain {

    EntityManagerFactory factory;
    
    public static void main(String[] args) {
        DummyMain dummyMain=new DummyMain();
        dummyMain.factory = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        
    }
    
    public void testPersist(){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        FetvaCategory fetvaCategory=new FetvaCategory();
        fetvaCategory.setCategoryOrder(1);
        fetvaCategory.setCategoryRate(100);
        fetvaCategory.setCreatedUser(new SiteUser("1233","Abdurrahmant"
        ,"Abdurrahman","Türkeri","abdurrahman.turkeri@gmail.com","905342843427"
        ));
        fetvaCategory.setId("12334334");
        fetvaCategory.setLabel("Mevlud Kandilinde oruç tutulması ile ilgili caiziyet.");
        fetvaCategory.setName("Mevlüd Kandilinde oruç.");
        em.persist(fetvaCategory);
        em.getTransaction().commit();
        
    }

}
