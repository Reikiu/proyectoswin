package com.clases;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author ggggggggggggggggnnnc
 */
public class FondoInscribitEstudiante implements Border{
    private BufferedImage InsCarrera;
 //funcion para dibujar la imagen  en el mdi
    public FondoInscribitEstudiante()  {
        try {
            //creamos un objeto url  para cargar la ruta de la imagen
            
            URL log= new URL(getClass().getResource("../Imagenes/study.jpg").toString());
        
           InsCarrera= ImageIO.read(log);
        } catch (Exception e) {
        }
        
    }
   
   //dibujamos la imagen
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      //cargamos las dimensiones de la imagen
       
         g.drawImage(InsCarrera,(x+(width-InsCarrera.getWidth())/2),(y+(height-InsCarrera.getHeight())/2),null);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);//centramos la imagen
    }

    public boolean isBorderOpaque() {
        return false;
    }
    
}
