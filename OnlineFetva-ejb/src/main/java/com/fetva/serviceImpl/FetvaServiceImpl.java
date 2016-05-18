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
public class FetvaServiceImpl extends BaseServiceImpl implements FetvaService {

    @Override
    public void saveFetva(Fetva fetva) throws Exception {
        try {
            em = accessEntityManager();
            em.getTransaction().begin();
            em.persist(fetva);
            em.getTransaction().commit();
            em.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Fetva> listOfFetva() {
        em = accessEntityManager();
        List<Fetva> fetvaList = new ArrayList<>();
        em.getTransaction().begin();
        fetvaList = em.createQuery("from Fetva").getResultList();
        em.getTransaction().commit();
        em.close();
        return fetvaList;
    }

    @Override
    public void deleteFetva(Fetva fetva) {

        try {

            em = accessEntityManager();
            em.getTransaction().begin();
            em.remove(fetva);
            em.getTransaction().commit();
            em.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<Fetva> listOfFetvaByCategory(String categoryId) {
        em = accessEntityManager();
        List<Fetva> fetvaList = new ArrayList<>();
        em.getTransaction().begin();
        Query query = em.createQuery("from Fetva f   where f.fetvaCategoryId=:categoryId");
        query.setParameter("categoryId", categoryId);
        fetvaList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return fetvaList;

    }
    
    @Override
    public List<Fetva> getFetvaById(String fetvaId) {
        em = accessEntityManager();
        List<Fetva> fetvaList = new ArrayList<>();
        em.getTransaction().begin();
        Query query = em.createQuery("from Fetva f   where f.id=:fetvaId");
        query.setParameter("fetvaId", fetvaId);
        fetvaList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return fetvaList;

    }

}
