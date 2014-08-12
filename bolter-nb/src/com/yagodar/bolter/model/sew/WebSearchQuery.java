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

package com.yagodar.bolter.model.sew;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
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
