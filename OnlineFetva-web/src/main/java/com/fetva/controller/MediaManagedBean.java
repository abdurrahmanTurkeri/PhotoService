/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.controller;

import com.fetva.service.MediaService;
import com.fetva.types.Media;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "mediaManagedBean")
@SessionScoped
public class MediaManagedBean implements Serializable {

    @Inject
    MediaService mediaService;

    private Media media = new Media();
    private List<Media> mediaList;
    private UploadedFile uploadedFile;

    /**
     * Creates a new instance of QuestionManagedBean
     */
    public MediaManagedBean() {
    }

    @PostConstruct
    public void init() {
        mediaList = mediaService.listOfMedia();
    }

    public void saveMedia() {
        try {
            /**
            InputStream is = uploadedFile.getInputstream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int nRead = 0;
            byte[] tempBuffer = new byte[16384];
            while ((is.read(tempBuffer, 0, tempBuffer.length)) < 0) {
                bos.write(tempBuffer, 0, nRead);
            }
            bos.flush();
            media.setMediaData(bos.toByteArray());
            media.setName(uploadedFile.getFileName());
            media.setType(uploadedFile.getContentType());
               */
            mediaService.saveData(media);
        } catch (Exception ex) {
            Logger.getLogger(MediaManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        mediaList = mediaService.listOfMedia();
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            FacesMessage message = new FacesMessage("Başarılı", event.getFile().getFileName() + " Dosyaya isim verip kaydet butonuna basınız.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            InputStream is = event.getFile().getInputstream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int nRead = 0;
            byte[] tempBuffer = new byte[16384];
            while ((is.read(tempBuffer, 0, tempBuffer.length)) < 0) {
                bos.write(tempBuffer, 0, nRead);
            }
            bos.flush();
            media.setMediaData(bos.toByteArray());
            media.setName(event.getFile().getFileName());
            media.setType(event.getFile().getContentType());

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
