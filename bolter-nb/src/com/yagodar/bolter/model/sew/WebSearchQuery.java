/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model.sew;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author АППДКт78М
 */
public class WebSearchQuery {
    public WebSearchQuery(String url, String key) {
        this.url = url;
        this.key = key;
    }
    
    public void putPostParam(String param, String value) {
        postParams.put(param, value);
    }
    
    public String getPostParam(String param) {
        return postParams.get(param);
    }
    
    public String getQuery() {
        String result = url + key;
        
        Iterator<String> it = postParams.keySet().iterator();
                
        if(it.hasNext()) {
            result += SMB_QUESTION + genParamValueStr(it.next());
        }
        
        while(it.hasNext()) {
            result += SMB_AMPERSAND + genParamValueStr(it.next());
        }
        
        return result;
    }
    
    private String genParamValueStr(String param) {
        return param + SMB_EQUAL + getPostParam(param);
    }
    
    private final String url;
    private final String key;
    private final HashMap<String, String> postParams = new HashMap<>();
    
    
    static final private String SMB_QUESTION = "?";
    static final private String SMB_AMPERSAND = "&";
    static final private String SMB_EQUAL = "=";
}
