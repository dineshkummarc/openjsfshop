package com.openshop.beans;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class SessionBean {

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public SessionBean() {

        try {

            Configuration config = new PropertiesConfiguration("config.properties");
            String language = config.getString("locale");

            setLanguage(language);

        } catch (ConfigurationException e) {
            e.printStackTrace(); //todo: Exception Handling
        }

    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}
