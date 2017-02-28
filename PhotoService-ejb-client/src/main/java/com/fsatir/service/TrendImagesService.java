/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.service;

import com.fsatir.types.TrendImages;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AKIN
 */
@Local
public interface TrendImagesService {
    
    public List<TrendImages> listOfTrendImages() throws Exception;
    
    public List<TrendImages> listOfTrendImagesByHashtag(String hashtag) throws Exception;
    
    public TrendImages getTrendImage(String trendImageID) throws Exception;
    
    public TrendImages saveTrendImage(TrendImages trendImage) throws Exception;
    
    public void deleteTrendImage(List<TrendImages> trendImageList) throws Exception;
}
