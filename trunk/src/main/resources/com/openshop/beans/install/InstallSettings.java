package com.openshop.beans.install;


public class InstallSettings {

    private String languageSelection;
    private String dbHostname;
    private String dbUsername;
    private String dbPassword;
    private String dbDBMS;
    private String dbSchema;

    public InstallSettings() {
    }

    public String getLanguageSelection() {
        return languageSelection;
    }

    public void setLanguageSelection(String languageSelection) {
        this.languageSelection = languageSelection;
    }

    public String getDbHostname() {
        return dbHostname;
    }

    public void setDbHostname(String dbHostname) {
        this.dbHostname = dbHostname;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbDBMS() {
        return dbDBMS;
    }

    public void setDbDBMS(String dbDBMS) {
        this.dbDBMS = dbDBMS;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }
}
