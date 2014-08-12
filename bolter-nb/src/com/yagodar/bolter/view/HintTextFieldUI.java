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
 * @author Yagodarov Andrey <yagodarov.a.e@gmail.com>
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
