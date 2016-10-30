/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.controller;

import com.fetva.service.DummyServiceLocal;
import com.fetva.types.FetvaCategory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hibernate.ogm.demos.ogm101.part1.Person;

/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "categoryManagedBean1")
@RequestScoped
public class CategoryManagedBean implements Serializable {

    /**
     * Creates a new instance of CategoryManagedBean
     */
    public CategoryManagedBean() {
    }

    private String userData;
    
    private List<Person> personList = new ArrayList<>();

    @Inject
    DummyServiceLocal service;

    @PostConstruct
    public void init() {
        try {
            userData = service.getData();
            personList = service.getListOfPerson();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertUser() {
        try {
            service.insertData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    

}
