package com.openshop.dao;

/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 12:42
 * Contact & Support: http://sharea.de
 */

import com.openshop.beans.ArticleSearchBean;
import com.openshop.entities.ArticleBean;
import com.openshop.entities.ArticleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Database Access Object for Articles
 */
@Repository("articleDao")
public class ArticleDaoImpl extends AbstractDaoImpl implements ArticleDao {

    private final Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);

    /**
     * Constructor
     */
    public ArticleDaoImpl() {

    }

    /**
     * Reads out article by given id
     *
     * @param id article id
     * @return ArticleBean with article data
     */
    public ArticleBean getArticle(Long id) {

        ArticleBean article;

        logger.debug("Create Query and select");
        Query articleQuery = getEm().createQuery("select a from ArticleBean a where a.articleId = :articleId");
        articleQuery.setParameter("articleId", id);
        article = (ArticleBean) articleQuery.getSingleResult();

        logger.debug("Read out: " + article.getTitle() + " with: " + article.getProperties().toString());


        return article;
    }

    /**
     * Reads out a List of ArticleBeans given by set filters
     *
     * @param searchBean Filter Search Bean
     * @return List<ArticleBean>
     */
    public List<ArticleBean> getArticlesList(ArticleSearchBean searchBean) {

        logger.debug("Get EntityManager");

        List<ArticleBean> articles;

        logger.debug("Create Query and select");
        Query articleQuery = getEm().createQuery("select a from ArticleBean a where (:articleNumber is null or a.articleNumber = :articleNumber) and (:articleTitle is null or a.title = :articleTitle)");

        articleQuery.setParameter("articleNumber", searchBean.getTxtArticleNumber());
        articleQuery.setParameter("articleTitle", searchBean.getTxtArticleTitle());

        if (searchBean.getFirstPage() != null && searchBean.getPageSize() != null) {
            articleQuery.setFirstResult(searchBean.getFirstPage());
            articleQuery.setMaxResults(searchBean.getPageSize());
        }

        articles = (List<ArticleBean>) articleQuery.getResultList();
        logger.debug("Selected Articles: " + articles.size());

        for (ArticleBean article : articles) {
            logger.debug("Read out: " + article.getTitle() + " with " + article.getProperties().toString());
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

        ArticleBean article;

        logger.debug("Create Query and select");
        Query articleQuery = getEm().createQuery("select a from ArticleBean a where a.articleNumber = :articleNumber");
        articleQuery.setParameter("articleNumber", articleNumber);
        article = (ArticleBean) articleQuery.getSingleResult();

        logger.debug("Read out: " + article.getTitle() + " with " + article.getProperties().toString());


        return article;
    }

    /**
     * Create new Article without properties
     *
     * @param article ArticleBean
     */
    public void insertArticle(ArticleBean article) {
        logger.debug("Add article to database");
        getEm().persist(article);
    }

    /**
     * Create new Article with Properties
     *
     * @param articleWithProps ArticleBean with ArticleData
     * @param propertyList     List with Article Properties
     */
    public void insertArticleWithProperties(ArticleBean articleWithProps, List<ArticleProperty> propertyList) {

        logger.debug("Insert each Property to Database");

        for (ArticleProperty property : propertyList) {
            property.setProperty(articleWithProps);
            getEm().persist(property);
            articleWithProps.getProperties().add(property);
        }

        logger.debug("Insert Article");
        getEm().persist(articleWithProps);

    }

    /**
     * Removes Article from Database
     *
     * @param bean Article to Remove
     */
    public void removeArticle(ArticleBean bean) {

        logger.debug("Remove all Article Properties from Database");
        for (ArticleProperty property : bean.getProperties()) {
            getEm().remove(property);
        }

        logger.debug("Remove Article from Database");
        getEm().remove(bean);

    }

    /**
     * Updates given ArticleBean
     *
     * @param bean ArticleBean
     */
    public void updateArticle(ArticleBean bean) {

        logger.debug("Get ArticleBean to update");
        ArticleBean articleBean = getEm().merge(bean);

        logger.debug("Update all Article Properties");
        for (ArticleProperty property : articleBean.getProperties()) {
            getEm().merge(property);
        }

    }

    /**
     * Counts available Articles after selecting with given SearchBean
     *
     * @param searchBean Filter SearchBean
     * @return Long count(availableArticles)
     */
    public Long countArticlesBySearchBean(ArticleSearchBean searchBean) {

        Long count;

        Query query = getEm().createQuery("select count(a) from ArticleBean a where (:articleNumber is null or a.articleNumber = :articleNumber) and (:articleTitle is null or a.title = :articleTitle)");
        query.setParameter("articleNumber", searchBean.getTxtArticleNumber());
        query.setParameter("articleTitle", searchBean.getTxtArticleTitle());

        count = (Long) query.getSingleResult();

        return count;
    }
}
