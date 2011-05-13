package com.openshop.beans;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class SessionBean {

    private Logger logger = LoggerFactory.getLogger(SessionBean.class);
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private PropertiesConfiguration config;

    public SessionBean() {

        try {
            config = new PropertiesConfiguration("config.properties");
            String language = config.getString("locale");
            config.setReloadingStrategy(new FileChangedReloadingStrategy());

            setLanguage(language);

        } catch (ConfigurationException e) {
            logger.error(e.getMessage());
        }

    }

    public Locale getLocale() {

        if (!config.getString("locale").equals(locale.getLanguage())) {
            setLanguage(config.getString("locale"));
        }
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
