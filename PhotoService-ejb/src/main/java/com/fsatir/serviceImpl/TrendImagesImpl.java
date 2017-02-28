/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.serviceImpl;

import com.fsatir.service.TrendImagesService;
import com.fsatir.types.TrendImages;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author AKIN
 */
@Dependent
@Stateless
public class TrendImagesImpl extends BaseServiceImpl implements TrendImagesService{

    EntityManager entityManager;
    
    @Override
    public List<TrendImages> listOfTrendImages() throws Exception {
        List<TrendImages> trendImagesList = new ArrayList<>();
        try {
            entityManager = accessEntityManager();
            entityManager.getTransaction().begin();
            trendImagesList = entityManager.createQuery("from TrendImages").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return trendImagesList;
    }

    @Override
    public List<TrendImages> listOfTrendImagesByHashtag(String hashtag) throws Exception {
      
         List<TrendImages> trendImagesList = new ArrayList<>();
        try {
            entityManager = accessEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from TrendImages a where a.trendName=:param1");
            query.setParameter("param1", hashtag);
            trendImagesList = query.getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return trendImagesList;
        
    }

    @Override
    public TrendImages getTrendImage(String trendImageID) throws Exception {
        TrendImages trendImage = null;
        entityManager = accessEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from TrendImages a where a.id=:param1");
        query.setParameter("param1", trendImageID);
        trendImage = (TrendImages) query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return trendImage;
    }

    @Override
    public TrendImages saveTrendImage(TrendImages trendImage) throws Exception {
        entityManager = accessEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(trendImage);
        entityManager.getTransaction().commit();
        entityManager.close();
        return trendImage;
    }

    @Override
    public void deleteTrendImage(List<TrendImages> trendImageList) throws Exception {
        entityManager = accessEntityManager();
        entityManager.getTransaction().begin();
        for(int i=0; i < trendImageList.size(); i++)
          {
            em.remove(em.contains(trendImageList.get(i)) ? trendImageList.get(i) : em.merge(trendImageList.get(i)));
          } 
        entityManager.getTransaction().commit();
        entityManager.close(); 
    }
    
    
}
