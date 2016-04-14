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
    @Path("/kategori/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fetva> getFetvaList(@PathParam("param") String categoryId) {
       try{
        List<Fetva> result = fetvaService.listOfFetvaByCategory(categoryId);
        return result;
       }catch(Exception ex){
           ex.printStackTrace();
           return null;
       }
    }
}
