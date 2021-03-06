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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
 */
public abstract class AWebSearchEngineWrapper {
    public AWebSearchEngineWrapper(String name, String url, String key) {
        this.name = name;
        this.url = url;
        this.key = key;
    }
    
    @Override
    public String toString() {
        return name + " (" + url + ")";
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    
    public String getMark() {
        return mark;
    }
    
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    
    public String genSearchQuery(String searchWord, ArrayList<String> listAtSites, Date dateFrom, Date dateTo) {
        WebSearchQuery webSearchQuery = new WebSearchQuery(url, key);
        
        applyFilter(SearchFilter.SEARCH_WORD, webSearchQuery, searchWord);
        applyFilter(SearchFilter.SEARCH_AT_SITES, webSearchQuery, listAtSites);
        applyFilter(SearchFilter.SEARCH_DATE, webSearchQuery, new Date[] {dateFrom, dateTo});
        
        return webSearchQuery.getQuery();
    }
        
    protected void applyFilter(SearchFilter filter, WebSearchQuery webSearchQuery, Object filterData) {
        String additPostParamValue;
        
        switch (filter) {
            case SEARCH_WORD:
                additPostParamValue = new String();
                
                String searchWord = (String) filterData;
                if(searchWord == null) {
                    searchWord = new String();
                }
                additPostParamValue += searchWord;
                
                addToSearchWordPostParamValue(webSearchQuery, additPostParamValue);
                break;
            case SEARCH_AT_SITES:
                ArrayList<String> listSites = (ArrayList<String>) filterData;

                if (listSites != null && listSites.size() > 0) {
                    additPostParamValue = new String();
                    
                    additPostParamValue += SMB_LEFT_BKT;
                    for (String site : listSites) {
                        additPostParamValue += SEARCH_AT_SITE_DEF_OP + site + SEARCH_AT_SITE_DEF_OP_SEP;
                    }
                    additPostParamValue += SMB_RIGHT_BKT;

                    addToSearchWordPostParamValue(webSearchQuery, additPostParamValue);
                }
                break;
            case SEARCH_DATE:
                additPostParamValue = new String();
                
                Date dateFrom = ((Date[]) filterData)[0];
                Date dateTo = ((Date[]) filterData)[1];
                
                if (dateFrom != null || dateTo != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(SEARCH_DATE_DEF_OP_PATTERN);
                            
                    additPostParamValue += SEARCH_DATE_DEF_OP;

                    if (dateFrom != null && dateTo != null && dateFrom.before(dateTo)) {
                        additPostParamValue += dateFormat.format(dateFrom) + SEARCH_DATE_DEF_OP_SEP;
                        additPostParamValue += dateFormat.format(dateTo);
                    } else if (dateFrom != null && (dateTo == null || !dateFrom.before(dateTo))) {
                        additPostParamValue += SMB_BIGGER + dateFormat.format(dateFrom);
                    } else if (dateTo != null && dateFrom == null) {
                        additPostParamValue += SMB_SMALLER + dateFormat.format(dateTo);
                    }

                    addToSearchWordPostParamValue(webSearchQuery, additPostParamValue);
                }
                break;
            default:
                break;
        }
    }
    
    protected String urlEncode(String s) {
        String result = new String();
                
        try {
            result += URLEncoder.encode(s, URL_ENC);
        } catch (UnsupportedEncodingException exNotUsed) {}

        return result;
    }
    
    abstract protected String getPostParamName(SearchFilter filter);
    
    private void addToSearchWordPostParamValue(WebSearchQuery webSearchQuery, String addit) {
        String curPostParamValue;
        String additPostParamValue = new String();

        curPostParamValue = webSearchQuery.getPostParam(getPostParamName(SearchFilter.SEARCH_WORD));
        
        if(curPostParamValue == null) {
            curPostParamValue = new String();
        }
        
        if (!curPostParamValue.isEmpty()) {
            additPostParamValue += SMB_SPACE;
        }
        
        additPostParamValue += addit;

        additPostParamValue = urlEncode(additPostParamValue);
        webSearchQuery.putPostParam(getPostParamName(SearchFilter.SEARCH_WORD), curPostParamValue + additPostParamValue);
    }
    
    private String mark;
    private final String name;
    private final String url;
    private final String key;
    
    protected enum SearchFilter {
        SEARCH_WORD,
        SEARCH_AT_SITES,
        SEARCH_DATE,
        ;
    }
    
    static final protected String SMB_SPACE = " ";
    static final protected String SMB_LEFT_BKT = "(";
    static final protected String SMB_RIGHT_BKT = ")";
    static final protected String SMB_BIGGER = ">";
    static final protected String SMB_SMALLER = "<";
    
    static final private String URL_ENC = "UTF-8";
    
    static final private String SEARCH_AT_SITE_DEF_OP = "site:";    
    static final private String SEARCH_AT_SITE_DEF_OP_SEP = " | ";
    static final private String SEARCH_DATE_DEF_OP = "date:";
    static final private String SEARCH_DATE_DEF_OP_SEP = "..";
    static final private String SEARCH_DATE_DEF_OP_PATTERN = "yyyyMMdd";
}
