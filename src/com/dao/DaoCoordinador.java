/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.Coordinador;
import com.clases.Estudiante;
import com.clases.Usuarios;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 **Nombre de la clase:DaoCoordinador
 * Fecha:16/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 
 */
public class DaoCoordinador extends Conexion{
    //mostrar Coordinador
    public List<Coordinador> mostrarCoordinardor() throws Exception {
        List<Coordinador> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "SELECT *from coordinador where estado=1 ";
            PreparedStatement pst = this.getCon().prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                Coordinador prov = new Coordinador();
                prov.setCodigoCo(rs.getInt("IdCoordinador"));
                prov.setNombreCo(rs.getString("NombCoordinador"));
                prov.setEdadCo(rs.getInt("EdadCoordinador"));
                prov.setSexo(rs.getString("SexoCor"));
                prov.setCorreoCo(rs.getString("CorreoCoord"));
                prov.setEscuela(rs.getInt("Escuela"));
                prov.setEstado(rs.getInt("estado"));
                lst.add(prov);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lst;
    }
    //Insertar coordinador
     public String insertarCoordinador(Coordinador pro) {
        int i = 0;
        int n=0;
        try {
            this.conectar();
             String sql = "INSERT INTO `coordinador`(`IdCoordinador`, `NombCoordinador`, `EdadCoordinador`, `SexoCor`, `CorreoCoord`,Escuela, `estado`) VALUES (?,?,?,?,?,?,?)";
            String SQL = "SELECT `IdCoordinador`, `NombCoordinador`, `EdadCoordinador`, `SexoCor`, `CorreoCoord`,Escuela FROM `coordinador` WHERE IdCoordinador=? ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            PreparedStatement pre2 = this.getCon().prepareStatement(SQL);            
            pre.setInt(1, pro.getCodigoCo());
            pre.setString(2, pro.getNombreCo());
            pre.setInt(3, pro.getEdadCo());
            pre.setString(4,pro.getSexo());
            pre.setString(5,pro.getCorreoCo());
             pre.setInt(6, pro.getEscuela());
            pre.setInt(7,1);
             pre2.setInt(1, pro.getCodigoCo());
             ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                n=1;               
            }
            if(n==0){
            pre.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Coordinador insertado correctamente");
            } 
            if(n==1){
             JOptionPane.showMessageDialog(null, "El coordinador ya existe");
            }
        } catch (SQLException e) {
                           JOptionPane.showMessageDialog(null, "error"+e);
        }
        return " ";
    }
     //Modificar
      public String modificarCoor(Coordinador pro){
        int i = 0;
        int n=0;     
         try 
        {
            this.conectar();
            String sql="UPDATE `coordinador` SET `NombCoordinador`=?,`EdadCoordinador`=?,`SexoCor`=?,`CorreoCoord`=?,Escuela=? WHERE `IdCoordinador`=?";
              PreparedStatement pre=this.getCon().prepareStatement(sql);           
             pre.setString(1,pro.getNombreCo());
             pre.setInt(2,pro.getEdadCo());
             pre.setString(3,pro.getSexo());
              pre.setString(4,pro.getCorreoCo());
              pre.setInt(5,pro.getEscuela());
              pre.setInt(6,pro.getCodigoCo());           
             pre.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Usuario modficado correctamente");          
        }       
         catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al Modificar "+e);
        }
        finally
        {            
        }
    return " ";
    }
      //Eliminado fisico
       public String eliminarCoor(Coordinador pro){
        try 
        {
            this.conectar();
            String sql="delete from coordinador where IdCoordinador=?;";
            PreparedStatement pre=this.getCon().prepareStatement(sql);
           pre.setInt(1,pro.getCodigoCo());
            pre.executeUpdate();
            
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al Eliminar "+e);
        }
        finally
        {      
        }
    return " ";
    }
       //eliminado logico
        public String EliminadoLogico(Coordinador pro){
        int i = 0;
        int n=0;
        
         try 
        {
            this.conectar();
            String sql="UPDATE `coordinador` SET estado=? WHERE `IdCoordinador`=?";
              PreparedStatement pre=this.getCon().prepareStatement(sql);                       
             pre.setInt(1,0);
              pre.setInt(2,pro.getCodigoCo());           
             pre.executeUpdate();               
        }       
         catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al Modificar "+e);
        }
        finally
        {            
        }
    return " ";
    }
        //buscar si el coordinador existe
    public boolean buscarCoordinadorExist(int codigo, String nombre,Integer edad,String correo) {
        boolean existe = false;
        try {
            this.conectar();
            String SQL = "select * from coordinador where IdCoordinador=? and NombCoordinador=? and EdadCoordinador=? and CorreoCoord=? ";
            PreparedStatement ps = this.getCon().prepareStatement(SQL);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, edad);
             ps.setString(4, correo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        return existe;

    }
}

