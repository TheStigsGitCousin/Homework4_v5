/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author David
 */
@Stateless
public class CurrencyFacade {
    
    @PersistenceContext(unitName = "cPU")
    private EntityManager em;
    
    public CurrencyDTO createCurrency(String currencyName, String currencyAbbreviation, double currencyValue) {
        try{
            CurrencyDTO cu=new Currency(currencyName, currencyAbbreviation, currencyValue);
            em.persist(cu);
            return cu;
        }catch(Exception e){}
        return null;
    }
    
    public CurrencyDTO getCurrency(String currencyName){
        System.out.println("Name: "+currencyName);
        return em.find(Currency.class, currencyName);
    }
    
    public LinkedHashMap<String, CurrencyDTO> getCurrencies(){
        Query query = em.createQuery("SELECT e FROM Currency e");
        LinkedHashMap<String, CurrencyDTO> map=new LinkedHashMap<>();
        Collection<CurrencyDTO> col=(Collection<CurrencyDTO>) query.getResultList();
        for(CurrencyDTO c : col){
            map.put(c.getCurrencyName(), c);
        }
        return map;
    }
}
