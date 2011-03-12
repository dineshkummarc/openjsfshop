package com.openshop.controller.install;

import com.openshop.beans.install.InstallSettings;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@ManagedBean
@SessionScoped
public class InstallController {

    private final InstallSettings settings = new InstallSettings();
    private PropertiesConfiguration config;

    private SqlSession session;

    public InstallController() {

        try {
            config = new PropertiesConfiguration("config.properties");
            config.setReloadingStrategy(new FileChangedReloadingStrategy());

            setDefaultInstallSettings();
        } catch (Exception e) {
            e.printStackTrace(); //todo: Exception Handling
        }

    }

    public String setLanguage() {

        try {

            config.setProperty("locale", getSettings().getLanguageSelection());
            config.save();

        } catch (ConfigurationException e) {
            e.printStackTrace();  //todo: Exception handling
        }

        return "stepDatabase";
    }

    public String setDatabase() {

        if (!getSettings().getDbHostname().equals("")) {
            config.setProperty("database.hostname", getSettings().getDbHostname());
        }
        if (!getSettings().getDbUsername().equals("")) {
            config.setProperty("database.username", getSettings().getDbUsername());
        }
        if (!getSettings().getDbPassword().equals("")) {
            config.setProperty("database.password", getSettings().getDbPassword());
        }
        if (!getSettings().getDbSchema().equals("")) {
            config.setProperty("database.schema", getSettings().getDbSchema());
        }

        try {
            config.save();
            System.out.println("DB Passwort: " + config.getString("database.password"));
        } catch (ConfigurationException e) {
            e.printStackTrace();  //todo: Exception Handling
        }

        if (checkDatabaseConnection()) {

            try {
                Reader reader = Resources.getResourceAsReader("com/openshop/dao/mappings/configuration.xml");
                SqlSessionFactory mapClient = new SqlSessionFactoryBuilder().build(reader);

                session = mapClient.openSession(DriverManager.getConnection("jdbc:mysql://" + getSettings().getDbHostname() + ":3306/" + getSettings().getDbSchema(), getSettings().getDbUsername(), getSettings().getDbPassword()));

                //Start Creating Tables
                session.insert("createArticles");
                session.commit(true);


            } catch (Exception e) {
                e.printStackTrace(); // todo: Exception Handling
            } finally {
                session.close();
            }

            return "stepAdministrator";
        }

        return null;
    }

    public String setAdministrator() {
        return "stepComplete";
    }

    private void setDefaultInstallSettings() {

        getSettings().setDbDBMS(config.getString("database.dbms"));
        getSettings().setDbHostname(config.getString("database.hostname"));
        getSettings().setDbUsername(config.getString("database.username"));
        getSettings().setDbPassword(config.getString("database.password"));
        getSettings().setDbSchema(config.getString("database.schema"));

    }

    private boolean checkDatabaseConnection() {

        Connection conn = null;
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://" + getSettings().getDbHostname() + ":3306/" + getSettings().getDbSchema(), getSettings().getDbUsername(), getSettings().getDbPassword());
        } catch (Exception e) {
            e.printStackTrace(); //todo: Exception Handling
        } finally {
            try {
                if (conn != null)
                    conn.close();
                else {
                    context.addMessage(null, new FacesMessage("Error", "Couldn't connect to Database."));
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();  //todo: Exception Handling
            }
        }
        return true;
    }

    public InstallSettings getSettings() {
        return settings;
    }

}
