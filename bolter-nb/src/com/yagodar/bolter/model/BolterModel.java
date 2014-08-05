/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model;

import com.yagodar.bolter.model.sew.AWebSearchEngineWrapper;
import com.yagodar.bolter.model.sew.WebSearchEngineWrapperHolder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author АППДКт78М
 */
public class BolterModel implements Serializable {

    public BolterModel() {
        init();
    }
    
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

    public ArrayList<AWebSearchEngineWrapper> getWebSearchEngineWrappers() {
        ArrayList<AWebSearchEngineWrapper> wrappers = new ArrayList<>();

        for (String wrapperMark : mapWebSearchEngineWrapperMarks.keySet()) {
            wrappers.add(WebSearchEngineWrapperHolder.valueOf(wrapperMark).getInstance());
        }

        return wrappers;
    }

    public ArrayList<AWebSearchEngineWrapper> getUsedWebSearchEngineWrappers() {
        ArrayList<AWebSearchEngineWrapper> usedWrappers = new ArrayList<>();
                
        for(String wrapperMark : mapWebSearchEngineWrapperMarks.keySet()) {
            if(isUsedWebSearchEngineWrapper(wrapperMark)) {
                usedWrappers.add(WebSearchEngineWrapperHolder.valueOf(wrapperMark).getInstance());
            }
        }
        
        return usedWrappers;
    }

    public void putUsedWebSearchEngineWrapperMark(String webSearchEngineWrapperMark, boolean isUsed) {
        mapWebSearchEngineWrapperMarks.put(webSearchEngineWrapperMark, isUsed);
    }
    
    public boolean isUsedWebSearchEngineWrapper(String webSearchEngineWrapperMark) {
        return mapWebSearchEngineWrapperMarks.get(webSearchEngineWrapperMark);
    }
    
    private void init() {
        putUsedWebSearchEngineWrapperMark(WebSearchEngineWrapperHolder.YANDEX_SEW.name(), true);
        putUsedWebSearchEngineWrapperMark(WebSearchEngineWrapperHolder.GOOGLE_SEW.name(), true);
    }
    
    private String searchWord;
    private String lastAtSite;
    private Date dateFrom;
    private Date dateTo;
    private final HashMap<String, Boolean> mapWebSearchEngineWrapperMarks = new HashMap<>();
    private final HashMap<String, Boolean> mapAtSiteUrlStrs = new HashMap<>();
}
