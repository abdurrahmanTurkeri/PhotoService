/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.service.FetvaCategoryService;
import com.fetva.service.FetvaService;
import com.fetva.types.Fetva;
import com.fetva.types.FetvaCategory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Dependent
@Stateless
public class FetvaServiceImpl implements FetvaService {

    EntityManagerFactory emf;
    EntityManager entityManager;

    // @PersistenceContext
    //EntityManager entityManager;
    @Override
    public void saveFetva(Fetva fetva) throws Exception {
        try {
            emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(fetva);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Fetva> listOfFetva() {
        emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        entityManager = emf.createEntityManager();
        List<Fetva> fetvaList = new ArrayList<>();
        entityManager.getTransaction().begin();
        fetvaList = entityManager.createQuery("from Fetva").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return fetvaList;
    }

    @Override
    public void deleteFetva(Fetva fetva) {

        try {

            emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(fetva);
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<Fetva> listOfFetvaByCategory(String categoryId) {
        emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        entityManager = emf.createEntityManager();
        List<Fetva> fetvaList = new ArrayList<>();
        entityManager.getTransaction().begin();
        
        Query query = entityManager.createQuery("from Fetva f   where f.fetvaCategoryId=:categoryId");
        query.setParameter("categoryId", categoryId);
        fetvaList = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return fetvaList;

    }

}
