package GUIBiblia;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rober
 */
import ClasesBiblia.Libro;
import DAOBiblia.LibroDao;
import DAOBiblia.UsuarioDao;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
public class GUILibro extends javax.swing.JFrame {

    /**
     * Creates new form Libro
     */
    private DefaultTableModel tableModelLibro;
    private LibroDao lebroDao;
    private List<Libro> listaLibros;
    private int IdULibEditao=-1;
    public GUILibro() {    
        initComponents();
        InicializarComponentesLibro();
        Actualizar.setVisible(false);
    }
    
    private void InicializarComponentesLibro(){
        tableModelLibro = new DefaultTableModel(new Object[]{"Id","Titulo","Autor","TipoLibro","Editorial","Existencias","AñoPublicacion","NumeroPaginas","Disponible"},0 );
        lebroDao = new LibroDao("datosLibros.csv");
        listaLibros = new ArrayList<>();
        cargarDatosTablaLibro();
    }
    
    private void cargarDatosTablaLibro(){
        try {
            //listaLibros = lebroDao.cargarLibrosCSV();
            listaLibros = lebroDao.obtenerLibros();
            tableModelLibro.setRowCount(0);            
            for(Libro a:listaLibros){
                String disp = a.isDisponible() ? "Disponible" : "Agotado";
                tableModelLibro.addRow(new Object[]{a.getIdLibro(),a.getTitulo(),a.getAutor(),a.getTipoLibro(),a.getEditorial(),a.getExistencias(),a.getAnoPublicacion(),a.getNumpaginas(),disp});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos)"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarTextos(){
        LibTitulo.setText("");
        LibAutor.setText("");
        LibEditorial.setText("");
        LibExistencias.setText("");
        LibNumeroPag.setText("");
        LibAnoPublicacion.setText("");
        LibBox.setSelectedIndex(0);
    }
    
    private Libro guardarenlista(){
        String Titulo = LibTitulo.getText().trim();
        String Autor = LibAutor.getText().trim();
        String TipoLibro = (String)LibBox.getSelectedItem();
        String Editorial = LibEditorial.getText().trim();
        String Existenciastex = LibExistencias.getText().trim();
        String AnoPublicaciontex = LibAnoPublicacion.getText().trim();
        String NumeroPagtex = LibNumeroPag.getText().trim();
        
        if(Titulo.isEmpty()||Autor.isEmpty()||TipoLibro.isEmpty()||Editorial.isEmpty()||Existenciastex.isEmpty()||AnoPublicaciontex.isEmpty()||NumeroPagtex.isEmpty()){
            JOptionPane.showMessageDialog(this, "Todos los campos se deben de llenar","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (Existenciastex.length()<=0) {
            JOptionPane.showMessageDialog(this, "No puedes registrar un libro si no hay existencias","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (AnoPublicaciontex.length()>4) {
            JOptionPane.showMessageDialog(this, "No creo que hayas conseguido un libro del futuro","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (AnoPublicaciontex.length()<0) {
            JOptionPane.showMessageDialog(this, "Año positivo de favor","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (NumeroPagtex.length()<=0) {
            JOptionPane.showMessageDialog(this, "Debe tener al menos una pagina el Libro","Error",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        try {
            int Existencias=Integer.parseInt(Existenciastex);
            int AnoPublicacion=Integer.parseInt(AnoPublicaciontex);
            int NumeroPag=Integer.parseInt(NumeroPagtex);
            boolean disponible = true;
            return new Libro( Titulo, Autor, TipoLibro, Editorial, Existencias, AnoPublicacion, NumeroPag, disponible);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Las existencias, Año de publicacion y numero de paginas son numeros", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void ModoEdicion(Libro libroAEditar) {
        LibTitulo.setText(libroAEditar.getTitulo());
        LibAutor.setText(libroAEditar.getAutor());
        LibBox.setSelectedItem(String.valueOf(libroAEditar.getTipoLibro()));
        LibEditorial.setText(libroAEditar.getEditorial());
        LibExistencias.setText(String.valueOf(libroAEditar.getExistencias()));
        LibAnoPublicacion.setText(String.valueOf(libroAEditar.getAnoPublicacion()));
        LibNumeroPag.setText(String.valueOf(libroAEditar.getNumpaginas()));
        this.IdULibEditao = libroAEditar.getIdLibro();
        Registro.setVisible(false);
        Regresar.setVisible(false);
        Mostrar.setVisible(false);
        Actualizar.setVisible(true);

        Librolabel.setText("Editar Libro");
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
        Librolabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LibTitulo = new javax.swing.JTextField();
        LibAutor = new javax.swing.JTextField();
        LibEditorial = new javax.swing.JTextField();
        LibExistencias = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        LibBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        LibAnoPublicacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        LibNumeroPag = new javax.swing.JTextField();
        Actualizar = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();
        Registro = new javax.swing.JButton();
        Regresar = new javax.swing.JButton();

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

        Librolabel.setFont(new java.awt.Font("Modern No. 20", 0, 24)); // NOI18N
        Librolabel.setForeground(new java.awt.Color(255, 255, 255));
        Librolabel.setText("Libro");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(Librolabel)
                .addContainerGap(571, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Librolabel)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Titulo:");

        jLabel3.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Autor:");

        jLabel4.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tipo de Libro:");

        jLabel5.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Editorial:");

        jLabel6.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Existencias:");

        LibExistencias.setColumns(6);

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setForeground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        LibBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Terror", "Fantasia", "Mitologia", "Historia", "Comedia", "Aventura", "Infantil", "Suspenso", "Romance", "Misterio", "Educativo" }));

        jLabel7.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Año Publicacion:");

        LibAnoPublicacion.setColumns(6);

        jLabel8.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Numero de paginas:");

        LibNumeroPag.setColumns(6);

        Actualizar.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Actualizar.setText("Actualizar");
        Actualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 153, 153)));
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
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

        Regresar.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Regresar.setText("Regresar");
        Regresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 153, 153)));
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(391, 391, 391)
                .addComponent(Actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Registro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LibTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                    .addComponent(LibAutor)
                                    .addComponent(LibBox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LibEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LibExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LibAnoPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LibNumeroPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {LibAnoPublicacion, LibExistencias, LibNumeroPag});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LibTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LibAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LibBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LibEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(LibExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(LibAnoPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(LibNumeroPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Actualizar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Regresar)
                        .addComponent(Registro)
                        .addComponent(Mostrar)))
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {LibAnoPublicacion, LibExistencias, LibNumeroPag});

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        // TODO add your handling code here:
        DatosLibro ventanal = new DatosLibro();
        ventanal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MostrarActionPerformed

    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
        // TODO add your handling code here:
        Libro nuevo = guardarenlista();
        if (nuevo!=null) {
            try {
                listaLibros.add(nuevo);
                lebroDao.insertarLibro(nuevo);
                lebroDao.guardarLibrosCSV(listaLibros);
                cargarDatosTablaLibro();
                limpiarTextos();
                JOptionPane.showMessageDialog(this, "Guardado","Exito",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_RegistroActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        // TODO add your handling code here:
        VentanaMani ventanal = new VentanaMani();
        ventanal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        // TODO add your handling code here:
        Libro libroEditado = guardarenlista();

        if (libroEditado != null) {
            try {
                libroEditado.setIdLibro(IdULibEditao);
                lebroDao.actualizarLibro(libroEditado);
                listaLibros=lebroDao.obtenerLibros();
                lebroDao.guardarLibrosCSV(listaLibros);
                JOptionPane.showMessageDialog(this, "Libro Actualizao", "Que capo", JOptionPane.INFORMATION_MESSAGE);
                limpiarTextos();
                Registro.setVisible(true);
                Regresar.setVisible(true);
                Mostrar.setVisible(true);
                Actualizar.setVisible(false);
                IdULibEditao = -1;
                Librolabel.setText("Libro");
                DatosLibro ventanaTabla = new DatosLibro();
                ventanaTabla.setVisible(true);
                this.dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_ActualizarActionPerformed

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(GUILibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUILibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUILibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUILibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUILibro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JTextField LibAnoPublicacion;
    private javax.swing.JTextField LibAutor;
    private javax.swing.JComboBox<String> LibBox;
    private javax.swing.JTextField LibEditorial;
    private javax.swing.JTextField LibExistencias;
    private javax.swing.JTextField LibNumeroPag;
    private javax.swing.JTextField LibTitulo;
    private javax.swing.JLabel Librolabel;
    private javax.swing.JButton Mostrar;
    private javax.swing.JButton Registro;
    private javax.swing.JButton Regresar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
