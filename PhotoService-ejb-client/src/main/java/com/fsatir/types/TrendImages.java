/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.types;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import org.hibernate.annotations.GenericGenerator;



@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "TrendImages")
@Table(name = "TrendImages")
public class TrendImages implements Serializable{

    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private Long tweetID;
    private String user_screenName;
    private int retweet_count;
    private int favorite_count;
    private String trendImgURL;
    private String trendName;
    private byte [] trendImg;
    
    
    
    //***
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trendImages")
    private Media media;
    
    public TrendImages() {

    }

    public Long getTweetID() {
        return tweetID;
    }

    public void setTweetID(Long tweetID) {
        this.tweetID = tweetID;
    }
    
    
    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }


    public byte[] getTrendImg() {
        return trendImg;
    }

    public void setTrendImg(byte[] trendImg) {
        this.trendImg = trendImg;
    }
    

    
    public String getTrendName() {
        return trendName;
    }

    public void setTrendName(String trendName) {
        this.trendName = trendName;
    }  

    public String getUser_screenName() {
        return user_screenName;
    }

    public void setUser_screenName(String user_screenName) {
        this.user_screenName = user_screenName;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getTrendImgURL() {
        return trendImgURL;
    }

    public void setTrendImgURL(String trendImgURL) {
        this.trendImgURL = trendImgURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

