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
