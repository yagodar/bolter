/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yagodar.bolter.model.sew;

/**
 *
 * @author АППДКт78М
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
