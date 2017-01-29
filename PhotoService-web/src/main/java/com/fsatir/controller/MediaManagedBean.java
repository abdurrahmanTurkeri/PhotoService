/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.controller;

import com.fsatir.service.MediaService;
import com.fsatir.service.PhotoCategoryService;
import com.fsatir.types.Media;
import com.fsatir.types.PhotoCategory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.naming.InitialContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author abdurrahmanturkeri
 */
@ManagedBean(name = "mediaManagedBean")
@ViewScoped
public class MediaManagedBean implements Serializable {

    @EJB
    MediaService mediaService;
    
    @Inject
    PhotoCategoryService categoryService;

    private Media media = new Media();
    private List<Media> mediaList;
    private UploadedFile uploadedFile;
    private List<PhotoCategory> categoryList;
    private List<PhotoCategory> selectedCategoryList = new ArrayList<>();

    /**
     * Creates a new instance of QuestionManagedBean
     */
    public MediaManagedBean() {
    }
    

    @PostConstruct
    public void init() {
        try {
            mediaList = mediaService.listOfMedia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveMedia() {
        try {
            /**
             * InputStream is = uploadedFile.getInputstream();
             * ByteArrayOutputStream bos = new ByteArrayOutputStream(); int
             * nRead = 0; byte[] tempBuffer = new byte[16384]; while
             * ((is.read(tempBuffer, 0, tempBuffer.length)) < 0) {
             * bos.write(tempBuffer, 0, nRead); } bos.flush();
             * media.setMediaData(bos.toByteArray());
             * media.setName(uploadedFile.getFileName());
             * media.setType(uploadedFile.getContentType());
             */
            media.setCategoryList(selectedCategoryList);
            mediaService.saveMedia(media);
            mediaList = mediaService.listOfMedia();
        } catch (Exception ex) {
            Logger.getLogger(MediaManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public StreamedContent getImage(byte[] bytes) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }

        if (bytes != null) {
            StreamedContent graphic = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");
            return graphic;
        } else {
            return null;
        }

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

    public List<PhotoCategory> completeCategory(String query) {
        categoryList = (List<PhotoCategory>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("categoryList");
        if (categoryList == null || categoryList.isEmpty()) {
            categoryList = categoryService.listOfCategory();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categoryList", categoryList);
        }
        List<PhotoCategory> filteredCategorys = new ArrayList<>();

        for (PhotoCategory photoCategory : categoryList) {
            if (photoCategory.getName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredCategorys.add(photoCategory);
            }
        }

        return filteredCategorys;
    }

    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
        selectedCategoryList.add((PhotoCategory) event.getObject());

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

    public List<PhotoCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<PhotoCategory> categoryList) {
        this.categoryList = categoryList;
    }

}
