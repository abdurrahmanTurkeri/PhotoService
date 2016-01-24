/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.types;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author abdurrahmanturkeri
 */

@Embeddable
@NoSql
public class Question implements Serializable{
    
    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;
    
    private String questionText;
    
    /**
     * Sorunun kaynagı ekran goruntusu , xml yada standard text olabilir bunun icin lookup yapılmalı 
     */
    //TODO:lookup ile bu data valide edilmeli ve sayfaya getirilmeli 
    private String questionSourceType;
    
    @Embedded
    private SiteUser createdUser;
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionSourceType() {
        return questionSourceType;
    }

    public void setQuestionSourceType(String questionSourceType) {
        this.questionSourceType = questionSourceType;
    }

    public SiteUser getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(SiteUser createdUser) {
        this.createdUser = createdUser;
    }
    
    
    
    
    
    
    
}
