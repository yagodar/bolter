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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
 */
public class GoogleSearchEngineWrapper extends AWebSearchEngineWrapper {

    public GoogleSearchEngineWrapper() {
        super("Google", "http://google.ru/", "search");
    }

    public static GoogleSearchEngineWrapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleSearchEngineWrapper();
        }

        return INSTANCE;
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
        switch (filter) {
            case SEARCH_DATE:
                return SEARCH_DATE_POST_PARAM_NAME;
            default:
                return SEARCH_WORD_POST_PARAM_NAME;
        }
    }

    static private GoogleSearchEngineWrapper INSTANCE;

    static final private String SEARCH_WORD_POST_PARAM_NAME = "q";
    static final private String SEARCH_DATE_POST_PARAM_NAME = "tbs";
    static final private String SEARCH_DATE_POST_PARAM_PATTERN = "dd.MM.yyyy";
    static final private String SEARCH_DATE_PARAM_CDR = "cdr:1";
    static final private String SEARCH_DATE_PARAM_FROM = "cd_min:";
    static final private String SEARCH_DATE_PARAM_TO = "cd_max:";
    static final private String SEARCH_DATE_PARAM_SEP = ",";
}
