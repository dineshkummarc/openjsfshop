package controller;

import com.openshop.beans.UserLevel;
import com.openshop.beans.UserSearchBean;
import com.openshop.dao.UserDao;
import com.openshop.entities.UserBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 18:03
 * Contact & Support: http://sharea.de
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-jpa.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {

        UserBean newUser = new UserBean();
        newUser.setLevel(UserLevel.USER);
        newUser.setEmail("patrick@sharea.de");
        newUser.setPassword("start123");
        newUser.setFirstName("Patrick");
        newUser.setSurname("T.");

        userDao.addUser(newUser);

        newUser = new UserBean();
        newUser.setFirstName("Patrick");

        userDao.addUser(newUser);
    }

    @Test
    public void selectUserByEMail() {

        UserBean user = userDao.selectUserByEMail("patrick@sharea.de");

        assertTrue(user.getFirstName().equals("Patrick"));

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
