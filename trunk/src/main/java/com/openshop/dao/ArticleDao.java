package com.openshop.dao;

import com.openshop.beans.ArticleSearchBean;
import com.openshop.entities.ArticleBean;
import com.openshop.entities.ArticleProperty;

import java.util.List;

/**
 * User: PatrickTrautmann
 * Date: 19.08.11
 * Time: 16:06
 */
public interface ArticleDao {

    public ArticleBean getArticle(Long id);

    public List<ArticleBean> getArticlesList(ArticleSearchBean searchBean);

    public ArticleBean getArticleByArticleNumber(Long articleNumber);

    public void insertArticle(ArticleBean article);

    public void insertArticleWithProperties(ArticleBean articleWithProps, List<ArticleProperty> propertyList);

    public void updateArticle(ArticleBean bean);

    public Long countArticlesBySearchBean(ArticleSearchBean searchBean);
}
