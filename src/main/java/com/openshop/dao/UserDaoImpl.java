package com.openshop.dao;

import com.openshop.beans.UserSearchBean;
import com.openshop.entities.UserBean;
import org.slf4j.Logger;

import javax.persistence.Query;
import java.util.List;


/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 18:01
 * Contact & Support: http://sharea.de
 */
public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserDaoImpl.class);

    /**
     * Constructor
     */
    public UserDaoImpl() {

    }

    /**
     * Add new User to Database
     *
     * @param user UserBean
     */
    public void addUser(UserBean user) {

        logger.debug("Insert new User");
        getEm().merge(user);

    }

    /**
     * Removes given User from Database
     *
     * @param user UserBean - User to remove
     */
    public void removeUser(UserBean user) {
        logger.debug("Remove User");
        getEm().remove(user);
    }


    /**
     * Updates given User in Database
     *
     * @param user UserBean - User to update
     */
    public void updateUser(UserBean user) {

        logger.debug("Update User");
        getEm().merge(user);
    }

    /**
     * Selects User from Database with given eMail Address
     *
     * @param eMail email address
     * @return UserBean
     */
    public UserBean selectUserByEMail(String eMail) {

        UserBean user;

        logger.debug("Select User with eMail: " + eMail);

        Query userQuery = getEm().createQuery("select u from UserBean u where u.email = :email");
        userQuery.setParameter("email", eMail);

        user = (UserBean) userQuery.getSingleResult();


        return user;
    }

    /**
     * Selects Users from Database with given SearchBean
     *
     * @param searchBean User SearchBean
     * @return List with selected Users
     */
    @SuppressWarnings({"unchecked"})
    public List<UserBean> selectUsersByCriteria(UserSearchBean searchBean) {

        List<UserBean> users;

        logger.debug("Select Users with SearchBean");

        Query userQuery = getEm().createQuery("select u from UserBean u where (:firstName is null or u.firstName = :firstName) and (:surname is null or u.surname = :surname) and (:email is null or u.email = :email)");
        userQuery.setParameter("email", searchBean.geteMail());
        userQuery.setParameter("firstName", searchBean.getPrename());
        userQuery.setParameter("surname", searchBean.getSurname());

        users = (List<UserBean>) userQuery.getResultList();

        return users;

    }
}
