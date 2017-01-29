/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.controller;

import com.fsatir.service.PhotoCategoryService;
import com.fsatir.types.PhotoCategory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "categoryManagedBean")
@javax.faces.view.ViewScoped
public class PhotoCategoryManagedBean implements Serializable{

    @Inject
    PhotoCategoryService service;

    private PhotoCategory photoCategory = new PhotoCategory();
    private PhotoCategory selectedCategory;

    private List<PhotoCategory> categoryList;

    /**
     * Creates a new instance of FetvaCayegoryManagedBean
     */
    public PhotoCategoryManagedBean() {
    }

    @PostConstruct
    public void init() {
        categoryList = service.listOfCategory();
    }

    public void saveCategory() {
        try {
            service.saveCategory(photoCategory);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }
        categoryList = service.listOfCategory();

    }

    public void deleteCategory() {

        try {
            service.deleteCategory(selectedCategory);
            categoryList = service.listOfCategory();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));

        }

    }

    public void updateCategory() {

    }

    public void showFetvaList() {

    }

    public void fillSelectedCategory(PhotoCategory fetvaCategory) {
        selectedCategory = fetvaCategory;
    }

    public PhotoCategory getPhotoCategory() {
        return photoCategory;
    }

    public void setPhotoCategory(PhotoCategory photoCategory) {
        this.photoCategory = photoCategory;
    }

  

    public List<PhotoCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<PhotoCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public PhotoCategory getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(PhotoCategory selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

}
