package com.openshop.controller.admincp;

import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ArticleController {

    private Logger logger = Logger.getLogger(ArticleController.class);

    /**
     * Constructor
     */
    public ArticleController() {

    }

    /**
     * Redirect
     *
     * @return Redirects to new article creation page
     */
    public String addNewArticle() {
        return "newArticle";
    }


}
