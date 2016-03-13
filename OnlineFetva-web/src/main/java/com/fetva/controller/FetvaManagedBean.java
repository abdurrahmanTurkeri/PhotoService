/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.controller;

import com.fetva.service.FetvaCategoryService;
import com.fetva.types.Fetva;
import com.fetva.types.FetvaCategory;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "fetvaManagedBean")
@RequestScoped
public class FetvaManagedBean {
    @Inject
    private FetvaCategoryService categoryService;
    
    private Fetva fetva=new Fetva();
    private List<FetvaCategory> fetvaCategoryList;

    /**
     * Creates a new instance of FetvaManagedBean
     */
    public FetvaManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        fetvaCategoryList=categoryService.listOfCategory();
    }
    
    public void saveFetva(){
        
    }
    
    

    public Fetva getFetva() {
        return fetva;
    }

    public void setFetva(Fetva fetva) {
        this.fetva = fetva;
    }

    public List<FetvaCategory> getFetvaCategoryList() {
        return fetvaCategoryList;
    }

    public void setFetvaCategoryList(List<FetvaCategory> fetvaCategoryList) {
        this.fetvaCategoryList = fetvaCategoryList;
    }
    
    
    
    
    
}
