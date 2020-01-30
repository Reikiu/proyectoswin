/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vista;

import com.clases.Carrera;
import com.clases.Coordinador;
import com.clases.Estudiante;
import com.clases.FondoHome;
import com.clases.FondoLogin;
import com.dao.DaoCarrera;
import com.dao.DaoCoordinador;
import com.sun.glass.events.KeyEvent;
import com.utilidades.Validar;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 **Nombre de la clase:frmRegistrarCoordinadores
 * Fecha:19/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class frmRegistrarCoodinadores extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmRegistrarCoodinadores
     */
    public frmRegistrarCoodinadores() {
        initComponents();
        txtEstado.setVisible(false);
        JCoordinador.setBorder(new FondoLogin());
        mostrarCoordinador();
        mostrarCarreras();
        limpiar();

    }

    DaoCoordinador dac = new DaoCoordinador();
    Coordinador coo = new Coordinador();
     DaoCarrera Dac = new DaoCarrera();
         Carrera Car = new Carrera();

    //mostrar coordinador

    public void mostrarCoordinador() {
        String[] columnas = {"Codigo ", "Nombre", "Edad", "Sexo", "Correo", "Escuela","estado"};
        Object[] obj = new Object[7];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls = dac.mostrarCoordinardor();
            for (int i = 0; i < ls.size(); i++) {
                coo = (Coordinador) ls.get(i);
                obj[0] = coo.getCodigoCo();
                obj[1] = coo.getNombreCo();
                obj[2] = coo.getEdadCo();
                obj[3] = coo.getSexo();
                obj[4] = coo.getCorreoCo();
                obj[5]=coo.getEscuela();
                obj[6] = coo.getEstado();
                tabla.addRow(obj);
            }
            ls = dac.mostrarCoordinardor();
            this.JTabCoord.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos del Coordinador" + e);
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
    //insertar coordinador

    public void insertar() throws Exception {
        int codigo = Integer.parseInt(this.txtCarnetCoor.getText());
        String nombreC = this.txtNombeCoor.getText();
        int edad = Integer.parseInt(this.JEdadCoor.getValue().toString());
        String genero = String.valueOf(this.JEdadCoor.getValue().toString());
        if (dac.buscarCoordinadorExist(codigo, nombreC, edad, nombreC) == false) {
            coo.setCodigoCo(Integer.parseInt(this.txtCarnetCoor.getText()));
            coo.setNombreCo(this.txtNombeCoor.getText());
            coo.setEdadCo(Integer.parseInt(this.JEdadCoor.getValue().toString()));
            if (this.JFemenino.isSelected()) {
                coo.setSexo("Femenino");
            } else {
                coo.setSexo("Masculino");
            }
            coo.setCorreoCo(this.txtCorreoCoo.getText());
            coo.setEscuela(Integer.parseInt(this.txtCodCarr.getText()));
            dac.insertarCoordinador(coo);
        } else {
            JOptionPane.showMessageDialog(null, "El coordinador ya existe");
        }
    }
    //recuperar valores de tabla carrera
    public void llenarTablaCarr() {
        int fila = this.JtabCarrera.getSelectedRow();
        this.txtCodCarr.setText(String.valueOf(this.JtabCarrera.getValueAt(fila, 0)));
       
        mostrarCarreras();
    }
    //recuperar datos de tabla

    public void llenarTablaCoo() {
        int fila = this.JTabCoord.getSelectedRow();
        this.txtCarnetCoor.setText(String.valueOf(this.JTabCoord.getValueAt(fila, 0)));
        this.txtNombeCoor.setText(String.valueOf(this.JTabCoord.getValueAt(fila, 1)));
        this.JEdadCoor.setValue(this.JTabCoord.getValueAt(fila, 2));
        String genero = String.valueOf(this.JTabCoord.getValueAt(fila, 3));
        if (genero.equals("Femenino")) {
            this.JFemenino.setSelected(true);
        } else {
            this.JMasculino.setSelected(true);
        }
        this.txtCorreoCoo.setText(String.valueOf(this.JTabCoord.getValueAt(fila, 4)));
        this.txtCodCarr.setText(String.valueOf(this.JTabCoord.getValueAt(fila, 5)));
        this.txtEstado.setText(String.valueOf(this.JTabCoord.getValueAt(fila, 6)));
    }
    //modificar

    public void modificar() {
        try {
            int codigo = Integer.parseInt(this.txtCarnetCoor.getText());
            String nombreC = this.txtNombeCoor.getText();
            int edad = Integer.parseInt(this.JEdadCoor.getValue().toString());
            String genero = String.valueOf(this.JEdadCoor.getValue().toString());
            if (dac.buscarCoordinadorExist(codigo, nombreC, edad, nombreC) == false) {
                coo.setCodigoCo(Integer.parseInt(this.txtCarnetCoor.getText()));
                coo.setNombreCo(this.txtNombeCoor.getText());
                coo.setEdadCo(Integer.parseInt(this.JEdadCoor.getValue().toString()));
                if (this.JFemenino.isSelected()) {
                    coo.setSexo("Femenino");
                } else {
                    coo.setSexo("Masculino");
                }
                 coo.setEscuela(Integer.parseInt(this.txtCodCarr.getText()));
                coo.setCorreoCo(this.txtCorreoCoo.getText());
                int SioNo = JOptionPane.showConfirmDialog(this, "Desea modificar al Coordinador?", "Modificar Coordinador", JOptionPane.YES_NO_OPTION);
                if (SioNo == 0) {
                    dac.modificarCoor(coo);
                } else {
                    JOptionPane.showMessageDialog(null, "El coordinador ya existe");
                }
            } else {
                 JOptionPane.showMessageDialog(null, "El coordinador ya existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//eliminado fisico
    public void eliminar() {
        try {
            coo.setCodigoCo(Integer.parseInt(this.txtCarnetCoor.getText()));
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar Coordinador? ", "Eliminar Coordinador? ", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                dac.eliminarCoor(coo);
                JOptionPane.showMessageDialog(rootPane, "Eliminado con Exito  ", "Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
                llenarTablaCoo();
                mostrarCoordinador();
            } else {

            }
        } catch (Exception e) {
        }
    }
    
    //eliminado logico
    public void eliminadoLogico() {
        try {
            coo.setCodigoCo(Integer.parseInt(this.txtCarnetCoor.getText()));
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar Coordinador? ", "Eliminar Coordinador? ", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                dac.EliminadoLogico(coo);
                JOptionPane.showMessageDialog(rootPane, "Coordinador eliminado con Exito  ", "Confirmacion ", JOptionPane.INFORMATION_MESSAGE);
                llenarTablaCoo();
                mostrarCoordinador();
            } else {

            }
        } catch (Exception e) {
        }
    }
    //limpiar cajas de texto

    public void limpiar() {
        this.txtCarnetCoor.setText("");
        this.txtCorreoCoo.setText("");
        this.txtNombeCoor.setText("");
        this.JEdadCoor.setValue(18);
        this.buttonGroup1.clearSelection();
        this.txtCodCarr.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JCoordinador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTabCoord = new javax.swing.JTable();
        btnEnviar = new javax.swing.JButton();
        btnActualiza = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtCarnetCoor = new javax.swing.JFormattedTextField();
        txtNombeCoor = new javax.swing.JTextField();
        JEdadCoor = new javax.swing.JSpinner();
        JMasculino = new javax.swing.JRadioButton();
        JFemenino = new javax.swing.JRadioButton();
        txtCorreoCoo = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtabCarrera = new javax.swing.JTable();
        Carrera = new javax.swing.JLabel();
        txtCodCarr = new javax.swing.JTextField();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Carnet :");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Edad:");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sexo:");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Correo Electronico");

        JTabCoord.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JTabCoord.setModel(new javax.swing.table.DefaultTableModel(
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
        JTabCoord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTabCoordMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTabCoord);

        btnEnviar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/guardar.png"))); // NOI18N
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnActualiza.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnActualiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/actualizar.png"))); // NOI18N
        btnActualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizaActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/cubo-de-la-basura.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Imagenes/escoba.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        try {
            txtCarnetCoor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCarnetCoor.setMaximumSize(new java.awt.Dimension(0, 0));
        txtCarnetCoor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarnetCoorKeyTyped(evt);
            }
        });

        txtNombeCoor.setMaximumSize(new java.awt.Dimension(0, 0));
        txtNombeCoor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombeCoorKeyTyped(evt);
            }
        });

        JEdadCoor.setModel(new javax.swing.SpinnerNumberModel(18, 18, 90, 1));
        JEdadCoor.setMaximumSize(new java.awt.Dimension(0, 0));

        buttonGroup1.add(JMasculino);
        JMasculino.setText("Masculino");

        buttonGroup1.add(JFemenino);
        JFemenino.setText("Femenino");

        txtCorreoCoo.setMaximumSize(new java.awt.Dimension(0, 0));
        txtCorreoCoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoCooActionPerformed(evt);
            }
        });
        txtCorreoCoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoCooKeyTyped(evt);
            }
        });

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

        Carrera.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        Carrera.setForeground(new java.awt.Color(255, 255, 255));
        Carrera.setText("Codigo Carrera:");

        javax.swing.GroupLayout JCoordinadorLayout = new javax.swing.GroupLayout(JCoordinador);
        JCoordinador.setLayout(JCoordinadorLayout);
        JCoordinadorLayout.setHorizontalGroup(
            JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JCoordinadorLayout.createSequentialGroup()
                .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(JCoordinadorLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jLabel1)
                            .addGap(67, 67, 67)
                            .addComponent(txtCarnetCoor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JCoordinadorLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JCoordinadorLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(67, 67, 67)
                                    .addComponent(txtNombeCoor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JCoordinadorLayout.createSequentialGroup()
                                    .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                    .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(JCoordinadorLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(JCoordinadorLayout.createSequentialGroup()
                                                    .addComponent(JMasculino)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(JFemenino, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtCorreoCoo, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                                .addGroup(JCoordinadorLayout.createSequentialGroup()
                                                    .addGap(7, 7, 7)
                                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtCodCarr)))
                                        .addGroup(JCoordinadorLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(JEdadCoor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(JCoordinadorLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JCoordinadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Carrera)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        JCoordinadorLayout.setVerticalGroup(
            JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JCoordinadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JCoordinadorLayout.createSequentialGroup()
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCarnetCoor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombeCoor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(25, 25, 25)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JEdadCoor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JMasculino)
                            .addComponent(JFemenino)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreoCoo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JCoordinadorLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JCoordinadorLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtCodCarr, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualiza))
                        .addGap(18, 18, 18)
                        .addGroup(JCoordinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar)
                            .addComponent(btnLimpiar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JCoordinadorLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JCoordinador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCoordinador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
        mostrarCoordinador();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCorreoCooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoCooActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoCooActionPerformed

    private void btnActualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizaActionPerformed
        modificar();
        mostrarCoordinador();
        mostrarCarreras();
        limpiar();
    }//GEN-LAST:event_btnActualizaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminadoLogico();
        mostrarCoordinador();
        mostrarCarreras();
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed
    Validar val = new Validar();
    private void txtCarnetCoorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarnetCoorKeyTyped
        val.Solonumeros(evt);
    }//GEN-LAST:event_txtCarnetCoorKeyTyped

    private void txtNombeCoorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombeCoorKeyTyped
        val.SoloLetras(evt);
    }//GEN-LAST:event_txtNombeCoorKeyTyped

    private void txtCorreoCooKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoCooKeyTyped

    }//GEN-LAST:event_txtCorreoCooKeyTyped

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed

        try {
            if (txtCarnetCoor.getText().isEmpty() || txtNombeCoor.getText().isEmpty() || txtCorreoCoo.getText().isEmpty()||JEdadCoor.getValue().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se permiten campos vacios ");
            } else {
                insertar();
                mostrarCoordinador();
                mostrarCarreras();
                limpiar();
            }

        } catch (Exception ex) {

        }

    }//GEN-LAST:event_btnEnviarActionPerformed

    private void JTabCoordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTabCoordMouseClicked
        llenarTablaCoo();
    }//GEN-LAST:event_JTabCoordMouseClicked

    private void JtabCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtabCarreraMouseClicked
        llenarTablaCarr();
    }//GEN-LAST:event_JtabCarreraMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Carrera;
    private javax.swing.JPanel JCoordinador;
    private javax.swing.JSpinner JEdadCoor;
    private javax.swing.JRadioButton JFemenino;
    private javax.swing.JRadioButton JMasculino;
    private javax.swing.JTable JTabCoord;
    private javax.swing.JTable JtabCarrera;
    private javax.swing.JButton btnActualiza;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField txtCarnetCoor;
    private javax.swing.JTextField txtCodCarr;
    private javax.swing.JTextField txtCorreoCoo;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNombeCoor;
    // End of variables declaration//GEN-END:variables
}
