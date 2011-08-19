package com.openshop.controller.admincp;


import com.openshop.beans.ArticleSearchBean;
import com.openshop.dao.ArticleDao;
import com.openshop.entities.ArticleBean;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private ArticleSearchBean searchBean;

    private LazyDataModel articleTable;

    //Database
    private final ArticleDao articleDao;

    /**
     * Constructor
     */
    public ArticleController() {
        searchBean = new ArticleSearchBean();
        articleDao = new ArticleDao();
    }

    /**
     * Redirect
     *
     * @return Redirects to new article creation page
     */
    @SuppressWarnings({"SameReturnValue"})
    public String addNewArticle() {
        return "newArticle";
    }

    @SuppressWarnings({"SameReturnValue"})
    public String searchArticles() {
        articleTable = null;

        return null;
    }

    @SuppressWarnings({"SameReturnValue"})
    public String resetFilter() {
        searchBean = new ArticleSearchBean();

        return null;
    }

    private void retrieveArticleTable() {

        articleTable = new LazyDataModel<ArticleBean>() {
            @Override
            public List<ArticleBean> load(int first, int pageSize, String s, SortOrder sortOrder, Map<String, String> stringStringMap) {
                searchBean.setFirstPage(first);
                searchBean.setPageSize(pageSize);

                logger.debug("SearchBean Content: " + searchBean.toString());

                List<ArticleBean> articles;

                articles = articleDao.getArticlesList(searchBean);

                return articles;
            }
        };

        articleTable.setPageSize(10);
        articleTable.setRowCount(articleDao.countArticlesBySearchBean(searchBean).intValue());

    }

    /*
    Getters and Setters
     */

    public LazyDataModel getArticleTable() {
        if (articleTable == null) {
            retrieveArticleTable();
        }
        return articleTable;
    }

    public ArticleSearchBean getSearchBean() {
        if (searchBean == null) {
            searchBean = new ArticleSearchBean();
        }
        return searchBean;
    }

    public void setSearchBean(ArticleSearchBean searchBean) {
        this.searchBean = searchBean;
    }
}
