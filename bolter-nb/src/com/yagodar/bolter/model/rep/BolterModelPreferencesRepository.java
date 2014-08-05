/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model.rep;

import com.yagodar.bolter.model.BolterModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.prefs.Preferences;

/**
 *
 * @author АППДКт78М
 */
public class BolterModelPreferencesRepository implements IRepository<BolterModel> {

    public BolterModelPreferencesRepository() {
        prefNode = Preferences.userNodeForPackage(getClass());
    }

    static public BolterModelPreferencesRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BolterModelPreferencesRepository();
        }

        return INSTANCE;
    }
    
    @Override
    public BolterModel load() {
        BolterModel model = null;
        
        byte bolterModelByteArray[] = prefNode.getByteArray(BOLTER_MODEL_MARK, null);
        if (bolterModelByteArray != null) {
            ObjectInputStream in;
            ByteArrayInputStream bin = new ByteArrayInputStream(bolterModelByteArray);
            try {
                in = new ObjectInputStream(bin);
                model = (BolterModel) in.readObject();
                in.close();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        if (model == null) {
            model = new BolterModel();
        }

        return model;
    }

    @Override
    public void save(BolterModel model) {
        if (model != null) {
            ObjectOutputStream out;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try {
                out = new ObjectOutputStream(bout);
                out.writeObject(model);
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            prefNode.putByteArray(BOLTER_MODEL_MARK, bout.toByteArray());
        }
    }

    private final Preferences prefNode;
    
    static private BolterModelPreferencesRepository INSTANCE;
    
    static final private String BOLTER_MODEL_MARK = "bolter_model";
}
