/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vista;

import com.clases.Carrera;
import com.clases.FondoCarrera;
import com.dao.DaoCarrera;
import com.sun.glass.events.KeyEvent;
import com.utilidades.Validar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 **Nombre de la clase:frmCarrera 
 * Fecha:19/01/2020
 * Version:1.4
 * CopyRight: ITCA-FEPADE
 * @author ServicioSocial
 *
 */
public class frmCarrera extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmCarrera
     */
    public frmCarrera() {
        initComponents();
        txtEstado.setVisible(false);
        JCarrera.setBorder(new FondoCarrera());
        mostrarCarreras();
        limpiar();

    }
    DaoCarrera car = new DaoCarrera();
    Carrera Carr = new Carrera();
//Mostrar Carreras
    public void mostrarCarreras() {
        String[] columnas = {"Codigo Carrera ", "Nombre Carrera", "TipoCarrera", "estado"};
        Object[] obj = new Object[4];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls = car.mostrarCarrera();
            for (int i = 0; i < ls.size(); i++) {
                Carr = (Carrera) ls.get(i);
                obj[0] = Carr.getIdCarrera();
                obj[1] = Carr.getNombreCarrera();
                obj[2] = Carr.getTipoCarrera();
                obj[3] = Carr.getEstado();
                tabla.addRow(obj);
            }
            ls = car.mostrarCarrera();
            this.JTabCArr.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos del Carrera" + e);
            e.toString();
        }

    }
//Insertar
    public void insertar() throws Exception {
        int codigo = Integer.parseInt(this.txtCodCar.getText());
        String carrera = this.txtNombreCar.getText();
        String tipoCarrera = this.cmbTipocar.getSelectedItem().toString();
        if (car.buscarCarreraExist(codigo, carrera, tipoCarrera) == false) {
            Carr.setIdCarrera(Integer.parseInt(this.txtCodCar.getText()));
            Carr.setNombreCarrera(this.txtNombreCar.getText());
            Carr.setTipoCarrera(this.cmbTipocar.getSelectedItem().toString());
            car.insertarCarrera(Carr);
        } else {
            JOptionPane.showMessageDialog(null, "La carrera ya existe ingrese una carrera diferente ");
        }
    }
//Recuperar datos de tabla
    public void llenarTablaCarr() {
        int fila = this.JTabCArr.getSelectedRow();
        this.txtCodCar.setText(String.valueOf(this.JTabCArr.getValueAt(fila, 0)));
        this.txtNombreCar.setText(String.valueOf(this.JTabCArr.getValueAt(fila, 1)));
        this.cmbTipocar.setSelectedItem(this.JTabCArr.getValueAt(fila, 2));
        this.txtEstado.setText(String.valueOf(this.JTabCArr.getValueAt(fila, 3)));
    }
//modificar
    public void modificar() {
        try {
              int codigo = Integer.parseInt(this.txtCodCar.getText());
        String carrera = this.txtNombreCar.getText();
        String tipoCarrera = this.cmbTipocar.getSelectedItem().toString();
          if (car.buscarCarreraExist(codigo, carrera, tipoCarrera) == false) {
            Carr.setIdCarrera(Integer.parseInt(this.txtCodCar.getText()));
            Carr.setNombreCarrera(this.txtNombreCar.getText());
            Carr.setTipoCarrera(this.cmbTipocar.getSelectedItem().toString());
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea modificar la carrera?", "Modificar Carrera", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                car.modificarCarrera(Carr);
            } else {
            }
          }else{
              JOptionPane.showMessageDialog(null, "La carrera ya existe ingrese una carrera diferente");
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //eliminado fisico
       public void eliminar() {
        try {
            Carr.setIdCarrera(Integer.parseInt(this.txtCodCar.getText()));
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar Carrera? ", "Eliminar Carrera? ", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                car.eliminarCarrera(Carr);
                JOptionPane.showMessageDialog(rootPane, "Carrera eliminada con Exito  ", "Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
            } else {
            }
        } catch (Exception e) {
        }
    }
//Eliminado logico
    public void eliminadoLogico() {
        try {
            Carr.setIdCarrera(Integer.parseInt(this.txtCodCar.getText()));
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar Carrera? ", "Eliminar Carrera? ", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                car.EliminadoLogico(Carr);
                JOptionPane.showMessageDialog(rootPane, "Carrera eliminada con Exito  ", "Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
            } else {
            }
        } catch (Exception e) {
        }
    }

    public void limpiar() {

        this.txtCodCar.setText("");
        this.txtNombreCar.setText("");
        this.cmbTipocar.setSelectedIndex(0);
        this.txtEstado.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JCarrera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTabCArr = new javax.swing.JTable();
        txtNombreCar = new javax.swing.JTextField();
        cmbTipocar = new javax.swing.JComboBox<>();
        txtEstado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodCar = new javax.swing.JTextField();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Codigo Carrera");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre Carrera");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo Carrera");

        JTabCArr.setBackground(new java.awt.Color(153, 153, 153));
        JTabCArr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JTabCArr.setForeground(new java.awt.Color(255, 255, 255));
        JTabCArr.setModel(new javax.swing.table.DefaultTableModel(
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
        JTabCArr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTabCArrMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTabCArr);

        txtNombreCar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        txtNombreCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCarActionPerformed(evt);
            }
        });
        txtNombreCar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreCarKeyTyped(evt);
            }
        });

        cmbTipocar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        cmbTipocar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "Tecnico", "Ingenieria" }));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/actualizar.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/cubo-de-la-basura.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/escoba.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/guardar.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        txtCodCar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        javax.swing.GroupLayout JCarreraLayout = new javax.swing.GroupLayout(JCarrera);
        JCarrera.setLayout(JCarreraLayout);
        JCarreraLayout.setHorizontalGroup(
            JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(JCarreraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JCarreraLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTipocar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JCarreraLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtCodCar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JCarreraLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNombreCar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 224, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JCarreraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JCarreraLayout.setVerticalGroup(
            JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JCarreraLayout.createSequentialGroup()
                .addGroup(JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JCarreraLayout.createSequentialGroup()
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JCarreraLayout.createSequentialGroup()
                            .addGap(32, 188, Short.MAX_VALUE)
                            .addGroup(JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtCodCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtNombreCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbTipocar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGroup(JCarreraLayout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(JCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 Validar val = new Validar();
    private void txtNombreCarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCarKeyTyped

        val.SoloLetras(evt);
    }//GEN-LAST:event_txtNombreCarKeyTyped

    private void JTabCArrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTabCArrMouseClicked
        llenarTablaCarr();
    }//GEN-LAST:event_JTabCArrMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        modificar();
        mostrarCarreras();
        limpiar();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        try {
            if (txtCodCar.getText().isEmpty() || txtNombreCar.getText().isEmpty() || cmbTipocar.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "No se permiten campos vacios ");
            } else {
                insertar();
                mostrarCarreras();
                limpiar();
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        eliminadoLogico();
        mostrarCarreras();
        limpiar();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        limpiar();
        mostrarCarreras();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txtNombreCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JCarrera;
    private javax.swing.JTable JTabCArr;
    private javax.swing.JComboBox<String> cmbTipocar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodCar;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNombreCar;
    // End of variables declaration//GEN-END:variables
}
