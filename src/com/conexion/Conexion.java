
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase:Conexion
 * Fecha:15/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class Conexion {
    private Connection con;//hacer la importacion java
 
    public Connection getCon() {
        return con;
    }
    
    public boolean conectar(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/serviciosocial", "root", "");
            return true;
        } 
        catch (SQLException |ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error al conectarxdsdd"+e);
       return false;
        }   
    }
    
    
    public boolean desconectar(){
    
        try {if(con!=null){
            if(con.isClosed()==false){
            con.close();
            }
        }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al desconectar"+e.getMessage());

        }
    return true;
    }
}
