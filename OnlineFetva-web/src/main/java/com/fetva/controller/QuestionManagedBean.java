/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.controller;

import com.fetva.service.QuestionService;
import com.fetva.service.UserService;
import com.fetva.types.Question;
import com.fetva.types.SiteUser;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "questionManagedBean")
@ViewScoped
public class QuestionManagedBean implements Serializable{

    @Inject
    QuestionService questionService;
    @Inject
    UserService userService;
    private SiteUser siteUser;
    private List<SiteUser> userList;

    private Question question = new Question();
    private List<Question> questionList;
    private Question selectedQuestion=new Question();
    private Question tmpSelectedQuestion=new Question();

    /**
     * Creates a new instance of QuestionManagedBean
     */
    public QuestionManagedBean() {
    }

    @PostConstruct
    public void init() {
        tmpSelectedQuestion=null;
        questionList = questionService.listOfQuestion();
         siteUser = (SiteUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("siteUser");
       
    }

    public void saveQuestion() {
        try {
            question.setCreatedUser(siteUser);
            question.setQuestionSourceType("Twitter");
            questionService.saveQuestion(question);
        } catch (Exception ex) {
            Logger.getLogger(QuestionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        questionList = questionService.listOfQuestion();
    }
    
     public void deleteQuestion() {
        try {
         questionService.deleteQuestion(selectedQuestion);
        } catch (Exception ex) {
            Logger.getLogger(QuestionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        questionList = questionService.listOfQuestion();
    }
    
    
    public void setselctedQuestion(){
        tmpSelectedQuestion=selectedQuestion;
    }
    

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Question getSelectedQuestion() {
        return selectedQuestion;
    }

    public void setSelectedQuestion(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    public Question getTmpSelectedQuestion() {
        return tmpSelectedQuestion;
    }

    public void setTmpSelectedQuestion(Question tmpSelectedQuestion) {
        this.tmpSelectedQuestion = tmpSelectedQuestion;
    }
    
    
    
    

}
