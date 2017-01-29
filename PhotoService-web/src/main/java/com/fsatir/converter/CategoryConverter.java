/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.converter;

import com.fsatir.types.PhotoCategory;

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
        PhotoCategory photoCategory=null;    
        if (value != null && value.trim().length() > 0) {
            List<PhotoCategory> photoCategoryList = (List<PhotoCategory>) context.getCurrentInstance().getExternalContext().getSessionMap().get("categoryList");

            for (PhotoCategory f : photoCategoryList) {
                if (f.getId().equals(value)) {
                  photoCategory=f;  
                }
            }
            return photoCategory;
        } else {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((PhotoCategory) value).getId());
        } else {
            return null;
        }

    }

}
