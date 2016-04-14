/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.service;

import com.fetva.types.Fetva;

import java.util.List;
import javax.ejb.Local;

@Local
public interface FetvaService extends BaseService{
    
    public void saveFetva(Fetva fetva) throws Exception;
    
    public List<Fetva> listOfFetva();
    
    public List<Fetva> listOfFetvaByCategory(String categoryId);
    
    public void deleteFetva(Fetva fetva);
    
}
