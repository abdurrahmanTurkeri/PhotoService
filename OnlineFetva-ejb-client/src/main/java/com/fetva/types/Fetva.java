
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
public class Fetva implements Serializable{
    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;
    
    @Embedded
    @Field(name = "fetvaCategory")
    private FetvaCategory fetvaCategory;
    
    
            
    
    
    
    
    
    
}
