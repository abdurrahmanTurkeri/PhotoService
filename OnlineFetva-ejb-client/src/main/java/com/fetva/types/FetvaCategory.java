
package com.fetva.types;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author abdurrahmanturkeri
 */

@Entity(name = "FetvaCategory")
public class FetvaCategory implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String name;
    
    private String label;
    
    private int categoryOrder;
    
    private int categoryRate; 
    
    
    
    @OneToOne
    private SiteUser createdUser;
    

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

    public SiteUser getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(SiteUser createdUser) {
        this.createdUser = createdUser;
    }

 
    
    
    
}
