package controller;

import com.openshop.beans.ArticleSearchBean;
import com.openshop.dao.ArticleDao;
import com.openshop.entities.ArticleBean;
import com.openshop.entities.ArticleProperty;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ArticleControllerTest {

    private Logger logger = Logger.getLogger(ArticleControllerTest.class);

    private ArticleDao articleDao;

    @Before
    public void before() {

        articleDao = new ArticleDao();
        ArticleBean bean = new ArticleBean("Coffee Cup", "Bring me some coffee here!");
        bean.setArticleNumber(1234L);

        articleDao.insertArticle(bean);

        ArticleBean articleWithProps = new ArticleBean("Sneakers Shoes", "Beatiful lightweight Shoes for young people");
        articleWithProps.setArticleNumber(2341L);

        List<ArticleProperty> propertyList = new ArrayList<ArticleProperty>();
        ArticleProperty test1 = new ArticleProperty("color", "red");
        test1.setSpecialPrice(14.23);
        propertyList.add(test1);

        ArticleProperty test2 = new ArticleProperty("color", "green");
        test2.setSpecialPrice(13.42);
        propertyList.add(test2);


        articleDao.insertArticleWithProperties(articleWithProps, propertyList);

    }

    @Test
    public void selectArticleList() {

        List<ArticleBean> articles = articleDao.getArticlesList(new ArticleSearchBean());

        assertTrue(articles.size() == 2);

    }

    @Test
    public void selectArticlesByTitle() {
        ArticleSearchBean searchBean = new ArticleSearchBean();
        searchBean.setTxtArticleTitle("Sneakers Shoes");

        List<ArticleBean> articles = articleDao.getArticlesList(searchBean);

        assertTrue(articles.get(0).getTitle().equals("Sneakers Shoes"));
    }

    @Test
    public void selectArticleByArticleNo() {
        ArticleBean article = articleDao.getArticleByArticleNumber(2341L);
        assertTrue(article.getTitle().equals("Sneakers Shoes"));
    }

    @Test
    public void removeArticleByBean() {
        ArticleBean article = articleDao.getArticleByArticleNumber(2341L);
        articleDao.removeArticle(article);

        article = articleDao.getArticleByArticleNumber(2341L);
        assertTrue(article == null);
    }

}
