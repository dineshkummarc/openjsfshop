package com.openshop.dao;

import com.openshop.beans.UserSearchBean;
import com.openshop.entities.UserBean;

import java.util.List;

/**
 * User: PatrickTrautmann
 * Date: 19.08.11
 * Time: 16:10
 */
public interface UserDao {

    public void addUser(UserBean user);
    public void removeUser(UserBean user);
    public void updateUser(UserBean user);
    public UserBean selectUserByEMail(String eMail);
    public List<UserBean> selectUsersByCriteria(UserSearchBean searchBean);
}
