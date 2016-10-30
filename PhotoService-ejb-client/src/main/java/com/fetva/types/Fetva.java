package com.fetva.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author abdurrahmanturkeri
 */
@XmlRootElement(name = "fetva")
@Entity
public class Fetva implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER)
    private FetvaCategory fetvaCategory;

    @OneToOne(fetch = FetchType.EAGER)
    private Question question;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Answer> answerList;
    
    private String fetvaCategoryId;

    /*
    *Fetvanın statusu cevaplandı cevap bekliyor  
     */
    //TODO enum ile data lookup yapılacak
    private boolean fetvaStatus;

    /*
    *Fetvanın yayınlanma statusu   yayınla yayınlama 
     */
    //TODO enum ile data lookup yapılacak
    private boolean activationStatus;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date publishDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FetvaCategory getFetvaCategory() {
        return fetvaCategory;
    }

    public void setFetvaCategory(FetvaCategory fetvaCategory) {
        this.fetvaCategory = fetvaCategory;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean getFetvaStatus() {
        return fetvaStatus;
    }

    public void setFetvaStatus(boolean fetvaStatus) {
        this.fetvaStatus = fetvaStatus;
    }

    public boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public List<Answer> getAnswerList() {
        if(answerList==null){
           answerList=new ArrayList<>();
        }
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getFetvaCategoryId() {
        return fetvaCategoryId;
    }

    public void setFetvaCategoryId(String fetvaCategoryId) {
        this.fetvaCategoryId = fetvaCategoryId;
    }
    
    
    
    

}
