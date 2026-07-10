/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOBiblia;

import ClasesBiblia.Libro;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;

public class LibroDao {
    private final File archivoLibro;

    public LibroDao(String rutaArchivo) {
        this.archivoLibro = new File(rutaArchivo);
    }
    
    // Constructor por defecto para base de datos
    public LibroDao() {
        this.archivoLibro = new File("libros.csv");
    }

    public void insertarLibro(Libro libro) {
        String sql = "insert into libros (Titulo, Autor, TipoLibro, Editorial, Existencias, AñoPublicacion, NumeroPaginas, Disponible) values (?,?,?,?,?,?,?,?)";
        
        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getTipoLibro());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getExistencias());
            stmt.setInt(6, libro.getAnoPublicacion());
            stmt.setInt(7, libro.getNumpaginas()); 
            stmt.setBoolean(8, libro.isDisponible());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    libro.setIdLibro(idGenerado);
                    System.out.println("Libro insertado con ID: " + idGenerado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar libro: " + e.getMessage());
        }
    }

    public List<Libro> obtenerLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "select * from libros";

        try (Connection conn = new ConexionBD().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Libro l = new Libro(
                    rs.getInt("id"),              
                    rs.getString("Titulo"),       
                    rs.getString("Autor"),
                    rs.getString("TipoLibro"),
                    rs.getString("Editorial"),
                    rs.getInt("Existencias"),
                    rs.getInt("AñoPublicacion"),
                    rs.getInt("NumeroPaginas"),
                    rs.getBoolean("Disponible")
                );
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarLibro(Libro libro) {
        String sql = "update libros set Titulo=?, Autor=?, TipoLibro=?, Editorial=?, Existencias=?, AñoPublicacion=?, NumeroPaginas=?, Disponible=? where id=?";

        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getTipoLibro());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getExistencias());
            stmt.setInt(6, libro.getAnoPublicacion());
            stmt.setInt(7, libro.getNumpaginas());
            stmt.setBoolean(8, libro.isDisponible());            
            stmt.setInt(9, libro.getIdLibro());
            
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Libro actualizado correctamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminarLibro(int idLibro) {
        String sql = "delete from libros where id=?";

        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLibro);
            
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Libro eliminado.");
            } else {
                System.out.println("No se encontró libro con ID: " + idLibro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }
    
    public Libro buscarTitulo(String titulo) {
        String sql = "select * from libros where Titulo = ?";
        Libro librosacado = null;

        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                librosacado = new Libro(
                    rs.getInt("id"),
                    rs.getString("Titulo"),
                    rs.getString("Autor"),
                    rs.getString("TipoLibro"),
                    rs.getString("Editorial"),
                    rs.getInt("Existencias"),
                    rs.getInt("AñoPublicacion"),
                    rs.getInt("NumeroPaginas"),
                    rs.getBoolean("Disponible")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librosacado;
    }

    // --- Manejo de Archivos CSV ---

    public List<Libro> cargarLibrosCSV() throws IOException {
        List<Libro> lista = new ArrayList<>();
        if (!archivoLibro.exists()) {
            archivoLibro.createNewFile();
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivoLibro))) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (primera) {
                    primera = false;
                    continue;
                }
                String[] datos = linea.split(",");
                if (datos.length == 9) { 
                    Libro l = new Libro(
                            Integer.parseInt(datos[0]),
                            datos[1],
                            datos[2],
                            datos[3],
                            datos[4],
                            Integer.parseInt(datos[5]),
                            Integer.parseInt(datos[6]),
                            Integer.parseInt(datos[7]),
                            Boolean.parseBoolean(datos[8])
                    );
                    lista.add(l);
                }
            }
        } 
        return lista;
    }

    public void guardarLibrosCSV(List<Libro> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoLibro))) {
            // Encabezado
            bw.write("id,Titulo,Autor,TipoLibro,Editorial,Existencias,Año,Paginas,Disponible");
            bw.newLine();
            // Filas
            for (Libro l : lista) {
                bw.write(l.toStringLibro()); 
                bw.newLine();
            }
        }
    }
}