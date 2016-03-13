package com.fetva.types;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author abdurrahmanturkeri
 */

@Entity
public class Fetva implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private FetvaCategory fetvaCategory;

    @OneToOne
    private Question question;

    private String answer;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

}
