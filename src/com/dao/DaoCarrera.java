
package com.dao;

import com.clases.Carrera;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 **Nombre de la clase:DaoCarrera 
 * Fecha:15/01/2020
 * Version:1.4
 * CopyRight: ITCA-FEPADE
 * @author ServicioSocial
 *
 */
public class DaoCarrera extends Conexion {

    public List<Carrera> mostrarCarrera() throws Exception {
        List<Carrera> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "SELECT * FROM carrera where estado=1";
            PreparedStatement pst = this.getCon().prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                Carrera prov = new Carrera();
                prov.setIdCarrera(rs.getInt("IdCarrera"));
                prov.setNombreCarrera(rs.getString("NombreCarrera"));
                prov.setTipoCarrera(rs.getString("TipoCarrera"));
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

    public void consultarCarrera(JComboBox cmbTipoCar) {

//Creamos objeto tipo Connection    
        java.sql.Connection conectar = null;
        PreparedStatement pst = null;
        ResultSet result = null;

//Creamos la Consulta SQL
        String SSQL = "SELECT * FROM carrera ";

//Establecemos bloque try-catch-finally
        try {
            this.conectar();
            //Establecemos conexión con la BD 
            conectar = this.getCon();
            //Preparamos la consulta SQL
            pst = conectar.prepareStatement(SSQL);
            //Ejecutamos la consulta
            result = pst.executeQuery();
            //LLenamos nuestro ComboBox
            cmbTipoCar.addItem("Seleccione una opción");
            while (result.next()) {
                cmbTipoCar.addItem(result.getString("TipoCarrera"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            if (conectar != null) {
                try {
                    conectar.close();
                    result.close();
                    conectar = null;
                    result = null;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
//insertar carrera
    public String insertarCarrera(Carrera pro) {
        int i = 0;
        int n = 0;
        try {
            this.conectar();
            String sql = "INSERT INTO carrera VALUES (?,?,?,?)";
            String SQL = "SELECT * FROM `carrera` WHERE IdCarrera=? ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            PreparedStatement pre2 = this.getCon().prepareStatement(SQL);
            pre.setInt(1, pro.getIdCarrera());
            pre.setString(2, pro.getNombreCarrera());
            pre.setString(3, pro.getTipoCarrera());
            pre.setInt(4, 1);
            pre2.setInt(1, pro.getIdCarrera());
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                n = 1;
            }
            if (n == 0) {
                pre.executeUpdate();
                JOptionPane.showMessageDialog(null, "Carrera insertado correctamente");
            }
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "La carrera ya existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e);
        }
        return " ";
    }
//modificar carrera
    public String modificarCarrera(Carrera pro) {
        int i = 0;
        int n = 0;
        try {
            this.conectar();
            String sql = "UPDATE `carrera` SET `NombreCarrera`=?,`Tipocarrera`=? WHERE IdCarrera=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, pro.getNombreCarrera());
            pre.setString(2, pro.getTipoCarrera());
            pre.setInt(3, pro.getIdCarrera());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Carrera modficada correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar carrera" + e);
        } finally {
        }
        return " ";
    }
//eliminado fisico
    public String eliminarCarrera(Carrera pro) {
        try {
            this.conectar();
            String sql = "delete from carrera where IdCarrera=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, pro.getIdCarrera());
            pre.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar " + e);
        } finally {
        }
        return " ";
    }

    //eliminado logico
    public String EliminadoLogico(Carrera pro) {
        int i = 0;
        int n = 0;
        try {
            this.conectar();
            String sql = "UPDATE `carrera` SET estado=? WHERE IdCarrera=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, 0);
            pre.setInt(2, pro.getIdCarrera());
            pre.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar carrera" + e);
        } finally {
        }
        return " ";
    }

    //buscar si existe la carrera
    public boolean buscarCarreraExist(int codigo, String nombre, String tipo) {
        boolean existe = false;
        try {
            this.conectar();
            String SQL = "select * from carrera where IdCarrera=? and NombreCarrera=? and TipoCarrera=? ";
            PreparedStatement ps = this.getCon().prepareStatement(SQL);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, tipo);
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
