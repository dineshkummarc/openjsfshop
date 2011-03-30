package controller;


import com.openshop.beans.UserLevel;
import com.openshop.controller.admincp.LoginController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class LoginControllerTest {

    LoginController login = new LoginController();
    TestHelper testHelper = new TestHelper();

    /**
     * Inserts test Administrator to Database
     */
    @Before
    public void insertTestData() {

        testHelper.connect();

        final String insertStatement = "INSERT INTO user (level, email, password, prename, surname, street, streetNumber, place, country) VALUES (" + UserLevel.ADMIN.getLevel() + ", 'developer@sharea.de', MD5('test'), 'Developer', 'Account', '', 0, 'IDE', 'Java')";
        testHelper.execute(insertStatement);
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

        testHelper.execute("DELETE FROM user WHERE email = 'developer@sharea.de'");
        testHelper.disconnect();

    }
}
