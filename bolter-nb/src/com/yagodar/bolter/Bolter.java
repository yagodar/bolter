/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author profselection-left
 */
public class Bolter {

    /**
     * @param args the command line arguments
     */
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
        try {
            final FileLock lock = new FileOutputStream(new File(LOCK_FILE_NAME)).getChannel().tryLock();
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
        } catch (Exception ex) {}
        
        return true;
    }
    
    private static final String LOCK_FILE_NAME = ".lock";
    private static final long SLEEP_TIME_IF_LOCK = 750;
}
