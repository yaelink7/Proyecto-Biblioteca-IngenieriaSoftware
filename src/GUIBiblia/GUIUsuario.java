package GUIBiblia;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rober
 */

import ClasesBiblia.Usuario;
import DAOBiblia.UsuarioDao;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class GUIUsuario extends javax.swing.JFrame {

    /**
     * Creates new form Usuario
     */
    private DefaultTableModel tableModelUsuario;
    private UsuarioDao UsureroDao;
    private List<Usuario> listaUsuarios;
    private int IdUsuEditao=-1;
    public GUIUsuario() {
        initComponents();
        InicializarComponentesUsuario();
        Actualizar.setVisible(false);
    }
    
    private void InicializarComponentesUsuario(){
        tableModelUsuario = new DefaultTableModel(new Object[]{"Id","Nombre","Apellidos","Calle","Colonia","Numero","Codigo Postal","Telefono","Correo"},0 );
        UsureroDao = new UsuarioDao("datosUsuario.csv");
        listaUsuarios = new ArrayList<>();
        cargarDatosTablaUsuario();
    }
    
    private void cargarDatosTablaUsuario(){
        try {
            //listaUsuarios = UsureroDao.cargarUsuariosCSV();
            listaUsuarios = UsureroDao.obtenerUsuarios();
            tableModelUsuario.setRowCount(0);
            for(Usuario a:listaUsuarios){
                tableModelUsuario.addRow(new Object[]{a.getId(),a.getNombre(),a.getApellido(),a.getCalle(),a.getColonia(),a.getNumero(),a.getCodigoPostal(),a.getTelefono(),a.getCorreo()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos)"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarTextos(){
        UsuNombre.setText("");
        UsuApellido.setText("");
        UsuCalle.setText("");
        UsuColon.setText("");
        UsuNumero.setText("");
        UsuCP.setText("");
        UsuTelefono.setText("");
        UsuCorreo.setText("");
    }
    
    private Usuario guardarenlista(){
        String Nombre = UsuNombre.getText().trim();
        String Apellido = UsuApellido.getText().trim();
        String Calle = UsuCalle.getText().trim();
        String Colonia = UsuColon.getText().trim();
        String Numerotex = UsuNumero.getText().trim();
        String CPtex = UsuCP.getText().trim();
        String Telefonotex = UsuTelefono.getText().trim();
        String Correo = UsuCorreo.getText().trim();
        
        if(Nombre.isEmpty()||Apellido.isEmpty()||Calle.isEmpty()||Colonia.isEmpty()||Numerotex.isEmpty()||CPtex.isEmpty()||Telefonotex.isEmpty()||Correo.isEmpty()){
            JOptionPane.showMessageDialog(this, "Todos los campos se deben de llenar","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (Numerotex.length()>3) {
            JOptionPane.showMessageDialog(this, "El numero de la casa no puede tener mas de 3 digitos","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (CPtex.length()>5||CPtex.length()<5) {
            JOptionPane.showMessageDialog(this, "El codigo postal en Mexico se maneja de 5 digitos","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (Telefonotex.length()>10||Telefonotex.length()<10) {
            JOptionPane.showMessageDialog(this, "Los numeros telefonicos en Mexico manejan 10 digitos estafador","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        try {
            int Numero=Integer.parseInt(Numerotex);
            long CP=Long.parseLong(CPtex);
            long Telefono=Long.parseLong(Telefonotex);
            return new Usuario(Nombre, Apellido, Calle, Colonia, Numero, CP, Telefono, Correo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique que Número, Codigo Postal y Teléfono sean numéricos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void ModoEdicion(Usuario usuarioAEditar) {
        UsuNombre.setText(usuarioAEditar.getNombre());
        UsuApellido.setText(usuarioAEditar.getApellido());
        UsuCalle.setText(usuarioAEditar.getCalle());
        UsuColon.setText(usuarioAEditar.getColonia());
        UsuNumero.setText(String.valueOf(usuarioAEditar.getNumero()));
        UsuCP.setText(String.valueOf(usuarioAEditar.getCodigoPostal()));
        UsuTelefono.setText(String.valueOf(usuarioAEditar.getTelefono()));
        UsuCorreo.setText(usuarioAEditar.getCorreo());
        this.IdUsuEditao = usuarioAEditar.getId();
        Registro.setVisible(false);
        Regresar.setVisible(false);
        Mostrar.setVisible(false);
        Actualizar.setVisible(true);

        UsuarioLabel.setText("Editar Usuario");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        UsuarioLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        UsuNombre = new javax.swing.JTextField();
        UsuCalle = new javax.swing.JTextField();
        UsuColon = new javax.swing.JTextField();
        UsuNumero = new javax.swing.JTextField();
        UsuCP = new javax.swing.JTextField();
        UsuTelefono = new javax.swing.JTextField();
        Regresar = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();
        Registro = new javax.swing.JButton();
        UsuCorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        UsuApellido = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Actualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        UsuarioLabel.setFont(new java.awt.Font("Modern No. 20", 1, 24)); // NOI18N
        UsuarioLabel.setForeground(new java.awt.Color(255, 255, 255));
        UsuarioLabel.setText("Usuario");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(UsuarioLabel)
                .addContainerGap(549, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(UsuarioLabel)
                .addGap(14, 14, 14))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Direccion");

        jLabel4.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Calle:");

        jLabel5.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Colonia:");

        jLabel6.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Numero:");

        jLabel7.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Codigo Postal:");

        jLabel8.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Correo Electronico:");

        jLabel9.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Telefono:");

        UsuNumero.setColumns(5);

        Regresar.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Regresar.setText("Regresar");
        Regresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 153, 153)));
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        Mostrar.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Mostrar.setText("Mostrar");
        Mostrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 153, 153)));
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });

        Registro.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Registro.setText("Registrar");
        Registro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 153, 153)));
        Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Apellidos:");

        jLabel12.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Datos de contacto");

        Actualizar.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Actualizar.setText("Actualizar");
        Actualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 153, 153)));
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Registro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(UsuNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(UsuCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(UsuColon, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24)
                        .addComponent(UsuNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(UsuTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(504, 504, 504)
                        .addComponent(UsuApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(UsuCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(448, 448, 448)
                        .addComponent(UsuCP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel8)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(jLabel12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(jLabel5))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(408, 408, 408)
                .addComponent(jLabel8))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UsuNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4))
                            .addComponent(UsuCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(UsuColon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UsuNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9))
                            .addComponent(UsuTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(UsuApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(UsuCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(UsuCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Actualizar)
                    .addComponent(Regresar)
                    .addComponent(Registro)
                    .addComponent(Mostrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        // TODO add your handling code here:
        VentanaMani ventanal = new VentanaMani();
        ventanal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarActionPerformed

    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
        // TODO add your handling code here:
        Usuario nuevo = guardarenlista();
        if (nuevo!=null) {
            try {
            listaUsuarios.add(nuevo);
            UsureroDao.insertarUsuario(nuevo);
            UsureroDao.guardarUsuarioCSV(listaUsuarios);
            cargarDatosTablaUsuario();
            limpiarTextos();
            JOptionPane.showMessageDialog(this, "Guardado","Exito",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_RegistroActionPerformed

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        // TODO add your handling code here:
        DatosUsuario ventanal = new DatosUsuario();
        ventanal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MostrarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        // TODO add your handling code here:                                            
        Usuario usuarioEditado = guardarenlista(); 

        if (usuarioEditado != null) {
            try {
                usuarioEditado.setId(IdUsuEditao);
                UsureroDao.actualizarUsuario(usuarioEditado);
                listaUsuarios=UsureroDao.obtenerUsuarios();
                UsureroDao.guardarUsuarioCSV(listaUsuarios);
                JOptionPane.showMessageDialog(this, "Usuario Actualizao", "Que capo", JOptionPane.INFORMATION_MESSAGE);
                limpiarTextos();
                Registro.setVisible(true);
                Regresar.setVisible(true);
                Mostrar.setVisible(true);
                Actualizar.setVisible(false);
                IdUsuEditao = -1;
                UsuarioLabel.setText("Usuario");
                DatosUsuario ventanaTabla = new DatosUsuario();
                ventanaTabla.setVisible(true);
                this.dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_ActualizarActionPerformed

    /**
     * @param args the command        String Calle = UsuNombre.getText().trim();
 line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Mostrar;
    private javax.swing.JButton Registro;
    private javax.swing.JButton Regresar;
    private javax.swing.JTextField UsuApellido;
    private javax.swing.JTextField UsuCP;
    private javax.swing.JTextField UsuCalle;
    private javax.swing.JTextField UsuColon;
    private javax.swing.JTextField UsuCorreo;
    private javax.swing.JTextField UsuNombre;
    private javax.swing.JTextField UsuNumero;
    private javax.swing.JTextField UsuTelefono;
    private javax.swing.JLabel UsuarioLabel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
