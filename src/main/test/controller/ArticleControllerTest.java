package controller;

import com.openshop.entities.ArticleBean;
import com.openshop.entities.ArticleProperty;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ArticleControllerTest {

    private Logger logger = Logger.getLogger(ArticleControllerTest.class);

    private EntityManagerFactory emf;
    private EntityManager em;
    private String PERSISTENCE_UNIT_NAME = "openjsfdb";

    @Before
    public void before() {

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();

        em.getTransaction().begin();
        ArticleBean bean = new ArticleBean("Sneakers Shoes", "Beatiful lightweight Shoes for young people");
        em.persist(bean);

        List<ArticleProperty> props = new ArrayList<ArticleProperty>();

        ArticleProperty test1 = new ArticleProperty("color", "red");
        test1.setSpecialPrice(14.23);
        test1.setProperty(bean);
        ArticleProperty test2 = new ArticleProperty("color", "green");
        test2.setSpecialPrice(13.42);
        test2.setProperty(bean);
        em.persist(test1);
        em.persist(test2);

        bean.getProperties().add(test1);
        bean.getProperties().add(test2);
        em.persist(bean);

        em.getTransaction().commit();
    }

    @Test
    public void checkInputs() {

        List<ArticleBean> articles = em.createQuery("select a from ArticleBean a where a.articleId = 1").getResultList();

        assertTrue(articles.size() == 1);
        assertTrue(articles.get(0).getProperties().size() != 0);

        System.out.println(articles.get(0).getProperties().size());

        assertTrue(articles.get(0).getProperties().get(0).getValue1().equals("color"));


    }

    @Test
    public void selectByProperty() {

    }

    @After
    public void closeAll() {
        em.close();
    }


}
