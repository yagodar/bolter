/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model;

import com.yagodar.bolter.model.sew.AWebSearchEngineWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author АППДКт78М
 */
public class BolterModel {
    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
     
    public String getLastAtSite() {
        return lastAtSite;
    }

    public void setLastAtSite(String lastAtSite) {
        this.lastAtSite = lastAtSite;
    }

    public Set<String> getAtSiteUrlStrs() {
        return mapAtSiteUrlStrs.keySet();
    }

    public ArrayList<String> getUsedAtSiteUrlStrs() {
        ArrayList<String> usedAtSiteUrlStrs= new ArrayList<>();
                
        for(String atSiteUrlStr : mapAtSiteUrlStrs.keySet()) {
            if(isUsedAtSiteUrlStr(atSiteUrlStr)) {
                usedAtSiteUrlStrs.add(atSiteUrlStr);
            }
        }
        
        return usedAtSiteUrlStrs;
    }
    
    public void putUsedAtSiteUrlStr(String atSiteUrlStr, boolean isUsed) {
        mapAtSiteUrlStrs.put(atSiteUrlStr, isUsed);
    }
    
    public boolean isUsedAtSiteUrlStr(String atSiteUrlStr) {
        return mapAtSiteUrlStrs.get(atSiteUrlStr);
    }
    
    public void clearAtSiteUrlStr() {
        mapAtSiteUrlStrs.clear();
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Set<AWebSearchEngineWrapper> getWebSearchEngineWrappers() {
        return mapWebSearchEngineWrappers.keySet();
    }
    
    public ArrayList<AWebSearchEngineWrapper> getUsedWebSearchEngineWrappers() {
        ArrayList<AWebSearchEngineWrapper> usedWrappers= new ArrayList<>();
                
        for(AWebSearchEngineWrapper wrapper : mapWebSearchEngineWrappers.keySet()) {
            if(isUsedWebSearchEngineWrapper(wrapper)) {
                usedWrappers.add(wrapper);
            }
        }
        
        return usedWrappers;
    }

    public void putUsedWebSearchEngineWrapper(AWebSearchEngineWrapper webSearchEngineWrapper, boolean isUsed) {
        mapWebSearchEngineWrappers.put(webSearchEngineWrapper, isUsed);
    }
    
    public boolean isUsedWebSearchEngineWrapper(AWebSearchEngineWrapper webSearchEngineWrapper) {
        return mapWebSearchEngineWrappers.get(webSearchEngineWrapper);
    }
        
    private String searchWord;
    private String lastAtSite;
    private Date dateFrom;
    private Date dateTo;
    private final HashMap<AWebSearchEngineWrapper, Boolean> mapWebSearchEngineWrappers = new HashMap<>();
    private final HashMap<String, Boolean> mapAtSiteUrlStrs = new HashMap<>();
}
