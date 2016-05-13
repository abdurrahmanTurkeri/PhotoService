/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.controller;

import com.fetva.service.FetvaCategoryService;
import com.fetva.types.FetvaCategory;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "categoryManagedBean")
@RequestScoped
public class FetvaCategoryManagedBean {

    @Inject
    FetvaCategoryService service;

    private FetvaCategory fetvaCategory = new FetvaCategory();
    private FetvaCategory selectedCategory;

    private List<FetvaCategory> categoryList;

    /**
     * Creates a new instance of FetvaCayegoryManagedBean
     */
    public FetvaCategoryManagedBean() {
    }

    @PostConstruct
    public void init() {
        categoryList = service.listOfCategory();
    }

    public void saveCategory() {
        try {
            service.saveCategory(fetvaCategory);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }
        categoryList = service.listOfCategory();

    }

    public void deleteCategory() {

        try {
            service.deleteCategory(selectedCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));

        }

    }

    public void updateCategory() {

    }

    public void showFetvaList() {

    }

    public void fillSelectedCategory(FetvaCategory fetvaCategory) {
        selectedCategory = fetvaCategory;
    }

    public FetvaCategory getFetvaCategory() {
        return fetvaCategory;
    }

    public void setFetvaCategory(FetvaCategory fetvaCategory) {
        this.fetvaCategory = fetvaCategory;
    }

    public List<FetvaCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<FetvaCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public FetvaCategory getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(FetvaCategory selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

}
