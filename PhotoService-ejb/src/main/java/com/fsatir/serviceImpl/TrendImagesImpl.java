/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.serviceImpl;

import com.fsatir.service.TrendImagesService;
import com.fsatir.statics.QuestionSourceTypes;
import com.fsatir.types.Media;
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
    public List<Media> listOfTrendImages() throws Exception {
        List<Media> trendImagesList = new ArrayList<>();
        try {
            entityManager = accessEntityManager();
            if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
            }
            Query query = entityManager.createQuery("from Media  m where m.source=:param1");
            query.setParameter("param1", QuestionSourceTypes.FROM_TWITTER.getSourceType());
            trendImagesList=query.getResultList();
            entityManager.getTransaction().commit();
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return trendImagesList;
    }

    @Override
    public List<Media> listOfTrendImagesByHashtag(String hashtag) throws Exception {
      
         List<Media> trendImagesList = new ArrayList<>();
        try {
            entityManager = accessEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Media a where a.trendName=:param1");
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
    public Media getTrendImage(String trendImageID) throws Exception {
        Media trendImage = null;
        entityManager = accessEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Media a where a.id=:param1");
        query.setParameter("param1", trendImageID);
        trendImage = (Media) query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return trendImage;
    }

    @Override
    public Media saveTrendImage(Media trendImage) throws Exception {
        entityManager = accessEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(trendImage);
        entityManager.getTransaction().commit();
        entityManager.close();
        return trendImage;
    }

    @Override
    public void deleteTrendImage(List<Media> trendImageList) throws Exception {
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
