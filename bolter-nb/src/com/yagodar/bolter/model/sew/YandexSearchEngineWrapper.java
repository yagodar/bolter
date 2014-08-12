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

/**
 *
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
 */
public class YandexSearchEngineWrapper extends AWebSearchEngineWrapper {

    public YandexSearchEngineWrapper() {
        super("Яндекс", "http://yandex.ru/", "yandsearch");
    }

    public static YandexSearchEngineWrapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new YandexSearchEngineWrapper();
        }

        return INSTANCE;
    }

    @Override
    protected String getPostParamName(SearchFilter filter) {
        return SEARCH_WORD_POST_PARAM_NAME;
    }

    static private YandexSearchEngineWrapper INSTANCE;

    static final private String SEARCH_WORD_POST_PARAM_NAME = "text";
}
