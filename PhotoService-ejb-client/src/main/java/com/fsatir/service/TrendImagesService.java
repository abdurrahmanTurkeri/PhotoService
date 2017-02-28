/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.service;

import com.fsatir.types.Media;
import com.fsatir.types.TrendImages;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AKIN
 */
@Local
public interface TrendImagesService {
    
    public List<Media> listOfTrendImages() throws Exception;
    
    public List<Media> listOfTrendImagesByHashtag(String hashtag) throws Exception;
    
    public Media getTrendImage(String trendImageID) throws Exception;
    
    public Media saveTrendImage(Media trendImage) throws Exception;
    
    public void deleteTrendImage(List<Media> trendImageList) throws Exception;
}
