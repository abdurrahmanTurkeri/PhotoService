/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.service;

import com.fetva.types.Media;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MediaService {
    
    public void saveData(Media media) throws Exception;
    
    public List<Media> listOfMedia();
    
}
