package com.openshop.controller.admincp;

import com.openshop.dao.ArticleDao;
import com.openshop.entities.ArticleBean;
import com.openshop.entities.ArticleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class NewArticleController {

    private Logger logger = LoggerFactory.getLogger(NewArticleController.class);
    private ArticleDao articleDao;

    private ArticleBean newArticle;
    private List<ArticleProperty> newProperties;

    /**
     * Constructor
     */
    public NewArticleController() {
        articleDao = new ArticleDao();
        newArticle = new ArticleBean();
        newProperties = new ArrayList<ArticleProperty>();
    }

    public String newProperty() {

        logger.debug("Add new Property to New Article");

        if (newProperties.size() == 0 || !newProperties.get(0).getValue1().equals("") && !newProperties.get((newProperties.size() - 1)).getValue1().equals(""))
            newProperties.add(new ArticleProperty());

        return null;
    }

    public String resetNewArticle() {

        logger.debug("Reset New Article");
        newArticle = new ArticleBean();
        newProperties = new ArrayList<ArticleProperty>();

        return null;
    }

    public String addArticle() {

        if (newArticle.getArticleNumber() != null) {

            logger.debug("Insert new Article to Database");
            articleDao.insertArticleWithProperties(newArticle, newProperties);
        }

        resetNewArticle();

        return "articleManager?faces-redirect=true";
    }

    public ArticleBean getNewArticle() {
        return newArticle;
    }

    public void setNewArticle(ArticleBean newArticle) {
        this.newArticle = newArticle;
    }

    public List<ArticleProperty> getNewProperties() {
        return newProperties;
    }

    public void setNewProperties(List<ArticleProperty> newProperties) {
        this.newProperties = newProperties;
    }
}
