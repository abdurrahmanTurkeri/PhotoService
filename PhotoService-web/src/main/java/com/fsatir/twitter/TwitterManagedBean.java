/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.twitter;

import com.fsatir.controller.MediaManagedBean;
import com.fsatir.service.MediaService;
import com.fsatir.statics.QuestionSourceTypes;
import com.fsatir.statics.TwitterInfos;
import com.fsatir.types.Media;
import com.fsatir.types.SiteUser;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
    
    private static final int TURKEY_WOEID = 23424969;
    private static final int WORLD_WOEID = 1;
    List<Media> myMediaList =new ArrayList<>();
    List<Media> myMediaListFiltered;
    List<Media> myMediaListSelected;
    private Media media;
    private boolean  showDetail;
    

    @Inject
    MediaService mediaService;
    
    
    
    public TwitterManagedBean() {
    }
    
    @PostConstruct
    public void init() {
      
       
        try{            
               myMediaListFiltered = mediaService.listOfMedia();
               if(myMediaListFiltered==null || myMediaListFiltered.size()<1){
                   myMediaListFiltered=(List<Media>)   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("myMediaListFiltered");         
               }
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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("myMediaListFiltered",myMediaListFiltered);
         
        }catch(TwitterException ex){
            ex.printStackTrace();
        }
    }
    
       
    
    public List<Media> trendImageList(Twitter twitter,String trendName){
        
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
                       
                        Media myMedia = new Media();
                        int control=0;
                        for(MediaEntity me : status.getMediaEntities())
                            {
                                //Tek görsel almayı kesinleştirmek için kontrol.
                                //İleride modeldeki trendImgURL diziye dönüştürülerek kontrol kaldırılabilir.
                               if(control < 1)
                                    myMedia.setTrendImgURL(me.getMediaURLHttps());
                                control++;
                            }
                            // URL null değilse, görsel ve bigileri list'e eklenir.
                        if(myMedia.getTrendImgURL() != null)
                            {                               
                                myMedia.setTweetID(status.getId());
                                myMedia.setFavorite_count(status.getFavoriteCount());
                                myMedia.setRetweet_count(status.getRetweetCount());
                                //myMedia.setTrendImgURL(status.getMediaEntities()[0].getMediaURLHttps());
                                myMedia.setTrendName(trendName);
                                myMedia.setName(Long.toString(status.getId()));
                                myMedia.setMediaRowNo(status.getId());
                                myMedia.setType(status.getMediaEntities()[0].getType());  
                                
                                myMediaList.add(counter++,myMedia);
                            }
                    }
               }
                while ((query = result.nextQuery()) != null && result.getRateLimitStatus().getRemaining() > 0);

        } 
            catch (TwitterException e) 
        {
                e.printStackTrace();
        }
        
       myMediaListFiltered = filterList(myMediaList);
        
        return myMediaListFiltered;
        
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
    public List<Media> filterList(List<Media> tL){
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
        myMediaListFiltered = mediaService.listOfMedia();
    }
    
    
    
    /*
        Seçilen trend arama sonuçlarını DB'ye kaydetmek için
    */
    public void saveTrendImages() throws Exception{
        
        if(myMediaListSelected.size() < 1)
        {
            showMessage("Öncelikle kaydedilecek sonuçları seçiniz.");
        }
        else
        {
            SiteUser siteUser=(SiteUser)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("siteUser");
            for(Media localMedia : myMediaListSelected)
            {                               
                byte [] arr = fetchImageFromURL(localMedia.getTrendImgURL());
                localMedia.setMediaData(arr);
                localMedia.setSource(QuestionSourceTypes.FROM_TWITTER.getSourceType());
                localMedia.setSiteUser(siteUser);
                localMedia.setInsertDate(new Date());
                mediaService.saveMedia(localMedia);
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
        } 
        catch (IOException e) {
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
     
     public void showDetail(){
         showDetail=true;
     }

    public List<Media> getMyMediaList() {
        return myMediaList;
    }

    public void setMyMediaList(List<Media> myMediaList) {
        this.myMediaList = myMediaList;
    }

    public List<Media> getMyMediaListSelected() {
        return myMediaListSelected;
    }

    public void setMyMediaListSelected(List<Media> myMediaListSelected) {
        this.myMediaListSelected = myMediaListSelected;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

     
     
    public List<Media> getTrendImagesList() {
        return myMediaList;
    }

    public void setTrendImagesList(List<Media> myMediaList) {
        this.myMediaList = myMediaList;
    }

    public List<Media> getMyMediaListFiltered() {
        return myMediaListFiltered;
    }

    public void setMyMediaListFiltered(List<Media> myMediaListFiltered) {
        this.myMediaListFiltered = myMediaListFiltered;
    }

    public List<Media> getSelectedTrendImagesList() {
        return myMediaListSelected;
    }

    public void setSelectedTrendImagesList(List<Media> myMediaListSelected) {
        this.myMediaListSelected = myMediaListSelected;
    }

    public boolean isShowDetail() {
        return showDetail;
    }

    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
    }
    
}
