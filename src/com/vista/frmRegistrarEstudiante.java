/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vista;

import com.clases.Carrera;
import com.clases.Estudiante;
import com.clases.FondoEstudiante;
import com.clases.FondoHome;
import com.clases.FondoInscribitEstudiante;
import com.clases.Usuarios;
import com.dao.DaoCarrera;
import com.dao.DaoEstudiante;
import com.sun.glass.events.KeyEvent;
import com.utilidades.Validar;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.digest.DigestUtils;

/**
 **Nombre de la clase:frmRegistrarEstudiante
 * Fecha:20/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial

 */
public class frmRegistrarEstudiante extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmRegistrarEstudiante
     */
    public frmRegistrarEstudiante() {
        initComponents();
        JEstudiante.setBorder(new FondoInscribitEstudiante());
        mostrarEstudiantes();
        mostrarCarreras();
        de.consultarCarrera(cmbCarrera);  
        limpiar();

    }
    DaoEstudiante de = new DaoEstudiante();
    Estudiante est = new Estudiante();
    DaoCarrera Dac = new DaoCarrera();
    Carrera Car = new Carrera();
//Mostrar estudiantes
    public void mostrarEstudiantes() {
        String[] columnas = {"Codigo ", "Nombre", "Edad", "Sexo", "Correo", "ID Carrera", "Estado Carrera", "IdCarrera", "Nombre Carrera", "TipoCarrera", "Sede","estado"};
        Object[] obj = new Object[12];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls = de.mostrarEstudiante();
            for (int i = 0; i < ls.size(); i++) {
                est = (Estudiante) ls.get(i);
                obj[0] = est.getCarnet();
                obj[1] = est.getNombreEst();
                obj[2] = est.getEdad();
                obj[3] = est.getSexoEst();
                obj[4] = est.getCorreoEst();
                obj[5] = est.getIdCarreras();
                obj[6] = est.getEstadoCarrera();
                obj[7] = est.getIdCarrera();
                obj[8] = est.getNombreCarrera();
                obj[9] = est.getTipoCarrera();
                obj[10] = est.getSede();
                obj[11] = est.getEstado();
                tabla.addRow(obj);
            }
            ls = de.mostrarEstudiante();
            this.JTabEstudiante.setModel(tabla);
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos del Estudiante" + e);
            e.toString();
        }

    }
//mostrar carreras
    public void mostrarCarreras() {
        String[] columnas = {"Codigo Carrera ", "Nombre Carrera", "TipoCarrera"};
        Object[] obj = new Object[3];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls = Dac.mostrarCarrera();
            for (int i = 0; i < ls.size(); i++) {
                Car = (Carrera) ls.get(i);
                obj[0] = Car.getIdCarrera();
                obj[1] = Car.getNombreCarrera();
                obj[2] = Car.getTipoCarrera();

                tabla.addRow(obj);
            }
            ls = Dac.mostrarCarrera();
            this.JtabCarrera.setModel(tabla);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos del Carrera" + e);
            e.toString();
        }

    }
//recuperar valores de tabla estudiante
    public void llenarTabla() {
        int fila = this.JTabEstudiante.getSelectedRow();
        this.txtCarnetEst.setText(String.valueOf(this.JTabEstudiante.getValueAt(fila, 0)));
        this.txtNombreEst.setText(String.valueOf(this.JTabEstudiante.getValueAt(fila, 1)));
        this.JEdad.setValue(this.JTabEstudiante.getValueAt(fila, 2));
        String genero = String.valueOf(this.JTabEstudiante.getValueAt(fila, 3));
        if (genero.equals("Femenino")) {
            this.JFemenino.setSelected(true);

        } else {
            this.JMasculino.setSelected(true);
        }
        this.txtCorreoEst.setText(String.valueOf(this.JTabEstudiante.getValueAt(fila, 4)));
        this.txtCodCarrera.setText(String.valueOf(this.JTabEstudiante.getValueAt(fila, 5)));
        this.cmbEstadoCar.setSelectedItem(this.JTabEstudiante.getValueAt(fila, 6));
        this.cmbCarrera.setSelectedItem(this.JTabEstudiante.getValueAt(fila, 8));
        this.cmbTipoCarrera.setSelectedItem(this.JTabEstudiante.getValueAt(fila, 9));
        this.cmbSede.setSelectedItem(this.JTabEstudiante.getValueAt(fila, 10));
    }
//recuperar valores de tabla carrera
    public void llenarTablaCarr() {
        int fila = this.JtabCarrera.getSelectedRow();
        this.txtCodCarrera.setText(String.valueOf(this.JtabCarrera.getValueAt(fila, 0)));
        this.cmbCarrera.setSelectedItem(this.JtabCarrera.getValueAt(fila, 1));
        this.cmbTipoCarrera.setSelectedItem(this.JtabCarrera.getValueAt(fila, 2));
        mostrarCarreras();
    }
//insertar
    public void insertar() {
        est.setCarnet(Integer.parseInt(this.txtCarnetEst.getText()));
        est.setNombreEst(this.txtNombreEst.getText());
        est.setEdad(Integer.parseInt(this.JEdad.getValue().toString()));
        if (this.JFemenino.isSelected()) {
            est.setSexoEst("Femenino");
        } else {
            est.setSexoEst("Masculino");
        }
        est.setIdCarreras(Integer.parseInt(this.txtCodCarrera.getText()));
        est.setEstadoCarrera(this.cmbEstadoCar.getSelectedItem().toString());
        est.setCorreoEst(this.txtCorreoEst.getText());
        est.setSede(this.cmbSede.getSelectedItem().toString());
        de.insertarEstudiante(est);
        mostrarEstudiantes();
    }
    //Modificar
     public void modificar(){
    try {
        int id=Integer.parseInt(this.txtCarnetEst.getText());
        String nombre=this.txtNombreEst.getText();
         if (de.buscarEstudianteExist(id, nombre) == false) {
       est.setCarnet(Integer.parseInt(this.txtCarnetEst.getText()));
        est.setNombreEst(this.txtNombreEst.getText());
        est.setEdad(Integer.parseInt(this.JEdad.getValue().toString()));
        if (this.JFemenino.isSelected()) {
            est.setSexoEst("Femenino");
        } else {
            est.setSexoEst("Masculino");
        }
        est.setIdCarreras(Integer.parseInt(this.txtCodCarrera.getText()));
        est.setEstadoCarrera(this.cmbEstadoCar.getSelectedItem().toString());
        est.setCorreoEst(this.txtCorreoEst.getText());
        est.setSede(this.cmbSede.getSelectedItem().toString());
        int SioNo=JOptionPane.showConfirmDialog(this,"Desea modificar al Estudiante?", "Modificar Estudiante",JOptionPane.YES_NO_OPTION);
        if(SioNo==0){
        de.modificarEstudiante(est);
        llenarTabla(); 
        mostrarEstudiantes();
        }else{        
    }
         }else{
               JOptionPane.showMessageDialog(null, "El estudiante ya existe");
         }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
     //eliminado fisico
     public void eliminar(){
    try {
        est.setCarnet(Integer.parseInt(this.txtCarnetEst.getText()));
        int SioNo=JOptionPane.showConfirmDialog(this, "Desea eliminar Estudiante? ", "Eliminar Estudiante? ",JOptionPane.YES_NO_OPTION);
        if(SioNo==0){
        de.eliminarEstudiante(est);
        JOptionPane.showMessageDialog(rootPane, "Eliminado con Exito  ", "Confirmacion ",JOptionPane.INFORMATION_MESSAGE);
        llenarTabla();
        mostrarEstudiantes();
        }
        else{
        
        }
    } catch (Exception e) {
     }
        }
     //eliminado logico
     public void eliminadoLogico(){
    try {
        est.setCarnet(Integer.parseInt(this.txtCarnetEst.getText()));
        int SioNo=JOptionPane.showConfirmDialog(this, "Desea eliminar Estudiante? ", "Eliminar Estudiante? ",JOptionPane.YES_NO_OPTION);
        if(SioNo==0){
        de.eliminadoLogico(est);
        JOptionPane.showMessageDialog(rootPane, "Eliminado con Exito  ", "Confirmacion ",JOptionPane.INFORMATION_MESSAGE);
        llenarTabla();
        mostrarEstudiantes();
        }
        else{
        
        }
    } catch (Exception e) {
     }
        }
     //limpiar cajas de texto
     public void limpiar(){
     this.txtCarnetEst.setText("");
     this.txtNombreEst.setText("");
     this.JEdad.setValue(17);
     this.buttonGroup1.clearSelection();
     this.cmbCarrera.setSelectedIndex(0);
     this.cmbTipoCarrera.setSelectedIndex(0);
     this.txtCodCarrera.setText(" ");
     this.cmbEstadoCar.setSelectedIndex(0);
     this.txtCorreoEst.setText("");
     this.cmbSede.setSelectedIndex(0);
      mostrarCarreras();
      mostrarEstudiantes();
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JEstudiante = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTabEstudiante = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtabCarrera = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCarnetEst = new javax.swing.JFormattedTextField();
        txtNombreEst = new javax.swing.JTextField();
        JMasculino = new javax.swing.JRadioButton();
        JFemenino = new javax.swing.JRadioButton();
        cmbCarrera = new javax.swing.JComboBox<>();
        txtCorreoEst = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JEdad = new javax.swing.JSpinner();
        cmbTipoCarrera = new javax.swing.JComboBox<>();
        cmbEstadoCar = new javax.swing.JComboBox<>();
        cmbSede = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodCarrera = new javax.swing.JFormattedTextField();

        setClosable(true);

        JTabEstudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JTabEstudiante.setModel(new javax.swing.table.DefaultTableModel(
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
        JTabEstudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTabEstudianteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTabEstudiante);

        JtabCarrera.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JtabCarrera.setModel(new javax.swing.table.DefaultTableModel(
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
        JtabCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtabCarreraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JtabCarrera);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/cubo-de-la-basura.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/guardar.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/escoba.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/actualizar.png"))); // NOI18N
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Correo Electronico:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Carrera:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Sexo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Carnet:");

        try {
            txtCarnetEst.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCarnetEst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarnetEstKeyTyped(evt);
            }
        });

        txtNombreEst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEstKeyTyped(evt);
            }
        });

        buttonGroup1.add(JMasculino);
        JMasculino.setText("Masculino");

        buttonGroup1.add(JFemenino);
        JFemenino.setText("Femenino");

        cmbCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCarreraActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Estado Carrera");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tipo Carrera:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Edad:");

        JEdad.setModel(new javax.swing.SpinnerNumberModel(17, 17, 90, 1));

        cmbTipoCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "Tecnico", "Ingenieria" }));

        cmbEstadoCar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "En Curso", "Finalizada" }));

        cmbSede.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "Sede Santa Tecla", "Regional La Unión", "Regional San Miguel", "Regional Santa Ana", "Regional Zacatecoluca" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Sede ITCA:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Codigo Carrera:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(JEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(JMasculino)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JFemenino)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel5)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cmbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNombreEst, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCarnetEst, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbTipoCarrera, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbEstadoCar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(116, 116, 116)
                            .addComponent(txtCorreoEst, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarnetEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEst, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JMasculino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JFemenino, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(cmbTipoCarrera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCarrera)
                    .addComponent(jLabel7)
                    .addComponent(cmbEstadoCar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout JEstudianteLayout = new javax.swing.GroupLayout(JEstudiante);
        JEstudiante.setLayout(JEstudianteLayout);
        JEstudianteLayout.setHorizontalGroup(
            JEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(17, 17, 17))
            .addComponent(jScrollPane1)
        );
        JEstudianteLayout.setVerticalGroup(
            JEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JEstudianteLayout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addGroup(JEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 Validar val=new Validar();
    private void txtCarnetEstKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarnetEstKeyTyped
       val.Solonumeros(evt);
    }//GEN-LAST:event_txtCarnetEstKeyTyped

    private void txtNombreEstKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEstKeyTyped
      val.SoloLetras(evt);
    }//GEN-LAST:event_txtNombreEstKeyTyped

    private void JTabEstudianteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTabEstudianteMouseClicked
        llenarTabla();
    }//GEN-LAST:event_JTabEstudianteMouseClicked

    private void cmbCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCarreraActionPerformed


    }//GEN-LAST:event_cmbCarreraActionPerformed

    private void JtabCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtabCarreraMouseClicked
        llenarTablaCarr();
    }//GEN-LAST:event_JtabCarreraMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        try {
            if (txtCarnetEst.getText().isEmpty() || txtNombreEst.getText().isEmpty() || JEdad.getValue().toString().isEmpty()||buttonGroup1.getSelection().equals("")||txtCorreoEst.getText().isEmpty()||cmbCarrera.getSelectedIndex()==0||cmbEstadoCar.getSelectedIndex()==0||cmbSede.getSelectedIndex()==0||cmbTipoCarrera.getSelectedIndex()==0||txtCodCarrera.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se permiten campos vacios ");
            } else {
                   insertar();
        mostrarEstudiantes();
        limpiar();
            }

        } catch (Exception ex) {
        } 
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        modificar();
        mostrarEstudiantes();
        limpiar();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
       eliminadoLogico();
       mostrarEstudiantes();
       limpiar();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        limpiar();
       cmbCarrera.removeAllItems();
      mostrarCarreras();
      mostrarEstudiantes();
       de.consultarCarrera(cmbCarrera);   
    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner JEdad;
    private javax.swing.JPanel JEstudiante;
    private javax.swing.JRadioButton JFemenino;
    private javax.swing.JRadioButton JMasculino;
    private javax.swing.JTable JTabEstudiante;
    private javax.swing.JTable JtabCarrera;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbCarrera;
    private javax.swing.JComboBox<String> cmbEstadoCar;
    private javax.swing.JComboBox<String> cmbSede;
    private javax.swing.JComboBox<String> cmbTipoCarrera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField txtCarnetEst;
    private javax.swing.JFormattedTextField txtCodCarrera;
    private javax.swing.JTextField txtCorreoEst;
    private javax.swing.JTextField txtNombreEst;
    // End of variables declaration//GEN-END:variables
}
