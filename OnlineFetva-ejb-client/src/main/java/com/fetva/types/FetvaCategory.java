
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
public class FetvaCategory implements Serializable{
    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;
    
    private String name;
    
    private String label;
    
    private int categoryOrder;
    
    private int categoryRate; 
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(int categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public int getCategoryRate() {
        return categoryRate;
    }

    public void setCategoryRate(int categoryRate) {
        this.categoryRate = categoryRate;
    }
    
    
    
    
    
    
}
