package com.openshop.controller.admincp;

import com.openshop.entities.UserBean;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class);

    private final EntityManager em;

    /**
     * Constructor
     */
    public LoginController() {

        logger.debug("Started LoginController");
        String PERSISTENCE_UNIT_NAME = "openjsfdb";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();

    }

    /**
     * EventHandler for Login Button at login.xhtml
     *
     * @return if success = "dashboard" else null
     */
    public String buttonLogIn() {

        //Login Button got clicked
        logger.debug("Login Button clicked");
        return null;
    }

    /*
        Selection Methods
     */

    /**
     * Checks if user and password combo are existing
     *
     * @param email    Administrator Username
     * @param password Administrator Password
     * @return If User were successfully found: true
     */
    public boolean checkLogin(String email, String password) {

        //create query
        logger.debug("Query user from database");
        Query emQuery = em.createQuery("select u from UserBean u where u.email = :email and u.password = MD5(:password)");
        emQuery.setParameter("email", email);
        emQuery.setParameter("password", password);

        //Select the user from database
        UserBean user = (UserBean) emQuery.getSingleResult();

        //if selected equals database email = success
        return (user.getEmail().equals(email));
    }

}
