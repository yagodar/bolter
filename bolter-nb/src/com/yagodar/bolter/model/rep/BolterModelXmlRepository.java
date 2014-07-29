/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model.rep;

import com.yagodar.bolter.model.BolterModel;

/**
 *
 * @author АППДКт78М
 */
public class BolterModelXmlRepository implements IRepository<BolterModel> {

    @Override
    public BolterModel load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(BolterModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static private final String XML_FILE_NAME = "bolter-model.xml";
}
