/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.rest.controller;

import com.fetva.service.FetvaCategoryService;
import com.fetva.service.FetvaService;
import com.fetva.types.Fetva;
import com.fetva.types.FetvaCategory;
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
@Path("/kategori")
@RequestScoped
public class FetvaCategoryRsController {

    @Inject
    FetvaCategoryService categoryService;

    /**
     * Creates a new instance of FetvaRsController
     */
    public FetvaCategoryRsController() {
    }

    @GET
    @Path("/kategoriListesi")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public CategoryContainer getFetvaCategoryList() {
        try {
            List<FetvaCategory> result = categoryService.listOfCategory();

            CategoryContainer categoryContainer = new CategoryContainer();
            categoryContainer.setFetvaCategoryList(result);
            return categoryContainer;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
