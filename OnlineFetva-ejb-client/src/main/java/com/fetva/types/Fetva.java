
package com.fetva.types;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author abdurrahmanturkeri
 */

@Embeddable
@NoSql
public class Fetva implements Serializable{
    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;
    
    @Embedded
    @Field(name = "fetvaCategory")
    private FetvaCategory fetvaCategory;
    
    @Embedded
    @Field(name = "question")
    private Question question;
    
    private String answer;
    
    /*
    *Fetvanın statusu cevaplandı cevap bekliyor  
    */
    //TODO enum ile data lookup yapılacak
    private int fetvaStatus;
    
    
      /*
    *Fetvanın yayınlanma statusu   yayınla yayınlama 
    */
    //TODO enum ile data lookup yapılacak
    private int activationStatus;
    
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

    public int getFetvaStatus() {
        return fetvaStatus;
    }

    public void setFetvaStatus(int fetvaStatus) {
        this.fetvaStatus = fetvaStatus;
    }

    public int getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(int activationStatus) {
        this.activationStatus = activationStatus;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    
    
    
    
}
