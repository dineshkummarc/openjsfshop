package com.openshop.beans;

/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 18:15
 * Contact & Support: http://sharea.de
 */
public class UserSearchBean {

    private String eMail;
    private String prename;
    private String surname;

    public UserSearchBean() {

    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
