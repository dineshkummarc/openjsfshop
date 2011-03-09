package com.openshop.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.Locale;

public class LocaleConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.toString().equals(s)) {
                return locale;
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
