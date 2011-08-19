package controller;


import openshop.controller.admincp.LoginController;
import openshop.entities.UserBean;
import openshop.util.MD5;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.Assert.assertTrue;

public class LoginControllerTest {

    Logger logger = Logger.getLogger(LoginControllerTest.class);

    LoginController login = new LoginController();

    private EntityManagerFactory emf;
    private EntityManager em;
    private String PERSISTENCE_UNIT_NAME = "openjsfdb";

    /**
     * Inserts test Administrator to Database
     */
    @Before
    public void insertTestData() {

        logger.debug("Init LoginControllerTest");
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(new UserBean("developer@sharea.de", MD5.makeMD5("test")));
        em.getTransaction().commit();

    }

    /**
     * Checks functionality for Login Button
     */
    @Test
    public void checkLogin() {

        assertTrue("Check if User exists", login.checkLogin("developer@sharea.de", "test"));

    }

    /**
     * Removes added TestData
     */
    @After
    public void removeTestData() {

        em.getTransaction().begin();
        em.remove(new UserBean("developer@sharea.de", MD5.makeMD5("test")));
        em.getTransaction().commit();

        em.close();

    }
}
