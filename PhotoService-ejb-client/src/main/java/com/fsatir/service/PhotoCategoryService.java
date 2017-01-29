/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.service;

import com.fsatir.types.PhotoCategory;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PhotoCategoryService extends BaseService{
    
    public void saveCategory(PhotoCategory fetvaCategory) throws Exception;
    
    public List<PhotoCategory> listOfCategory();
    
    public void deleteCategory(PhotoCategory fetvaCategory) throws Exception ;
    
}
