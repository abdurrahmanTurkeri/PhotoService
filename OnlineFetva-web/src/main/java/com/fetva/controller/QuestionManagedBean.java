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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "questionManagedBean")
@RequestScoped
public class QuestionManagedBean {

    @Inject
    QuestionService questionService;
     @Inject
     UserService userService;
     private SiteUser siteUser;
     private List<SiteUser> userList;

    private Question question=new Question();
    private List<Question> questionList;

    /**
     * Creates a new instance of QuestionManagedBean
     */
    public QuestionManagedBean() {
    }

    @PostConstruct
    public void init() {
        questionList = questionService.listOfQuestion();
       userList= userService.listOfUser();
       if(userList !=null &&  !userList.isEmpty()){
           siteUser=userList.get(0);
       }
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

}
