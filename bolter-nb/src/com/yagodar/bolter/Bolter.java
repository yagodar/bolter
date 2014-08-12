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

package com.yagodar.bolter;

import com.yagodar.bolter.model.rep.BolterModelPreferencesRepository;
import com.yagodar.bolter.view.BolterFrame;
import com.yagodar.bolter.view.LockFrame;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/**
 *
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
 */
public class Bolter {

    public static void main(String[] args) {
        if (!lock()) {
            (new LockFrame()).setVisible(true);
            
            try {
                Thread.sleep(SLEEP_TIME_IF_LOCK);
            } catch (InterruptedException ex) {}
            
            System.exit(1);
        }
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                (new BolterFrame(BolterModelPreferencesRepository.getInstance().load())).setVisible(true);
            }
        });
    }
    
    private static boolean lock() {
        File lockFileDir = new File(LOCK_FILE_DIR);
        if (!lockFileDir.exists()) {
            lockFileDir.mkdir();
        }

        try {
            final FileLock lock = new FileOutputStream(new File(LOCK_FILE_DIR, LOCK_FILE_NAME)).getChannel().tryLock();
            if (lock != null) {
                Thread t = new Thread(new Runnable(){
                    public void run() {
                        while (true) {
                            try {
                                if (lock.isValid()) {};
                                Thread.sleep(Long.MAX_VALUE);
                            } catch (InterruptedException e) {}
                        }
                    }
                });
                t.setDaemon(true);
                t.start();
            }
            return lock != null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return true;
    }
    
    private static final String LOCK_FILE_DIR = System.getProperty("user.home") + "\\.bolter";
    private static final String LOCK_FILE_NAME = ".lock";
    private static final long SLEEP_TIME_IF_LOCK = 750;
}
