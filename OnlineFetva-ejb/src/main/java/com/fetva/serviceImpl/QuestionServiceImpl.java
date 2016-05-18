/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.service.QuestionService;
import com.fetva.types.Question;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

@Dependent
@Stateless
public class QuestionServiceImpl extends BaseServiceImpl implements QuestionService {

   
    @Override
    public void saveQuestion(Question question) throws Exception {
        em=accessEntityManager();
        em.getTransaction().begin();
        em.persist(em.contains(question) ? question : em.merge(question));
        em.getTransaction().commit();

        em.close();

    }

    @Override
    public List<Question> listOfQuestion() {
        List<Question> questionList = new ArrayList<>();
        try {
            em=accessEntityManager();
            em.getTransaction().begin();
            questionList = em.createQuery("from Question").getResultList();
            em.getTransaction().commit();
            em.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionList;
    }

    @Override
    public void deleteQuestion(Question question) {
        try {
            em=accessEntityManager();
            em.getTransaction().begin();
            em.remove(em.contains(question) ? question : em.merge(question));
            em.getTransaction().commit();
            em.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }

}
