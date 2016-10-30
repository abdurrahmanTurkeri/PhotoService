/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.rest.controller;

import com.fetva.service.FetvaService;
import com.fetva.types.Fetva;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author abdurrahmanturkeri
 */
@Path("/fetvaListesi")
@RequestScoped
public class FetvaRsController {

    @Inject
    FetvaService fetvaService;

    /**
     * Creates a new instance of FetvaRsController
     */
    public FetvaRsController() {
    }

    @GET
    @Path("/kategori/{param}/{noDetail}")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public FetvaContainer getFetvaList(@PathParam("param") String categoryId,
            @PathParam("noDetail") String noDetail
            ) {
        try {
            List<Fetva> result =null;
            FetvaContainer fetvaContainer = new FetvaContainer();
            if(noDetail!=null && noDetail.equals("true")){
                
            }else{
               result = fetvaService.listOfFetvaByCategory(categoryId);
            }
            
            fetvaContainer.setFetvaList(result);
            return fetvaContainer;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @GET
    @Path("/fetva/{param}")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public FetvaContainer getFetvaList(@PathParam("param") String fetvaId) {
        try {
            List<Fetva> result =null;
            FetvaContainer fetvaContainer = new FetvaContainer();
            result = fetvaService.getFetvaById(fetvaId);
            fetvaContainer.setFetvaList(result);
            return fetvaContainer;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
