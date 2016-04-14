/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.converter;

import com.fetva.types.FetvaCategory;
import com.fetva.types.Question;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author abdurrahmanturkeri
 */
@FacesConverter("categoryConverter")
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FetvaCategory fetvaCategory=null;    
        if (value != null && value.trim().length() > 0) {
            List<FetvaCategory> fetvaCategoryList = (List<FetvaCategory>) context.getCurrentInstance().getExternalContext().getSessionMap().get("categoryList");

            for (FetvaCategory f : fetvaCategoryList) {
                if (f.getId().equals(value)) {
                  fetvaCategory=f;  
                }
            }
            return fetvaCategory;
        } else {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((FetvaCategory) value).getId());
        } else {
            return null;
        }

    }

}
