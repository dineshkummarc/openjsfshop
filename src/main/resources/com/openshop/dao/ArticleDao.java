package com.openshop.dao;

/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 12:42
 * Contact & Support: http://sharea.de
 */

import com.openshop.dao.impl.IDatabaseController;
import com.openshop.entities.ArticleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Database Access Object for Articles
 */
public class ArticleDao implements IDatabaseController {

    private Logger logger = LoggerFactory.getLogger(ArticleDao.class);
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(IDatabaseController.PERSISTENCE_UNIT_NAME);

    /**
     * Constructor
     */
    public ArticleDao() {

    }

    /**
     * Creates EntityManager with Connection to Database
     *
     * @return EntityManager
     */
    private EntityManager startConnection() {
        logger.debug("Starting Connection and returning EntityManager");
        return emf.createEntityManager();
    }

    /**
     * Reads out article by given id
     *
     * @param id article id
     * @return ArticleBean with article data
     */
    public ArticleBean getArticle(Long id) {

        logger.debug("Get EntityManager");
        EntityManager em = startConnection();
        ArticleBean article;
        try {
            logger.debug("Create Query and select");
            Query articleQuery = em.createQuery("select a from ArticleBean a where a.articleId = :articleId");
            articleQuery.setParameter("articleId", id);
            article = (ArticleBean) articleQuery.getSingleResult();
        } finally {
            logger.debug("Close EntityManager");
            em.close();
        }

        return article;
    }

    /**
     * Reads out articles by given Article Number
     *
     * @param articleNumber article number
     * @return ArticleBean with article data
     */
    public ArticleBean getArticleByArticleNumber(Long articleNumber) {

        logger.debug("Get EntityManager");
        EntityManager em = startConnection();
        ArticleBean article;
        try {
            logger.debug("Create Query and select");
            Query articleQuery = em.createQuery("select a from ArticleBean a where a.articleNumber = :articleNumber");
            articleQuery.setParameter("articleNumber", articleNumber);
            article = (ArticleBean) articleQuery.getSingleResult();
        } finally {
            logger.debug("Close EntityManager");
            em.close();
        }

        return article;
    }

}
