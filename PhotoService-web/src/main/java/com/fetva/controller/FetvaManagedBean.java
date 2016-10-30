/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.controller;

import com.fetva.service.FetvaCategoryService;
import com.fetva.service.FetvaService;
import com.fetva.service.QuestionService;
import com.fetva.statics.QuestionSourceTypes;
import com.fetva.types.Answer;
import com.fetva.types.Fetva;
import com.fetva.types.FetvaCategory;
import com.fetva.types.Question;
import com.fetva.types.SiteUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.primefaces.context.RequestContext;


/**
 *
 * @author abdurrahmanturkeri
 */
@Named(value = "fetvaManagedBean")
@RequestScoped
public class FetvaManagedBean {

    @Inject
    private FetvaCategoryService categoryService;

    @Inject
    private QuestionService questionService;

    @Inject
    FetvaService fetvaService;
    
    QuestionSourceTypes questionSourceTypes;

    private String answerText;

    private Fetva fetva = new Fetva();
    private List<FetvaCategory> fetvaCategoryList;
    private List<Question> questionList;
    private List<Question> tempQuestionList;
    private List<Fetva> fetvaList;
    private Fetva selectedFetva;
    private Fetva tempSelectedFetva;
    private List<String> selectedSourceTypes;
    /**
     * Creates a new instance of FetvaManagedBean
     */
    public FetvaManagedBean() {
    }

    @PostConstruct
    public void init() {
        fetvaCategoryList = categoryService.listOfCategory();
        questionList = questionService.listOfQuestion();
        tempQuestionList=new ArrayList<>(questionList);
        fetvaList=fetvaService.listOfFetva();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("questionList", questionList);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categoryList", fetvaCategoryList);
    }

    public void saveFetva() {
        try {
           SiteUser siteUser=(SiteUser)SecurityUtils.getSubject().getSession().getAttribute("siteUser");
            Answer answer = new Answer();
            answer.setAnswerDate(new Date());
            answer.setAnswerText(answerText);
            answer.setAnsweredUser(siteUser);
            fetva.getAnswerList().add(answer);
            fetva.setFetvaCategoryId(fetva.getFetvaCategory().getId());
           
            fetvaService.saveFetva(fetva);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public void filterQuestions(){
        tempQuestionList.clear();
        if(getSelectedSourceTypes().size()<1){
            tempQuestionList=new ArrayList<>(questionList);
        }
        for(Question q :questionList){
            if(getSelectedSourceTypes().contains(q.getQuestionSourceType())){
                tempQuestionList.add(q);
            }
        }
        
        RequestContext.getCurrentInstance().update("insertForm:questionInput");
    }

    public Fetva getFetva() {
        return fetva;
    }

    public void setFetva(Fetva fetva) {
        this.fetva = fetva;
    }

    public List<FetvaCategory> getFetvaCategoryList() {
        return fetvaCategoryList;
    }

    public void setFetvaCategoryList(List<FetvaCategory> fetvaCategoryList) {
        this.fetvaCategoryList = fetvaCategoryList;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Fetva> getFetvaList() {
        return fetvaList;
    }

    public void setFetvaList(List<Fetva> fetvaList) {
        this.fetvaList = fetvaList;
    }

    public Fetva getSelectedFetva() {
        return selectedFetva;
    }

    public void setSelectedFetva(Fetva selectedFetva) {
        this.selectedFetva = selectedFetva;
    }

    public Fetva getTempSelectedFetva() {
        return tempSelectedFetva;
    }

    public void setTempSelectedFetva(Fetva tempSelectedFetva) {
        this.tempSelectedFetva = tempSelectedFetva;
    }

    public List<Question> getTempQuestionList() {
        return tempQuestionList;
    }

    public void setTempQuestionList(List<Question> tempQuestionList) {
        this.tempQuestionList = tempQuestionList;
    }

    public List<String> getSelectedSourceTypes() {
        return selectedSourceTypes;
    }

    public void setSelectedSourceTypes(List<String> selectedSourceTypes) {
        this.selectedSourceTypes = selectedSourceTypes;
    }

   
    
    

}
