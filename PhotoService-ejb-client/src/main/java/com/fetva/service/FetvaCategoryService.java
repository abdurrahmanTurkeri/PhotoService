/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.service;

import com.fetva.types.FetvaCategory;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FetvaCategoryService extends BaseService{
    
    public void saveCategory(FetvaCategory fetvaCategory) throws Exception;
    
    public List<FetvaCategory> listOfCategory();
    
    public void deleteCategory(FetvaCategory fetvaCategory) throws Exception ;
    
}
