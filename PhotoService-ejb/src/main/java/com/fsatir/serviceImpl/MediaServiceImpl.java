/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.serviceImpl;

import com.fsatir.service.MediaService;
import com.fsatir.types.Media;
import com.fsatir.types.PhotoCategory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Dependent
@Stateless
public class MediaServiceImpl extends BaseServiceImpl implements MediaService {

    
    EntityManager entityManager;

   

    @Override
    public List<Media> listOfMedia() throws Exception {

        List<Media> mediaList = new ArrayList<>();
        try {
            entityManager = accessEntityManager();
            if(!entityManager.getTransaction().isActive()){
                entityManager.getTransaction().begin();
              }
            mediaList = entityManager.createQuery("from Media").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mediaList;
    }

    
    @Override
    public List<Media> listOfMediaBySource(String source) throws Exception {
        List<Media> mediaList = new ArrayList<>();
        try {
            entityManager = accessEntityManager();
            if(!entityManager.getTransaction().isActive()){
                 entityManager.getTransaction().begin();
             }
            Query q = entityManager.createQuery("from Media m where m.source=:param1");
            q.setParameter("param1", source);
            mediaList = (List<Media>) q.getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return mediaList;
    }
    
    
    @Override
    public List<Media> listOfMediaByCategory(String categoryId) throws Exception {

        List<Media> mediaList = new ArrayList<>();
        List<Media> mediaListReturn = new ArrayList<>();
        PhotoCategory photoCategory = null;
        int i = 0;
        try {
            entityManager = accessEntityManager();
            if(!entityManager.getTransaction().isActive()){
                 entityManager.getTransaction().begin();
             }
            Query q = entityManager.createQuery("from PhotoCategory p where p.id=:param1");
            q.setParameter("param1", categoryId);
            photoCategory = (PhotoCategory) q.getSingleResult();
            
            mediaList = entityManager.createQuery("from Media").getResultList();

            for(Media m : mediaList)
            {                
               if(mediaList.get(i).getCategoryList().contains(photoCategory))
               {
                    mediaListReturn.add(m);
               }                
                i++;
            }
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mediaListReturn;
    }

    @Override
    public Media getMediaDetail(String mediaId) throws Exception {
        Media media = null;
        entityManager = accessEntityManager();
        if(!entityManager.getTransaction().isActive()){
          entityManager.getTransaction().begin();
        }
        Query query = entityManager.createQuery("from Media a where a.id=:param1");
        query.setParameter("param1", mediaId);
        media = (Media) query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return media;
    }

    @Override
    public Media saveMedia(Media media) throws Exception {
        entityManager = accessEntityManager();
        if(!entityManager.getTransaction().isActive()){
          entityManager.getTransaction().begin();
        }
        entityManager.merge(media.getSiteUser());
        entityManager.persist(media);
        entityManager.getTransaction().commit();
        entityManager.close();
        return media;
     }

    @Override
    public void deleteMedia(List<Media> selectedMediaList) throws Exception {
        entityManager = accessEntityManager();
        if(!entityManager.getTransaction().isActive()){
                entityManager.getTransaction().begin();
        }
        for(int i=0; i < selectedMediaList.size(); i++)
          {
            em.remove(em.contains(selectedMediaList.get(i)) ? selectedMediaList.get(i) : em.merge(selectedMediaList.get(i)));
          } 
        entityManager.getTransaction().commit();
        entityManager.close();        
   }

    @Override
    public List<Media> listOfMediaByCategoryLabel(String categoryLabel) throws Exception {
       
        List<Media> mediaList = new ArrayList<>();
        List<Media> mediaListReturn = new ArrayList<>();
        PhotoCategory category = null;
        int i = 0;
        try {
            entityManager = accessEntityManager();
            if(!entityManager.getTransaction().isActive()){
                 entityManager.getTransaction().begin();
             }            
            
            Query q = entityManager.createQuery("from PhotoCategory p where p.label=:param1");
            q.setParameter("param1", categoryLabel);
            category = (PhotoCategory) q.getSingleResult();
         
            
            mediaList = entityManager.createQuery("from Media").getResultList();
       
            for(Media m : mediaList)
            { 
               if(m.getCategoryList().contains(category))
               {
                     mediaListReturn.add(m);
               }          
               i++;
            }
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mediaListReturn;        
    }

  
  
  

}
