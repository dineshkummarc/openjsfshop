package com.openshop.controller.admincp;

import com.openshop.entities.ArticleBean;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
public class NewArticleController {

    private Logger logger = Logger.getLogger(NewArticleController.class);

    private EntityManagerFactory emf;
    private EntityManager em;
    private String PERSISTENCE_UNIT_NAME = "openjsfdb";

    private ArticleBean selectedArticle;

    /**
     * Constructor
     */
    public NewArticleController() {

        logger.debug("Init NewArticleController");

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();

    }

    private void updateArticle() {

        em.getTransaction().begin();
        em.merge(this.selectedArticle);
        em.getTransaction().commit();

    }


}
