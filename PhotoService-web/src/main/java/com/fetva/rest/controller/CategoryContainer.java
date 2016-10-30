/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.rest.controller;

import com.fetva.types.FetvaCategory;
import java.util.List;

/**
 *
 * @author abdurrahmanturkeri
 */

public class CategoryContainer {
    
    
    private List<FetvaCategory> fetvaCategoryList;

    public List<FetvaCategory> getFetvaCategoryList() {
        return fetvaCategoryList;
    }

    public void setFetvaCategoryList(List<FetvaCategory> fetvaCategoryList) {
        this.fetvaCategoryList = fetvaCategoryList;
    }

   
    
   
    
}
