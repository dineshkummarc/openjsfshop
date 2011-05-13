package com.openshop.dao;

/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 12:42
 * Contact & Support: http://sharea.de
 */

import com.openshop.beans.ArticleSearchBean;
import com.openshop.dao.impl.IDatabaseController;
import com.openshop.entities.ArticleBean;
import com.openshop.entities.ArticleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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

            logger.debug("Read out: " + article.getTitle() + " with: " + article.getProperties().toString());

        } finally {
            logger.debug("Close EntityManager");
            em.close();
        }

        return article;
    }

    /**
     * Reads out a List of ArticleBeans given by setted filters
     *
     * @param searchBean Filter Search Bean
     * @return List<ArticleBean>
     */
    public List<ArticleBean> getArticlesList(ArticleSearchBean searchBean) {

        logger.debug("Get EntityManager");

        EntityManager em = startConnection();
        List<ArticleBean> articles;

        try {
            logger.debug("Create Query and select");
            Query articleQuery = em.createQuery("select a from ArticleBean a where (:articleNumber is null or a.articleNumber = :articleNumber) and (:articleTitle is null or a.title = :articleTitle)");

            articleQuery.setParameter("articleNumber", searchBean.getTxtArticleNumber());
            articleQuery.setParameter("articleTitle", searchBean.getTxtArticleTitle());

            if (searchBean.getFirstPage() != null && searchBean.getPageSize() != null) {
                articleQuery.setFirstResult(searchBean.getFirstPage());
                articleQuery.setMaxResults(searchBean.getPageSize());
            }

            articles = (List<ArticleBean>) articleQuery.getResultList();

            for (ArticleBean article : articles) {
                logger.debug("Read out: " + article.getTitle() + " with " + article.getProperties().toString());
            }

        } finally {
            logger.debug("Close EntityManager");
            em.close();
        }

        return articles;
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

            logger.debug("Read out: " + article.getTitle() + " with " + article.getProperties().toString());

        } finally {
            logger.debug("Close EntityManager");
            em.close();
        }

        return article;
    }

    /**
     * Create new Article without properties
     *
     * @param article ArticleBean
     */
    public void insertArticle(ArticleBean article) {

        logger.debug("Get Entity Manager");
        EntityManager em = startConnection();

        try {
            logger.debug("Get Transaction");
            em.getTransaction().begin();
            em.persist(article);
            logger.debug("Commit Transaction");
            em.getTransaction().commit();

        } finally {
            logger.debug("Close EntityManager");
            em.close();
        }

    }

    /**
     * Create new Article with Properties
     *
     * @param articleWithProps ArticleBean with ArticleData
     * @param propertyList     List with Article Properties
     */
    public void insertArticleWithProperties(ArticleBean articleWithProps, List<ArticleProperty> propertyList) {

        logger.debug("Get Entity Manager");
        EntityManager em = startConnection();

        try {
            logger.debug("Get Transaction");
            em.getTransaction().begin();

            logger.debug("Insert each Property to Database");

            for (ArticleProperty property : propertyList) {
                property.setProperty(articleWithProps);
                em.persist(property);
                articleWithProps.getProperties().add(property);
            }

            logger.debug("Insert Article");
            em.persist(articleWithProps);

            logger.debug("Commit Transaction");
            em.getTransaction().commit();

        } finally {
            logger.debug("Close EntityManager");
            em.close();
        }

    }
}
