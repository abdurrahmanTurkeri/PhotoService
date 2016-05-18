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

@Dependent
@Stateless
public class FetvaCategoryServiceImpl extends BaseServiceImpl implements FetvaCategoryService {

    @Override
    public void saveCategory(FetvaCategory fetvaCategory) throws Exception {

        em = accessEntityManager();
        em.getTransaction().begin();
        em.persist(fetvaCategory);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<FetvaCategory> listOfCategory() {
        em = accessEntityManager();
        List<FetvaCategory> categoryList = new ArrayList<>();
        em.getTransaction().begin();
        categoryList = em.createQuery("from FetvaCategory").getResultList();
        em.getTransaction().commit();
        em.close();
        return categoryList;
    }

    @Override
    public void deleteCategory(FetvaCategory fetvaCategory) throws Exception {
        em = accessEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(fetvaCategory) ? fetvaCategory : em.merge(fetvaCategory));
        em.getTransaction().commit();
        em.close();
    }

}
