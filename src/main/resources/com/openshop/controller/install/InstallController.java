package com.openshop.controller.install;

import com.openshop.beans.install.InstallSettings;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class InstallController {

    private final InstallSettings settings = new InstallSettings();
    private PropertiesConfiguration config;

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

        //ToDo: Finish it :)
        if (getSettings().getDbDBMS().equals("mysql")) {
            config.setProperty("database.dbms", getSettings().getDbDBMS());
        }
        //...

        return "stepAdministrator";
    }

    private void setDefaultInstallSettings() {

        getSettings().setDbDBMS(config.getString("database.dbms"));
        getSettings().setDbHostname(config.getString("database.hostname"));
        getSettings().setDbUsername(config.getString("database.username"));
        getSettings().setDbPassword(config.getString("database.password"));
        getSettings().setDbSchema(config.getString("database.schema"));

    }

    public InstallSettings getSettings() {
        return settings;
    }

}
