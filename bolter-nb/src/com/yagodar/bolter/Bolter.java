/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yagodar.bolter;

import com.yagodar.bolter.model.rep.BolterModelPreferencesRepository;
import com.yagodar.bolter.view.BolterFrame;
import java.awt.EventQueue;

/**
 *
 * @author profselection-left
 */
public class Bolter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                (new BolterFrame(BolterModelPreferencesRepository.getInstance().load())).setVisible(true);
            }
        });
    }

}
