/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.serviceImpl;

import com.fsatir.service.PhotoCategoryService;
import com.fsatir.types.PhotoCategory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

@Dependent
@Stateless
public class PhotoCategoryServiceImpl extends BaseServiceImpl implements PhotoCategoryService {

    @Override
    public void saveCategory(PhotoCategory fetvaCategory) throws Exception {

        em = accessEntityManager();
        em.getTransaction().begin();
        em.persist(fetvaCategory);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<PhotoCategory> listOfCategory() {
        em = accessEntityManager();
        List<PhotoCategory> categoryList = new ArrayList<>();
        em.getTransaction().begin();
        categoryList = em.createQuery("from PhotoCategory").getResultList();
        em.getTransaction().commit();
        em.close();
        return categoryList;
    }

    @Override
    public void deleteCategory(PhotoCategory fetvaCategory) throws Exception {
        em = accessEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(fetvaCategory) ? fetvaCategory : em.merge(fetvaCategory));
        em.getTransaction().commit();
        em.close();
    }

}
