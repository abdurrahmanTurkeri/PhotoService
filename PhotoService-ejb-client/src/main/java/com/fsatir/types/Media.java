/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.types;

import com.fsatir.statics.TwitterInfos;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
    private Integer likeCount=0;
    /*
        Number of shares by CapsApp users
    */
    private Integer shareCount=0;

    /*
     *Media Type jpg avi mp4 mpeg  
     */
    private String type;
    
    /*
       Source of Media: Twitter, CapsApp, Admin
    */
    private String source;
    
    //***
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER , targetEntity = TrendImages.class )
    private TrendImages trendImages;
    
    @ManyToMany(targetEntity = PhotoCategory.class, fetch = FetchType.EAGER)
    private List<PhotoCategory> categoryList;
    
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private SiteUser siteUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
 
    
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

   
    
    
    
    

}
