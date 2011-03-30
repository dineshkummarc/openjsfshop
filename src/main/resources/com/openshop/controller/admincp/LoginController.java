package com.openshop.controller.admincp;

import com.openshop.beans.UserBean;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.faces.bean.ManagedBean;
import java.io.Reader;

@ManagedBean
public class LoginController {

    private SqlSession session;

    /**
     * Constructor
     */
    public LoginController() {

    }

    /**
     * Connects to Database
     */
    public void startConnection() {

        try {

            Reader reader = Resources.getResourceAsReader("com/openshop/dao/mappings/configuration.xml");
            SqlSessionFactory mapClient = new SqlSessionFactoryBuilder().build(reader);

            session = mapClient.openSession();


        } catch (Exception e) {
            e.printStackTrace();
        }

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

        Integer exists;

        try {
            startConnection();
            exists = (Integer) session.selectOne("checkAdminLogin", new UserBean(email, password));
        } finally {
            session.close();
        }

        return (exists != 0);
    }

}
