/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOBiblia;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDao {
    
    public boolean registrarPrestamo(int idLibro, int idUsuario, String fechaPrestamo, String fechaDevolucion) {
        String sqlInsert = "insert into prestamos (id_libro, id_usuario, fecha_prestamo, fecha_devolucion, estado) values (?, ?, ?, ?, 'Activo')";
        String sqlUpdate = "update libros set Existencias = Existencias - 1 where id = ?";
        
        Connection conn = null;

        try {
            // Se utiliza la instanciación correcta de tu clase ConexionBD
            conn = new ConexionBD().getConexion();
            conn.setAutoCommit(false);

            try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                stmtInsert.setInt(1, idLibro);
                stmtInsert.setInt(2, idUsuario);
                stmtInsert.setString(3, fechaPrestamo); 
                stmtInsert.setString(4, fechaDevolucion);
                stmtInsert.executeUpdate();
            }
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setInt(1, idLibro);
                stmtUpdate.executeUpdate();
            }
            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static class DataPrestamoEspecifica {
        public int idPrestamo;
        public String tituloLibro;
        public String nombreUsuario;
        public String fechaPrestamo;
        public String fechaDevolucion;
        public String estado;

        public DataPrestamoEspecifica(int id, String titulo, String usuario, String fechaP, String fechaD, String estado) {
            this.idPrestamo = id;
            this.tituloLibro = titulo;
            this.nombreUsuario = usuario;
            this.fechaPrestamo = fechaP;
            this.fechaDevolucion = fechaD;
            this.estado = estado;
        }
    }

    public List<DataPrestamoEspecifica> obtenerHistorialPrestamos() {
        List<DataPrestamoEspecifica> lista = new ArrayList<>();
        
        String sql = "select p.id, l.Titulo, u.Nombre, u.Apellido, p.fecha_prestamo, p.fecha_devolucion, p.estado " +
                     "from prestamos p " +
                     "inner join libros l on p.id_libro = l.id " +
                     "inner join usuarios u on p.id_usuario = u.id " +
                     "order by p.fecha_prestamo desc";

        try (Connection conn = new ConexionBD().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombreCompleto = rs.getString("Nombre") + " " + rs.getString("Apellido");
                
                lista.add(new DataPrestamoEspecifica(
                    rs.getInt("id"),
                    rs.getString("Titulo"),
                    nombreCompleto,
                    rs.getString("fecha_prestamo"),
                    rs.getString("fecha_devolucion"),
                    rs.getString("estado")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar historial: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean realizarDevolucion(int idPrestamo) {
        String sqlUpdatePrestamo = "update prestamos set estado = 'Devuelto' where id = ?";
        String sqlUpdateLibro = "update libros set Existencias = Existencias + 1 where id = (select id_libro from prestamos where id = ?)";

        Connection conn = null;
        try {
            // Se utiliza la instanciación correcta de tu clase ConexionBD
            conn = new ConexionBD().getConexion();
            conn.setAutoCommit(false);
            try (PreparedStatement stmtLibro = conn.prepareStatement(sqlUpdateLibro)) {
                stmtLibro.setInt(1, idPrestamo);
                stmtLibro.executeUpdate();
            }

            try (PreparedStatement stmtPrestamo = conn.prepareStatement(sqlUpdatePrestamo)) {
                stmtPrestamo.setInt(1, idPrestamo);
                stmtPrestamo.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { }
            return false;
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ex) { }
        }
    }
}
