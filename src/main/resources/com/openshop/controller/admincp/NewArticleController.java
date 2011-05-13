package com.openshop.controller.admincp;

import com.openshop.dao.ArticleDao;
import com.openshop.entities.ArticleBean;
import com.openshop.entities.ArticleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NewArticleController {

    private Logger logger = LoggerFactory.getLogger(NewArticleController.class);
    private ArticleDao articleDao;

    private ArticleBean newArticle;

    /**
     * Constructor
     */
    public NewArticleController() {
        articleDao = new ArticleDao();
        newArticle = new ArticleBean();
    }

    public String newProperty() {

        newArticle.getProperties().add(new ArticleProperty());

        return null;
    }

    public ArticleBean getNewArticle() {
        return newArticle;
    }

    public void setNewArticle(ArticleBean newArticle) {
        this.newArticle = newArticle;
    }
}
