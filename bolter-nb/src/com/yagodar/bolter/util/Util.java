/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author АППДКт78М
 */
public class Util {
    public static void browseLink(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (IOException | URISyntaxException ex) {}
    }
}
