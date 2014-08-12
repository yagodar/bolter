/**
 * Bolter. Interactive web-search. 
 * <http://vk.com/bolter_app>
 * 
 * Copyright (C) 2014  Yagodarov Andrey <yagodarov.a.e@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
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
