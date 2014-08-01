/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

/**
 *
 * @author АППДКт78М
 */
public class HintTextFieldUI extends BasicTextFieldUI implements FocusListener {
    public HintTextFieldUI(String hint) {
        this.hint = hint;
    }
    
    @Override
    protected void paintSafely(Graphics g) {
        super.paintSafely(g);
        
        JTextComponent comp = getComponent();
        if(hint != null && comp.getText().isEmpty() && !comp.hasFocus()){
            Insets compInsets = comp.getInsets();
            Font compFont = comp.getFont();
            FontMetrics compFontMetrics = comp.getFontMetrics(compFont);
            
            g.setColor(comp.getForeground().brighter().brighter());
            g.setFont(compFont.deriveFont(Font.ITALIC));
            g.drawString(hint, compInsets.left, compInsets.top + (comp.getHeight() - compInsets.top - compInsets.bottom) / 2 + compFontMetrics.getHeight() / 2 - compFontMetrics.getDescent() - compFontMetrics.getLeading());          
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        repaint();
    }
    
    @Override
    protected void installListeners() {
        super.installListeners();
        getComponent().addFocusListener(this);
    }
    
    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        getComponent().removeFocusListener(this);
    }
    
    private void repaint() {
        JTextComponent comp = getComponent();
        
        if(comp != null) {
            comp.repaint();           
        }
    }
    
    private final String hint;
}
