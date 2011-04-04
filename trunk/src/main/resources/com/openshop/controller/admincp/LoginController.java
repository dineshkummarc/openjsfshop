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

    private Logger logger = Logger.getLogger(LoginController.class);

    private EntityManagerFactory emf;
    private EntityManager em;
    private String PERSISTENCE_UNIT_NAME = "openjsfdb";

    /**
     * Constructor
     */
    public LoginController() {

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();

    }

    /**
     * EventHandler for Login Button at login.xhtml
     *
     * @return if success = "dashboard" else null
     */
    public String buttonLogIn() {


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

        Query emQuery = em.createQuery("select u from UserBean u where u.email = :email and u.password = MD5(:password)");
        emQuery.setParameter("email", email);
        emQuery.setParameter("password", password);

        UserBean user = (UserBean) emQuery.getSingleResult();

        return (user.getEmail().equals(email));
    }

}
