/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.service.FetvaCategoryService;
import com.fetva.types.FetvaCategory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Dependent
@Stateless
public class FetvaCategoryServiceImpl implements FetvaCategoryService {

    EntityManagerFactory emf;
    EntityManager entityManager;

    // @PersistenceContext
    //EntityManager entityManager;
    @Override
    public void saveCategory(FetvaCategory fetvaCategory) throws Exception {
        emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(fetvaCategory);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<FetvaCategory> listOfCategory() {
        emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        entityManager = emf.createEntityManager();
        List<FetvaCategory> categoryList = new ArrayList<>();
        entityManager.getTransaction().begin();
        categoryList = entityManager.createQuery("from FetvaCategory").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return categoryList;
    }
    
    @Override
    public void deleteCategory(FetvaCategory fetvaCategory) throws Exception {
        emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(fetvaCategory);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
