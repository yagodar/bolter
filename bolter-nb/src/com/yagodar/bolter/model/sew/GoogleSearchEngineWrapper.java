/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model.sew;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author АППДКт78М
 */
public class GoogleSearchEngineWrapper extends AWebSearchEngineWrapper {
    public GoogleSearchEngineWrapper() {
        super("Google", "http://google.ru/", "search");
    }

    @Override
    protected void applyFilter(SearchFilter filter, WebSearchQuery webSearchQuery, Object filterData) {
        String postParamValue;
        
        switch (filter) {
            case SEARCH_DATE:
                postParamValue = new String();

                Date dateFrom = ((Date[]) filterData)[0];
                Date dateTo = ((Date[]) filterData)[1];
                
                if (dateFrom != null || dateTo != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(SEARCH_DATE_POST_PARAM_PATTERN);
                    
                    postParamValue += SEARCH_DATE_PARAM_CDR;
                    postParamValue += SEARCH_DATE_PARAM_SEP;
                    
                    if (dateFrom != null && dateTo != null && dateFrom.before(dateTo)) {
                        postParamValue += SEARCH_DATE_PARAM_FROM + dateFormat.format(dateFrom);
                        postParamValue += SEARCH_DATE_PARAM_SEP;
                        postParamValue += SEARCH_DATE_PARAM_TO + dateFormat.format(dateTo);
                    } else if (dateFrom != null && (dateTo == null || !dateFrom.before(dateTo))) {
                        postParamValue += SEARCH_DATE_PARAM_FROM + dateFormat.format(dateFrom);
                    } else if (dateTo != null && dateFrom == null) {
                        postParamValue += SEARCH_DATE_PARAM_TO + dateFormat.format(dateTo);
                    }
                    
                    webSearchQuery.putPostParam(getPostParamName(SearchFilter.SEARCH_DATE), urlEncode(postParamValue));
                }
                break;
            default:
                super.applyFilter(filter, webSearchQuery, filterData);
                break;
        }
    }

    @Override
    protected String getPostParamName(SearchFilter filter) {
        switch(filter) {
            case SEARCH_DATE:
                return SEARCH_DATE_POST_PARAM_NAME;
            default:
                return SEARCH_WORD_POST_PARAM_NAME;
        }
    }
    
    static final private String SEARCH_WORD_POST_PARAM_NAME = "q";
    static final private String SEARCH_DATE_POST_PARAM_NAME = "tbs";
    static final private String SEARCH_DATE_POST_PARAM_PATTERN = "dd.MM.yyyy";
    static final private String SEARCH_DATE_PARAM_CDR = "cdr:1";
    static final private String SEARCH_DATE_PARAM_FROM = "cd_min:";
    static final private String SEARCH_DATE_PARAM_TO = "cd_max:";
    static final private String SEARCH_DATE_PARAM_SEP = ",";
}
