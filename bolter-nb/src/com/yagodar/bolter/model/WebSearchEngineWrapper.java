/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 *
 * @author АППДКт78М
 */
public abstract class WebSearchEngineWrapper {

    public WebSearchEngineWrapper(String name, String url, String postParamsStart) {
       this.name = name;
       this.url = url;
       this.postParamsStart = postParamsStart;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    
    public String genSearchQuery(String searchWord, ArrayList<String> listSites) {
        String searchQuery = getUrl();
        
        try {
            searchQuery += URL_PATH_SEP + URL_POST_PARAMS_START_SEP + postParamsStart + URLEncoder.encode(appendSearchAtSites(searchWord, listSites), URL_ENC);
        } catch (UnsupportedEncodingException exNotUsed) {}
        
        return searchQuery;
    }
    
    private String appendSearchAtSites(String searchWord, ArrayList<String> listSites) {
        if(searchWord == null) {
            searchWord = new String();
        }
        
        if(listSites != null && listSites.size() > 0) {
            searchWord += SMB_SPACE + SMB_LEFT_BKT;
            
            for(String site : listSites) {
                searchWord += SEARCH_QUERY_AT_SITE_PARAM + site + SEARCH_QUERY_AT_SITE_PARAM_SEP;
            }
            
            searchWord += SMB_RIGHT_BKT;
        }
        
        return searchWord;
    }
    
    private final String name;
    private final String url;
    private final String postParamsStart;
    
    static final private String URL_ENC = "UTF-8";
    static final private String URL_PATH_SEP = "/";
    static final private String URL_POST_PARAMS_START_SEP = "?";
    
    static final private String SMB_SPACE = " ";
    static final private String SMB_LEFT_BKT = "(";
    static final private String SMB_RIGHT_BKT = ")";
    
    static final private String SEARCH_QUERY_AT_SITE_PARAM = "site:";
    static final private String SEARCH_QUERY_AT_SITE_PARAM_SEP = " | ";
}
