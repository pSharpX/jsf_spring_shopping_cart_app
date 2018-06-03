/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author CHRISTIAN
 */
@Component
@Scope(value = "session")
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String localeCode;
    private String currentLocaleCode;
    private static final Map<String, Object> countries;

    static {
        countries = new LinkedHashMap<>();
        countries.put("English", Locale.ENGLISH);
        countries.put("Spanish", new Locale("es"));        
    }
        

    public LanguageBean() {		
		this.localeCode = this.getCurrentLocaleCode();
	}

	public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
    
    public String getCurrentLocaleCode() {
    	return FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
    }

	public void setCurrentLocaleCode(String currentLocaleCode) {
		this.currentLocaleCode = currentLocaleCode;
	}    
    
}
