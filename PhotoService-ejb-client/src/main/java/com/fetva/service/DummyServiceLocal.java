/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.service;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import org.hibernate.ogm.demos.ogm101.part1.Person;


/**
 *
 * @author abdurrahmanturkeri
 */
@Local
public interface DummyServiceLocal {
    
    public String getData();
    
    public void insertData() throws Exception;
    
    public List<Person> getListOfPerson();
        
}
