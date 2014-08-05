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
public enum WebSearchEngineWrapperHolder {

    YANDEX_SEW(YandexSearchEngineWrapper.getInstance()),
    GOOGLE_SEW(GoogleSearchEngineWrapper.getInstance()),;

    private WebSearchEngineWrapperHolder(AWebSearchEngineWrapper instance) {
        this.instance = instance;
        this.instance.setMark(name());
    }

    public AWebSearchEngineWrapper getInstance() {
        return instance;
    }

    private final AWebSearchEngineWrapper instance;
}
