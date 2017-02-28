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
import com.fsatir.types.TrendImages;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
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
@SessionScoped
public class MediaManagedBean implements Serializable {

    @EJB
    MediaService mediaService;
    
    @Inject
    PhotoCategoryService categoryService;

    private Media media = new Media();
    private Media selectedMedia;
    private List<Media> selectedMediaList;
    private List<Media> mediaList;
    private UploadedFile uploadedFile;
    private List<PhotoCategory> categoryList;
    private List<PhotoCategory> selectedCategoryList = new ArrayList<>();
    public static final String SOURCE_CAPSAPP = "CAPSAPP";
    public static final String SOURCE_ADMIN = "ADMIN";    
    public static final String SOURCE_TWITTER = "TWITTER";
    private TrendImages trendImages = new TrendImages();
    
    
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
            media.setCategoryList(selectedCategoryList);
            media.setTrendImages(trendImages);
            media.setSource(SOURCE_ADMIN);
            media.setLikeCount(1);
            media.setShareCount(1);
            mediaService.saveMedia(media);
            mediaList = mediaService.listOfMedia();
        } catch (Exception ex) {
            Logger.getLogger(MediaManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteSelectedMedia() {
        try {
            mediaService.deleteMedia(selectedMediaList);
            mediaList = mediaService.listOfMedia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Silme başarılı!"));
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }
    }

    /*
        Görsellerin DB'den çekilip, <p:graphicImage 'lerde gösterilmesi
    */
    public StreamedContent getImage() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }        
        else
        {
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            String imgID = req.getParameter("imageID");           
            Media m = mediaService.getMediaDetail(imgID);           
            if(m.getMediaData() == null)
            {
                return null;
            }
            else
                return new DefaultStreamedContent(new ByteArrayInputStream(m.getMediaData()), "image/jpeg");   
        }
    }

    
    /*
        FileUpload olayı
    */
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        // fileUploadListener ile gelen dosyayı UploadedFile nesnesi atıyoruz.
        UploadedFile file = event.getFile();
        byte[] foto = IOUtils.toByteArray(file.getInputstream());
        
        // form içinde set edilmemiş media özelliklerini set ediyoruz
        String fileType = file.getContentType();
        media.setType(fileType);
        media.setMediaData(foto);
        
        FacesMessage message = new FacesMessage("Başarılı", event.getFile().getFileName() + " Dosyaya isim verip kaydet butonuna basınız.");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    /*
        Kategori seçiminde tetiklenen olayı
    */
    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kategori eklendi.", ((PhotoCategory) event.getObject()).getName()));
        selectedCategoryList.add((PhotoCategory) event.getObject());

    }

    public void onSelectedItemsDelete(SelectEvent event){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı!", "Silme işlemi tamamlandı.");         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /*
        GETTERS & SETTERS
    */
    
    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Media getSelectedMedia() {
        return selectedMedia;
    }

    public void setSelectedMedia(Media selectedMedia) {
        this.selectedMedia = selectedMedia;
    }

    public List<Media> getSelectedMediaList() {
        return selectedMediaList;
    }

    public void setSelectedMediaList(List<Media> selectedMediaList) {
        this.selectedMediaList = selectedMediaList;
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
