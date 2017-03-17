/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.rest.controller;

import com.fsatir.types.Media;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AKIN
 */
@XmlRootElement
public class MediaContainer {
    private List<Media> mediaList;

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
    
}
