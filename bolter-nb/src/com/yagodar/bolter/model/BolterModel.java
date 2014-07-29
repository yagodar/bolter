/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model;

import com.yagodar.bolter.model.sew.AWebSearchEngineWrapper;
import java.util.ArrayList;
import java.util.Calendar;
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
     
    public String getLastSite() {
        return lastSite;
    }

    public void setLastSite(String lastSite) {
        this.lastSite = lastSite;
    }

    public ArrayList<String> getListSites() {
        return listSites;
    }

    public void setListSites(ArrayList<String> listSites) {
        this.listSites = listSites;
    }

    public Calendar getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Calendar dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Calendar getDateTo() {
        return dateTo;
    }

    public void setDateTo(Calendar dateTo) {
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

    public void setUsedWebSearchEngineWrapper(AWebSearchEngineWrapper webSearchEngineWrapper, boolean isUsed) {
        this.mapWebSearchEngineWrappers.put(webSearchEngineWrapper, isUsed);
    }
    
    public boolean isUsedWebSearchEngineWrapper(AWebSearchEngineWrapper webSearchEngineWrapper) {
        return this.mapWebSearchEngineWrappers.get(webSearchEngineWrapper);
    }
        
    private String searchWord;
    private String lastSite;
    private ArrayList<String> listSites;
    private Calendar dateFrom;
    private Calendar dateTo;
    private final HashMap<AWebSearchEngineWrapper, Boolean> mapWebSearchEngineWrappers = new HashMap<>();
}
