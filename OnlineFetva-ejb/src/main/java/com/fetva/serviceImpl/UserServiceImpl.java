/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.service.QuestionService;
import com.fetva.service.UserService;
import com.fetva.types.Question;
import com.fetva.types.SiteUser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Dependent
@Stateless
public class UserServiceImpl implements UserService {

    EntityManagerFactory emf;
    EntityManager entityManager;

    //@PersistenceContext
    //EntityManager entityManager;
    @Override
    public void saveUser(SiteUser siteUser) throws Exception {
        emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(siteUser);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<SiteUser> listOfUser() {
        List<SiteUser> siteUserList = new ArrayList<>();
        try {
            emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            siteUserList = entityManager.createQuery("from SiteUser").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return siteUserList;

    }

}
