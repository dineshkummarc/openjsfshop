package com.openshop.dao;

import com.openshop.beans.UserSearchBean;
import com.openshop.dao.impl.IDatabaseController;
import com.openshop.entities.UserBean;
import org.slf4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 18:01
 * Contact & Support: http://sharea.de
 */
public class UserDao implements IDatabaseController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(UserDao.class);
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(IDatabaseController.PERSISTENCE_UNIT_NAME);

    /**
     * Constructor
     */
    public UserDao() {

    }

    private EntityManager startConnection() {
        logger.debug("Starting Connection and returning EntityManager");
        return emf.createEntityManager();
    }

    /**
     * Add new User to Database
     *
     * @param user UserBean
     */
    public void addUser(UserBean user) {

        EntityManager em = startConnection();
        try {

            em.getTransaction().begin();

            logger.debug("Insert new User");
            em.persist(user);

            logger.debug("Commit");
            em.getTransaction().commit();

        } catch (NoResultException e) {
            logger.info("No entry were found");
        } finally {
            logger.debug("Closing EntityManager");
            em.close();
        }

    }

    /**
     * Removes given User from Database
     *
     * @param user UserBean - User to remove
     */
    public void removeUser(UserBean user) {

        EntityManager em = startConnection();
        try {

            em.getTransaction().begin();

            UserBean removeUser = em.merge(user);

            logger.debug("Remove User");
            em.remove(removeUser);

            logger.debug("Commit");
            em.getTransaction().commit();

        } catch (NoResultException e) {
            logger.info("No entry were found");
        } finally {
            logger.debug("Closing EntityManager");
            em.close();
        }


    }


    /**
     * Updates given User in Database
     *
     * @param user UserBean - User to update
     */
    public void updateUser(UserBean user) {

        EntityManager em = startConnection();
        try {

            em.getTransaction().begin();

            UserBean updateUser = em.merge(user);

            logger.debug("Update User");
            em.merge(updateUser);

            logger.debug("Commit");
            em.getTransaction().commit();

        } catch (NoResultException e) {
            logger.info("No entry were found");
        } finally {
            logger.debug("Closing EntityManager");
            em.close();
        }

    }

    /**
     * Selects User from Database with given eMail Address
     *
     * @param eMail email address
     * @return UserBean
     */
    public UserBean selectUserByEMail(String eMail) {

        EntityManager em = startConnection();
        UserBean user = null;

        try {

            logger.debug("Select User with eMail: " + eMail);

            Query userQuery = em.createQuery("select u from UserBean u where u.email = :email");
            userQuery.setParameter("email", eMail);

            user = (UserBean) userQuery.getSingleResult();

        } catch (NoResultException e) {
            logger.info("No entry were found");
        } finally {
            logger.debug("Closing EntityManager");
            em.close();
        }

        return user;
    }

    /**
     * Selects Users from Database with given SearchBean
     *
     * @param searchBean User SearchBean
     * @return List with selected Users
     */
    public List<UserBean> selectUsersByCriteria(UserSearchBean searchBean) {

        EntityManager em = startConnection();
        List<UserBean> users = new ArrayList<UserBean>();

        try {

            logger.debug("Select Users with SearchBean");

            Query userQuery = em.createQuery("select u from UserBean u where (:prename is null or u.prename = :prename) and (:surname is null or u.surname = :surname) and (:email is null or u.email = :email)");
            userQuery.setParameter("email", searchBean.geteMail());
            userQuery.setParameter("prename", searchBean.getPrename());
            userQuery.setParameter("surname", searchBean.getSurname());

            users = (List<UserBean>) userQuery.getResultList();

        } catch (NoResultException e) {
            logger.info("No entry were found");
        } finally {
            logger.debug("Closing EntityManager");
            em.close();
        }

        return users;

    }
}
