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
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
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
