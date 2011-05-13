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

    private boolean isNotNullOrEmpty(Object obj) {
        return (obj != null && !obj.equals(""));
    }

    public String geteMail() {
        return (isNotNullOrEmpty(eMail)) ? eMail : null;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPrename() {
        return (isNotNullOrEmpty(prename)) ? prename : null;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSurname() {
        return (isNotNullOrEmpty(surname)) ? surname : null;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
