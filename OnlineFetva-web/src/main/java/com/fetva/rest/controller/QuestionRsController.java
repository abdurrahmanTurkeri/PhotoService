/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.rest.controller;

import com.fetva.service.FetvaCategoryService;
import com.fetva.service.FetvaService;
import com.fetva.service.QuestionService;
import com.fetva.service.UserService;
import com.fetva.statics.QuestionSourceTypes;
import com.fetva.types.Fetva;
import com.fetva.types.FetvaCategory;
import com.fetva.types.Question;
import com.fetva.types.SiteUser;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author abdurrahmanturkeri
 */
@Path("/soru")
@RequestScoped
public class QuestionRsController {

    @Inject
    QuestionService questionService;
    
    @Inject 
    UserService userService;

    /**
     * Creates a new instance of FetvaRsController
     */
    public QuestionRsController() {
    }

    @GET
    @Path("/{questionText}/{soranIsim}/{soranNo}/{soranEmail}")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response askQuestion(@PathParam("questionText") String questionText,
            @PathParam("soranIsim") String soranIsim, @PathParam("soranNo") String soranNo,
            @PathParam("soranEmail") String soranEmail) {
        WrapperObject wrapperObject=new WrapperObject();
        try {
            Question question=new Question();
            SiteUser siteUser=new SiteUser(soranIsim,soranEmail,soranNo);
            SiteUser createdUser= userService.saveUser(siteUser);
            question.setCreatedUser(createdUser);
            question.setQuestionSourceType(QuestionSourceTypes.FROM_MOBILE.getSourceType());
            question.setQuestionText(questionText);
            question.setQuestionTitle("Ziyaretci Sorusu");
            
            questionService.saveQuestion(question);
            wrapperObject.setResult("Sorunuz Alinmistir");
            return Response.status(200).entity(wrapperObject).build();

        } catch (Exception ex) {
            wrapperObject.setResult("ERROR");
            ex.printStackTrace();
            return Response.status(500).entity(wrapperObject).build();
        }
    }
}
