/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.types;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author abdurrahmanturkeri
 */
@Entity(name = "Media")
@Table(name = "Media")
public class Media implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String url;
    
    private byte[] mediaData;
    
    private String name;
    
    private Long mediaRowNo;
    
    /*
        Number of likes by CapsApp users
    */
    private int likeCount;
    /*
        Number of shares by CapsApp users
    */
    private int shareCount;

    /*
     *Media Type jpg avi mp4 mpeg  
     */
    private String type;
    
    /*
       Source of Media: Twitter, CapsApp, Admin
    */
    private String source;
    
    //***
    @OneToOne(cascade = CascadeType.ALL, targetEntity = TrendImages.class,fetch = FetchType.EAGER)
    private TrendImages trendImages;
    
    @ManyToMany(targetEntity = PhotoCategory.class)
    private List<PhotoCategory> categoryList;
    
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private SiteUser siteUser;
    
    
 
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getMediaData() {
        return mediaData;
    }

    public void setMediaData(byte[] mediaData) {
        this.mediaData = mediaData;
    }

    public String getName() {
        return name;
    }

   

    public void setName(String name) {
        this.name = name;
    }

    public List<PhotoCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<PhotoCategory> categoryList) {
        this.categoryList = categoryList;
    }

   

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public Long getMediaRowNo() {
        return mediaRowNo;
    }

    public void setMediaRowNo(Long mediaRowNo) {
        this.mediaRowNo = mediaRowNo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public TrendImages getTrendImages() {
        return trendImages;
    }

    public void setTrendImages(TrendImages trendImages) {
        this.trendImages = trendImages;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
   
    
    
    
    

}
