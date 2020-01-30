/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilidades;

import java.awt.event.KeyEvent;

/**
 * Nombre de la clase: Validar
* Fecha:18/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class Validar {
    
    public void SoloLetras(KeyEvent evt){
      Character s = evt.getKeyChar();
        if (!Character.isLetter(s) && s != com.sun.glass.events.KeyEvent.VK_SPACE) {
            evt.consume();
        }
    
    
    }
    public void Solonumeros(KeyEvent evt){
    
     Character s = evt.getKeyChar();
        if (!Character.isDigit(s)) {
            evt.consume();
        }
    }
}
