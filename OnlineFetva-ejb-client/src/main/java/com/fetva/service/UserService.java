/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.service;

import com.fetva.types.SiteUser;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserService  extends BaseService{
    
    public SiteUser saveUser(SiteUser siteUser) throws Exception;
    
    public List<SiteUser> listOfUser();
    
    public SiteUser LoadUser(String userName,String password);
    
}
