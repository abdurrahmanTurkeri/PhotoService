/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.twitter;

import com.fsatir.service.MediaService;
import com.fsatir.service.TrendImagesService;
import com.fsatir.statics.TwitterInfos;
import com.fsatir.types.Media;
import com.fsatir.types.TrendImages;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.SelectEvent;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "twitterManagedBean")
@ViewScoped
public class TwitterManagedBean implements Serializable {
    
    public static final int TURKEY_WOEID = 23424969;
    List<TrendImages> trendImagesList =new ArrayList<>();
    List<TrendImages> trendImagesListFiltered;
    List<TrendImages> selectedTrendImagesList;
    List<Media> mediaList;
    Media media = new Media();
    
    
    @Inject
    TrendImagesService trendService;
    
    @Inject
    MediaService mediaService;
    
    
    
    public TwitterManagedBean() {
    }
    
    @PostConstruct
    public void init() {
      
       
        try{
               trendImagesListFiltered = trendService.listOfTrendImages();
//             mediaList = mediaService.listOfMedia();
//             for(Media m :  mediaList)
//             {
//                 trendImagesListFiltered.add(m.getTrendImages());
//                 
//             }
            } 
        catch (Exception ex) {
            Logger.getLogger(TwitterManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
    /*
        Twitter'dan trendtopic arama sonuçlarının çekilmesi
    */
     public void pullTrendTopicImages(){      
           try{
            Twitter twitter=bringMyTwitterInstance();
            Trends trends = twitter.getPlaceTrends(TURKEY_WOEID);
            int counter=0;
            for(Trend trend:trends.getTrends()){
               trendImageList(twitter, trend.getName());
               counter++;
            } 
            showMessage(counter+" adet sonuç getirildi.!");
        }catch(TwitterException ex){
            ex.printStackTrace();
        }
    }
    
       
    
    public List<TrendImages> trendImageList(Twitter twitter,String trendName){
        
        Query query = new Query(trendName+" AND filter:images");
        query.setCount(1);
        QueryResult result;
        try {
                do {
                    result = twitter.search(query);
                    
                    List<twitter4j.Status> tweets = result.getTweets();
                    int counter = 0;
                    for (twitter4j.Status status : tweets)
                    {
                        
                        TrendImages trendImages = new TrendImages();
                        int control=0;
                        for(MediaEntity me : status.getMediaEntities())
                            {
                                //Tek görsel almayı kesinleştirmek için kontrol.
                                //İleride modeldeki trendImgURL diziye dönüştürülerek kontrol kaldırılabilir.
                               if(control < 1)
                                    trendImages.setTrendImgURL(me.getMediaURLHttps());
                                control++;
                            }
                            // URL null değilse, görsel ve bigileri list'e eklenir.
                        if(trendImages.getTrendImgURL() != null)
                            {
                                
                                trendImages.setFavorite_count(status.getFavoriteCount());
                                trendImages.setRetweet_count(status.getRetweetCount());
                                trendImages.setUser_screenName(status.getUser().getScreenName());
                                trendImages.setTrendName(trendName);
                                trendImagesList.add(counter++,trendImages);
                            }
                    }
               }
                while ((query = result.nextQuery()) != null);

        } 
            catch (TwitterException e) 
        {
                e.printStackTrace();
        }
        
       trendImagesListFiltered = filterList(trendImagesList);
        
        return trendImagesListFiltered;
        
    }
    
  
    /*
        Twitter bağlantısı oluşturmak için
    */
     public Twitter bringMyTwitterInstance() throws TwitterException{
        
        String consumerKey = TwitterInfos.CONSUMER_KEY.getCredentialValue();
        String consumerSecret = TwitterInfos.CONSUMER_SECRET.getCredentialValue();
        String oAuthToken = TwitterInfos.OAUTH_TOKEN.getCredentialValue();
        String oAuthSecret = TwitterInfos.OAUTH_SECRET.getCredentialValue();

        
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(consumerKey);
        builder.setOAuthConsumerSecret(consumerSecret);
        builder.setOAuthAccessToken(oAuthToken);
        builder.setOAuthAccessTokenSecret(oAuthSecret);
        Configuration configuration = builder.build();
        TwitterFactory factory = new TwitterFactory(configuration);
        Twitter twitter = factory.getInstance();
        return twitter;
    }
     

     /*
        Aynı URL'e sahip imajların ayıklanması
     */
    public List<TrendImages> filterList(List<TrendImages> tL){
        for(int i=0; i < tL.size(); i++)
        {
           
           for(int j=i+1; j < tL.size() && i < tL.size(); j++)
           {
               if(tL.get(i).getTrendImgURL().equals(tL.get(j).getTrendImgURL()))
               {
                   tL.remove(j);
               }
           }
        }        
        return tL;
    }
     
    /*
        DB'deki trend arama sonuçlarını çekmek için
    */
    public void pullTrendImagesFromDB() throws Exception{
        trendImagesListFiltered = trendService.listOfTrendImages();
    }
    
    
    
    /*
        Seçilen trend arama sonuçlarını DB'ye kaydetmek için
    */
    public void saveTrendImages() throws Exception{
        
        if(selectedTrendImagesList.size() < 1)
        {
            showMessage("Öncelikle kaydedilecek sonuçları seçiniz.");
        }
        else
        {
            for(TrendImages ti : selectedTrendImagesList)
            { 
                
                byte [] arr = fetchImageFromURL(ti.getTrendImgURL());
                ti.setTrendImg(arr);
//                media.setSource(SOURCE_TWITTER);
//                media.setTrendImages(ti);
//                mediaService.saveMedia(media);
                trendService.saveTrendImage(ti);
            }
             showMessage("Trend sonuçları veritabanına kaydedildi.");
             
        }     
    }
    
    
    /*
        trend imajlarının byte diziye çevrilmesi
    */
    private byte[] fetchImageFromURL(String imgURL) throws Exception {
        URL url = new URL(imgURL);
        InputStream is = null;
        byte[] bytes = null;
        try {
          is = url.openStream ();
          bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
          e.printStackTrace();
        }
        finally {
          if (is != null) is.close();
        }
        return bytes;
    }
    
    
     public void showMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     

    
    public List<TrendImages> getTrendImagesList() {
        return trendImagesList;
    }

    public void setTrendImagesList(List<TrendImages> trendImagesList) {
        this.trendImagesList = trendImagesList;
    }

    public List<TrendImages> getTrendImagesListFiltered() {
        return trendImagesListFiltered;
    }

    public void setTrendImagesListFiltered(List<TrendImages> trendImagesListFiltered) {
        this.trendImagesListFiltered = trendImagesListFiltered;
    }

    public List<TrendImages> getSelectedTrendImagesList() {
        return selectedTrendImagesList;
    }

    public void setSelectedTrendImagesList(List<TrendImages> selectedTrendImagesList) {
        this.selectedTrendImagesList = selectedTrendImagesList;
    }
    
    
     
}