/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author David
 */
@FacesConverter("myConverter")
public class CurrencyConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Currency c=new Currency();
        c.setCurrencyName(submittedValue);
        return c;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        return ((CurrencyDTO)modelValue).getCurrencyName();
    }

}
