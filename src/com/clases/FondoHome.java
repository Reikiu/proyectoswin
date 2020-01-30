
package com.clases;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;



/**
 **Nombre de la clase:FondoHome
* Fecha:15/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 
 */
public class FondoHome implements Border {

    private BufferedImage image;
 
 //funcion para dibujar la imagen  en el mdi
    public FondoHome() {
        try {
            //creamos un objeto url  para cargar la ruta de la imagen
            URL imagePath= new URL(getClass().getResource("../Imagenes/social.jpg").toString());
           
           image=ImageIO.read(imagePath);
          
        } catch (Exception e) {
        }
        
    }
   
   //dibujamos la imagen
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      //cargamos las dimensiones de la imagen
        g.drawImage(image,(x+(width-image.getWidth())/2),(y+(height-image.getHeight())/2),null);
         
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);//centramos la imagen
    }

    public boolean isBorderOpaque() {
        return false;
    }
    
    
}
