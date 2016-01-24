/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.types;

import java.io.Serializable;
import javax.persistence.Embeddable;
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
    
    private String questionSourceType;
    
    
    
    
}
