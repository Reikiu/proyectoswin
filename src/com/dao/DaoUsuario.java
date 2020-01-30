/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.Usuarios;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 **Nombre de la clase:DaouUsuario 
 *  Fecha:17/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 *
 */
public class DaoUsuario extends Conexion {
    //mostrar usuarios

    public List<Usuarios> mostrarUsuarios() throws Exception {

        ResultSet rs;
        List<Usuarios> lst = new ArrayList();

        try {
            this.conectar();
            String sql = "select * from usuarios where estado=1";
            PreparedStatement pst = this.getCon().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Usuarios prov = new Usuarios();
                prov.setIdUsuario(rs.getInt("IdUsuario"));
                prov.setUsuario(rs.getString("Usuario"));
                prov.setPass(rs.getString("Pass"));
                prov.setPrivilegios(rs.getInt("Privilegios"));
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
    //buscar usuarios para login

    public boolean buscarUsuario(String usuario, String pass) {
        boolean logeado = false;
        try {
            this.conectar();
            String SQL = "select IdUsuario,Usuario,Pass,Privilegios from usuarios where Usuario=? and Pass=? ";
            PreparedStatement ps = this.getCon().prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logeado = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar acceder a su cuenta" + e.getMessage());
        }
        return logeado;
    }

    //buscar privilegios del usuario
    public int buscarNivel(String usuario, String pass) {
        int nivel = 0;
        try {
            this.conectar();
            String SQL = "select IdUsuario,Usuario,Pass,Privilegios from usuarios where Usuario=? and Pass=? ";
            PreparedStatement ps = this.getCon().prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("Privilegios");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar acceder a su cuenta" + e.getMessage());
        }
        return nivel;
    }
    
    //buscar estado
    public int buscarEstado(String usuario, String pass) {
        int nivel = 0;
        try {
            this.conectar();
            String SQL = "select IdUsuario,Usuario,Pass,Privilegios,estado from usuarios where Usuario=? and Pass=? ";
            PreparedStatement ps = this.getCon().prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("estado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar acceder a su cuenta" + e);
        }
        return nivel;
    }
//insertar usuarios

    public String insertarUsuario(Usuarios pro) {
        int i = 0;
        int n = 0;
        try {
            this.conectar();
            String sql = "insert into usuarios (IdUsuario,Usuario, Pass, Privilegios,estado) values(null,?,?,?,?);";
            String SQL = "select IdUsuario,Usuario,Pass,Privilegios from usuarios where Usuario=? and Pass=? ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            PreparedStatement pre2 = this.getCon().prepareStatement(SQL);
            pre.setString(1, pro.getUsuario());
            pre.setString(2, pro.getPass());
            pre.setInt(3, pro.getPrivilegios());
            pre.setInt(4, 1);
            pre2.setString(1, pro.getUsuario());
            pre2.setString(2, pro.getPass());
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                n = 1;
            }
            if (n == 0) {
                pre.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario insertado correctamente");
            }
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "El usuario y/o contraseña ya existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro" + e);
        }
        return " ";
    }

    //modificar usuarios
    public String modificarUsuario(Usuarios pro) {
        int i = 0;
        int n = 0;
        try {
            this.conectar();
            String sql = "update usuarios set Usuario=?, Pass=?,Privilegios=? where IdUsuario=?;";
            String SQL = "select IdUsuario,Usuario,Pass,Privilegios from usuarios where  Usuario=? and Pass=? and Privilegios=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            PreparedStatement pre2 = this.getCon().prepareStatement(SQL);
            pre.setString(1, pro.getUsuario());
            pre.setString(2, pro.getPass());
            pre.setInt(3, pro.getPrivilegios());
            pre.setInt(4, pro.getIdUsuario());
            pre2.setString(1, pro.getUsuario());
            pre2.setString(2, pro.getPass());
            pre2.setInt(3, pro.getPrivilegios());
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                n = 1;
            }
            if (n == 0) {
                pre.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario modficado correctamente");
            }
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "El usuario y/o contraseña ya existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar " + e);
        } finally {
        }
        return " ";
    }
    //eliminado fisico

    public String eliminarUsuario(Usuarios pro) {
        try {
            this.conectar();
            String sql = "delete from usuarios where IdUsuario=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, pro.getIdUsuario());
            pre.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar " + e);
        } finally {
        }
        return " ";
    }
    //eliminado logico

    public String EliminadoLogico(Usuarios pro) {
        int i = 0;
        int n = 0;
        try {
            this.conectar();
            String sql = "update usuarios set estado=? where IdUsuario=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, 0);
            pre.setInt(2, pro.getIdUsuario());
            pre.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar " + e);
        } finally {
        }
        return " ";
    }

}
