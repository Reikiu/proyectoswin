/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.clases.Carrera;
import com.clases.Estudiante;
import com.clases.Usuarios;
import com.conexion.Conexion;
import com.vista.frmRegistrarEstudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 **Nombre de la clase:DaoEstudiante
* Fecha:16/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 
 */
public class DaoEstudiante extends Conexion {
//mostrar estudiantes
    public List<Estudiante> mostrarEstudiante() throws Exception {
        List<Estudiante> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "SELECT e.IdEstudiante, e.NombreEstudiante, e.Edad, e.Sexo, e.CorreoElectronico, e.IdCarrera, e.EstadoCarrera,c.IdCarrera, c.NombreCarrera, c.Tipocarrera ,e.Sede,e.estado FROM estudiantes e   INNER JOIN carrera c on c.IdCarrera=e.IdCarrera where e.estado=1";
            PreparedStatement pst = this.getCon().prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                Estudiante prov = new Estudiante();
                prov.setCarnet(rs.getInt("IdEstudiante"));
                prov.setNombreEst(rs.getString("NombreEstudiante"));
                prov.setEdad(rs.getInt("Edad"));
                prov.setSexoEst(rs.getString("Sexo"));
                prov.setCorreoEst(rs.getString("CorreoElectronico"));
                prov.setIdCarreras(rs.getInt("IdCarrera"));
                prov.setEstadoCarrera(rs.getString("EstadoCarrera"));
                prov.setIdCarrera(rs.getInt("Idcarrera"));
                prov.setNombreCarrera(rs.getString("NombreCarrera"));
                prov.setTipoCarrera(rs.getString("Tipocarrera"));
                prov.setSede(rs.getString("Sede"));
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
    //consultando carreras
    public void consultarCarrera(JComboBox cmbCodFacultad) {
//Creamos objeto tipo Connection    
        java.sql.Connection conectar = null;
        PreparedStatement pst = null;
        ResultSet result = null;
//Creamos la Consulta SQL
        String SSQL = "SELECT * FROM carrera where estado=1";

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
            cmbCodFacultad.addItem("Seleccione una opción");
            while (result.next()) {             
                cmbCodFacultad.addItem(result.getString("NombreCarrera"));
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
    //insertar estudiantes
    public String insertarEstudiante(Estudiante pro) {
        int i = 0;
        int n=0;
        try {
            this.conectar();
             String sql = "INSERT INTO `estudiantes`(`IdEstudiante`, `NombreEstudiante`, `Edad`, `Sexo`, `CorreoElectronico`, `IdCarrera`, `EstadoCarrera`, `Sede`,`estado`) VALUES (?,?,?,?,?,?,?,?,?)";
            String SQL = "SELECT `IdEstudiante`, `NombreEstudiante`, `Edad`, `Sexo`, `CorreoElectronico`, `IdCarrera`, `EstadoCarrera`, `Sede` FROM `estudiantes` WHERE IdEstudiante=? ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            PreparedStatement pre2 = this.getCon().prepareStatement(SQL);
            
            pre.setInt(1, pro.getCarnet());
            pre.setString(2, pro.getNombreEst());
            pre.setInt(3, pro.getEdad());
            pre.setString(4,pro.getSexoEst());
            pre.setString(5,pro.getCorreoEst());
            pre.setInt(6,pro.getIdCarreras());
            pre.setString(7,pro.getEstadoCarrera());
            pre.setString(8,pro.getSede());
            pre.setInt(9, 1);
             pre2.setInt(1, pro.getCarnet());
             ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                n=1;      
            }
            if(n==0){
            pre.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Usuario insertado correctamente"); 
            } 
            if(n==1){
             JOptionPane.showMessageDialog(null, "La carrera ya existe");
            }
        } catch (SQLException e) {
                           JOptionPane.showMessageDialog(null, "error"+e);
        }
        return " ";
    }
    //Modificar estudiante
    public String modificarEstudiante(Estudiante pro){
        int i = 0;
        int n=0;        
         try {
             this.conectar();
            String sql="UPDATE `estudiantes` SET `NombreEstudiante`=?,`Edad`=?,`Sexo`=?,`CorreoElectronico`=?,`IdCarrera`=?,`EstadoCarrera`=?,`Sede`=? where IdEstudiante=?;";
            String SQL = "SELECT `IdEstudiante`, `NombreEstudiante`, `Edad`, `Sexo`, `CorreoElectronico`, `IdCarrera`, `EstadoCarrera`, `Sede` FROM `estudiantes`  ";
            PreparedStatement pre=this.getCon().prepareStatement(sql);
            PreparedStatement pre2 = this.getCon().prepareStatement(SQL);         
            pre.setString(1, pro.getNombreEst());
            pre.setInt(2, pro.getEdad());
            pre.setString(3,pro.getSexoEst());
            pre.setString(4,pro.getCorreoEst());
            pre.setInt(5,pro.getIdCarreras());
            pre.setString(6,pro.getEstadoCarrera());
            pre.setString(7,pro.getSede());
            pre.setInt(8, pro.getCarnet());        
             pre.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Estudiante modficado correctamente");              
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro al modificar estudiante "+e);
        }  
        finally
        {          
        }
    return " ";
        } 
    //Eliminado fisico
    public String eliminarEstudiante(Estudiante pro){
        try 
        {
            this.conectar();
            String sql="delete from estudiantes where IdEstudiante=?;";
            PreparedStatement pre=this.getCon().prepareStatement(sql);
           pre.setInt(1,pro.getCarnet());
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
      public String eliminadoLogico(Estudiante pro){
        int i = 0;
        int n=0;        
         try {
             this.conectar();
            String sql="UPDATE `estudiantes` SET estado=? where IdEstudiante=?;";       
            PreparedStatement pre=this.getCon().prepareStatement(sql);           
            pre.setInt(1, 0);  
            pre.setInt(2, pro.getCarnet());        
             pre.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Estudiante modficado correctamente");              
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro al modificar estudiante "+e);
        }  
        finally
        {          
        }
    return " ";
        } 
        //buscar si existe el estudiante
    public boolean buscarEstudianteExist(int codigo, String nombre) {
        boolean existe = false;
        try {
            this.conectar();
            String SQL = "select * from estudiantes where  IdEstudiante=? and  `NombreEstudiante`=? ";
            PreparedStatement ps = this.getCon().prepareStatement(SQL);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
         
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

