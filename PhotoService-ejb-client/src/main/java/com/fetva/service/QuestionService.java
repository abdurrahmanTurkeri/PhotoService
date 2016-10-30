/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.service;

import com.fetva.types.Question;
import java.util.List;
import javax.ejb.Local;

@Local
public interface QuestionService {
    
    public void saveQuestion(Question question) throws Exception;
    
    public List<Question> listOfQuestion();
    
    public void deleteQuestion(Question question);
    
}
