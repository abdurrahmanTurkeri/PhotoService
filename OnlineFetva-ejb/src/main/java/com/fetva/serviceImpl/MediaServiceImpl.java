/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.service.MediaService;
import com.fetva.service.QuestionService;
import com.fetva.service.UserService;
import com.fetva.types.Media;
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
public class MediaServiceImpl implements MediaService {

    EntityManagerFactory emf;
    EntityManager entityManager;

    //@PersistenceContext
    //EntityManager entityManager;
   

    @Override
    public void saveData(Media media) throws Exception {
        emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(media);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<Media> listOfMedia() {

        List<Media> mediaList = new ArrayList<>();
        try {
            emf = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            mediaList = entityManager.createQuery("from Media").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mediaList;
    }

}
