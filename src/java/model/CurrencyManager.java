/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author David
 */
@Named("currencyManager")
@SessionScoped
public class CurrencyManager implements Serializable {
    
    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CurrencyFacade currencyFacade;
    @Inject
    private Conversation conversation;
    private String fromCurrency;
    private String toCurrency;
    private double amount=1;
    private double result=-1;
    private Map<String, String> allCurrencies=new HashMap<>();
    private double convertionResult;
    
    @PostConstruct
    public void initialize() {
//        CurrencyDTO cu;
//        cu=currencyFacade.createCurrency("SEK", "kr", 8.52540);
//        cu=currencyFacade.createCurrency("USD", "$", 1);
//        cu=currencyFacade.createCurrency("EUR", "€", 0.92072);
//        cu=currencyFacade.createCurrency("GBP", "£", 0.66587);
        
        if(allCurrencies.isEmpty()){
            LinkedHashMap<String, CurrencyDTO> list=currencyFacade.getCurrencies();
            for(CurrencyDTO cd : list.values()){
                allCurrencies.put(cd.getCurrencyName(), cd.getCurrencyName());
            }
        }
    }
    
    public void convertCurrencies(){
        CurrencyDTO from=(CurrencyDTO) currencyFacade.getCurrency(fromCurrency);
        CurrencyDTO to=(CurrencyDTO) currencyFacade.getCurrency(toCurrency);
        if(from!=null && to!=null){
            result = (amount / from.getCurrencyValue()) * to.getCurrencyValue();
        }
    }
    
    public Map<String, String> getAllCurrencies() {
        return allCurrencies;
    }
    
    public String getFromCurrency() {
        return fromCurrency;
    }
    
    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
    
    public String getToCurrency() {
        return toCurrency;
    }
    
    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
    
    public double getResult() {
        return result;
    }
    
    public void setResult(double result) {
        this.result = result;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}

//        CurrencyDTO cu=currencyFacade.createCurrency("Kronor", "kr", 7);
//        if(cu!=null)
//            allCurrencies.put(cu.getCurrencyName(), cu.getCurrencyName());
//        cu=currencyFacade.createCurrency("Dollar", "$", 14);
//        if(cu!=null)
//            allCurrencies.put(cu.getCurrencyName(), cu.getCurrencyName());
//        cu=currencyFacade.createCurrency("Euro", "€", 9);
//        if(cu!=null)
//            allCurrencies.put(cu.getCurrencyName(), cu.getCurrencyName());