package com.openshop.controller.install;

import com.openshop.beans.install.InstallSettings;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class InstallController {

    private final InstallSettings settings = new InstallSettings();

    public String setLanguage() {

        PropertiesConfiguration config = null;
        try {

            config = new PropertiesConfiguration("config.properties");
            config.setProperty("locale", getSettings().getLanguageSelection());
            config.save();

        } catch (ConfigurationException e) {
            e.printStackTrace();  //todo: Exception handling
        }

        return "stepDatabase";
    }

    public InstallSettings getSettings() {
        return settings;
    }

}
