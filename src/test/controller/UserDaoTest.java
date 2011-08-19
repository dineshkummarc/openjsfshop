package controller;

import openshop.beans.UserLevel;
import openshop.beans.UserSearchBean;
import openshop.dao.UserDao;
import openshop.entities.UserBean;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 18:03
 * Contact & Support: http://sharea.de
 */
public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = new UserDao();

        UserBean newUser = new UserBean();
        newUser.setLevel(UserLevel.USER);
        newUser.setEmail("patrick@sharea.de");
        newUser.setPassword("start123");
        newUser.setPrename("Patrick");
        newUser.setSurname("T.");

        userDao.addUser(newUser);

        newUser = new UserBean();
        newUser.setPrename("Patrick");

        userDao.addUser(newUser);
    }

    @Test
    public void selectUserByEMail() {

        UserBean user = userDao.selectUserByEMail("patrick@sharea.de");

        assertTrue(user.getPrename().equals("Patrick"));

    }

    @Test
    public void selectUsersByCriteria() {

        UserSearchBean searchBean = new UserSearchBean();
        searchBean.setPrename("Patrick");

        List<UserBean> users = userDao.selectUsersByCriteria(searchBean);

        assertTrue(users.size() == 2);

    }

    @Test
    public void updateUser() {

        UserBean user = userDao.selectUserByEMail("patrick@sharea.de");
        user.setSurname("Mango");

        userDao.updateUser(user);

        user = userDao.selectUserByEMail("patrick@sharea.de");
        assertTrue(user.getSurname().equals("Mango"));

    }

    @Test
    public void removeUser() {

        UserBean user = userDao.selectUserByEMail("patrick@sharea.de");
        userDao.removeUser(user);

        user = userDao.selectUserByEMail("patrick@sharea.de");
        assertTrue(user == null);

    }

}
