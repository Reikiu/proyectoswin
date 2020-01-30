
package com.clases;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 **Nombre de la clase:FondoEstudiante
 * Fecha:15/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class FondoEstudiante implements Border{
      private BufferedImage Estudiante;
 //funcion para dibujar la imagen  en el mdi
    public FondoEstudiante()  {
        try {
            //creamos un objeto url  para cargar la ruta de la imagen
            
            URL log= new URL(getClass().getResource("../Imagenes/social.jpg").toString());
        
           Estudiante= ImageIO.read(log);
        } catch (Exception e) {
        }
        
    }

    //dibujamos la imagen
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      //cargamos las dimensiones de la imagen
       
         g.drawImage(Estudiante,(x+(width-Estudiante.getWidth())/2),(y+(height-Estudiante.getHeight())/2),null);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);//centramos la imagen
    }

    public boolean isBorderOpaque() {
        return false;
    }
}
