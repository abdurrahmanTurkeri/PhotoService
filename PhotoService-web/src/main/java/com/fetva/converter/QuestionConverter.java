/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.converter;

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
@FacesConverter("quesionConverter")
public class QuestionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Question question=null;    
        if (value != null && value.trim().length() > 0) {
            List<Question> questionList = (List<Question>) context.getCurrentInstance().getExternalContext().getSessionMap().get("questionList");

            for (Question q : questionList) {
                if (q.getId().equals(value)) {
                  question=q;  
                }
            }
            return question;
        } else {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((Question) value).getId());
        } else {
            return null;
        }

    }

}
