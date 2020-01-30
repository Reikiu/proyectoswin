/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vista;

import com.clases.FondoHome;
import com.clases.FondoLogin;
import com.clases.Usuarios;
import com.dao.DaoUsuario;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 **Nombre de la clase:frmUsuarios 
 * Fecha:16/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class frmUsuarios extends javax.swing.JInternalFrame {

    public frmUsuarios() {
        initComponents();
        jFondoLogin.setBorder(new FondoLogin());
        this.txtIdUser.setVisible(false);
        this.txtestado.setVisible(false);
        mostrarUsuarios();
        limpiar();
    }
    Usuarios prov = new Usuarios();
    DaoUsuario du = new DaoUsuario();

    public void mostrarUsuarios() {
        String[] columnas = {"ID", "Usuario", "Contraseña", "Privilegios", "Estado"};
        Object[] obj = new Object[5];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls = du.mostrarUsuarios();
            for (int i = 0; i < ls.size(); i++) {
                prov = (Usuarios) ls.get(i);
                obj[0] = prov.getIdUsuario();
                obj[1] = prov.getUsuario();
                obj[2] = prov.getPass();
                obj[3] = prov.getPrivilegios();
                obj[4] = prov.getEstado();
                tabla.addRow(obj);
            }
            ls = du.mostrarUsuarios();
            this.JUsuarios.setModel(tabla);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos del Usuarios ");
            e.toString();
        }

    }

    public void llenarTabla() {
        int fila = this.JUsuarios.getSelectedRow();
        this.txtIdUser.setText(String.valueOf(this.JUsuarios.getValueAt(fila, 0)));
        this.txtUsuario.setText(String.valueOf(this.JUsuarios.getValueAt(fila, 1)));
        this.txtPass2.setText(String.valueOf(this.JUsuarios.getValueAt(fila, 2)));
        this.txtPass1.setText(String.valueOf(this.JUsuarios.getValueAt(fila, 2)));
        if (this.JUsuarios.getValueAt(fila, 3).equals(1)) {
            this.cmbPrivilegios.setSelectedIndex(1);
        }
        if (this.JUsuarios.getValueAt(fila, 3).equals(2)) {
            this.cmbPrivilegios.setSelectedIndex(2);
        }
        this.txtestado.setText(String.valueOf(this.JUsuarios.getValueAt(fila, 4)));
    }

    public void insertar() {
        int nivel = this.cmbPrivilegios.getSelectedIndex();
        if (nivel != 0) {
            String usuario = txtUsuario.getText();
            prov.setUsuario(this.txtUsuario.getText());
            String pass = this.txtPass1.getText();
            String passmd5 = pass;
            String pass2 = this.txtPass2.getText();
            String passmd52 = DigestUtils.md5Hex(pass2);
            prov.setPass(passmd5);
            prov.setPrivilegios(this.cmbPrivilegios.getSelectedIndex());
            du.buscarUsuario(usuario, passmd5);
            if (pass.equals(pass2)) {                
                    if (du.buscarUsuario(usuario, pass) == false) {
                        du.insertarUsuario(prov);
                        mostrarUsuarios();
                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseña o el usuario no se encuentra disponible ingrese un usuario y contraseña diferente ");
                    }
                    mostrarUsuarios();                
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no concuerdans ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un nivel de usuario");
        }
    }

    public void modificar() {

        try {
            prov.setIdUsuario(Integer.parseInt(this.txtIdUser.getText()));
            prov.setUsuario(this.txtUsuario.getText());
            String pass = this.txtPass2.getText();
            String passmd5 = pass;
            prov.setPass(passmd5);
            prov.setPrivilegios(cmbPrivilegios.getSelectedIndex());
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea modificar al usuario?", "Modificar Usuario", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                du.modificarUsuario(prov);
                llenarTabla();
                mostrarUsuarios();
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() {
        try {
            prov.setIdUsuario(Integer.parseInt(this.txtIdUser.getText()));
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar Usuario? ", "Eliminar Usuario? ", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                du.EliminadoLogico(prov);
                JOptionPane.showMessageDialog(rootPane, "Eliminado con Exito  ", "Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
                llenarTabla();
                mostrarUsuarios();
            } else {

            }
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        txtIdUser.setText("");
        txtPass2.setText("");
        txtPass1.setText("");
        txtUsuario.setText("");
        cmbPrivilegios.setSelectedIndex(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFondoLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPass2 = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        cmbPrivilegios = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JUsuarios = new javax.swing.JTable();
        txtIdUser = new javax.swing.JTextField();
        txtestado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPass1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setFocusable(false);
        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel3.setText("Privilegios");

        txtPass2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        txtUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        cmbPrivilegios.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        cmbPrivilegios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Seleccione---", "Administrador", "Invitado" }));

        JUsuarios.setBackground(new java.awt.Color(102, 102, 102));
        JUsuarios.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        JUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        JUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JUsuarios);

        txtIdUser.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setText("Repetir contraseña");

        txtPass1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/guardar.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/cubo-de-la-basura.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/actualizar.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/escoba.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jFondoLoginLayout = new javax.swing.GroupLayout(jFondoLogin);
        jFondoLogin.setLayout(jFondoLoginLayout);
        jFondoLoginLayout.setHorizontalGroup(
            jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
            .addGroup(jFondoLoginLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFondoLoginLayout.createSequentialGroup()
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFondoLoginLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jFondoLoginLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jFondoLoginLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jFondoLoginLayout.createSequentialGroup()
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFondoLoginLayout.createSequentialGroup()
                                .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jFondoLoginLayout.createSequentialGroup()
                                .addComponent(cmbPrivilegios, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(23, 23, 23))))))
        );
        jFondoLoginLayout.setVerticalGroup(
            jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFondoLoginLayout.createSequentialGroup()
                .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jFondoLoginLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(jFondoLoginLayout.createSequentialGroup()
                                .addComponent(cmbPrivilegios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))))
                    .addGroup(jFondoLoginLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(5, 5, 5)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFondoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFondoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JUsuariosMouseClicked
        llenarTabla();
    }//GEN-LAST:event_JUsuariosMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        if(txtUsuario.getText().isEmpty()||txtPass1.getPassword().equals("")||txtPass2.getPassword().equals("")){
       JOptionPane.showMessageDialog(null, "No se permiten campos vacios ");
       }else{
          insertar();
                mostrarUsuarios();
                limpiar();
       }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        eliminar();
        mostrarUsuarios();
        limpiar();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        modificar();
        mostrarUsuarios();
        limpiar();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        limpiar();
        mostrarUsuarios();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JUsuarios;
    private javax.swing.JComboBox<String> cmbPrivilegios;
    private javax.swing.JPanel jFondoLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtIdUser;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtestado;
    // End of variables declaration//GEN-END:variables
}
